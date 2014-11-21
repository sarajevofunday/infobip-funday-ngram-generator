package funday.ngram.generator.configuration;

import funday.ngram.generator.freemarker.FreeMarkerEngine;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spark.ModelAndView;
import static spark.Spark.get;

/**
 *
 * @author adostic
 * @since 21.11.2014 12:21:28 PM
 */
@Configuration
public class SparkConfiguration {

	@Bean
	public ApiService apiService() throws IOException {
		ApiService apiService = new ApiService(freeMarkerEngine());
		apiService.init();
		return apiService;
	}

	@Bean
	public FreeMarkerEngine freeMarkerEngine() throws IOException {
		return new FreeMarkerEngine();
	}

}

class ApiService {
	private final FreeMarkerEngine freeMarkerEngine;

	ApiService(FreeMarkerEngine freeMarkerEngine) {
		this.freeMarkerEngine = freeMarkerEngine;
	}

	public void init() throws IOException {		
		get("/", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>(1);
			attributes.put("message", getText());

			// The hello.ftl file is located in directory:
			// src/test/resources/spark/template/freemarker
			return new ModelAndView(attributes, "index.ftl");

		}, freeMarkerEngine);
	}

	private String getText() {
		return "Generated Text";
	}
}
