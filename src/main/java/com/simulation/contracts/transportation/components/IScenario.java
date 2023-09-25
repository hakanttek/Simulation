package com.simulation.contracts.transportation.components;

import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;
import com.simulation.contracts.transportation.units.IWeight;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

public interface IScenario extends IMap {

    void complateVehicleMoving();

    int numberOfVehicles();

    IVehicle vehicle(String name, ISpeed maxSpeed, IMap.TransportationMode mode, IDateUnit depatureDate, ILink... links);

    IVehicleFleet vehicleFleet(String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight, IDateUnit depatureDate, ILink... links);

    IVehicleFleet vehicleFleet(String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight);
	
    IVehicleFleet vehicleFleet(String name, ISpeed maxSpeed, IWeight containerCapacity, IWeight totalWeight, IDateUnit depatureDate, List<ILink> links);
	
    IVehicle vehicle(String name, ISpeed maxSpeed, TransportationMode mode, IDateUnit depatureDate, List<ILink> links);
    
    void run(double leftSpace, double rightSpace, double topSpace, double bottomSpace, ILength nodeR, Color nodeColor, Font nodeTextFont, Color nodeTextColor, Color roadColor, Color railColor, Color seaColor);

    IVehicle getVehicle(int index);

    IVehicle vehicle(String name, ISpeed maxSpeed, IMap.TransportationMode mode);

    SimulationState getSimulationState();

    enum SimulationState {
        Continuing,
        Completed
    }

	IVehicleFleet getVehicles();

	void move(ITime time);


}
