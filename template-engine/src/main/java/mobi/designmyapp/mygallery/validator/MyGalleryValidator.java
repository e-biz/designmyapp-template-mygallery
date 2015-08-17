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
package mobi.designmyapp.mygallery.validator;


import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.sdk.exception.ValidationException;
import mobi.designmyapp.sdk.validator.ContentValidator;


/**
 * Created by Loïc Ortola on 8/6/14.
 * The validator checks that the Template object sent is semantically valid.
 * You should only validate elements which have an impact on the destination template (your business-logic).
 * You should not do any I/O operations here, as they should be handled in the processing part.
 */
public class MyGalleryValidator implements ContentValidator<MyGalleryTemplate> {

  public void validate(MyGalleryTemplate obj) {

    String customTheme = obj.getAppTheme();
    // Validate that provided custom theme is valid
    if (customTheme != null && !customTheme.trim().isEmpty() && !"blue".equals(customTheme) && !"orange".equals(customTheme)) {
      throw new ValidationException("Illegal theme value provided.");
    }
  }
}
