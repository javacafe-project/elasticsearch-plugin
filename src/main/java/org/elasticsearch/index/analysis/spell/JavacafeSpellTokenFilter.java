package org.elasticsearch.index.analysis.spell;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by seungmokang on 2018. 4. 12..
 */
@Deprecated
public final class JavacafeSpellTokenFilter extends TokenFilter {

    final char[] COMPATIBILITY_CHOSUNGs = {
            0x3131, 0x3132, 0x3134, 0x3137, 0x3138,     // ㄱ, ㄲ, ㄴ, ㄷ, ㄸ
            0x3139, 0x3141, 0x3142, 0x3143, 0x3145,     // ㄹ, ㅁ, ㅂ, ㅃ, ㅅ
            0x3146, 0x3147, 0x3148, 0x3149, 0x314A,     // ㅆ, ㅇ, ㅈ, ㅉ, ㅊ
            0x314B, 0x314C, 0x314D, 0x314E              // ㅋ, ㅌ, ㅍ, ㅎ
    };

    final char[] COMPATIBILITY_JUNGSUNGs = {
            0x314F, 0x3150, 0x3151, 0x3152, 0x3153,         // ㅏ, ㅐ, ㅑ, ㅒ, ㅓ
            0x3154, 0x3155, 0x3156, 0x3157, 0x3158,         // ㅔ, ㅕ, ㅖ, ㅗ, ㅘ
            0x3159, 0x315A, 0x315B, 0x315C, 0x315D,         // ㅙ, ㅚ, ㅛ, ㅜ, ㅝ
            0x315E, 0x315F, 0x3160, 0x3161, 0x3162,         // ㅞ, ㅟ, ㅠ, ㅡ, ㅢ
            0x3163                                          // ㅣ
    };

    final char[] COMPATIBILITY_JONGSUNGs = {
            0x0000, 0x3131, 0x3132, 0x3133, 0x3134,         // 채움, ㄱ, ㄲ, ㄳ, ㄴ
            0x3135, 0x3136, 0x3137, 0x3139, 0x313A,         // ㄵ, ㄶ, ㄷ, ㄹ, ㄺ
            0x313B, 0x313C, 0x313D, 0x313E, 0x313F,         // ㄻ, ㄼ, ㄽ, ㄾ, ㄿ
            0x3140, 0x3141, 0x3142, 0x3144, 0x3145,         // ㅀ, ㅁ, ㅂ, ㅄ, ㅅ
            0x3146, 0x3147, 0x3148, 0x314A, 0x314B,         // ㅆ, ㅇ, ㅈ, ,ㅊ,  ㅋ
            0x314C, 0x314D, 0x314E                          // ㅌ, ㅍ, ㅎ
    };

    
    private CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private PositionIncrementAttribute posIncAtt = addAttribute(PositionIncrementAttribute.class);
    private Queue<char[]> terms;

    
    
    
    public JavacafeSpellTokenFilter(TokenStream input) {
        super(input);
        this.terms = new LinkedList<char[]>();

    }

    
    
    @Override
    public boolean incrementToken() throws IOException {
        if (!terms.isEmpty()) {
            char[] buffer = terms.poll();
            termAtt.setEmpty();
            termAtt.copyBuffer(buffer, 0, buffer.length);
            posIncAtt.setPositionIncrement(0);
            
            return true;
        }

        if(!input.incrementToken()) {
            return false;
            
        } else {
            String word = this.input.getAttribute(CharTermAttribute.class).toString().trim();
            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 0; i<word.length(); i++) {
                char init = word.charAt(i);

                if(init >= 0xAC00 && init <= 0xD79D) {
                    int chosung = (init - 0xAC00)/21/28;  // 0 ~ 18
                    int jungsung = (init - 0xAC00)/28%21; // 0 ~ 20
                    int jongsung = (init - 0xAC00)%28;    // 0 ~ 27

                    stringBuilder.append(COMPATIBILITY_CHOSUNGs[chosung]).append(COMPATIBILITY_JUNGSUNGs[jungsung]);
                    if(jongsung != COMPATIBILITY_JONGSUNGs[0]) {
                        stringBuilder.append(COMPATIBILITY_JONGSUNGs[jongsung]);
                    }
                }
                else {
                    if(Character.isLetterOrDigit(init)) {
                        stringBuilder.append(String.valueOf(init));
                    }

                }


            }

            int lengths = stringBuilder.length();

//            for(int idx = 0; idx<lengths; idx++){
//                terms.add(stringBuilder.substring(0,idx+1).toString().toCharArray());
//            }
            terms.add(stringBuilder.toString().toCharArray());
            return true;

        }
    }
}
