package mobi.designmyapp.mygallery.builder;

import mobi.designmyapp.common.container.manager.ContainerManager;
import mobi.designmyapp.common.container.model.Container;
import mobi.designmyapp.common.container.model.ContainerProgressRequest;
import mobi.designmyapp.common.container.model.MigrationRemoteBundle;
import mobi.designmyapp.common.container.provider.Node;
import mobi.designmyapp.common.engine.service.ContextService;
import mobi.designmyapp.common.engine.service.ProgressService;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.mygallery.utils.MyGalleryBuildStep;
import mobi.designmyapp.sdk.builder.ContainerBuilder;
import mobi.designmyapp.sdk.exception.BuildException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyGalleryContainerBuilder extends ContainerBuilder<MyGalleryTemplate> {

  private static final String MYGALLERY_CONTAINER_NAME_300 = "mygallery-container-v3.0.0";
  private static final String MYGALLERY_CONTAINER_NAME_310 = "mygallery-container-v3.1.0";

  public MyGalleryContainerBuilder() {
  }


  @Override
  public void launch(MyGalleryTemplate myGalleryTemplate, ContainerManager containerManager) {

    ProgressService progressService = UtilsFactory.getProgressService();
    ContextService contextService = UtilsFactory.getContextService();

    final String appId = contextService.getAppId();

    progressService.progress(appId, MyGalleryBuildStep.MYGALLERY_START.toString(),
        ContainerProgressRequest.builder().percentage(0)
            .message("Starting container").build());

    Container container;
    switch (myGalleryTemplate.getVersion()) {
      case "3.0.0":
        container = Container.builder()
            .name(MYGALLERY_CONTAINER_NAME_300)
            .image("designmyapp/mygallery:v3.0.0")
            .toCommandContainer()
            .mapExposedPorts(true)
            .mode(Container.Mode.DETACHED)
            .addEnvVariable("MYGALLERY_CONTAINER_NAME", MYGALLERY_CONTAINER_NAME_300)
            .build();
        containerManager.startContainers(container);
        break;

      case "3.1.0":
        Container.CommandContainerBuilder builder = Container.builder()
            .name(MYGALLERY_CONTAINER_NAME_310)
            .image("designmyapp/mygallery:v3.1.0")
            .toCommandContainer()
            .mapExposedPorts(true)
            .mode(Container.Mode.DETACHED);

        MigrationRemoteBundle migrationRemoteBundle = UtilsFactory.getContextService().getMigrationRemoteBundle();
        Node targetNode = UtilsFactory.getContextService().getTargetedNode();

        if (targetNode != null) { //means we are migrating
          if (migrationRemoteBundle != null) { // means we are migrating to an other node
            builder.addEnvVariable("MYGALLERY_CONTAINER_NAME", MYGALLERY_CONTAINER_NAME_310)
                .addEnvVariable("MYGALLERY_REMOTE_BUNDLE_HOST", migrationRemoteBundle.getHost())
                .addEnvVariable("MYGALLERY_REMOTE_BUNDLE_PORT", String.valueOf(migrationRemoteBundle.getPort()))
                .addEnvVariable("DMA_SECURITY_TOKEN", migrationRemoteBundle.getSecurityToken());
          } else { // we are migrating locally: add the data volume transfer
            builder.bindDataVolumeContainer("data_volume_transfer");
          }
          containerManager.startContainers(targetNode, builder.build());
        } else { // we are not migrating
          containerManager.startContainers(builder.build());
        }
        break;

      default:
        throw new BuildException("Version " + myGalleryTemplate.getVersion() + " is not supported");
    }

    progressService.progress(appId, MyGalleryBuildStep.MYGALLERY_FINISH.toString(),
        ContainerProgressRequest.builder().percentage(100)
            .message("Container ready").build());
  }


  @Override
  public void build(MyGalleryTemplate myGalleryTemplate) {
    // No processing
  }


  @Override
  public List<String> getBuildSteps(MyGalleryTemplate template) {
    return Arrays.stream(MyGalleryBuildStep.values())
        .map(MyGalleryBuildStep::toString)
        .collect(Collectors.toList());
  }

  /**
   * Construct and returns the backend endpoint.
   * @param containers the list of containers
   * @return the backend endpoint
   */
  @Override
  public Object formatResponse(Container... containers) {
    if (containers.length > 0) {
      Container container = containers[0];
      Container.CommandOptions options = container.getOptions();
      String endpoint = container.getEndpoint();
      StringBuilder url = new StringBuilder("http://");
      try {
        URI uri = new URI(endpoint);
        url.append(uri.getHost());
      } catch (URISyntaxException e) {
        throw new IllegalStateException("Invalid endpoint: " + endpoint);
      }
      return url.append(":")
          .append(options.getPortsMap().get(8080))
          .append("/")
          .toString();
    }
    return null;
  }

}
