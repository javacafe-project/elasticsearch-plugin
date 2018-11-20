package org.elasticsearch.index.analysis.autocomplete;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

public class JavacafeAutocompleteTokenizerFactory extends AbstractTokenizerFactory {
    public JavacafeAutocompleteTokenizerFactory(IndexSettings indexSettings,
                                                Environment environment,
                                                String name,
                                                Settings settings) {

        super(indexSettings, name, settings);

    }

    @Override
    public Tokenizer create() {
        return new JavacafeAutocompleteTokenizer();
    }
}
