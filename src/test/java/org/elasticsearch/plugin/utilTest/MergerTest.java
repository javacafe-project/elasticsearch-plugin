package org.elasticsearch.plugin.utilTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.common.merger.KoreanMerger;
import org.junit.Test;

public class MergerTest {

    
    @Test
    public void mergerTest() throws Exception {
        List<String> jasoList = new ArrayList<>();
        jasoList.add("ㅎ");
        jasoList.add("ㅏ");
        jasoList.add("ㄴ");
        jasoList.add("ㄱ");
        jasoList.add("ㅡ");
        jasoList.add("ㄹ");
        
        KoreanMerger merger = new KoreanMerger();
        String word = merger.merge(jasoList);
        
        System.out.println("결과 : " + word);
        assertEquals("한글", word);
    }
    
    
    @Test
    public void mergerTest2() throws Exception {
        List<String> jasoList = new ArrayList<>();
        jasoList.add("ㅈ");
        jasoList.add("ㅏ");
        jasoList.add("ㅂ");
        jasoList.add("ㅏ");
        jasoList.add("ㅋ");
        jasoList.add("ㅏ");
        jasoList.add("ㅍ");
        jasoList.add("ㅔ");
        
        KoreanMerger merger = new KoreanMerger();
        String word = merger.merge(jasoList);
        
        System.out.println("결과 : " + word);
        assertEquals("자바카페", word);
    }

    
    @Test
    public void mergerTest3() throws Exception {
        List<String> jasoList = new ArrayList<>();
        jasoList.add("ㅅ");
        jasoList.add("ㅏ");
        jasoList.add("ㅁ");
        jasoList.add("ㅅ");
        jasoList.add("ㅓ");
        jasoList.add("ㅇ");
        jasoList.add("ㅈ");
        jasoList.add("ㅓ");
        jasoList.add("ㄴ");
        jasoList.add("ㅈ");
        jasoList.add("ㅏ");
        
        KoreanMerger merger = new KoreanMerger();
        String word = merger.merge(jasoList);
        
        System.out.println("결과 : " + word);
        assertEquals("삼성전자", word);
    }
    
}





