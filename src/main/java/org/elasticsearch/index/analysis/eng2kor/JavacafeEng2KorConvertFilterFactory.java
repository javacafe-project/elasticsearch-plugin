package org.elasticsearch.index.analysis.eng2kor;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;


public class JavacafeEng2KorConvertFilterFactory extends AbstractTokenFilterFactory {

    
    public JavacafeEng2KorConvertFilterFactory(IndexSettings indexSettings, Environment env , String name, Settings settings) {
        super(indexSettings, name, settings);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JavacafeEng2KorConvertFilter(tokenStream);
    }
    
    
}
