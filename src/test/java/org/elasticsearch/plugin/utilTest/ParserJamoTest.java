package org.elasticsearch.plugin.utilTest;

import static org.junit.Assert.assertEquals;

import org.elasticsearch.index.common.parser.KoreanJamoParser;
import org.junit.Test;

public class ParserJamoTest {


    @Test
    public void jamoTest() {
        String token = "자바카페";
        KoreanJamoParser parser = new KoreanJamoParser();
        String result = parser.parse(token);

        System.out.println(result);
        assertEquals("ㅈㅏㅂㅏㅋㅏㅍㅔ", result);
    }
    
    
    @Test
    public void jamoTest2() {
        String token = "삼성전자";
        KoreanJamoParser parser = new KoreanJamoParser();
        String result = parser.parse(token);

        System.out.println(result);
        assertEquals("ㅅㅏㅁㅅㅓㅇㅈㅓㄴㅈㅏ", result);
    }
    
}
