package com.simulation.contracts.transportation.components;

import java.util.List;

import com.simulation.contracts.transportation.components.IMap.ILink;
import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;

public interface IVehicle {

    ILength getX_pos();

    ILength getY_pos();

    IMap.TransportationMode getMode();

    IAssignment getAssignment();

    void assign(IDateUnit depatureTime, IMap.ILink ...links);
    
    void assign(IDateUnit depatureTime, List<IMap.ILink> links);
    
    interface IAssignment {
        
        void move(ITime time);

        /* state of assigment */
        enum AssignmentState{
            Enroute,
            Completed;
            public boolean IsEnroute(){
                return this == Enroute;
            }
            public boolean IsCompleted(){
                return this == Completed;
            }
        }

		ILength getX_pos();

		ILength getY_pos();

		AssignmentState getState();

		int getCurrentLinkIndex();

		ILink CurrentLink();

		IDateUnit getDepatureTime();

		ILink getCurrentLink();

    }
}