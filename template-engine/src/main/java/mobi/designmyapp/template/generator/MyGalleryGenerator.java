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
package mobi.designmyapp.template.generator;

import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.sdk.model.Generator;

/**
 * Created by Loïc Ortola on 8/6/14.
 * The Generator is the most important class of all.
 * It will be read by the DesignMyApp engine to import your code.
 * Every Generator is associated with your template tag (unique identifier of your template).
 * Warning: Your generator class MUST be in the mobi.designmyapp.template.generator package!
 */
public class MyGalleryGenerator extends Generator<MyGalleryTemplate> {

  public static final String TEMPLATE_TAG = "mygallery";

  public MyGalleryGenerator() {
    super(MyGalleryTemplate.class);
  }

  /**
   * Get your template unique tag.
   * Every Generator has an unique template tag.
   *
   * @return the template tag value
   */
  @Override
  public String getTemplateTag() {
    return TEMPLATE_TAG;
  }

}
