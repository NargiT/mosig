<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : stations.xsl.xsl
    Created on : April 21, 2011, 10:35 AM
    Author     : twk
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" encoding="UTF-8"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/results">
        <div id="search_results">
            <xsl:for-each select="/results/result">
                <xsl:if test="@type = 'bus'">
                    <span class="search_result">
                        <span>
                            <xsl:value-of select="@name"/>
                        </span>
                        <img src="img/bus.png"/>
                    </span>
                </xsl:if>
                <xsl:if test="@type = 'tram'">
                    <div class="search_result">
                        <span>
                            <xsl:value-of select="@name"/>
                        </span>
                        <img src="img/tram.png"/>
                    </div>
                </xsl:if>
                <xsl:if test="@type = 'bustram'">
                    <div class="search_result">
                        <span>
                            <xsl:value-of select="@name"/>
                        </span>
                        <img src="img/bus.png"/>
                        <img src="img/tram.png"/>
                    </div>
                </xsl:if>
                <xsl:if test="@type = 'address'">
                    <div class="search_result">
                        <span>
                            <xsl:value-of select="@name"/>
                        </span>
                        <img src="img/address.png"/>
                    </div>
                </xsl:if>
            </xsl:for-each>
        </div>
    </xsl:template>

</xsl:stylesheet>
