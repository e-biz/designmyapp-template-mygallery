<#include "mygallery_template.ftl"/>
<@email>
  <p>
    ${applicationName} is ready to download!
  </p>

  <p>
    <a href="${downloadLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Download it !
    </a>
    <a href="${accountValidationLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Account
    </a>
  </p>

  <p>
    <a href="${howToInstallLink}">
      How to install your application.
    </a>
  </p>
</@email>
