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

import mobi.designmyapp.common.container.manager.ContainerManager;
import mobi.designmyapp.common.container.provider.Node;
import mobi.designmyapp.common.container.provider.NodeRequest;
import mobi.designmyapp.common.container.service.ContainerService;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.mygallery.builder.MyGalleryAndroidBuilder;
import mobi.designmyapp.mygallery.builder.MyGalleryContainerBuilder;
import mobi.designmyapp.mygallery.builder.MyGalleryIosBuilder;
import mobi.designmyapp.mygallery.model.MyGalleryPricing;
import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.mygallery.processor.ImageArchiveProcessor;
import mobi.designmyapp.mygallery.processor.MyGalleryPriceProcessor;
import mobi.designmyapp.mygallery.processor.MyGalleryProcessor;
import mobi.designmyapp.mygallery.utils.MyGalleryProperties;
import mobi.designmyapp.mygallery.validator.MyGalleryValidator;
import mobi.designmyapp.sdk.builder.AndroidBuilder;
import mobi.designmyapp.sdk.builder.ContainerBuilder;
import mobi.designmyapp.sdk.builder.IosBuilder;
import mobi.designmyapp.sdk.model.Generator;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.ContentProcessor;
import mobi.designmyapp.sdk.processor.PriceProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import mobi.designmyapp.sdk.processor.impl.ImageUploadProcessor;
import mobi.designmyapp.sdk.processor.impl.ZipUploadProcessor;
import mobi.designmyapp.sdk.validator.ContentValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 8/6/14.
 * The Generator is the most important class of all.
 * It will be read by the DesignMyApp engine to import your code.
 * Every Generator is associated with your template tag (unique identifier of your template).
 * Warning: Your generator class MUST be in the mobi.designmyapp.template.generator package!
 */
public class MyGalleryGenerator extends Generator<MyGalleryTemplate> {

  public static final String TEMPLATE_TAG = "mygallery";

  private List<UploadProcessor> uploadProcessors;
  private List<ArchiveProcessor> archiveProcessors;

  public MyGalleryGenerator() {
    super(MyGalleryTemplate.class);
    addVersion("3.0.0");
    // Create upload processors
    uploadProcessors = new ArrayList<>();
    // Support built-in image upload processor
    uploadProcessors.add(new ImageUploadProcessor());
	// Support built-in zip upload processor
    uploadProcessors.add(new ZipUploadProcessor());

    // Add custom image archive processor
    archiveProcessors = new ArrayList<>();
    archiveProcessors.add(new ImageArchiveProcessor());

    createDesignMyAppNode();
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

  /**
   * Get your template content validator.
   * This will be called when building your app, in order to verify that the Template object sent is semantically valid.
   *
   * @return the template ContentValidator
   */
  @Override
  public ContentValidator getValidator() {
    return new MyGalleryValidator();
  }

  /**
   * Get your template content processor.
   * This will be called after your app price is processed.
   * The ContentProcessor will allow you to do specific operations depending on the content of the Template object.
   * (Image processing, Resource bundle etc...)
   *
   * @return the template ContentProcessor
   */
  @Override
  public ContentProcessor getProcessor() {
    return new MyGalleryProcessor();
  }

  /**
   * Get the list of your template upload processors.
   * If your template provides features which require file uploads, you will need UploadProcessors.
   * The UploadProcessor allows you to specify the valid extensions which will be accepted by the upload module,
   * provide a custom namespace reserved for it, and enable some custom processing to be done after file is uploaded.
   *
   * @return the list of your template UploadProcessors
   */
  @Override
  public List<UploadProcessor> getUploadProcessors() {
    return uploadProcessors;
  }

  /**
   * Get your template android builder.
   * This will be called after your app content is processed.
   * The AndroidBuilder will allow you to do the final steps before your apk is built:
   * Copy your assets to the right folder, replace template tokens...
   *
   * @return the template AndroidBuilder
   */
  @Override
  public AndroidBuilder<MyGalleryTemplate> getAndroidBuilder() {
    return new MyGalleryAndroidBuilder();
  }

  /**
   * Get your template ios builder.
   * This will be called after your app content is processed.
   * The IosBuilder will allow you to do the final steps before your ipa is built:
   * Copy your assets to the right folder, replace template tokens...
   *
   * @return the template IosBuilder
   */
  @Override
  public IosBuilder<MyGalleryTemplate> getIosBuilder() {
    return new MyGalleryIosBuilder();
  }


  /**
   * Get your template container builder.
   * @return the template ContainerBuilder
   */
  @Override
  public ContainerBuilder<MyGalleryTemplate> getContainerBuilder() {
    return new MyGalleryContainerBuilder();
  }

  /**
   * Get your template price processor.
   * This will be called after your content validation.
   * The PriceProcessor will compute the total price which has to be paid depending on the content provided in the Template object.
   *
   * @return the template PriceProcessor
   */
  public PriceProcessor<MyGalleryTemplate, MyGalleryPricing> getPriceProcessor() {
    return new MyGalleryPriceProcessor();
  }


  /**
   * Get the list of your archive upload processors.
   * If your template supports zip file uploads, you will need to provide ArchiveProcessors.
   * The ArchiveProcessor allows you to specify the dedicated archive namespace, and enable some custom processing
   * after file has been unzipped.
   *
   * @return the list of your template ArchiveProcessors
   */
  @Override
  public List<ArchiveProcessor> getArchiveProcessors() {
    return archiveProcessors;
  }

  private void createDesignMyAppNode() {
    ContainerService cs = UtilsFactory.getContainerService();
    ContainerManager cm = cs.getContainerManager(TEMPLATE_TAG);
    NodeRequest dmaNodeRequest = NodeRequest.designMyApp()
        .templateTag(TEMPLATE_TAG)
        .endpoint(MyGalleryProperties.INSTANCE.getDmaNodeEndpoint())
        .keyId(MyGalleryProperties.INSTANCE.getDmaNodeKeyId())
        .secretKey(MyGalleryProperties.INSTANCE.getDmaNodeSecretKey())
        .poolSize(20)
        .priority(0)
        .build();
    Node designMyAppNode = cs.createNode(dmaNodeRequest);
    cm.addNode(designMyAppNode);
  }
}
