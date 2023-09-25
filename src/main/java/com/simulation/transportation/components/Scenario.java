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
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;
import com.simulation.contracts.transportation.units.IWeight;
import com.simulation.transportation.units.Time;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

/**
 *
 * @author hakantek
 */
public class Scenario extends Map implements IScenario {

    public Scenario(String name, IDateUnit currentDate, int delay, ITime movingTime) {
        super(name, currentDate);
        this.vehicles = new VehicleFleet();
        this.visualize = new SimulationVisualize(this, delay, movingTime);
        this.numberOfComplatedMoving = 0;
        this.simulationState = SimulationState.Continuing;
    }
    
    /* vehicles in the  scenario*/
    private IVehicleFleet vehicles;
    @Override
    public IVehicleFleet getVehicles() {
		return vehicles;
	}
    @Override
    public IVehicle getVehicle(int index){
       return this.vehicles.get(index);
    }
    @Override
    public int numberOfVehicles(){
        return this.vehicles.size();
    }
    private int numberOfComplatedMoving;
    @Override
	public void complateVehicleMoving(){
        this.numberOfComplatedMoving++;
    }
    
    /* visualize of scenario */
    private SimulationVisualize visualize;
    @Override
    public void run(double leftSpace, double rightSpace, double topSpace, double bottomSpace,
            ILength nodeR, Color nodeColor, Font nodeTextFont, Color nodeTextColor,
            Color roadColor, Color railColor, Color seaColor){
        this.visualize.run(leftSpace, rightSpace, topSpace, bottomSpace, nodeR, 
                nodeColor, nodeTextFont, nodeTextColor, roadColor, railColor, seaColor);
    }
    
    /* create a vehicle */
    @Override
    public IVehicle vehicle(String name, ISpeed maxSpeed, TransportationMode mode){
        this.vehicles.add(new Vehicle(this, name, maxSpeed, mode));
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    @Override
    public IVehicle vehicle(String name, ISpeed maxSpeed, TransportationMode mode, IDateUnit depatureDate, ILink ...links){
        this.vehicles.add(new Vehicle(this, name, maxSpeed, mode){
            {
                assign(depatureDate, links);
            }
        });
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    @Override
    public IVehicle vehicle(String name, ISpeed maxSpeed, IMap.TransportationMode mode, IDateUnit depatureDate, List<ILink> links){
        this.vehicles.add(new Vehicle(this, name, maxSpeed, mode){
            {
                assign(depatureDate, links);
            }
        });
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    
    /* create vehicle fleet */
    @Override
    public IVehicleFleet vehicleFleet(String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight){
        IVehicleFleet vehicleFleet = new VehicleFleet(this, name, maxSpeed, containerCapacity, totalWeight);
        this.vehicles.addAll(vehicleFleet);
        return vehicleFleet;
    }
    public IVehicleFleet vehicleFleet(String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight, IDateUnit depatureDate, ILink ...links){
        VehicleFleet vehicleFleet = new VehicleFleet(this, name, maxSpeed, containerCapacity, totalWeight){

			private static final long serialVersionUID = 1L;

			{// TODO arrange
                assign(depatureDate,Time.Minute(30), links);
            }
        };
        this.vehicles.addAll(vehicleFleet);
        return vehicleFleet;
    }
    @Override
    public IVehicleFleet vehicleFleet(String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight, IDateUnit depatureDate, List<ILink> links){
        VehicleFleet vehicleFleet = new VehicleFleet(this, name, maxSpeed, containerCapacity, totalWeight){

			private static final long serialVersionUID = 1L;

			{// TODO arrange
                assign(depatureDate, Time.Minute(30), links);
            }
        };
        this.vehicles.addAll(vehicleFleet);
        return vehicleFleet;
    }

    /* move vehicles */
    @Override
    public void move(ITime time){
        if(this.numberOfComplatedMoving < this.vehicles.size())
            this.vehicles.stream().forEach((vehicle) -> {
                vehicle.getAssignment().move(time);
        });
        else {
            this.simulationState = SimulationState.Completed;
            this.visualize.stop();
        }
    }
    
    /* state of simulation */
    private SimulationState simulationState;
    @Override
    public SimulationState getSimulationState() {
        return simulationState;
    }
}
