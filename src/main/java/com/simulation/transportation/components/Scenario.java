/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.components;

import com.simulation.transportation.units.Date;
import com.simulation.transportation.units.Length;
import com.simulation.transportation.units.Speed;
import com.simulation.transportation.units.Time;
import com.simulation.transportation.units.Weight;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 *
 * @author hakantek
 */
public class Scenario extends Map {

    public Scenario(String name, Date currentDate, int delay, Time movingTime) {
        super(name, currentDate);
        this.vehicles = new VehicleFleet();
        this.visualize = new SimulationVisualize(this, delay, movingTime);
        this.numberOfComplatedMoving = 0;
        this.simulationState = SimulationState.Continuing;
    }
    
    /* vehicles in the  scenario*/
    protected VehicleFleet vehicles;
    public Vehicle getVehicle(int index){
       return this.vehicles.get(index);
    }
    public int numberOfVehicles(){
        return this.vehicles.size();
    }
    private int numberOfComplatedMoving;
    protected void complateVehicleMoving(){
        this.numberOfComplatedMoving++;
    }
    
    /* visualize of scenario */
    private SimulationVisualize visualize;
    public void run(double leftSpace, double rightSpace, double topSpace, double bottomSpace,
            Length nodeR, Color nodeColor, Font nodeTextFont, Color nodeTextColor,
            Color roadColor, Color railColor, Color seaColor){
        this.visualize.run(leftSpace, rightSpace, topSpace, bottomSpace, nodeR, 
                nodeColor, nodeTextFont, nodeTextColor, roadColor, railColor, seaColor);
    }
    
    /* create a vehicle */
    public Vehicle vehicle(String name, Speed maxSpeed, Map.TransportationMode mode){
        this.vehicles.add(new Vehicle(this, name, maxSpeed, mode));
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    public Vehicle vehicle(String name, Speed maxSpeed, Map.TransportationMode mode, Date depatureDate, Link ...links){
        this.vehicles.add(new Vehicle(this, name, maxSpeed, mode){
            {
                assign(depatureDate, links);
            }
        });
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    public Vehicle vehicle(String name, Speed maxSpeed, Map.TransportationMode mode, Date depatureDate, ArrayList<Link> links){
        this.vehicles.add(new Vehicle(this, name, maxSpeed, mode){
            {
                assign(depatureDate, links);
            }
        });
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    
    /* create vehicle fleet */
    public VehicleFleet vehicleFleet(String name, Speed maxSpeed, Weight containerCapacity, Weight totalWeight){
        VehicleFleet vehicleFleet = new VehicleFleet(this, name, maxSpeed, containerCapacity, totalWeight);
        this.vehicles.addAll(vehicleFleet);
        return vehicleFleet;
    }
    public VehicleFleet vehicleFleet(String name, Speed maxSpeed, Weight containerCapacity, Weight totalWeight, Date depatureDate, Link ...links){
        VehicleFleet vehicleFleet = new VehicleFleet(this, name, maxSpeed, containerCapacity, totalWeight){
            {//Düzenlenecek
                assign(depatureDate,Time.Minute(30), links);
            }
        };
        this.vehicles.addAll(vehicleFleet);
        return vehicleFleet;
    }
    public VehicleFleet vehicleFleet(String name, Speed maxSpeed, Weight containerCapacity, Weight totalWeight, Date depatureDate, ArrayList<Link> links){
        VehicleFleet vehicleFleet = new VehicleFleet(this, name, maxSpeed, containerCapacity, totalWeight){
            {//Düzenlenecek
                assign(depatureDate, Time.Minute(30), links);
            }
        };
        this.vehicles.addAll(vehicleFleet);
        return vehicleFleet;
    }

    /* move vehicles */
    protected void move(Time time){
        if(this.numberOfComplatedMoving < this.vehicles.size())
            this.vehicles.stream().forEach((vehicle) -> {
                vehicle.assignment.move(time);
        });
        else {
            this.simulationState = SimulationState.Completed;
            this.visualize.stop();
        }
    }
    
    /* state of simulation */
    private SimulationState simulationState;
    public SimulationState getSimulationState() {
        return simulationState;
    }
    enum SimulationState{
        Continuing,
        Completed;
    }
}
