package com.rtambun.integration.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

public class RestaurantDataRepositoryContainer extends ElasticsearchContainer {

    //see https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/ for reference of elastic search
    //version for your spring boot
    private static final String ELASTICSEARCH_VERSION = "7.15.2";
    private static final String ELASTICSEARCH_REGISTRY  = "docker.elastic.co/elasticsearch/elasticsearch";

    public static final String ELASTICSEARCH_HOST = "localhost";
    public static final String ELASTICSEARCH_USERNAME = "elastic";
    public static final String ELASTICSEARCH_PASSWORD = "password";

    private static RestaurantDataRepositoryContainer restaurantDataRepositoryContainer;

    public static void startRestaurantDataRepositoryContainer() {
        if (restaurantDataRepositoryContainer == null) {
            restaurantDataRepositoryContainer = new RestaurantDataRepositoryContainer();
            restaurantDataRepositoryContainer.start();
        }
    }

    public static void stopRestaurantDataRepositoryContainer() {
        restaurantDataRepositoryContainer.stop();
        restaurantDataRepositoryContainer = null;
    }

    private RestaurantDataRepositoryContainer() {
        super(DockerImageName.parse(ELASTICSEARCH_REGISTRY).withTag(ELASTICSEARCH_VERSION));
        withPassword(ELASTICSEARCH_PASSWORD);
    }

    public static class Initializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {


        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            String elasticSearchPort = "elasticsearch.port=" +
                    restaurantDataRepositoryContainer.getMappedPort(9200);
            String elasticSearchHost = "elasticsearch.host=" + ELASTICSEARCH_HOST;
            String elasticSearchUserName = "elasticsearch.username=" + ELASTICSEARCH_USERNAME;
            String elasticSearchPassword = "elasticsearch.password=" + ELASTICSEARCH_PASSWORD;

            TestPropertyValues.of(elasticSearchPort).applyTo(applicationContext.getEnvironment());
            TestPropertyValues.of(elasticSearchHost).applyTo(applicationContext.getEnvironment());
            TestPropertyValues.of(elasticSearchUserName).applyTo(applicationContext.getEnvironment());
            TestPropertyValues.of(elasticSearchPassword).applyTo(applicationContext.getEnvironment());
        }
    }

}