package org.apache.solr.index.analysis.autocomplete;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;
import java.util.Map;


/**
 *
 * <fieldType name="text_auto" class="solr.TextField" positionIncrementGap="100">
 *       <analyzer type="index">
 *         <tokenizer class="org.apache.solr.index.analysis.autocomplete.JavacafeAutocompleteTokenizerFactory"/>
 *         <filter class="solr.EdgeNGramFilterFactory" minGramSize="1" maxGramSize="50"/>
 *         <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
 *         <filter class="solr.LowerCaseFilterFactory"/>
 *       </analyzer>
 *        <analyzer type="query">
 *         <tokenizer class="org.apache.solr.index.analysis.autocomplete.JavacafeAutocompleteTokenizerFactory"/>
 *         <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
 *         <filter class="solr.LowerCaseFilterFactory"/>
 *       </analyzer>
 *     </fieldType>
 * */
public class JavacafeAutocompleteTokenizerFactory   extends TokenizerFactory {
    public JavacafeAutocompleteTokenizerFactory(Map<String,String> args) {
        super(args);
    }


    @Override
    public Tokenizer create(AttributeFactory attributeFactory) {
        return new JavacafeAutocompleteTokenizer(attributeFactory);
    }
}
