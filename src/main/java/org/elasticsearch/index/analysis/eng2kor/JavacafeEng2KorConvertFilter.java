package org.elasticsearch.index.analysis.eng2kor;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.common.converter.EngToKorConverter;

/**
 * 영한 변환 필터
 *
 * @author hrkim
 *
 */
public final class JavacafeEng2KorConvertFilter extends TokenFilter {

    private EngToKorConverter converter;
    private CharTermAttribute termAtt;   

    
    public JavacafeEng2KorConvertFilter(TokenStream stream) {
        super(stream);       
        this.converter = new EngToKorConverter();
        this.termAtt = addAttribute(CharTermAttribute.class);   
    }

    
    @Override
    public boolean incrementToken() throws IOException {
        
        if (input.incrementToken()) {
            CharSequence parserdData = converter.convert(termAtt.toString());
            termAtt.setEmpty();
            termAtt.append(parserdData);
        
            return true;
        }
        
        return false;
    }
    
    

}
