package org.elasticsearch.plugin.analysis;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.chosung.JavacafeChosungTokenFilterFactory;
import org.elasticsearch.index.analysis.eng2kor.JavacafeEng2KorConvertFilterFactory;
import org.elasticsearch.index.analysis.jamo.JavacafeJamoTokenFilterFactory;
import org.elasticsearch.index.analysis.kor2eng.JavacafeKor2EngConvertFilterFactory;
import org.elasticsearch.index.analysis.spell.JavacafeSpellFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

/**
 * Javacafe에서 개발한 필터 리스트
 *
 * @author hrkim
 *
 */
public class JavacafePlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {        
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        
        // (1) 한글 자모 분석 필터
        extra.put("javacafe_jamo", JavacafeJamoTokenFilterFactory::new);
        
        // (2) 한글 초성 분석 필터
        extra.put("javacafe_chosung", JavacafeChosungTokenFilterFactory::new);
        
        // (3) 영한 오타 변환 필터
        extra.put("javacafe_eng2kor", JavacafeEng2KorConvertFilterFactory::new);
        
        // (4) 한영 오타 변환 필터
        extra.put("javacafe_kor2eng", JavacafeKor2EngConvertFilterFactory::new);

        // (5) 한글 스펠링 체크 필터
        extra.put("javacafe_spell", JavacafeSpellFilterFactory::new);
                        
        return extra;
    }

}



