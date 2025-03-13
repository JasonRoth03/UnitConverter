package com.jasonroth.unitconverter.service.conversion;

import com.jasonroth.unitconverter.model.ConversionRequest;

public interface ConversionService {

    double convert(ConversionRequest conversionRequest);

    double convertLength(ConversionRequest conversionRequest);

    double convertWeight(ConversionRequest conversionRequest);

    double convertTemperature(ConversionRequest conversionRequest);

}
