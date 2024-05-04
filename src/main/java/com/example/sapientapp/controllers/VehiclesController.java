package com.example.sapientapp.controllers;

import com.example.sapientapp.models.response.PersonResponse;
import com.example.sapientapp.models.response.PlanetsResponse;
import com.example.sapientapp.models.response.VehicleResponse;
import com.example.sapientapp.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/swapi3")
public class VehiclesController {

    @Autowired
    private SwapiService swapiService;

    @GetMapping("/vehicles")
    public VehicleResponse getAllVehicles() throws IOException {
        return (VehicleResponse) swapiService.fetchDataFromSwapiService("vehicles",null);
    }

    @GetMapping("/vehicles/search")
    public VehicleResponse searchVehicleByName(@RequestParam String name) throws IOException {
        String encodedName = URLEncoder.encode(name, "UTF-8");
        return (VehicleResponse) swapiService.fetchDataFromSwapiService("vehicles/?search=" + encodedName,encodedName);
    }
}
