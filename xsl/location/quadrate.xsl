<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">
    
    <xsl:param name="digits" select="3"></xsl:param>

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />

	<xsl:template match="/">
		<xsl:text>Name; </xsl:text>
       	<xsl:text>X; </xsl:text>
		<xsl:text>Y; </xsl:text>
		<xsl:text>A; </xsl:text>
		<xsl:text>B; </xsl:text>		
		<xsl:text>Bemerkung; </xsl:text>			
		<xsl:text>Bearbeiter; </xsl:text>
		<xsl:text>Datum&#xa;</xsl:text> 
		<xsl:for-each select="locations/location[category = 'quadrat']" >
			<xsl:variable name="note" select="note" />
			<xsl:variable name="created" select="created"/>
			<xsl:value-of select="name"/><xsl:text>; </xsl:text>
			<xsl:value-of select="x"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="y"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="a"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="b"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toCsv($note)"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="creator"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="concat(substring(created,9,2),'.',substring(created,6,2),'.',substring(created,1,4))"/><xsl:text>&#xa;</xsl:text> 	 	
		</xsl:for-each>		
	</xsl:template>

</xsl:stylesheet>