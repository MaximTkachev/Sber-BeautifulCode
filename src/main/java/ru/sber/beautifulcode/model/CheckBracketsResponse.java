package ru.sber.beautifulcode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckBracketsResponse {

    @JsonProperty("isCorrect")
    private boolean isCorrect;
}
