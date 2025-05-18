package com.example.coffe.dto;

import java.util.Map;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @SerializedName("drink_name")
    private String drinkName;
    private Map<String, Double> prices;

}