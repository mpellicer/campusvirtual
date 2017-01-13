<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:fo="http://www.w3.org/1999/XSL/Format"
version="1.0">

<xsl:template match="acta">


<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

<fo:layout-master-set>
        <fo:simple-page-master master-name="llistaa4"
                page-width="210mm" page-height="297mm"
                margin-top="0.5in" margin-bottom="0.5in"
                margin-left="0.5in" margin-right="0.5in">

                <fo:region-body margin-top="1.5cm" margin-bottom="0.5cm"/>
                <fo:region-after extent="1cm"/>
        </fo:simple-page-master>

</fo:layout-master-set>




<fo:page-sequence master-reference="llistaa4">

        <fo:static-content flow-name="xsl-region-after">
                <fo:block text-align="center">Pàgina <fo:page-number/> de
                <fo:page-number-citation ref-id="theEnd"/></fo:block>
        </fo:static-content>


<fo:flow flow-name="xsl-region-body">

<fo:block>
<xsl:variable name="ruta"><xsl:value-of select='logo'/></xsl:variable>
<fo:external-graphic height="2cm" src="{$ruta}"/>
</fo:block>


  <fo:block font-size="12pt"
        font-family="sans-serif"
        font-weight="bold"
        line-height="0.5cm"
        space-after.optimum="1pt"
        color="black"
        text-align="center"
        padding-top="0pt">
        Llistat de qualificacions

</fo:block>


<fo:table   space-before.optimum="20pt" space-after.optimum="20pt">

        <fo:table-column column-width="5cm"/>
        <fo:table-column column-width="10cm"/>

        <fo:table-body>

                 <fo:table-row>
                        <fo:table-cell>
                                <fo:block>Professor:</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                                <fo:block><xsl:value-of select="nom"/></fo:block>
                        </fo:table-cell>
                </fo:table-row>

                 <fo:table-row>
                        <fo:table-cell>
                                <fo:block>Nom d'assignatura:</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                                <fo:block><xsl:value-of select="nomass"/></fo:block>
                        </fo:table-cell>
                </fo:table-row>
                         <fo:table-row>
                        <fo:table-cell>
                                <fo:block>Codi d'assignatura:</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                                <fo:block><xsl:value-of select="codiass"/></fo:block>
                        </fo:table-cell>
                </fo:table-row>

                 <fo:table-row>
                        <fo:table-cell>
                                <fo:block>Any acadèmic:</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                                <fo:block><xsl:value-of select="anyaca"/></fo:block>
                        </fo:table-cell>
                </fo:table-row>
                 <fo:table-row>
                        <fo:table-cell>
                                <fo:block>Grup:</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                                <fo:block><xsl:value-of select="nomgrup"/></fo:block>
                        </fo:table-cell>
                </fo:table-row>
         <fo:table-row>
                        <fo:table-cell>
                                <fo:block>Convocatòria:</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                                <fo:block><xsl:value-of select="convo"/></fo:block>
                        </fo:table-cell>
                </fo:table-row>

        </fo:table-body>
</fo:table>





<!--    <fo:block text-align="start" space-after.optimum="3pt" line-height="15pt" font-family="sans-serif" font-size="12pt">
        </fo:block>-->


<fo:table>

        <fo:table-column column-width="3cm"/>
        <fo:table-column column-width="8cm"/>
        <fo:table-column column-width="2cm"/>


        <fo:table-header>

          <fo:table-row  space-before.optimum="10pt" space-after.optimum="10pt">
            <fo:table-cell>
              <fo:block font-weight="bold" text-align="center" vertical-align="middle"  border-width="1pt" border-color="white" background-color="#7a1652" color="white">
              Dni
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block font-weight="bold" text-align="center" vertical-align="middle"
                  border-width="1pt" border-color="white" background-color="#7a1652" color="white">
                Qualificació
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block font-weight="bold" text-align="center" vertical-align="middle"
                  border-width="1pt" border-color="white" background-color="#7a1652" color="white">
                Nota
              </fo:block>
            </fo:table-cell>

          </fo:table-row>
        </fo:table-header>

        <fo:table-body>
                <xsl:apply-templates select='linies/linia'>
                	<xsl:sort select="dnialu" />
                </xsl:apply-templates>
        </fo:table-body>
        </fo:table>


        <fo:block id="theEnd"/>
    </fo:flow>
  </fo:page-sequence>

</fo:root>
</xsl:template>

<xsl:template match='linia'>

          <fo:table-row>
            <fo:table-cell>
              <fo:block  text-align="center" vertical-align="middle" space-before.optimum="10pt">
              <xsl:value-of select="dnialu"/>
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block text-align="center" vertical-align="middle" space-before.optimum="10pt">
              <xsl:value-of select="quali"/>
              </fo:block>
            </fo:table-cell>
            <fo:table-cell>
              <fo:block text-align="center" vertical-align="middle" space-before.optimum="10pt">
              <xsl:value-of select="nota"/>
              </fo:block>
            </fo:table-cell>
          </fo:table-row>

</xsl:template>


</xsl:stylesheet>
