<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">
    
    <xsl:param name="digits" select="3"></xsl:param>

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />

	<xsl:template match="/">
		<xsl:text>Kampagne; </xsl:text>
		<xsl:text>Nummer; </xsl:text>
		<xsl:text>Kontext; </xsl:text>
       	<xsl:text>Holzart; </xsl:text>
		<xsl:text>Verprobung; </xsl:text>
		<xsl:text>Rechtswert; </xsl:text>
		<xsl:text>Hochwert; </xsl:text>
		<xsl:text>Drehwinkel; </xsl:text>
		<xsl:text>Rinde; </xsl:text>
		<xsl:text>Durchmesser; </xsl:text>
		<xsl:text>Neigung; </xsl:text>
		<xsl:text>Exposition; </xsl:text>
		<xsl:text>Profil; </xsl:text>
		<xsl:text>Spitze; </xsl:text>
		<xsl:text>Bemerkung;</xsl:text>			
		<xsl:text>Bearbeiter; </xsl:text>
		<xsl:text>Datum&#xa;</xsl:text> 
		<xsl:for-each select="piles/pile" >
			<xsl:variable name="note" select="note" />
			<xsl:variable name="x" select="x" />
			<xsl:variable name="y" select="y" />
			<xsl:variable name="created" select="created"/>
			<xsl:variable name="kind" select="kind"/>
			<xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.findSchnitt($x, $y)"/><xsl:text>;</xsl:text>
			<xsl:value-of select="campaign/name" /><xsl:text> </xsl:text>
			<xsl:value-of select="name"/><xsl:text>; </xsl:text>
			<xsl:value-of select="descriptor/name" /><xsl:text>; </xsl:text>
       		<xsl:value-of select="kind"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="sample"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="x"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="y"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="arc"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="bark"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="diameter"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="inclination"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="exposition"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="profile"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="top"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="java:de.jotwerk.gradoc.XslUtil.toCsv($note)"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="creator"/><xsl:text>; </xsl:text>
		    <xsl:value-of select="concat(substring(created,9,2),'.',substring(created,6,2),'.',substring(created,1,4))"/><xsl:text>&#xa;</xsl:text> 	 	
		</xsl:for-each>		
	</xsl:template>

</xsl:stylesheet>