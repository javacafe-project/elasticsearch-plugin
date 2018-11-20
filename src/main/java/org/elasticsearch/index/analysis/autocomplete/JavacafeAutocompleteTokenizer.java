package org.elasticsearch.index.analysis.autocomplete;

public class JavacafeAutocompleteTokenizer  extends JavacafeAutocompleteBaseTokenizer {
    /**
     * 자소 토크나이저 생성자
     *
     */
    public JavacafeAutocompleteTokenizer() {
        super();
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
