<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:java="http://xml.apache.org/xalan/java"
    exclude-result-prefixes="java">

	<xsl:output method="text" omit-xml-declaration="yes" media-type="text/csv" />

	<xsl:template match="/">
		<xsl:text>Kampagne, </xsl:text>
		<xsl:text>Name, </xsl:text>
		<xsl:text>Sedimentart, </xsl:text>
		<xsl:text>x, </xsl:text>
		<xsl:text>y, </xsl:text>	
		<xsl:text>Höhe, </xsl:text>	
       	<xsl:text>Bemerkung, </xsl:text>
       	<xsl:text>Bearbeiter, </xsl:text>
		<xsl:text>Datum&#xa;</xsl:text> 
		<xsl:for-each select="sediments/sediment" >
			<xsl:text>&quot;</xsl:text><xsl:value-of select="campaign/name" /><xsl:text>&quot;, </xsl:text>
			<xsl:text>&quot;</xsl:text><xsl:value-of select="name"/><xsl:text>&quot;, </xsl:text>
			<xsl:text>&quot;</xsl:text><xsl:value-of select="descriptor/name" /><xsl:text>&quot;, </xsl:text>
			<xsl:value-of select="x" /><xsl:text>, </xsl:text>
			<xsl:value-of select="y" /><xsl:text>, </xsl:text>
			<xsl:value-of select="height" /><xsl:text>, </xsl:text>
			<xsl:text>&quot;</xsl:text>
				<xsl:call-template name="escapeQuote"><xsl:with-param name="pText" select="note"/></xsl:call-template>
			<xsl:text>&quot;, </xsl:text>
       		<xsl:text>&quot;</xsl:text><xsl:value-of select="creator"/><xsl:text>&quot;, </xsl:text>
		    <xsl:value-of select="created"/><xsl:text>&#xa;</xsl:text> 	 	
		</xsl:for-each>		
	</xsl:template>
	
	<xsl:template name="escapeQuote">
      <xsl:param name="pText" select="."/>

      <xsl:if test="string-length($pText) >0">
       <xsl:value-of select=
        "substring-before(concat($pText, '&quot;'), '&quot;')"/>

       <xsl:if test="contains($pText, '&quot;')">
        <xsl:text>""</xsl:text>

        <xsl:call-template name="escapeQuote">
          <xsl:with-param name="pText" select=
          "substring-after($pText, '&quot;')"/>
        </xsl:call-template>
       </xsl:if>
      </xsl:if>
    </xsl:template>

</xsl:stylesheet>