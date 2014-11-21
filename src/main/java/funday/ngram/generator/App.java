package funday.ngram.generator;

import funday.ngram.generator.configuration.ContextConfiguration;
import funday.ngram.generator.configuration.SparkConfiguration;
import java.util.Properties;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author adostic
 * @since 21.11.2014 10:06:38 AM
 */
public class App {
	public static void main(String[] args) {
		BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure();

		ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class, SparkConfiguration.class);
		Properties properties = (Properties) context.getBean("properties");
		System.out.println(properties);
	}
}
