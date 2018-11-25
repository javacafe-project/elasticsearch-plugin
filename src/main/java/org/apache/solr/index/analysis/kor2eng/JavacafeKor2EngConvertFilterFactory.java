package org.apache.solr.index.analysis.kor2eng;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.index.analysis.kor2eng.JavacafeKor2EngConvertFilter;

import java.util.Map;


public class JavacafeKor2EngConvertFilterFactory extends TokenFilterFactory {

    
    public JavacafeKor2EngConvertFilterFactory(Map<String, String> args) {
        super(args);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JavacafeKor2EngConvertFilter(tokenStream);
    }
    
    
}
