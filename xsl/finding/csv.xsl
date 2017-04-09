<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">
    
    <xsl:param name="digits" select="3"></xsl:param>

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />
	
	<xsl:template match="/">	
		<xsl:text>Schnitt;</xsl:text>
		<xsl:text>Fundnummer;</xsl:text>
		<xsl:text>Viertelquadrat;</xsl:text>
		<xsl:text>Bestimmung;</xsl:text>
		<xsl:text>Befund;</xsl:text>
		<xsl:text>Kontext;</xsl:text>
		<xsl:text>Gewicht;</xsl:text>
       	<xsl:text>Bemerkung;</xsl:text>
		<xsl:text>Bearbeiter;</xsl:text>
		<xsl:text>Datum;</xsl:text>
		<xsl:text>Quadrat;</xsl:text>
		<xsl:text>Signatur&#xa;</xsl:text> 
		<xsl:for-each select="findings/finding" >
			<xsl:variable name="note" select="note" />
			<xsl:variable name="x" select="x" />
			<xsl:variable name="y" select="y" />
			<xsl:variable name="created" select="created"/>
			<xsl:variable name="kind" select="kind"/>
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.findSchnitt($x, $y)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="campaign/name" /><xsl:text> </xsl:text>
			<xsl:value-of select="location" /><xsl:text>-</xsl:text><xsl:value-of select="number"/><xsl:text>;</xsl:text>
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toVq($x, $y)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.findDescription($kind)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="descriptor/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="context/name" /><xsl:text>; </xsl:text>
		    <xsl:value-of select="weight"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toCsv($note)"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="creator"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.normDate($created)"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="substring(floor($x),4)" /><xsl:text>/</xsl:text><xsl:value-of select="substring(floor($y),4)" /><xsl:text>;</xsl:text>
		    <xsl:value-of select="kind" /><xsl:text>&#xa;</xsl:text> 	 	
		</xsl:for-each>		
	</xsl:template>
	
</xsl:stylesheet>