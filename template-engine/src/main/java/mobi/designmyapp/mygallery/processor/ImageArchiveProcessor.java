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
import mobi.designmyapp.common.engine.model.UploadRequest;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.impl.ImageUploadProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Loïc Ortola on 8/7/14.
 * The ArchiveProcessor enables to create custom processing when uploading a zip file.
 * The generic ZipUploadProcessor will extract the resource, angive you the outputDirectory in the upload request object
 */
public class ImageArchiveProcessor implements ArchiveProcessor<List<Image>> {

  public static final String NAMESPACE = "image";

  // List of valid extensions to be extracted
  private List<String> validExtensions = Arrays.asList("png", "jpg", "jpeg");

  public ImageArchiveProcessor() {
  }

  @Override
  public String getNamespace() {
    return NAMESPACE;
  }

  /**
   * Will recursively browse the extracted files and process all image resources to original image resources directory
   *
   * @param uploadRequest  the upload request
   * @param destDir        the directory where the file was unzipped
   * @param unhandledFiles the list of files ignored by the zip processor
   * @return the list of processed images with their URL
   * @throws java.io.IOException
   */
  @Override
  public List<Image> process(UploadRequest uploadRequest, File destDir, List<String> unhandledFiles) throws IOException {
    File resourceDir = UtilsFactory.getContextService().getResourceDirectory();
    // Retrieve directory of image resources
    File imgDestDirectory = new File(resourceDir, ImageUploadProcessor.DEFAULT_NAMESPACE);
    return getImages(destDir, imgDestDirectory);
  }

  private List<Image> getImages(File srcDir, File destDirectory) throws IOException {
    List<Image> images = new ArrayList<>();

    for (File f : srcDir.listFiles()) {
      // Recursive call on folders
      if (!f.isFile()) {
        images.addAll(getImages(f, destDirectory));
        continue;
      }

      String ext = UtilsFactory.getIOUtils().getExtension(f.getName());
      // Check if it is an image file, otherwise move on
      if (!validExtensions.contains(ext.substring(1))) {
        continue;
      }

      File destFile = new File(destDirectory, UtilsFactory.getDigestUtils().createHash(f) + ext);

      // Move original unzipped image to image resource directory
      if (!destFile.exists()) {
        UtilsFactory.getIOUtils().moveFile(f, destFile);
      }

      Image image = Image.builder()
          .fileName(destFile.getName())
          .originalName(f.getName())
          .prepareUrl()
          .namespace(ImageUploadProcessor.DEFAULT_NAMESPACE)
          .build();

      // Adding image to list
      if (!images.contains(image)) {
        images.add(image);
      }
    }
    return images;
  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
