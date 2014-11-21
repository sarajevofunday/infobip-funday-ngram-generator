package funday.ngram.generator.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import funday.ngram.generator.util.ResourceLoader;
import java.io.IOException;
import java.io.StringWriter;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 *
 * @author adostic
 * @since 21.11.2014 2:15:19 PM
 */
public class FreeMarkerEngine extends TemplateEngine {

    /** The FreeMarker configuration */
    private Configuration configuration;

    /**
     * Creates a FreeMarkerEngine
     */
    public FreeMarkerEngine() throws IOException {
        this.configuration = createDefaultConfiguration();
    }

    /**
     * Creates a FreeMarkerEngine with a configuration
     *
     * @param configuration The Freemarker configuration
     */
    public FreeMarkerEngine(Configuration configuration) {
        this.configuration = configuration;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        try {
            StringWriter stringWriter = new StringWriter();

            Template template = configuration.getTemplate(modelAndView.getViewName());
            template.process(modelAndView.getModel(), stringWriter);

            return stringWriter.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Sets FreeMarker configuration.
     * Note: If configuration is not set the default configuration
     * will be used.
     *
     * @param configuration the configuration to set
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private Configuration createDefaultConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(ResourceLoader.getFile("template"));
        return configuration;
    }

}
