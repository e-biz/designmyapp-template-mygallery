<#macro email>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width">
</head>
<body style="font-family: &quot;Maven Pro&quot;,sans-serif; color: #4B4B4B; background-color: #DFE7EA; margin: 0; padding: 0; border: none;">
<style type="text/css">
  p {
    color: #4B4B4B;
  }
  h3 {
    color: #950D71;
    font-size: 1.2em;
    font-weight: bold;
  }
  .mailbutton:hover {
    background-color: #E6E6E6 !important;
    border: 1px solid #ADADAD !important;
  }
  a {
    color: #428BCA;
    text-decoration: none;
  }
  a:hover {
    color: #2A6496;
    text-decoration: underline;
  }
</style>

<table
    style="border-spacing: 0; border-collapse: collapse; padding: 0; width: 100%; font-weight: normal; text-align: left; line-height: 19px; font-size: 1em; background: #DFE7EA; margin: 0; padding: 0; border: none;">
  <tr>
    <td>
      <img
          src="cid:logo"
          style="outline: none;text-decoration: none;float: left;display: block; margin-left: 95px; margin-right: 25px">
      <div style="background-color: #FFF; height: 41px; padding-top: 16px;">
        <span style="font-size: 22px; font-weight: bold; color: #9E9E9E;">My<span style="color: #950D71;">Gallery</span></span>
      </div>
      <div style="height: 10px; background: transparent url('cid:shadow') repeat-x">&nbsp;</div>
    </td>
  </tr>
  <tr>
    <td style="clear: both; text-align: center; font-family: &quot;Maven Pro&quot;,sans-serif; font-size: 1em; line-height: 20px; padding-bottom: 50px;">
      <#nested>
    </td>
  </tr>
  <tr>
    <td style="padding: 10px 0px; background: #DFE7EA url('cid:shadow') repeat-x; color: #950D71; text-align: center; font-family: &quot;Maven Pro&quot;,sans-serif; font-size: 14px; line-height: 20px;">
      © 2015 - Design My App
    </td>
  </tr>
</table>

</body>
</html>
</#macro>