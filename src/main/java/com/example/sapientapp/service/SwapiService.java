package com.example.sapientapp.service;

import com.example.sapientapp.models.SwapiFilm;
import com.example.sapientapp.models.SwapiPerson;
import com.example.sapientapp.models.SwapiPlanet;
import com.example.sapientapp.models.SwapiVehicle;
import com.example.sapientapp.models.response.FilmsResponse;
import com.example.sapientapp.models.response.PersonResponse;
import com.example.sapientapp.models.response.PlanetsResponse;
import com.example.sapientapp.models.response.VehicleResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SwapiService {

    private String swapiBaseUrl = "https://swapi.dev/api/";

    private final RestTemplate restTemplate;

    public SwapiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private CacheManager cacheManager;


    @Cacheable(value = "PersonResponse", unless = "#result != null")
    public <T> Object fetchDataFromSwapiService(String endpoint,String search) throws IOException {
        System.out.println("back end fetch "+endpoint);
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(swapiBaseUrl + endpoint);
            HttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse).get("results");
            if (root != null && root.isArray()) {
                if(endpoint.contains("people")) {
                    List<SwapiPerson> people = new ArrayList<>();
                    for (JsonNode personNode : root) {
                        SwapiPerson person = new SwapiPerson();
                        person.setName(personNode.get("name").asText());
                        person.setHeight(personNode.get("height").asText());
                        person.setMass(personNode.get("mass").asText());
                        person.setHairColor(personNode.get("hair_color").asText());
                        person.setGender(personNode.get("gender").asText());
                        person.setFilms(getFieldValues(personNode, "films"));
                        person.setVehicles(getFieldValues(personNode, "vehicles"));
                        person.setStarships(getFieldValues(personNode, "starships"));
                        people.add(person);
                    }
                    return new PersonResponse(people);
                }

                else if (endpoint.contains("planets")){
                    List<SwapiPlanet> planets = new ArrayList<>();
                    for (JsonNode planetNode : root) {
                        SwapiPlanet planet = new SwapiPlanet();
                        planet.setName(planetNode.get("name").asText());
                        planet.setClimate(planetNode.get("climate").asText());
                        planet.setDiameter(planetNode.get("diameter").asText());
                        planet.setPopulation(planetNode.get("population").asText());
                        planets.add(planet);
                    }
                    return new PlanetsResponse(planets);
                }

                else if (endpoint.contains("films")){
                    List<SwapiFilm> films = new ArrayList<>();
                    for (JsonNode filmNode : root) {
                        SwapiFilm film = new SwapiFilm();
                        film.setTitle(filmNode.get("title").asText());
                        film.setOpeningCrawl(filmNode.get("opening_crawl").asText());
                        films.add(film);
                    }
                    return new FilmsResponse(films);
                }

                else if (endpoint.contains("vehicles")){
                    List<SwapiVehicle> vehicles = new ArrayList<>();
                    for (JsonNode filmNode : root) {
                        SwapiVehicle vehicle = new SwapiVehicle();
                        vehicle.setName(filmNode.get("name").asText());
                        vehicle.setManufacturer(filmNode.get("model").asText());
                        vehicle.setModel(filmNode.get("manufacturer").asText());
                        vehicle.setCost_in_credits(filmNode.get("cost_in_credits").asText());
                        vehicles.add(vehicle);
                    }
                    return new VehicleResponse(vehicles);
                }

            }
        } catch (IOException e) {
            //throw new IOException();
            Cache cache = cacheManager.getCache("PersonResponse");
            PersonResponse response = (PersonResponse) ((search==null)?(cache.get("PersonResponse",PersonResponse.class)):(cache.get(search,PersonResponse.class)) );
            return response;
        }

        return null;
    }


    private List<String> getFieldValues(JsonNode node, String fieldName) {
        List<String> values = new ArrayList<>();
        JsonNode fieldNode = node.get(fieldName);
        if (fieldNode != null && fieldNode.isArray()) {
            for (JsonNode valueNode : fieldNode) {
                values.add(valueNode.asText());
            }
        }
        return values;
    }

}
