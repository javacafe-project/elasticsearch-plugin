package org.apache.solr.index.analysis.chosung;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.analysis.chosung.JavacafeChosungTokenFilter;

import java.util.Map;

public class JavacafeChosungTokenFilterFactory extends TokenFilterFactory {

    
    public JavacafeChosungTokenFilterFactory(Map<String, String> args) {
        super(args);
    }

    @Override
    public TokenStream create(TokenStream stream) {
        return new JavacafeChosungTokenFilter(stream);
    }

    
    
}
