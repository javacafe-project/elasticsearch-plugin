package org.elasticsearch.index.common.parser;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.common.util.JamoUtil;

/**
 * 한글 기본 Parser
 *
 * @author hrkim
 *
 */
public abstract class AbstractKoreanParser {
    
    
    /**
     * 토큰을 자음과 모음으로 파싱한다.
     * 
     * @param token
     * @return
     */
    public String parse(String token) {
        if (StringUtils.isBlank(token)) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        // 토큰을 한글자씩 잘라서 처리한다.
        char[] arrCh = token.toCharArray();        
        for(char ch : arrCh) {
            
            // 처리 할 char의 유니코드 인덱스를 구한다.
            int unicodeIndex = ch - JamoUtil.START_KOREA_UNICODE;

            // 한글 유니코드 범위 : 0xAC00 ~ 0xD7AF (11184개)
            // 한글 유니코드인지 검사한다.            
            if(unicodeIndex >= 0 && unicodeIndex <= 11184) {
                
                // 초성 유니코드
                int idxChoSung = unicodeIndex / (28 * 21);
                char chosung = JamoUtil.UNICODE_CHO_SUNG[idxChoSung];
                                
                // 중성 유니코드
                int idxJungSung = unicodeIndex % (28 * 21) / 28;
                char jungsung = JamoUtil.UNICODE_JUNG_SUNG[idxJungSung];
                
                // 종성 유니코드
                int idxJongSung = unicodeIndex % (28 * 21) % 28;
                char jongsung = JamoUtil.UNICODE_JONG_SUNG[idxJongSung];

                // 한글 한글자를 처리한다.
                processForKoreanChar(result, chosung, jungsung, jongsung);
            
            } else {
                
                // 한글이 아닌 한글자를 처리한다.
                processForOther(result, ch);
            }
        }

        // 토큰을 분석한 최종 결과를 리턴한다.
        return result.toString();
    }
    

    /**
     * 한글 문자를 처리한다.
     * 
     * @param sb
     * @param chosung
     * @param jungsung
     * @param jongsung
     */
    protected abstract void processForKoreanChar(StringBuilder sb, char chosung, char jungsung, char jongsung);
    
    
    /**
     * 한글 문자를 제외한 일반 문자를 처리한다.
     * 
     * @param sb
     * @param eachToken
     */
    protected abstract void processForOther(StringBuilder sb, char eachToken);


    
}
