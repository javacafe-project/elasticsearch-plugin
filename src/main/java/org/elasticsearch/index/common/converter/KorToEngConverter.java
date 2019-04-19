package org.elasticsearch.index.common.converter;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.common.type.CodeType;
import org.elasticsearch.index.common.util.JamoUtil;
import org.elasticsearch.index.common.util.KeyboardUtil;

/**
 * 한영 오타 변환기 (Kor -> Eng)
 *
 * @author hrkim
 *
 */
public class KorToEngConverter {

          
    /**
     * 토큰을 한글 키보드 기준으로 변환한다.
     * 
     * @param token
     * @return
     */
    public String convert(String token) {        
        StringBuilder sb = new StringBuilder();
        
        // 문자열을 한글자씩 잘라서 처리한다.
        String word = token.trim();        
        for (int index = 0; index < word.length(); index++) {

            // 처리 불가능한 글자는 그냥 넘긴다.
            if (KeyboardUtil.IGNORE_CHAR.indexOf(word.substring(index, index + 1)) > -1) {
                sb.append(word.substring(index, index + 1));
                index++;
            }
            if (index >= word.length()) {
            	break;
            }

            try {
                int init = word.charAt(index);
                int initUnicode = init - JamoUtil.START_KOREA_UNICODE;

                if (initUnicode > 0) {
                    /**
                     * 1글자로 조합형 한글이 들어올 경우 처리
                     */              
                    int cho  = initUnicode / 21 / 28;   // 0 ~ 18
                    String strCho = getSameEngChar(CodeType.CHOSUNG, cho);
                    if (StringUtils.isNotEmpty(strCho)) {
                        sb.append(strCho);
                    }
                    

                    int jung = initUnicode / 28 % 21;   // 0 ~ 20
                    String strJung = getSameEngChar(CodeType.JUNGSUNG, jung);
                    if (StringUtils.isNotEmpty(strJung)) {
                        sb.append(strJung);
                    }
                    
                    int jong = initUnicode % 28;        // 0 ~ 27
                    String strJong = getSameEngChar(CodeType.JONGSUNG, jong);
                    if (StringUtils.isNotEmpty(strJong)) {
                        sb.append(strJong);
                    }

                } else {
                    /**
                     * 1글자로 자모가 들어올 경우 처리
                     */
                    String subStr = String.valueOf((char) init);
                    sb.append(getSameEngCharForJamo(subStr, 0));
                }
            } catch(Exception e) {}
        }

        return sb.toString();
    }
    
    
    

    private String getSameEngChar(CodeType type, int pos) {
        switch (type) {
            case CHOSUNG:
                return KeyboardUtil.KEYBOARD_CHO_SUNG[pos];

            case JUNGSUNG:
                return KeyboardUtil.KEYBOARD_JUNG_SUNG[pos];

            case JONGSUNG:
                if ((pos - 1) > -1) {
                    return KeyboardUtil.KEYBOARD_JONG_SUNG[pos - 1];
                }
                return "";
        }

        return "";
    }
    

    private String getSameEngCharForJamo(String key, int pos) {
        for (int i=0; i<KeyboardUtil.KEYBOARD_KEY_KOR.length; i++) {
            if (KeyboardUtil.KEYBOARD_KEY_KOR[i].equals(key)) {
                return KeyboardUtil.KEYBOARD_KEY_ENG[i];
            }
        }
        
        return "";
    }
    
    
    
}












