<!DOCTYPE html>
<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics Systems' Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">

<style>
.login
{
	position:fixed;
	top:200px;
	font-size:xx-large;
	width: 75%;
	left: 50%;
	margin: 0 0 0 -37.5%;
}

input
{
	font-size:inherit;
}

.buttons
{
	position:fixed;
	right:4px;
	bottom:4px;
}
.buttons input,button
{
	font-size:xx-large;
}
.feed
{
	position: absolute;
	right: 0;
	top: 77px;
	z-index:-1;
}
</style>
</head>
<body>
<DIV class="title"><H1>Login Page</H1></DIV>

<DIV class="login">
<FORM method="post" action="j_security_check" autocomplete="on">
<TABLE>
<TR><TD>Username:</TD><TD><INPUT type="text" name="j_username"/></TD></TR>
<TR><TD>Password:</TD><TD><INPUT type="password" name="j_password"/></TD></TR>
</TABLE>
<span class="buttons"><INPUT type="submit" value="login"/></span>
</FORM>
</DIV>
<div class="feed"><!-- start feedwind code --><script type="text/javascript">document.write('<script type="text/javascript" src="' + ('https:' == document.location.protocol ? 'https://' : 'http://') + 'feed.mikle.com/js/rssmikle.js"><\/script>');</script><script type="text/javascript">(function() {var params = {rssmikle_url: "https://github.com/subwoofer359/aplsystem/commits/master.atom",rssmikle_frame_width: "400",rssmikle_frame_height: "400",rssmikle_target: "_blank",rssmikle_font: "Arial, Helvetica, sans-serif",rssmikle_font_size: "12",rssmikle_border: "on",responsive: "off",rssmikle_css_url: "",text_align: "left",corner: "on",autoscroll: "on",scrolldirection: "up",scrollstep: "3",mcspeed: "20",sort: "Off",rssmikle_title: "on",rssmikle_title_sentence: "ACME Plastic Systems:Commits",rssmikle_title_link: "",rssmikle_title_bgcolor: "#2A51A3",rssmikle_title_color: "#FFFFFF",rssmikle_title_bgimage: "",rssmikle_item_bgcolor: "#859EF7",rssmikle_item_bgimage: "",rssmikle_item_title_length: "55",rssmikle_item_title_color: "#2A51A3",rssmikle_item_border_bottom: "on",rssmikle_item_description: "on",rssmikle_item_description_length: "150",rssmikle_item_description_color: "#544766",rssmikle_item_date: "gl1",rssmikle_timezone: "Etc/GMT",datetime_format: "%b %e, %Y %l:%M:%S %p",rssmikle_item_description_tag: "off",rssmikle_item_description_image_scaling: "off",article_num: "15",rssmikle_item_podcast: "off"};feedwind_show_widget_iframe(params);})();</script><div style="font-size:10px; text-align:center; width:400;"><a href="http://feed.mikle.com/" target="_blank" style="color:#CCCCCC;">RSS Feed Widget</a><!--Please display the above link in your web page according to Terms of Service.--></div><!-- end feedwind code --></div>
</body>
</html>