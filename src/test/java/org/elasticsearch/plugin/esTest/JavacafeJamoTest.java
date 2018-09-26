package org.elasticsearch.plugin.esTest;

import java.io.IOException;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.plugin.analysis.JavacafePlugin;


public class JavacafeJamoTest extends AbstractPluginTest {

    
    /**
     * 자모 필터를 테스트한다.
     * 
     * @throws IOException
     */
    public void testJamoFilter() throws Exception {
                
        String source = "자바카페 한글";
        
        String[] result = new String[]{
                "ㅈㅏㅂㅏㅋㅏㅍㅔ", 
                "ㅎㅏㄴㄱㅡㄹ"
        };
        
        String filterName = "javacafe_jamo";
        

        // 실행
        TestAnalysis analysis = createTestAnalysis(
                new Index("test", ""), Settings.builder().build(), new JavacafePlugin()
        );
        
        TokenFilterFactory myFilter = analysis.tokenFilter.get(filterName);
        runFilter(myFilter, source, result);
    }
    

    
    
    
}
