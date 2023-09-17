package ru.sber.beautifulcode.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CheckBracketsRequest {

    String text;
}
