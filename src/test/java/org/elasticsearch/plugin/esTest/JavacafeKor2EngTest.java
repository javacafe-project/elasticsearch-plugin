package org.elasticsearch.plugin.esTest;

import java.io.IOException;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.plugin.analysis.JavacafePlugin;


public class JavacafeKor2EngTest extends AbstractPluginTest {

    
    /**
     * 한영 오타 변환기를 테스트한다.
     * 
     * @throws IOException
     */
    public void test() throws Exception {
                
        String source = "ㅓㅁㅍㅁㅊㅁㄹㄷ ㅑㅔㅗㅐㅜㄷ";
        
        String[] result = new String[]{
                "javacafe", 
                "iphone"
        };
        
        String filterName = "javacafe_kor2eng";
        

        // 실행
        TestAnalysis analysis = createTestAnalysis(
                new Index("test", ""), Settings.builder().build(), new JavacafePlugin()
        );
        
        TokenFilterFactory myFilter = analysis.tokenFilter.get(filterName);
        runFilter(myFilter, source, result);
    }
    
    
    
}
