package funday.ngram.generator.util;

import java.io.File;
import java.net.URL;

/**
 *
 * @author adostic
 * @since 13.01.2014 22:37
 */
public class ResourceLoader {

	public static File getFile(String path) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL systemResource = loader.getResource(path);
		return systemResource == null ? null : new File(systemResource.getPath());
	}

	private ResourceLoader() {
	}
}
