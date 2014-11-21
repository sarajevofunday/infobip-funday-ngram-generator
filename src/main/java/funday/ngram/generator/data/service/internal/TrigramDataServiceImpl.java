package funday.ngram.generator.data.service.internal;

import funday.ngram.generator.data.model.Trigram;
import funday.ngram.generator.data.service.TrigramDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Random;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by mobradovic on 11/21/14.
 */
public class TrigramDataServiceImpl implements TrigramDataService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TrigramDataServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Trigram getRandom() {
        long time = System.currentTimeMillis();
        Random random = new Random();

//        long count = mongoTemplate.count(new Query(),Trigram.class);
        long count = 500;
        int skip = random.nextInt((int) count);

        Trigram trigram = mongoTemplate.find(new Query().limit(-1).skip(skip), Trigram.class).get(0);

        System.out.println((System.currentTimeMillis() - time));
        return trigram;
    }

    @Override
    public Trigram get(String firstKey, String secondKey) {
        long time = System.currentTimeMillis();
        Trigram trigram = mongoTemplate.findOne(query(where("firstKey").is(firstKey).and("secondKey").is(secondKey)), Trigram.class);
        System.out.println((System.currentTimeMillis() - time));
        return trigram;
    }

    @Override
    public void save(Trigram trigram) {
        mongoTemplate.save(trigram);
    }

    @Override
    public void deleteAll() {
        mongoTemplate.dropCollection(Trigram.class);
    }
}
