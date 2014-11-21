package funday.ngram.generator;

import funday.ngram.generator.data.model.Trigram;
import funday.ngram.generator.data.service.TrigramDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * Created by mobradovic on 11/21/14.
 */
public class TrigramTextGenerator implements NgramTextGenerator {
    public static final String WHITESPACE = " ";
    private final TrigramDataService trigramDataService;

    @Autowired
    public TrigramTextGenerator(TrigramDataService trigramDataService) {
        this.trigramDataService = trigramDataService;
    }

    @Override
    public String generate(int wordCount) {
        int count = 3;
        StringBuilder text = new StringBuilder();
        Trigram trigram = trigramDataService.getRandom();
        String thirdWord = getRandomWordFromWordList(trigram.getValues());

        if (trigram == null) {
            throw new IllegalStateException("No trigrams in database");
        }

        appendWords(text, trigram.getFirstKey(), trigram.getSecondKey(), thirdWord);

        String first = trigram.getSecondKey();
        String second = thirdWord;

        while (count <= wordCount - 3) {
            trigram = trigramDataService.get(first, second);
            if (trigram == null) {
                trigram = trigramDataService.getRandom();
            }
            thirdWord = getRandomWordFromWordList(trigram.getValues());
            text.append(thirdWord).append(WHITESPACE);
            count += 1;
            first = trigram.getSecondKey();
            second = thirdWord;
        }
        return text.toString();
    }

    private void appendWords(StringBuilder text, String firstWord, String secondWord, String thirdWord) {
        text.append(firstWord).append(WHITESPACE).append(secondWord).append(WHITESPACE);
        text.append(thirdWord).append(WHITESPACE);
    }

    private String getRandomWordFromWordList(List<String> values) {
        if (values.isEmpty()) {
            throw new IllegalStateException();
        }
        Random random = new Random();
        return values.get(random.nextInt(values.size()));
    }
}
