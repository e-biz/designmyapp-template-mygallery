package mobi.designmyapp.mygallery.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum MyGalleryProperties {

  INSTANCE;

  private Properties properties;

  MyGalleryProperties() {
    Logger logger = LoggerFactory.getLogger(MyGalleryProperties.class);
    properties = new Properties();
    InputStream input = null;
    try {
      input = MyGalleryProperties.class.getClassLoader().getResourceAsStream("env-template-mygallery.properties");
      if (input == null) {
        logger.info("Can't find env-template-mygallery.properties ..... Searching for template-mygallery.properties");
        input = MyGalleryProperties.class.getClassLoader().getResourceAsStream("template-mygallery.properties");
        if (input == null)
          throw new IllegalStateException("Can't load template-mygallery.properties");
      }
      // load a properties file
      properties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
      throw new IllegalStateException(ex);
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
          throw new IllegalStateException(e);
        }
      }
    }
  }

  public String getDmaNodeEndpoint() {
    return properties.getProperty("dma.provider.endpoint");
  }

  public String getDmaNodeKeyId() {
    return properties.getProperty("dma.provider.keyId");
  }

  public String getDmaNodeSecretKey() {
    return properties.getProperty("dma.provider.secretKey");
  }
}
