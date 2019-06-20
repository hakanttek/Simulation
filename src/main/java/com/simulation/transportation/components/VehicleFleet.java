/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.components;

import com.simulation.transportation.units.Date;
import com.simulation.transportation.units.Speed;
import com.simulation.transportation.units.Time;
import com.simulation.transportation.units.Weight;
import java.util.ArrayList;

/**
 *
 * @author hakantek
 */
public class VehicleFleet extends ArrayList<Vehicle>{

    protected VehicleFleet() {
        super();
    }
    protected VehicleFleet(Scenario scenario, String name, Speed maxSpeed, Weight containerCapacity, Weight totalWeight){
        for(int i=0; i<totalWeight.division(containerCapacity); i++){
            this.add(new Vehicle(scenario, name, maxSpeed, Map.TransportationMode.Road));
        }
    }
    
    /* assign routemaps of fleet */
    public void assign(Date depatureTime, Time departureTimeDifference, Map.Link ...links){
        this.stream().forEach((vehicle) -> {
            vehicle.assign(depatureTime, links);
            depatureTime.spendTime(departureTimeDifference);
        });
    }
    public void assign(Date depatureTime, Time departureTimeDifference, ArrayList<Map.Link> links){
        this.stream().forEach((vehicle) -> {
            vehicle.assign(depatureTime, links);
            depatureTime.spendTime(departureTimeDifference);
        });
    }
}
