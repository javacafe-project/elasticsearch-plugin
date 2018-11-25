package org.apache.solr.index.analysis.autocomplete;

import org.apache.lucene.util.AttributeFactory;

public class JavacafeAutocompleteTokenizer  extends JavacafeAutocompleteBaseTokenizer {
    /**
     * 자소 토크나이저 생성자
     *
     */
     public JavacafeAutocompleteTokenizer(AttributeFactory factory) {
        super(factory);
    }

    /**
     * Collects only characters which do not satisfy
     * {@link Character#isWhitespace(int)}.
     */
    @Override
    protected boolean isTokenChar(int c) {
        return !isSplit(c);
    }

    /**
     * White Space로 토큰분해
     *
     * @param c
     * @return
     */
    protected boolean isSplit(int c) {
        if ((char) c == ' ') {
            return true;
        } else {
            return false;
        }
    }
}
