package automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils  implements IDataSource {
	Properties properties = new Properties();

	public PropertiesUtils(String fileName) throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(fileName));
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getData() throws Exception {
		
		for (String key : properties.stringPropertyNames()) {
			PropertyDictionary.map.put(key, properties.getProperty(key));
		}
		return PropertyDictionary.map;
	}
}
