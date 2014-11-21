package funday.ngram.generator.data.service;

import funday.ngram.generator.configuration.ContextConfiguration;
import funday.ngram.generator.data.model.Trigram;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TrigramDataServiceTest {
    private static TrigramDataService trigramDataService;
    @BeforeClass
    public static void setup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        trigramDataService = context.getBean(TrigramDataService.class);
    }

    @Test
    public void testGetRandom() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        Trigram trigram = new Trigram();
        trigram.setFirstKey("firstKey");
        trigram.setSecondKey("secondKey");
        trigram.setValues(Arrays.asList("firstWord", "secondWord", "thirdWord"));

        trigramDataService.save(trigram);
    }
}