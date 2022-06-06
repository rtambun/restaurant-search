package com.rtambun.restaurantsearch.repository;

import com.rtambun.restaurantsearch.model.RestaurantData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDataRepository extends ElasticsearchRepository<String, RestaurantData> {
}
