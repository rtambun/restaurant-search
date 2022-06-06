package com.rtambun.restaurantsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "restaurant_data")
public class RestaurantData {
    @Id
    public String id;

    @Field(type = FieldType.Text)
    public String restaurantId;

    @Field(type = FieldType.Text)
    public String searchText;

    @Field(type = FieldType.Double)
    public double[] location;
}
