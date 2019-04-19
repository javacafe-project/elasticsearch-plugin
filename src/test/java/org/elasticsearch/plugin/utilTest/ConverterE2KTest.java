package org.elasticsearch.plugin.utilTest;

import static org.junit.Assert.assertEquals;

import org.elasticsearch.index.common.converter.EngToKorConverter;
import org.junit.Test;

public class ConverterE2KTest {
    
    
    @Test
    public void test1() {
        String token = "wkqkzkvp";
        
        EngToKorConverter convert = new EngToKorConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("자바카페", result);
    }
    

    @Test
    public void test2() {
        String token = "tkatjdwjswk";
        
        EngToKorConverter convert = new EngToKorConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("삼성전자", result);
    }

   
    @Test
    public void test3() {
        String token = "gksrmf";
        
        EngToKorConverter convert = new EngToKorConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("한글", result);
    }
    
    
    @Test
    public void test4() {
        String token = "gksrmf1";
        
        EngToKorConverter convert = new EngToKorConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("한글1", result);
    }

    
    
}
