/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.components;

import com.simulation.contracts.transportation.components.IMap;
import com.simulation.contracts.transportation.components.IScenario;
import com.simulation.contracts.transportation.components.IVehicle;
import com.simulation.contracts.transportation.components.IVehicleFleet;
import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;
import com.simulation.contracts.transportation.units.IWeight;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hakantek
 */
public class VehicleFleet extends ArrayList<IVehicle> implements IVehicleFleet{

	private static final long serialVersionUID = 1L;

	protected VehicleFleet() {
        super();
    }
	
    protected VehicleFleet(IScenario scenario, String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight){
        for(int i=0; i<totalWeight.division(containerCapacity); i++){
            this.add(new Vehicle(scenario, name, maxSpeed, Map.TransportationMode.Road));
        }
    }
    
    /* assign route maps of fleet */
    @Override
    public void assign(IDateUnit depatureTime, ITime departureTimeDifference, IMap.ILink ...links){
        this.stream().forEach((vehicle) -> {
            vehicle.assign(depatureTime, links);
            depatureTime.spendTime(departureTimeDifference);
        });
    }
    @Override
    public void assign(IDateUnit depatureTime, ITime departureTimeDifference, List<IMap.ILink> links){
        this.stream().forEach((vehicle) -> {
            vehicle.assign(depatureTime, links);
            depatureTime.spendTime(departureTimeDifference);
        });
    }
}
