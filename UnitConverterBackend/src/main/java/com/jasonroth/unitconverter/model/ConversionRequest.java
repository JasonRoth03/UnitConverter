package com.jasonroth.unitconverter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversionRequest {
    private String type;
    private double value;
    private String from;
    private String to;
}
