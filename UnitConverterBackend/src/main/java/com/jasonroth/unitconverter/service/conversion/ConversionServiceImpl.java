package com.jasonroth.unitconverter.service.conversion;

import com.jasonroth.unitconverter.model.ConversionRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConversionServiceImpl implements ConversionService {
    @Override
    public double convert(ConversionRequest conversionRequest) {
        conversionRequest.setType(conversionRequest.getType().toLowerCase());
        switch(conversionRequest.getType()){
            case "length":
                return convertLength(conversionRequest);
            case "weight":
                return convertWeight(conversionRequest);
            case "temperature":
                return convertTemperature(conversionRequest);
            default:
                throw new IllegalArgumentException("Invalid conversion type: " + conversionRequest.getType());
        }
    }

    @Override
    public double convertLength(ConversionRequest conversionRequest) {
        Map<String, Double> lengthMap = Map.of(
                "millimeter", 0.001, "centimeter", 0.01, "meter", 1.0, "kilometer", 1000.0,
                "inch", 0.0254, "foot", 0.3048, "yard", 0.9144, "mile", 1609.34
        );
        if(!lengthMap.containsKey(conversionRequest.getFrom()) || !lengthMap.containsKey(conversionRequest.getTo())){
            throw new IllegalArgumentException("Invalid conversion length: " + conversionRequest.getFrom() + " " + conversionRequest.getTo());
        }
        double value = conversionRequest.getValue();
        return (value * lengthMap.get(conversionRequest.getFrom())) / lengthMap.get(conversionRequest.getTo());
    }

    @Override
    public double convertWeight(ConversionRequest conversionRequest) {
        Map<String, Double> weightMap = Map.of(
                "milligram", 0.001, "gram", 1.0, "kilogram", 1000.0,
                "ounce", 28.3495, "pound", 453.592
        );

        if(!weightMap.containsKey(conversionRequest.getFrom()) || !weightMap.containsKey(conversionRequest.getTo())){
            throw new IllegalArgumentException("Invalid conversion weight: " + conversionRequest.getFrom() + " " + conversionRequest.getTo());
        }

        double value = conversionRequest.getValue();
        return (value * weightMap.get(conversionRequest.getFrom())) / weightMap.get(conversionRequest.getTo());
    }

    @Override
    public double convertTemperature(ConversionRequest conversionRequest) {
        if (conversionRequest.getFrom().equalsIgnoreCase(conversionRequest.getTo())) {
            return conversionRequest.getValue(); // No conversion needed if both units are the same
        }
        double value = conversionRequest.getValue();

        switch (conversionRequest.getFrom().toLowerCase()) {
            case "celsius":
                if (conversionRequest.getTo().equalsIgnoreCase("fahrenheit")) return (value * 9 / 5) + 32;
                if (conversionRequest.getTo().equalsIgnoreCase("kelvin")) return value + 273.15;
                break;
            case "fahrenheit":
                if (conversionRequest.getTo().equalsIgnoreCase("celsius")) return (value - 32) * 5 / 9;
                if (conversionRequest.getTo().equalsIgnoreCase("kelvin")) return (value - 32) * 5 / 9 + 273.15;
                break;
            case "kelvin":
                if (conversionRequest.getTo().equalsIgnoreCase("celsius")) return value - 273.15;
                if (conversionRequest.getTo().equalsIgnoreCase("fahrenheit")) return (value - 273.15) * 9 / 5 + 32;
                break;
        }

        throw new IllegalArgumentException("Invalid temperature conversion");
    }
}
