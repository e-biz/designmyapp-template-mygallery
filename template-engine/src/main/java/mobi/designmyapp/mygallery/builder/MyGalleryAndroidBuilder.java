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
package mobi.designmyapp.mygallery.builder;

import mobi.designmyapp.common.engine.model.Image;
import mobi.designmyapp.common.util.IOUtils;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.sdk.builder.AndroidBuilder;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Loïc Ortola on 8/6/14.
 * The AndroidBuilder will allow you to do the final steps before your apk is built:
 * Copy your assets to the right folder, replace template tokens...
 */
public class MyGalleryAndroidBuilder extends AndroidBuilder<MyGalleryTemplate> {

  /**
   * In this method, you will have a read only access to your tmp directory, and a read-write access to your
   * work directory, containing your ready-for-build android template.
   *
   * @param template the provided template
   */
  @Override
  public void build(MyGalleryTemplate template) {
    // Read only access to the original template directory
    File templateDirectory = UtilsFactory.getContextService().getTemplateDirectory(Type.ANDROID, template.getVersion());

    // Read only access to your temp directory
    File tmpDirectory = UtilsFactory.getContextService().getTmpDirectory();
    // Read-write access to your work directory
    File workDirectory = UtilsFactory.getContextService().getWorkDirectory(Type.ANDROID);

    Map<String, String> model = new HashMap<>();

    String customTheme = template.getAppTheme();

    // If custom theme was selected, we replace the customTheme token with the selected one
    if (customTheme != null && !customTheme.trim().isEmpty()) {
      switch (customTheme) {
        case "blue":
          model.put(MyGalleryTemplate.TOKEN_APP_THEME, "AppThemeBlue");
          break;
        case "orange":
          model.put(MyGalleryTemplate.TOKEN_APP_THEME, "AppThemeOrange");
          break;
        default:
          model.put(MyGalleryTemplate.TOKEN_APP_THEME, "AppThemeDark");
          break;
      }
    } else {
      model.put(MyGalleryTemplate.TOKEN_APP_THEME, "AppThemeDark");
    }

    List<Image> images = template.getCustomImages();

    // If custom images were uploaded, we set the customImages token to true and copy all resources to the android template assets directory
    if (images != null && !images.isEmpty()) {

      model.put(MyGalleryTemplate.TOKEN_LOCAL_IMAGES, "true");

      File imagesDirectory = new File(tmpDirectory, "images");
      File imageThumbnailsDirectory = new File(tmpDirectory, "image-thumbnails");

      File imagesDestDirectory = new File(workDirectory, "assets/images");
      imagesDestDirectory.mkdirs();

      File imageThumbnailsDestDirectory = new File(workDirectory, "assets/image-thumbnails");
      imageThumbnailsDestDirectory.mkdirs();

      UtilsFactory.getIOUtils().copyDirectoryContent(imagesDirectory, imagesDestDirectory);
      UtilsFactory.getIOUtils().copyDirectoryContent(imageThumbnailsDirectory, imageThumbnailsDestDirectory);
    } else {
      model.put(MyGalleryTemplate.TOKEN_LOCAL_IMAGES, "false");
    }

    model.put(MyGalleryTemplate.TOKEN_ADD_IMAGE, String.valueOf(template.getAddImage()));
    model.put(MyGalleryTemplate.TOKEN_ENABLE_LOCAL_STORAGE, String.valueOf(template.getEnableLocalStorage()));

    String backendUrl = template.getBackendUrl();
    if (backendUrl != null) {
      if (!backendUrl.trim().isEmpty()) {
        model.put(MyGalleryTemplate.TOKEN_BACKEND_URL, backendUrl.trim());
      } else {
        model.put(MyGalleryTemplate.TOKEN_BACKEND_URL, MyGalleryTemplate.DEFAULT_BACKEND_URL);
      }
    } else {
      model.put(MyGalleryTemplate.TOKEN_BACKEND_URL, "");
    }

    // This will process the tokens in the manifest, bools.xml and strings.xml files
    final IOUtils ioUtils = UtilsFactory.getIOUtils();
    Arrays.asList("AndroidManifest.xml", "res/values/bools.xml", "res/values/strings.xml")
        .stream()
        .forEach(s -> ioUtils.replaceTokens(model,
            ioUtils.locateFile(templateDirectory, s),
            ioUtils.locateFile(workDirectory, s)));
  }
}