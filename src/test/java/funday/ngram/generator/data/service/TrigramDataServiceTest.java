package funday.ngram.generator.data.service;

import funday.ngram.generator.configuration.ContextConfiguration;
import funday.ngram.generator.data.model.Trigram;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TrigramDataServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrigramDataServiceTest.class);
    private static TrigramDataService trigramDataService;
    private static MongoTemplate template;

    @BeforeClass
    public static void setup() {
        System.setProperty("log4j.config", "/home/mobradovic/IdeaProjects/funday-ngram-generator/target/classes/log4j.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        template = context.getBean(MongoTemplate.class);
        trigramDataService = context.getBean(TrigramDataService.class);

        Trigram trigram = new Trigram();
        trigram.setFirstKey("firstKey");
        trigram.setSecondKey("secondKey");
        trigram.setValues(Arrays.asList("firstWord", "secondWord", "thirdWord"));

        template.save(trigram);
    }

    @Test
    public void testGetRandom() throws Exception {
        for (int i = 0; i < 100; i++) {
            Trigram trigram = new Trigram();
            trigram.setFirstKey("firstKey" + i);
            trigram.setSecondKey("secondKey" + i);
            trigram.setValues(Arrays.asList("firstWord", "secondWord", "thirdWord"));
            template.save(trigram);
        }

        Trigram objectOne = trigramDataService.getRandom();
        Trigram objectTwo = trigramDataService.getRandom();

        Assert.assertNotEquals(objectOne, objectTwo);

        template.dropCollection(Trigram.class);
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertNotNull(trigramDataService.get("firstKey", "secondKey"));
    }

    @Test
    public void testSave() throws Exception {
        template.dropCollection(Trigram.class);

        Trigram trigram = new Trigram();
        trigram.setFirstKey("firstKey");
        trigram.setSecondKey("secondKey");
        trigram.setValues(Arrays.asList("firstWord", "secondWord", "thirdWord"));

        trigramDataService.save(trigram);

        Trigram test = trigramDataService.get("firstKey", "secondKey");

        Assert.assertNotNull(test);
    }
}