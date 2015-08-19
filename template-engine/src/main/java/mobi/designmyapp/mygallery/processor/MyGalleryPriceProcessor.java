/*
 Copyright 2014 eBusiness Information

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package mobi.designmyapp.mygallery.processor;

import mobi.designmyapp.common.engine.model.Image;
import mobi.designmyapp.common.engine.service.PricingService;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.mygallery.model.MyGalleryPricing;
import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.sdk.exception.ProcessorException;
import mobi.designmyapp.sdk.processor.PriceProcessor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Loïc Ortola on 6/8/14.
 * The PriceProcessor computes the total price which has to be paid depending on the content provided in the Template object.
 */
public class MyGalleryPriceProcessor extends PriceProcessor<MyGalleryTemplate, MyGalleryPricing> {

  PricingService pricingService;

  public MyGalleryPriceProcessor() {
    super(MyGalleryPricing.class);
    pricingService = UtilsFactory.getPricingService();
  }

  /**
   * Price compute method
   * Only compute pricing related to your business logic
   *
   * @param template the template object provided by the client
   * @return the final price
   */
  @Override
  public BigDecimal computePrice(MyGalleryTemplate template) {
    BigDecimal price = BigDecimal.ZERO;

    MyGalleryPricing pricing = pricingService.getPricing();

    if (pricing == null) {
      throw new ProcessorException("pricing is null");
    }

    // Add template price
    price = price.add(pricing.templatePrice);

    // Add customTheme price if relevant
    String appTheme = template.getAppTheme();
    if (appTheme != null && !appTheme.trim().isEmpty()) {
      price = price.add(pricing.customThemePrice);
    }

    // Add customImage price n times depending on the number of custom images
    List<Image> images = template.getCustomImages();
    if (images != null && !images.isEmpty()) {
      price = price.add(pricing.customImagePrice.multiply(new BigDecimal(images.size())));
    }

    return price;
  }

}
