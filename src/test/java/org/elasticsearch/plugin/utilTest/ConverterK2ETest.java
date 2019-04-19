package org.elasticsearch.plugin.utilTest;

import static org.junit.Assert.assertEquals;

import org.elasticsearch.index.common.converter.KorToEngConverter;
import org.junit.Test;

public class ConverterK2ETest {
    
    

    @Test
    public void test1() {
        String token = "ㅓㅁㅍㅁㅊㅁㄹㄷ";
        
        KorToEngConverter convert = new KorToEngConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("javacafe", result);
    }
    
    
    @Test
    public void test2() {
        String token = "ㅑㅔㅗㅐㅜㄷ";
        
        KorToEngConverter convert = new KorToEngConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("iphone", result);
    }
    
    
    @Test
    public void test3() {
        String token = "재ㅡ무";
        
        KorToEngConverter convert = new KorToEngConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("woman", result);
    }
    
    
    @Test
    public void test4() {
        String token = "ㄴ므녀ㅜㅎ";
        
        KorToEngConverter convert = new KorToEngConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("samsung", result);
    }
    

    @Test
    public void test5() {
        String token = "ㄴ므녀ㅜㅎ1";
        
        KorToEngConverter convert = new KorToEngConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("samsung1", result);
    }
    
    
    @Test
    public void test6() {
        String token = "신혼여행(身魂旅行)";
        
        KorToEngConverter convert = new KorToEngConverter();
        String result = convert.convert(token);
        
        System.out.println(result);
        assertEquals("samsung1", result);
    }

}
