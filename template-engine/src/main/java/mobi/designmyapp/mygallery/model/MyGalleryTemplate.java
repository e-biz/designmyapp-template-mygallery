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

import mobi.designmyapp.common.engine.model.Image;
import mobi.designmyapp.common.engine.model.Template;
import mobi.designmyapp.template.generator.MyGalleryGenerator;

import java.util.List;

/**
 * Created by Loïc Ortola on 8/6/14.
 * The Template class represents what will be sent from the frontend (potentially what the client will have configured).
 * It also is the only reference taken into account to calculate the final price of the generated app(s).
 */
public class MyGalleryTemplate extends Template {

  public static final String TOKEN_APP_THEME = "appTheme";
  public static final String TOKEN_ADD_IMAGE = "addImage";
  public static final String TOKEN_ENABLE_LOCAL_STORAGE = "enableLocalStorage";
  public static final String TOKEN_LOCAL_IMAGES = "localImages";
  public static final String TOKEN_BACKEND_URL = "backendUrl";

  public static final String DEFAULT_BACKEND_URL = "http://imagegallery.designmyapp.mobi/";


  private List<Image> customImages;
  private String appTheme;
  private boolean addImage;
  private boolean enableLocalStorage;
  private String backendUrl;


  public MyGalleryTemplate() {
    super(MyGalleryGenerator.TEMPLATE_TAG);
  }

  public List<Image> getCustomImages() {
    return customImages;
  }

  public void setCustomImages(List<Image> customImages) {
    this.customImages = customImages;
  }

  public String getAppTheme() {
    return appTheme;
  }

  public void setAppTheme(String appTheme) {
    this.appTheme = appTheme;
  }

  public boolean getAddImage() {
    return addImage;
  }

  public void setAddImage(boolean addImage) {
    this.addImage = addImage;
  }

  public boolean getEnableLocalStorage() {
    return enableLocalStorage;
  }

  public void setEnableLocalStorage(boolean enableLocalStorage) {
    this.enableLocalStorage = enableLocalStorage;
  }

  public String getBackendUrl() {
    return backendUrl;
  }

  public void setBackendUrl(String backendUrl) {
    this.backendUrl = backendUrl;
  }
}
