<#include "mygallery_template.ftl"/>
<@email>
  <p>
    Someone generated an application with
    <span style="font-weight: bold; color: #FFF;">Design<span style="color: #950D71;">MyApp</span></span>
    and shared it with you !
  </p>

  <p>
    <a href="${downloadLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Download ${applicationName}
    </a>
  </p>

  <p>
    <a href="${howToInstallLink}">
      How to install your application
    </a>
  </p>
</@email>
