package com.crossover.imagesearch.properties;

import java.util.Properties;

/**
 * @author customfurnish.com
 */
public final class PropertyManager {
    private static Properties properties = null;

    /**
     * Its a private constructor.
     */
    private PropertyManager() {

    }

    private static String getProperty(final String name) {
        try {
            if (properties == null) {
                properties = new PropertyLoader().getProperties();
            }
            if (properties.containsKey(name)) {
                return properties.getProperty(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getElasticSearchUser() {
        return getProperty("aws.elasticsearch.username");
    }

    public static String getElasticSearchPassword() {
        return getProperty("aws.elasticsearch.password");
    }

    public static String getElasticSearchPath() {
        return getProperty("aws.elasticsearch.path");
    }

    public static String getElasticSearchIndex() {
        return getProperty("aws.elasticsearch.index");
    }
}
