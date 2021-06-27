package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlUtils {

    private static final Map<String, String> CONFIG;

    static {
        InputStream inputStream = YamlUtils.class.getClassLoader().getResourceAsStream("config.yaml");
        Yaml yaml = new Yaml();
        CONFIG = yaml.load(inputStream);
    }

    public String getProperty(String propertyName) {
        return CONFIG.get(propertyName);
    }

}