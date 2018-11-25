package org.apache.solr.index.analysis.kor2eng;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.index.analysis.kor2eng.JavacafeKor2EngConvertFilter;

import java.util.Map;

/**
 *
 *  <fieldType name="text_auto_ko2en" class="solr.TextField" positionIncrementGap="100">
 *       <analyzer type="index">
 *         <tokenizer class="solr.KoreanTokenizerFactory" decompoundMode="discard" outputUnknownUnigrams="false"/>
 *         <filter class="solr.KoreanPartOfSpeechStopFilterFactory" />
 *         <filter class="solr.KoreanReadingFormFilterFactory" />
 *         <filter class="solr.LowerCaseFilterFactory" />
 *       </analyzer>
 *        <analyzer type="query">
 *         <tokenizer class="solr.StandardTokenizerFactory"/>
 *         <filter class="org.apache.solr.index.analysis.kor2eng.JavacafeKor2EngConvertFilterFactory"/>
 *         <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
 *         <filter class="solr.SynonymGraphFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
 *         <filter class="solr.LowerCaseFilterFactory"/>
 *       </analyzer>
 *     </fieldType>
 *
 * */
public class JavacafeKor2EngConvertFilterFactory extends TokenFilterFactory {

    
    public JavacafeKor2EngConvertFilterFactory(Map<String, String> args) {
        super(args);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JavacafeKor2EngConvertFilter(tokenStream);
    }
    
    
}
