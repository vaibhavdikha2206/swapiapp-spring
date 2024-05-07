package com.example.sapientapp.controllers;

import com.example.sapientapp.models.response.FilmsResponse;
import com.example.sapientapp.models.response.PlanetsResponse;
import com.example.sapientapp.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/swapi3")
public class FilmsController {

    @Autowired
    private SwapiService swapiService;

    @GetMapping("/films")
    public FilmsResponse getAllFilms() throws IOException {
        return (FilmsResponse) swapiService.fetchDataFromSwapiService("films",null);
    }

    @GetMapping("/test")
    public String getString() throws IOException {
        return "TestYo22";
    }

    @GetMapping("/films/search")
    public FilmsResponse searchFilmByName(@RequestParam String name) throws IOException {
        String encodedName = URLEncoder.encode(name, "UTF-8");
        return (FilmsResponse) swapiService.fetchDataFromSwapiService("films/?search=" + encodedName,encodedName);
    }
}
