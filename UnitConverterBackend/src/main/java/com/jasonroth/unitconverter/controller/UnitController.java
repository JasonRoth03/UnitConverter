package com.jasonroth.unitconverter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/units")
@CrossOrigin(origins = "http://localhost:5173")
public class UnitController {

    @GetMapping("/length")
    public ResponseEntity<List<String>> getLengthUnits(){
        List<String> lengthUnits = Arrays.asList("millimeter", "centimeter", "meter", "kilometer", "inch", "foot", "yard", "mile");
        return ResponseEntity.ok(lengthUnits);
    }

    @GetMapping("/weight")
    public ResponseEntity<List<String>> getWeightUnits(){
        List<String> weightUnits = Arrays.asList("milligram", "gram", "kilogram", "ounce", "pound");
        return ResponseEntity.ok(weightUnits);
    }

    @GetMapping("/temperature")
    public ResponseEntity<List<String>> getTemperatureUnits(){
        List<String> temperatureUnits = Arrays.asList("celsius", "fahrenheit", "kelvin");
        return ResponseEntity.ok(temperatureUnits);
    }

}
