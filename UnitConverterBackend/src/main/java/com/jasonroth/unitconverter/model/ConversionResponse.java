package com.jasonroth.unitconverter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ConversionResponse {
    private double originalValue;
    private double convertedValue;
    private String from;
    private String to;
}
