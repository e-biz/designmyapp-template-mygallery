package mobi.designmyapp.mygallery.builder;

import mobi.designmyapp.common.container.manager.ContainerManager;
import mobi.designmyapp.common.container.model.Container;
import mobi.designmyapp.mygallery.model.MyGalleryTemplate;
import mobi.designmyapp.sdk.builder.ContainerBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class MyGalleryContainerBuilder extends ContainerBuilder<MyGalleryTemplate> {


  public MyGalleryContainerBuilder() {
  }


  @Override
  public void launch(MyGalleryTemplate myGalleryTemplate, ContainerManager containerManager) {
    // Start the container
    Container container = Container.builder()
        .name("mygallery-container")
        .image("designmyapp/mygallery:v3.0.0")
        .toCommandContainer()
        .mapExposedPorts(true)
        .mode(Container.Mode.DETACHED)
        .build();
    containerManager.startContainers(container);
  }


  @Override
  public void build(MyGalleryTemplate myGalleryTemplate) {
    // No processing
  }

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
