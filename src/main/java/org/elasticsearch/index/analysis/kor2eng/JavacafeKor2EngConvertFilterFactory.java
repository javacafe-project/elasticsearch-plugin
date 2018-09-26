package org.elasticsearch.index.analysis.kor2eng;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;


public class JavacafeKor2EngConvertFilterFactory extends AbstractTokenFilterFactory {

    
    public JavacafeKor2EngConvertFilterFactory(IndexSettings indexSettings, Environment env , String name, Settings settings) {
        super(indexSettings, name, settings);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JavacafeKor2EngConvertFilter(tokenStream);
    }
    
    
}
