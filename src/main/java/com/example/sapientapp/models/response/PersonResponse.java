package com.example.sapientapp.models.response;

import com.example.sapientapp.models.SwapiPerson;

import java.util.List;

public class PersonResponse {

    private List<SwapiPerson> results;

    public PersonResponse(List<SwapiPerson> results) {
        this.results = results;
    }

    public List<SwapiPerson> getResults() {
        return results;
    }

    public void setResults(List<SwapiPerson> results) {
        this.results = results;
    }
}
