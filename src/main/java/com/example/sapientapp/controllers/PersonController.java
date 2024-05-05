package com.example.sapientapp.controllers;

import com.example.sapientapp.models.response.PersonResponse;
import com.example.sapientapp.service.CacheService;
import com.example.sapientapp.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/swapi3")
public class PersonController {

    @Autowired
    private SwapiService swapiService;

    @Autowired
    private CacheService cacheManager;

    @GetMapping("/people")
    @Cacheable(value = "PersonResponse", unless = "#result != null")
    public PersonResponse getAllPeople() throws IOException {
        PersonResponse result = (PersonResponse) swapiService.fetchDataFromSwapiService("people",null);
        cacheManager.addToCache("PersonResponse","PersonResponse",result);
        return result;
    }

    @GetMapping("/people/search")
    @Cacheable(value = "PersonResponse", unless = "#result != null")
    public PersonResponse searchPeopleByName(@RequestParam String name) throws IOException {
        String encodedName = URLEncoder.encode(name, "UTF-8");
        PersonResponse result = (PersonResponse) swapiService.fetchDataFromSwapiService("people/?search=" + encodedName,encodedName);
        cacheManager.addToCache("PersonResponse",name,result);
        return result;
    }
}
