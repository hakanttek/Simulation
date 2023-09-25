package com.simulation.contracts.transportation.components;

import com.simulation.contracts.transportation.components.IMap.ILink;
import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;
import com.simulation.contracts.transportation.units.IWeight;
import java.util.ArrayList;
import java.util.List;

public interface IVehicleFleet extends List<IVehicle> {

    void assign(IDateUnit depatureTime, ITime departureTimeDifference, ILink... links);

    void assign(IDateUnit depatureTime, ITime departureTimeDifference, List<ILink> links);
}
