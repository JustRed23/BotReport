package dev.JustRed23.botreport.config;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

public class CaseConverter extends PropertyUtils {

    public Property getProperty(Class<?> type, String name) {
        return super.getProperty(type, convertToCamelCase(name));
    }

    private String convertToCamelCase(String hyphenated) {
        String[] parts = hyphenated.split("-");
        if (parts.length == 1) return parts[0];

        StringBuilder camelCaseString = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1));
        }
        return camelCaseString.toString();
    }
}
