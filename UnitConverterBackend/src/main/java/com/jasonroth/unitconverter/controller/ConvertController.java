package com.jasonroth.unitconverter.controller;

import com.jasonroth.unitconverter.model.ConversionRequest;
import com.jasonroth.unitconverter.model.ConversionResponse;
import com.jasonroth.unitconverter.service.conversion.ConversionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/convert")
public class ConvertController {
    private final ConversionService conversionService;

    @PostMapping()
    public ResponseEntity<ConversionResponse> convert(@RequestBody ConversionRequest conversionRequest) {
        double convertedValue = conversionService.convert(conversionRequest);
        ConversionResponse conversionResponse = ConversionResponse.builder()
                .originalValue(conversionRequest.getValue())
                .convertedValue(convertedValue)
                .from(conversionRequest.getFrom())
                .to(conversionRequest.getTo())
                .build();

        return ResponseEntity.ok(conversionResponse);
    }
}
