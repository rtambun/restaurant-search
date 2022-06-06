package com.rtambun.integration;

import com.rtambun.integration.container.RestaurantDataRepositoryContainer;
import com.rtambun.restaurantsearch.RestaurantSearchApplication;
import com.rtambun.restaurantsearch.model.RestaurantData;
import com.rtambun.restaurantsearch.repository.RestaurantDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = RestaurantSearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {
        RestaurantDataRepositoryContainer.Initializer.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableAutoConfiguration
@Slf4j
public class RestaurantDataRepositoryIntegrationTest {

    @BeforeAll
    private static void setUp() {
        RestaurantDataRepositoryContainer.startRestaurantDataRepositoryContainer();
    }

    @AfterAll
    private static void tearDown() {
        RestaurantDataRepositoryContainer.stopRestaurantDataRepositoryContainer();
    }

    @Autowired
    private RestaurantDataRepository restaurantDataRepository;


    @Test
    public void saveRestaurantData() {
        RestaurantData restaurantData = new RestaurantData(null, "restaurantId", "searchText", new double[0]);

        RestaurantData restaurantDataSaved = restaurantDataRepository.save(restaurantData);
        assertThat(restaurantDataSaved.getRestaurantId()).isNotNull();
        assertThat(restaurantData.getRestaurantId()).isEqualTo("restaurantId");
        assertThat(restaurantData.getSearchText()).isEqualTo("searchText");
        assertThat(restaurantData.getLocation().length).isEqualTo(0);

        List<RestaurantData> all = StreamSupport
                .stream(restaurantDataRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertThat(all.size()).isEqualTo(1);

        assertThat(all.get(0)).usingRecursiveComparison().isEqualTo(restaurantDataSaved);
    }
}
