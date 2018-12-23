package org.elasticsearch.plugin.utilTest;

import static org.junit.Assert.assertEquals;

import org.elasticsearch.index.common.parser.KoreanChosungParser;
import org.junit.Test;

public class ParserChosungTest {
    
    
    @Test
    public void chosungTest() {
        String token = "자바카페";
        KoreanChosungParser parser = new KoreanChosungParser();
        String result = parser.parse(token);

        System.out.println(result);
        assertEquals("ㅈㅂㅋㅍ", result);
    }
    

    @Test
    public void chosungTest2() {
        String token = "삼성전자";
        KoreanChosungParser parser = new KoreanChosungParser();
        String result = parser.parse(token);

        System.out.println(result);
        assertEquals("ㅅㅅㅈㅈ", result);
    }
    

    
    
    
}
