package org.apache.solr.index.analysis.eng2kor;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.index.analysis.eng2kor.JavacafeEng2KorConvertFilter;

import java.util.Map;
/**
 *
 *  <fieldType name="text_auto_en2ko" class="solr.TextField" positionIncrementGap="100">
 *       <analyzer type="index">
 *         <tokenizer class="solr.KoreanTokenizerFactory" decompoundMode="discard" outputUnknownUnigrams="false"/>
 *         <filter class="solr.KoreanPartOfSpeechStopFilterFactory" />
 *         <filter class="solr.KoreanReadingFormFilterFactory" />
 *         <filter class="solr.LowerCaseFilterFactory" />
 *       </analyzer>
 *        <analyzer type="query">
 *         <tokenizer class="solr.StandardTokenizerFactory"/>
 *         <filter class="org.apache.solr.index.analysis.eng2kor.JavacafeEng2KorConvertFilterFactory"/>
 *         <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
 *         <filter class="solr.SynonymGraphFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
 *         <filter class="solr.LowerCaseFilterFactory"/>
 *       </analyzer>
 *     </fieldType>
 *
 * */
public class JavacafeEng2KorConvertFilterFactory extends TokenFilterFactory {

    
    public JavacafeEng2KorConvertFilterFactory(Map<String, String> args) {
        super(args);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JavacafeEng2KorConvertFilter(tokenStream);
    }
    
    
}
