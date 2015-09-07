package mobi.designmyapp.mygallery.utils;

/**
 * Build steps for this template
 */
public enum MyGalleryBuildStep {

  MYGALLERY_START("starting"),
  MYGALLERY_FINISH("finishing");

  private final String name;

  MyGalleryBuildStep(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
