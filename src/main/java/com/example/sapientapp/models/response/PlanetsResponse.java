package com.example.sapientapp.models.response;

import com.example.sapientapp.models.SwapiPerson;
import com.example.sapientapp.models.SwapiPlanet;

import java.util.List;

public class PlanetsResponse {

    private List<SwapiPlanet> results;

    public PlanetsResponse(List<SwapiPlanet> results) {
        this.results = results;
    }

    public List<SwapiPlanet> getResults() {
        return results;
    }

    public void setResults(List<SwapiPlanet> results) {
        this.results = results;
    }
}
