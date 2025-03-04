package com.remaslover.dataanalyzermicroservice.config;


import com.jcabi.xml.XML;

import java.util.List;


public final class TextXPath {
    private final XML xml;
    private final String xpathExpr;

    public TextXPath(XML xml, String xpathExpr) {
        this.xml = xml;
        this.xpathExpr = xpathExpr;
    }

    public String getValue() {
        List<XML> nodes = xml.nodes(xpathExpr);
        if (nodes.isEmpty()) {
            throw new IllegalArgumentException("Node not found: " + xpathExpr);
        }
        return nodes.get(0).xpath("text()").get(0);
    }
}
