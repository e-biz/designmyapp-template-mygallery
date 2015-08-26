<#include "mygallery_template.ftl"/>
<@email>
  <p>
    Quelqu'un a généré une application avec
    <span style="font-weight: bold; color: #FFF;">Design<span style="color: #950D71;">MyApp</span></span>
    et l'a partagée avec vous !
  </p>

  <p>
    <a href="${downloadLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Télécharger ${applicationName}
    </a>
  </p>

  <p>
    <a href="${howToInstallLink}">
      Comment installer votre application.
    </a>
  </p>
</@email>
