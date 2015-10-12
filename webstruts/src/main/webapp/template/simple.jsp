<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<style>
	div, p, td { font-family: arial; }
	#header, #footer { text-align: center; color: red; }
	#header { font-size: 20pt; }
	#mainbody { font-size: 9pt; }
</style>

<div id="header"><decorator:title default="MySite" /></div>

<div id="mainbody"><decorator:body /></div>

<div id="footer">footer</div>

