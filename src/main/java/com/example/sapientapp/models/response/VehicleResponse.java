package com.example.sapientapp.models.response;

import com.example.sapientapp.models.SwapiVehicle;

import java.util.List;

public class VehicleResponse {

    private List<SwapiVehicle> results;

    public VehicleResponse(List<SwapiVehicle> results) {
        this.results = results;
    }

    public List<SwapiVehicle> getResults() {
        return results;
    }

    public void setResults(List<SwapiVehicle> results) {
        this.results = results;
    }

}
