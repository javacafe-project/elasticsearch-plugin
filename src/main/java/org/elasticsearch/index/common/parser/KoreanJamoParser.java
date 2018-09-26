package org.elasticsearch.index.common.parser;

import org.elasticsearch.index.common.util.JamoUtil;

/**
 * 한글 자모 Parser
 *
 * @author hrkim
 *
 */
public class KoreanJamoParser extends AbstractKoreanParser {

    
    
    
    @Override
    protected void processForKoreanChar(StringBuilder sb, char chosung, char jungsung, char jongsung) {
        sb.append(chosung).append(jungsung);

        if(jongsung != JamoUtil.UNICODE_JONG_SUNG_EMPTY) {
            sb.append(jongsung);
        }
    }

    
    
    @Override
    protected void processForOther(StringBuilder sb, char eachToken) {
        sb.append(eachToken);
    }

    
    
}


