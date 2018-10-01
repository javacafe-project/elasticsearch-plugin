package org.elasticsearch.plugin.luceneTest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.junit.Test;

public class TokenTest {


    String text = 
            "Every mammal on this planet instinctively develops a natural " + 
            "equilibrium with the surrounding environment; " + 
            "but you humans do not. Instead you multiply, " + 
            "and multiply, until every resource is consumed." +
            
            "The only way for you to survive is to spread to another area. " + 
            
            "There is another organism on this planet that follows the same pattern... a virus.";
    
    String fieldName = "content";
    
    
    @Test
    public void test() throws IOException {

        Reader textReader = new StringReader(text);
        
        // 필드명과 텍스트 값을 위한 TokenStream 생성
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        TokenStream tokenStream = standardAnalyzer.tokenStream(fieldName, textReader);

        CharTermAttribute terms = tokenStream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsets = tokenStream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttribute positions = tokenStream.addAttribute(PositionIncrementAttribute.class);
        
        System.out.println("INCR\t(START,\tEND)\tTERM");        
        System.out.println();
        
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            
            // 다음 토큰을 읽을때마다 attribute 값이 새롭게 세팅되어 제공된다.
            String term = terms.toString();

            int increment = positions.getPositionIncrement();
            
            int start = offsets.startOffset();
            int end = offsets.endOffset();
            
            System.out.print(increment + "\t" + "(" + start + ",\t" + end + ")\t" + term);
            System.out.println();
        }
        
        standardAnalyzer.close();
        
        
        assertTrue(true);
    }
    
    

    
}
