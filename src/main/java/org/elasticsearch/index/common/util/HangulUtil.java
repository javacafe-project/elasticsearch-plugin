package org.elasticsearch.index.common.util;

import java.util.List;

/**
 * 한글 유니코드 유틸리티
 * 
 * http://www.unicode.org/charts/PDF/UAC00.pdf
 *
 * @author hrkim
 *
 */
public class HangulUtil {


    /**
     * 초성 (19자)
     */
    public static final char[] CHO_SUNG = {
            'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 
            'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };


    /**
     * 중성 (21자)
     */
    public static final char[] JUNG_SUNG = {
            'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 
            'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'
    };
  
    
    /**
     * 종성 (28자) - "빈값" 포함
     */
    public static final char[] JONG_SUNG = {
            ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 
            'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 
            'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };
    
    
    /**
     * 한글 유니코드의 시작값 (가)
     * 
     * 16진수 : 0xAC00
     * 10진수 : 44032
     * 
     */
    public static final int START_KOREA_UNICODE_DECIMAL = 44032;




    
    
    public static int getOneHangulJamoSize(int startIdx, List<String> jamoList) {
        int remainJamoSize = jamoList.size() - startIdx;
        
        if (remainJamoSize == 1) {
            return -1;
        }
        
        if (remainJamoSize == 2 || remainJamoSize == 3) {
            return remainJamoSize;
        }
        
        // 초성이나 종성은 겹치는 문자가 존재하기 때문에 
        // 다음 글자의 중성을 이용하여 한글자의 사이즈를 검사한다.
        String strJungSung = new String(JUNG_SUNG);
        String strChar = jamoList.get(startIdx + 3);
        
        if (strJungSung.contains(strChar)) {
            return 2;
        }

        return 3;
    }
    
    
    public static int getChoSungIndex(int startIdx, List<String> jamoList) {
        String strChoSung = new String(CHO_SUNG);
        String strChoSungChar = jamoList.get(startIdx);
        
        return strChoSung.indexOf(strChoSungChar);
    }
    
    
    public static int getJungSungIndex(int startIdx, List<String> jamoList) {
        String strJungSung = new String(HangulUtil.JUNG_SUNG);
        String strJungSungChar = jamoList.get(startIdx + 1);
        
        return strJungSung.indexOf(strJungSungChar);
    }
    
    
    public static int getJongSungIndex(int startIdx, List<String> jamoList) {
        String strJongSung = new String(HangulUtil.JONG_SUNG);
        String strJongSungChar = jamoList.get(startIdx + 2);
        
        return strJongSung.indexOf(strJongSungChar);
    }
    
    
    
}


















