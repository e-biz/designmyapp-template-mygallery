<#include "mygallery_template.ftl"/>
<@email>
  <p>
    Votre application ${applicationName} est prête à télécharger ! <br/>

    Un compte a été créé avec les informations suivantes : <br/>
    Identifiant : <span style="font-size: 1.1em; font-weight: bold;">${userEmail}</span><br/>
    Mot de passe : <span style="font-size: 1.1em; font-weight: bold;">${password!"N/A"}</span><br/>
    <br/>
    Avec ce compte, vous pouvez gérer toutes vos ressources partagées et les applications que vous avez générées.<br/>
    Pour valider et accéder à votre compte, suivez ce lien : <a href="${accountValidationLink}">Mon compte</a><br/>
    Nous vous recommandons de changer votre mot de passe lors de votre première visite.<br>
    Si vous n'avez pas utilisé DesignMyApp pour générer une application merci d'ignorer ce message.
  </p>

  <p>
    blablabla blablabla
  </p>

  <p>
    <a href="${downloadLink}" class="mailbutton" style="display: inline-block; padding: 10px 16px; background-color: #FFF; border: 1px solid #CCC; border-radius: 6px; text-decoration: none; color: #333; font-size: 18px;">
      Télécharger
    </a>
  </p>

  <p>
    <a href="${howToInstallLink}">
      Comment installer votre application.
    </a>
  </p>
</@email>
