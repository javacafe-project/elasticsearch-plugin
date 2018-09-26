package org.elasticsearch.index.analysis.eng2kor;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.elasticsearch.index.common.converter.EngToKorConverter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 영한 변환 필터
 *
 * @author hrkim
 *
 */
public final class JavacafeEng2KorConvertFilter2 extends TokenFilter {

    private EngToKorConverter converter;
    private CharTermAttribute termAtt; 
    private PositionIncrementAttribute positionIncrementAttribute;
    
    private Queue<char[]> simpleQueue;

    
    public JavacafeEng2KorConvertFilter2(TokenStream stream) {
        super(stream);
        
        this.converter = new EngToKorConverter();
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);
        
        this.simpleQueue = new LinkedList<char[]>();       
    }

    
    @Override
    public boolean incrementToken() throws IOException {
        
        if (!simpleQueue.isEmpty()) {
            char[] buffer = simpleQueue.poll();
            termAtt.setEmpty();
            termAtt.copyBuffer(buffer, 0, buffer.length);
            positionIncrementAttribute.setPositionIncrement(0);
            
            return true;
        }
        
        if (!input.incrementToken()) {
            return false;
            
        } else {
            String result = converter.convert(termAtt.toString());
            simpleQueue.add(result.toCharArray());
            return true;
        }
    }
    
    

}
