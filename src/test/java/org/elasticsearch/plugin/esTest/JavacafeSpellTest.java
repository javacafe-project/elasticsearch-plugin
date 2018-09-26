package org.elasticsearch.plugin.esTest;

import java.io.IOException;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.plugin.analysis.JavacafePlugin;


public class JavacafeSpellTest extends AbstractPluginTest {

    
    /**
     * Spell 체크 필터를 테스트한다.
     * 
     * @throws IOException
     */
    public void testSpellFilter() throws Exception {
                
        String source = "삼성전자";
        
        String[] result = new String[] {
                "",
                "ㅅㅏㅁㅅㅓㅇㅈㅓㄴㅈㅏ"
        };
        
        String filterName = "javacafe_spell";
        

        // 실행
        TestAnalysis analysis = createTestAnalysis(
                new Index("test", ""), Settings.builder().build(), new JavacafePlugin()
        );
        
        TokenFilterFactory myFilter = analysis.tokenFilter.get(filterName);
        runFilter(myFilter, source, result);
    }
    

    
    
    
}
