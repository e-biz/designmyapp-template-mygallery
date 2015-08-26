<#include "mygallery_template.ftl"/>
<@email>
  <p>
    Votre application ${applicationName} est prête à télécharger !
  </p>

  <p>
    <a href="${downloadLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Télécharger la !
    </a>
    <a href="${accountValidationLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Compte utilisateur
    </a>
  </p>

  <p>
    <a href="${howToInstallLink}">
      Comment installer votre application.
    </a>
  </p>
</@email>
