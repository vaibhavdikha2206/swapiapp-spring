package com.example.sapientapp.models.response;

import com.example.sapientapp.models.SwapiFilm;
import com.example.sapientapp.models.SwapiPerson;

import java.util.List;

public class FilmsResponse {

    private List<SwapiFilm> results;

    public FilmsResponse(List<SwapiFilm> results) {
        this.results = results;
    }

    public List<SwapiFilm> getResults() {
        return results;
    }

    public void setResults(List<SwapiFilm> results) {
        this.results = results;
    }
}
