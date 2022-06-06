package com.rtambun.restaurantsearch.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantDataTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void noArgsConstructor() {
        RestaurantData restaurantData = new RestaurantData();

        assertThat(restaurantData.getId()).isNull();
        assertThat(restaurantData.getRestaurantId()).isNull();
        assertThat(restaurantData.getSearchText()).isNull();
        assertThat(restaurantData.getLocation()).isNull();

        restaurantData.setId("id");
        restaurantData.setRestaurantId("restaurantId");
        restaurantData.setSearchText("searchText");
        restaurantData.setLocation(new double[0]);

        assertThat(restaurantData.getId()).isEqualTo("id");
        assertThat(restaurantData.getRestaurantId()).isEqualTo("restaurantId");
        assertThat(restaurantData.getSearchText()).isEqualTo("searchText");
        assertThat(restaurantData.getLocation().length).isEqualTo(0);
    }

    @Test
    public void allArgsConstructor() {
        RestaurantData restaurantData = new RestaurantData("id", "restaurantId", "searchText", new double[0]);

        assertThat(restaurantData.getId()).isEqualTo("id");
        assertThat(restaurantData.getRestaurantId()).isEqualTo("restaurantId");
        assertThat(restaurantData.getSearchText()).isEqualTo("searchText");
        assertThat(restaurantData.getLocation().length).isEqualTo(0);
    }

}