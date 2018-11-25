package org.apache.solr.index.analysis.chosung;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.analysis.chosung.JavacafeChosungTokenFilter;

import java.util.Map;
/**
 *
 *  <fieldType name="text_auto_chosung" class="solr.TextField" positionIncrementGap="100">
 *       <analyzer type="index">
 *         <tokenizer class="solr.KoreanTokenizerFactory" decompoundMode="discard" outputUnknownUnigrams="false"/>
 *         <filter class="solr.KoreanPartOfSpeechStopFilterFactory" />
 *         <filter class="solr.KoreanReadingFormFilterFactory" />
 *         <filter class="org.apache.solr.index.analysis.chosung.JavacafeChosungTokenFilterFactory"/>
 *         <filter class="solr.EdgeNGramFilterFactory" minGramSize="1" maxGramSize="50"/>
 *         <filter class="solr.LowerCaseFilterFactory" />
 *       </analyzer>
 *        <analyzer type="query">
 *         <tokenizer class="solr.StandardTokenizerFactory"/>
 *         <filter class="org.apache.solr.index.analysis.chosung.JavacafeChosungTokenFilterFactory"/>
 *         <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
 *         <filter class="solr.SynonymGraphFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
 *         <filter class="solr.LowerCaseFilterFactory"/>
 *       </analyzer>
 *     </fieldType>
 *
 * */
public class JavacafeChosungTokenFilterFactory extends TokenFilterFactory {

    
    public JavacafeChosungTokenFilterFactory(Map<String, String> args) {
        super(args);
    }

    @Override
    public TokenStream create(TokenStream stream) {
        return new JavacafeChosungTokenFilter(stream);
    }

    
    
}
