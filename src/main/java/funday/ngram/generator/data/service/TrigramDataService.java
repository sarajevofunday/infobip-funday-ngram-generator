package funday.ngram.generator.data.service;

import funday.ngram.generator.data.model.Trigram;

/**
 * Created by mobradovic on 11/21/14.
 */
public interface TrigramDataService {
    Trigram getRandom();
    Trigram get(String firstKey, String secondKey);
}
