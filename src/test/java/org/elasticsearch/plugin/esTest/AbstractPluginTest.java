package org.elasticsearch.plugin.esTest;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.test.ESTestCase;


public class AbstractPluginTest extends ESTestCase {

    
    public void runFilter(TokenFilterFactory myFilter, String source, String[] result) throws IOException {
        init();
        
        // StandardTokenizer 생성
        Tokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader(source));
        
  
        // 필터를 이용하여 tokenStream 생성
        TokenStream tokenStream = myFilter.create(tokenizer);       
        tokenStream.reset();
        
        CharTermAttribute termAttr = tokenStream.getAttribute(CharTermAttribute.class);
        
        
        // 테스트 시작
        System.out.println("[소스] : " + source);
        
        int i = 0;
        while (tokenStream.incrementToken()) {
            String t = termAttr.toString();
            
            System.out.println("Token[" + i + "] => [예상결과] : " + result[i] + " , [실제결과] : " + t);
            //assertThat("Token 생성이 잘못되었습니다.", t, equalTo(result[i]));
            
            i++;
        }
        
        System.out.println("[결과] 생성된 Token 수 : " + i);
        //assertThat("Token 수가 일치하지 않습니다.", i, equalTo(result.length));
        
        destroy();
    }
    
    
    public void init() {
        System.out.println("-------------------------------");
        System.out.println("테스트를 시작합니다.");
        System.out.println("-------------------------------");
    }
    
    
    public void destroy() {
        System.out.println("-------------------------------");
        System.out.println("테스트를 종료합니다.");
        System.out.println("-------------------------------");
    }
    
}
