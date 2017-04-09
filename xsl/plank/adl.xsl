<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">
    
    <xsl:param name="digits" select="3"></xsl:param>

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />

	<xsl:template match="/">
		<xsl:text>Datensatz;</xsl:text>
		<xsl:text>Ortscode;</xsl:text>
		<xsl:text>Grabung;</xsl:text>
		<xsl:text>Fundnummer;</xsl:text>
		<xsl:text>PrimNr;</xsl:text>
       	<xsl:text>Sektor;</xsl:text>
		<xsl:text>Schnitt;</xsl:text>
		<xsl:text>BefundNr;</xsl:text>
		<xsl:text>A_Struktur;</xsl:text>
		<xsl:text>A_Phase;</xsl:text>
		<xsl:text>Holzbefund;</xsl:text>
		<xsl:text>Holzstruktur;</xsl:text>
		<xsl:text>Teilstruktur;</xsl:text>
		<xsl:text>Holzelement;</xsl:text>
		<xsl:text>Kx1;</xsl:text>
		<xsl:text>Kx;</xsl:text>
		<xsl:text>Kx2;</xsl:text>
		<xsl:text>Ky1;</xsl:text>
		<xsl:text>Ky;</xsl:text>
		<xsl:text>Ky2;</xsl:text>
		<xsl:text>Mark;</xsl:text>
		<xsl:text>Rinde;</xsl:text>
		<xsl:text>Erhaltung;</xsl:text>
		<xsl:text>Brandspuren;</xsl:text>			
		<xsl:text>Länge;</xsl:text>
		<xsl:text>Dm;</xsl:text>
		<xsl:text>Breite;</xsl:text>
		<xsl:text>Stärke;</xsl:text>
		<xsl:text>Art;</xsl:text>
		<xsl:text>ca_Ringe;</xsl:text>
		<xsl:text>Q_Form;</xsl:text>
		<xsl:text>Probe;</xsl:text>
		<xsl:text>Status;</xsl:text>
		<xsl:text>Kommentar&#xa;</xsl:text> 
		<xsl:for-each select="planks/plank" >
			<xsl:variable name="note" select="note" />
			<xsl:variable name="x" select="x" />
			<xsl:variable name="y" select="y" />
			<xsl:variable name="created" select="created"/>
			<xsl:variable name="kind" select="kind"/>
			<xsl:value-of select="position()" /><xsl:text>;</xsl:text>
			<xsl:value-of select="campaign/task/number" /><xsl:text>;</xsl:text>
			<xsl:value-of select="campaign/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="name"/><xsl:text>;</xsl:text>
			<xsl:text>Lnr;</xsl:text>
	       	<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.findSektor($x, $y)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.findSchnitt($x, $y)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="descriptor/name" /><xsl:text>;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:value-of select="x"/><xsl:text>;</xsl:text>
			<xsl:text>0;</xsl:text>
			<xsl:value-of select="x1"/><xsl:text>;</xsl:text>
			<xsl:value-of select="y"/><xsl:text>;</xsl:text>
			<xsl:text>0;</xsl:text>
			<xsl:value-of select="y1"/><xsl:text>;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:value-of select="bark"/><xsl:text>;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:value-of select="burning"/><xsl:text>;</xsl:text>
			<xsl:text>0;</xsl:text>
			<xsl:value-of select="diameter"/><xsl:text>;</xsl:text>
			<xsl:text>0;</xsl:text>
			<xsl:text>0;</xsl:text>
			<xsl:value-of select="kind"/><xsl:text>;</xsl:text>
			<xsl:text>0;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:value-of select="sample"/><xsl:text>;</xsl:text>
			<xsl:text>_;</xsl:text>
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toCsv($note)"/><xsl:text>&#xa;</xsl:text> 		    	 	
		</xsl:for-each>		
	</xsl:template>

</xsl:stylesheet>