package org.apache.solr.index.analysis.eng2kor;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.index.analysis.eng2kor.JavacafeEng2KorConvertFilter;

import java.util.Map;

public class JavacafeEng2KorConvertFilterFactory extends TokenFilterFactory {

    
    public JavacafeEng2KorConvertFilterFactory(Map<String, String> args) {
        super(args);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JavacafeEng2KorConvertFilter(tokenStream);
    }
    
    
}
