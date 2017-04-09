<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />

	<xsl:template match="/">
		<xsl:text>Kampagne;</xsl:text>
		<xsl:text>Name;</xsl:text>
		<xsl:text>Kontext;</xsl:text>
		<xsl:text>Fläche;</xsl:text>
		<xsl:text>Fotograf;</xsl:text>
		<xsl:text>Rechte;</xsl:text>       	
		<xsl:text>Bemerkung;</xsl:text>
		<xsl:text>Bearbeiter;</xsl:text>
		<xsl:text>Datum&#xa;</xsl:text> 
		<xsl:for-each select="photos/photo" >
			<xsl:variable name="note" select="note" />
			<xsl:value-of select="campaign/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="name"/><xsl:text>;</xsl:text>
			<xsl:value-of select="descriptor/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="location/name" /><xsl:text>;</xsl:text>
			<xsl:value-of select="photographer"/><xsl:text>;</xsl:text>
			<xsl:value-of select="rights"/><xsl:text>;</xsl:text>       		
		    <xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toCsv($note)"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="creator"/><xsl:text>;</xsl:text>
		    <xsl:value-of select="concat(substring(created,9,2),'.',substring(created,6,2),'.',substring(created,1,4))"/><xsl:text>&#xa;</xsl:text> 	 	
		</xsl:for-each>		
	</xsl:template>	

</xsl:stylesheet>