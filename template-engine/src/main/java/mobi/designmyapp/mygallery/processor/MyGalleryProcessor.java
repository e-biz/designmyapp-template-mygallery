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
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.sdk.exception.ProcessorException;
import mobi.designmyapp.sdk.processor.ContentProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Created by Loïc Ortola on 8/6/14.
 * The ContentProcessor will allow you to do specific operations depending on the content of the Template object.
 * In this case, the MyGalleryProcessor implements ContentProcessor<MyGalleryTemplate>
 */
public class MyGalleryProcessor implements ContentProcessor<MyGalleryTemplate> {

  private static final int THUMBNAIL_SIZE = 240;

  /**
   * The process method creates thumbnails of all uploaded images into the tmpDirectory/image-thumbnails folder,
   * and moves original uploaded images into the tmpDirectory/images folder.
   *
   * @param template the MyGalleryTemplate object provided by the client.
   * @throws mobi.designmyapp.sdk.exception.ProcessorException if illegal argument or state is found.
   */
  @Override
  public void process(MyGalleryTemplate template) {

    // Read only access to your uploaded resources directory
    File resourcesDirectory = UtilsFactory.getContextService().getResourceDirectory();

    // Read write access to your temp directory to put your processed resources
    File tmpDirectory = UtilsFactory.getContextService().getTmpDirectory();

    // If the user has uploaded some files
    if (template.getCustomImages() != null && !template.getCustomImages().isEmpty()) {
      File imageDirectory = new File(resourcesDirectory, "image");
      processCustomImages(imageDirectory, tmpDirectory, template.getCustomImages());
    }
  }

  private void processCustomImages(File resourcesDirectory, File tmpDirectory, List<Image> images) {

    // Create destination directories for image and thumbnails
    File imageDirectory = new File(tmpDirectory, "images");
    imageDirectory.mkdir();

    File thumbnailDirectory = new File(tmpDirectory, "image-thumbnails");
    thumbnailDirectory.mkdir();

    // Processing for each image
    for (Image image : images) {
      // Retrieve original image file in resources
      File imageFile = new File(resourcesDirectory, image.getFileName());

      // Checking if file exists on the file system, otherwise throw
      if (!imageFile.exists())
        throw new ProcessorException("Invalid file provided.");

      // Copy original file to images directory
      File destImageFile = new File(imageDirectory, image.getFileName());
      UtilsFactory.getIOUtils().copyFile(imageFile, destImageFile);

      // Generate thumbnail file into image-thumbnails directory (only for android)
      File destThumbImageFile = new File(thumbnailDirectory, image.getFileName());
      generateThumbnail(imageFile, destThumbImageFile, THUMBNAIL_SIZE);
    }
  }

  private void generateThumbnail(File srcImageFile, File destImageFile, int size) {
    // Resize image
    BufferedImage resizedBufferedImage = UtilsFactory.getImageUtils().resizeImageSquare(srcImageFile, size);
    // Write buffer to file
    UtilsFactory.getImageUtils().writeBufferedImageToFile(resizedBufferedImage, destImageFile);
  }

}
