package funday.ngram.generator.configuration;

import com.mongodb.Mongo;
import funday.ngram.generator.data.service.TrigramDataService;
import funday.ngram.generator.data.service.internal.TrigramDataServiceImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by mobradovic on 11/21/14.
 */
@Configuration
public class ContextConfiguration {

	@Bean
	public Properties properties() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		ClassLoader classLoader = this.getClass().getClassLoader();
		System.out.println(classLoader);
		InputStream stream = classLoader.getResourceAsStream("app.properties");
		properties.load(stream);
		return properties;
	}

	@Bean
	public MongoDbFactory mongo() {
		try {
			Properties properties = properties();
			String host = properties.getProperty("mongodb.host");
			int port = Integer.parseInt(properties.getProperty("mongodb.port"));
			String dbName = properties.getProperty("mongodb.database");
			return new SimpleMongoDbFactory(new Mongo(host, port), dbName);
		} catch (UnknownHostException e) {
			return null; //edit
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; //edit
		} catch (IOException e) {
			e.printStackTrace();
			return null; //edit
		}
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongo());
	}

	@Bean
	public TrigramDataService trigramDataService() {
		return new TrigramDataServiceImpl(mongoTemplate());
	}
}
