package com.simulation.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "scenario")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time_frame")
    private int timeFrame;

    @Column(name = "transportation_mode")
    private int transportationMode;

    @Column(name = "num_vehicles")
    private int numberOfVehicles;

    @Column(name = "road_map")
    private int roadMap; // Assuming this is a reference to a Link entity, you can use the Link ID

    // Constructors

    public Vehicle() {
        // Default constructor
    }

    public Vehicle(int timeFrame, int transportationMode, int numberOfVehicles, int roadMap) {
        this.timeFrame = timeFrame;
        this.transportationMode = transportationMode;
        this.numberOfVehicles = numberOfVehicles;
        this.roadMap = roadMap;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public int getTransportationMode() {
        return transportationMode;
    }

    public void setTransportationMode(int transportationMode) {
        this.transportationMode = transportationMode;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public int getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(int roadMap) {
        this.roadMap = roadMap;
    }
}