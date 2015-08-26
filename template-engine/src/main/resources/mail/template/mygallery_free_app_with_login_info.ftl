<#include "mygallery_template.ftl"/>
<@email>
  <p>
  ${applicationName} is ready to download ! <br/>

    An Account have been created with the following data : <br/>
    login : <span style="font-size: 1.1em; font-weight: bold;">${userEmail}</span><br/>
    password : <span style="font-size: 1.1em; font-weight: bold;">${password!"N/A"}</span><br/>
    <br/>
    By using this account you can manage all your shared resources and all your generated applications.<br/>
    To validate and access your account follow this link: <a href="${accountValidationLink}">My account</a><br/>
    It is recommended to change your password at your first visit <br>
    If you have never generate an application with DesignMyApp, please ignore this mail.
  </p>

  <p>

    <a href="${downloadLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Download
    </a>
  </p>

  <p>
    <a href="${howToInstallLink}">
      How to install your application
    </a>
  </p>
</@email>
