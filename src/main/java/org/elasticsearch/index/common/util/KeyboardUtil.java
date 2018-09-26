package org.elasticsearch.index.common.util;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.common.type.CodeType;

/**
 * 한글 키보드 유틸리티
 * 
 *
 * @author hrkim
 *
 */
public class KeyboardUtil {

    
    /**
     * Converter 진행시 무시되는 문자들
     */
    public static final String IGNORE_CHAR = "`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?\' \' ";

    
    
    /**
     * 초성 키에 해당하는 키보드상의 영문자 (19자)
     */
    public static final String[] KEYBOARD_CHO_SUNG = {
            "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", 
            "T", "d", "w", "W", "c", "z", "x", "v", "g"
    };
    
    /**
     * 중성 키에 해당하는 키보스상의 영문자 (21자)
     */
    public static final String[] KEYBOARD_JUNG_SUNG = {
            "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", 
            "ho", "hl", "y", "n", "nj", "np", "nl", "b", "m", "ml", "l"
    };
    
    /**
     * 종성 키에 해당하는 키보드상의 영문자 (27자) - "빈값" 제외
     */
    public static final String[] KEYBOARD_JONG_SUNG = {
            "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", 
            "fq", "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", 
            "d", "w", "c", "z", "x", "v", "g"
    };
        


    /**
     * 키보드상에서 한영키에 의해서 오타 교정이 필요한 키배열 (영문키 33자)
     */
    public static final String[] KEYBOARD_KEY_ENG = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
            "u", "v", "w", "x", "y", "z", "Q", "W", "E", "R", 
            "T", "O", "P"
    };
    
    /**
     * 키보드상에서 한영키에 의해서 오타 교정이 필요한 키배열 (한글키 33자)
     */
    public static final String[] KEYBOARD_KEY_KOR  = {
            "ㅁ", "ㅠ", "ㅊ", "ㅇ", "ㄷ", "ㄹ", "ㅎ", "ㅗ", "ㅑ", "ㅓ", 
            "ㅏ", "ㅣ", "ㅡ", "ㅜ", "ㅐ", "ㅔ", "ㅂ", "ㄱ", "ㄴ", "ㅅ", 
            "ㅕ", "ㅍ", "ㅈ", "ㅌ", "ㅛ", "ㅋ", "ㅃ", "ㅉ", "ㄸ", "ㄲ", 
            "ㅆ", "ㅒ", "ㅖ"
    };
   
    
    
    

    /**
     * 초성 정보를 제공한다.
     * 
     * - 초성과 매칭된 코드 조회 
     * - 한 자로 이루어진 초성코드만 존재한다.
     * 
     * @param index
     * @param word
     * @return
     */
    public static Map<String, Integer> getInfoForChoSung(int index, String word) {  
        int code = KeyboardUtil.makeUnicodeIndex(CodeType.CHOSUNG, word.substring(index, index + 1));
        int idx = index + 1;
        
        Map<String, Integer> m = new HashMap<>();
        m.put("code", code);
        m.put("idx", idx);

        return m;
    }
    
    
    /**
     * 중성 정보를 제공한다.
     * 
     * - 중성과 매칭된 코드 조회 
     * - 두 자로 이루어진 중성코드가 존재한다.
     * 
     * @param index
     * @param word
     * @return
     */
    public static Map<String, Integer> getInfoForJungSung(int index, String word) {
        int code = KeyboardUtil.getDoubleMedial(index, word);
        int idx = index + 2;
        
        if (-1 == code) {
            code = KeyboardUtil.getSingleMedial(index, word);
            idx = index + 1;                
        }
        
        Map<String, Integer> m = new HashMap<>();
        m.put("code", code);
        m.put("idx", idx);

        return m;
    }

    
    /**
     * 종성 정보를 제공한다.
     * 
     * - 종성과 매칭된 코드 조회 
     * - 두 자로 이루어진 종성코드가 존재한다.
     * 
     * @param index
     * @param word
     * @return
     */
    public static Map<String, Integer> getInfoForJongSung(int index, String word) {
        int code;
        int idx = index;
        
        int temp = KeyboardUtil.getDoubleFinal(idx, word);
        if (-1 == temp) {
            temp = KeyboardUtil.getSingleMedial(idx + 1, word);
            if (temp != -1) {
                code = 0;
                idx--;
            } else {
                code = KeyboardUtil.getSingleFinal(idx, word);
                if (code == -1) {
                    code = 0;
                    idx--;
                }
            }
            
        } else {
            code = temp;
            temp = KeyboardUtil.getSingleMedial(idx + 2, word);
            if (temp != -1) {
                code = KeyboardUtil.getSingleFinal(idx, word);
            } else {
                idx++;
            }
            
        }
        
        Map<String, Integer> m = new HashMap<>();
        m.put("code", code);
        m.put("idx", idx);

        return m;
    }
    
    
   
    
    /**
     * 1자로 구성된 중성 유니코드 Index를 리턴한다.
     * 
     * @param index
     * @param word
     * @return
     */
    private static int getSingleMedial(int index, String word) {
        if ((index + 1) <= word.length()) {
            return makeUnicodeIndex(CodeType.JUNGSUNG, word.substring(index, index+1));
        } else {
            return -1;
        }
    }

    /**
     * 2자로 구성된 중성 유니코드 Index를 리턴한다.
     * 
     * @param index
     * @param word
     * @return
     */
    private static int getDoubleMedial(int index, String word) {
        if ((index + 2) > word.length()) {
            return -1;
        } else {
            return makeUnicodeIndex(CodeType.JUNGSUNG, word.substring(index, index+2));
        }
    }

    /**
     * 1자로 구성된 종성 유니코드 Index를 리턴한다.
     * 
     * @param index
     * @param word
     * @return
     */
    private static int getSingleFinal(int index, String word) {
        if ((index + 1) <= word.length())  {
            return makeUnicodeIndex(CodeType.JONGSUNG, word.substring(index, index+1));
        } else {
            return -1;
        }
    }

    /**
     * 2자로 구성된 종성 유니코드 Index를 리턴한다.
     * 
     * @param index
     * @param word
     * @return
     */
    private static int getDoubleFinal(int index, String word) {
        if ((index + 2) > word.length()) {
            return -1;
        } else {
            return makeUnicodeIndex(CodeType.JONGSUNG, word.substring(index, index+2));
        }
    }    
    
    
    /**
     * 키보드상에 매칭된 유니코드값 Index를 리턴한다.
     * 
     * @param type
     * @param sub_str
     * @return
     */
    private static int makeUnicodeIndex(CodeType type, String subStr) {
        switch (type) {
            case CHOSUNG:                
                for (int i=0; i<KEYBOARD_CHO_SUNG.length; i++) {
                    if (KEYBOARD_CHO_SUNG[i].equals(subStr)) {
                        return i * 28 * 21; 
                    }
                }                
                break;

            case JUNGSUNG:
                for (int i=0; i<KEYBOARD_JUNG_SUNG.length; i++) {
                    if (KEYBOARD_JUNG_SUNG[i].equals(subStr)) {
                        return i * 28;
                    }
                }
                break;

            case JONGSUNG:
                for (int i=0; i<KEYBOARD_JONG_SUNG.length; i++) {
                    if (KEYBOARD_JONG_SUNG[i].equals(subStr)) {
                        return i + 1;
                    }
                }
                break;
                
            default:
                break;
        }

        return -1;
    } 
    
  
    
    
}


















