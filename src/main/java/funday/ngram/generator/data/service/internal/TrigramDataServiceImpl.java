package funday.ngram.generator.data.service.internal;

import funday.ngram.generator.data.model.Trigram;
import funday.ngram.generator.data.service.TrigramDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

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
        return null;
    }

    @Override
    public Trigram get(String firstKey, String secondKey) {
        mongoTemplate.findOne(query(where("firstKey").is(firstKey).and("secondKey").is(secondKey)), Trigram.class);
        return null;
    }

    @Override
    public void save(Trigram trigram) {
        mongoTemplate.save(trigram);
    }
}
