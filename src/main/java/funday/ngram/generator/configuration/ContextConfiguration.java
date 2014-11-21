package funday.ngram.generator.configuration;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 * Created by mobradovic on 11/21/14.
 */
@Configuration
public class ContextConfiguration {

    public static final String HOST = "localhost";
    public static final int PORT = 12345;
    public static final String DATABASE_NAME = "dbName";

    @Bean
    public MongoDbFactory getMongo() {
        try {
            return new SimpleMongoDbFactory(new Mongo(HOST, PORT), DATABASE_NAME);
        } catch (UnknownHostException e) {
            return null; //edit
        }
    }

    @Bean
    public MongoTemplate getMongoTemplate() {
       return new MongoTemplate(getMongo());
    }
}
