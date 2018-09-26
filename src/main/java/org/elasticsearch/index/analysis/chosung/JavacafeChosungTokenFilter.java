package org.elasticsearch.index.analysis.chosung;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.common.parser.KoreanChosungParser;

/**
 * 한글 초성 분석 필터
 *
 * @author hrkim
 *
 */
public final class JavacafeChosungTokenFilter extends TokenFilter {
    
    private KoreanChosungParser parser;
    private CharTermAttribute termAtt;

    
    public JavacafeChosungTokenFilter(TokenStream stream) {
        super(stream);
        this.parser = new KoreanChosungParser();
        this.termAtt = addAttribute(CharTermAttribute.class);
    }

    
    /**
     * 한글 초성 Parser를 이용하여 토큰을 파싱하고 Term을 구한다. 
     */
    @Override
    public boolean incrementToken() throws IOException {
        
        if (input.incrementToken()) {
            CharSequence parserdData = parser.parse(termAtt.toString());
            termAtt.setEmpty();
            termAtt.append(parserdData);
        
            return true;
        }
        
        return false;
    }
    
    
}
