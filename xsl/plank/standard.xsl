<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />

	<xsl:template match="/">
		<xsl:text>Schnitt;</xsl:text>
		<xsl:text>Kampagne;</xsl:text>
		<xsl:text>Nummer;</xsl:text>
		<xsl:text>Befund;</xsl:text>
		<xsl:text>Kontext;</xsl:text>
		<xsl:text>Brandspuren;</xsl:text>
		<xsl:text>Rinde;</xsl:text>
		<xsl:text>Durchmesser;</xsl:text>
       	<xsl:text>Holzart;</xsl:text>		
		<xsl:text>x;</xsl:text>
		<xsl:text>y;</xsl:text>
		<xsl:text>x1;</xsl:text>
		<xsl:text>y1;</xsl:text>		
		<xsl:text>Verprobung;</xsl:text>
		<xsl:text>Bemerkung;</xsl:text>			
		<xsl:text>Bearbeiter;</xsl:text>
		<xsl:text>Datum&#xa;</xsl:text> 
		<xsl:for-each select="planks/plank" >
			<xsl:variable name="note" select="note" />
			<xsl:variable name="x" select="x" />
			<xsl:variable name="y" select="y" />
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.findSchnitt($x, $y)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="campaign/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="name"/><xsl:text>;</xsl:text>
			<xsl:value-of select="descriptor/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="context/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="burning"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="bark"/><xsl:text>;</xsl:text>		  
		    <xsl:value-of select="diameter"/><xsl:text>;</xsl:text>
			<xsl:value-of select="kind" /><xsl:text>;</xsl:text>		    
		    <xsl:value-of select="x"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="y"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="x1"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="y1"/><xsl:text>;</xsl:text>		    
		    <xsl:value-of select="sample"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toCsv($note)"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="creator"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="concat(substring(created,9,2),'.',substring(created,6,2),'.',substring(created,1,4))"/><xsl:text>; </xsl:text><xsl:text>&#xa;</xsl:text> 	 	
		</xsl:for-each>		
	</xsl:template>
	
</xsl:stylesheet>