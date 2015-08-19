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
package mobi.designmyapp.mygallery.model;


import mobi.designmyapp.common.engine.model.Pricing;
import mobi.designmyapp.common.util.UtilsFactory;

import java.math.BigDecimal;

/**
 * Created by Loïc Ortola on 8/6/14.
 * The Pricing class will represent the pricing model of your app.
 * Hard coding the price of each feature will enable a default configuration, which can be later modified in your
 * template administration console.
 */
public class MyGalleryPricing extends Pricing {

  public BigDecimal customThemePrice;
  public BigDecimal customImagePrice;


  public MyGalleryPricing() {
    super();
    // Default price of your template: price people will pay without any options
    super.templatePrice = UtilsFactory.getPriceUtils().create("4.99");
    // The price people will pay to select a custom theme
    customThemePrice = UtilsFactory.getPriceUtils().create("1.99");
    // The price people will pay per image uploaded
    customImagePrice = UtilsFactory.getPriceUtils().create("0.11");
  }

}
