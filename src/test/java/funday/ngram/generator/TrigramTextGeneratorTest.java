package funday.ngram.generator;

import funday.ngram.generator.configuration.ContextConfiguration;
import funday.ngram.generator.data.model.Trigram;
import funday.ngram.generator.data.service.TrigramDataService;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrigramTextGeneratorTest {
    NgramTextGenerator ngramTextGenerator;
    @Before
    public void setup() {
        System.setProperty("log4j.config", "/home/mobradovic/IdeaProjects/funday-ngram-generator/target/classes/log4j.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        ngramTextGenerator = context.getBean(NgramTextGenerator.class);
    }

    @Test
    public void testGenerate() throws Exception {
        String text = ngramTextGenerator.generate(100);

        System.out.println(text);

        String[] words = text.split(" ");

//        Ass/**/ert.assertTrue(words.length > 17 && words.length < 23);
    }
}