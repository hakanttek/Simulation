/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.components;

import com.simulation.contracts.transportation.components.IMap;
import com.simulation.contracts.transportation.components.IMap.ILink;
import com.simulation.contracts.transportation.components.IMap.TransportationMode;
import com.simulation.contracts.transportation.components.IScenario;
import com.simulation.contracts.transportation.components.IVehicle;
import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;
import com.simulation.transportation.units.Length;
import com.simulation.transportation.units.Speed;

import java.util.List;

/**
 *
 * @author hakantek
 */
public class Vehicle implements IVehicle {
    protected Vehicle(IScenario scenario, String name, ISpeed maxSpeed, IMap.TransportationMode mode) {
        this.scenario = scenario;
        this.name = name;
        this.mode = mode;
    }

    /* name - scenario */
    public final String name;
    public final IScenario scenario;
    
    public ISpeed speed(){
        return Speed.Min(this.assignment.getCurrentLink().getLegalSpeedLimit());
    }
    
    /* location of node */
    @Override
    public ILength getX_pos() {
        return this.assignment.getX_pos();
    }
    @Override
    public ILength getY_pos() {
        return this.assignment.getY_pos();
    }
    
    /* properties */
    private final Map.TransportationMode mode;
    @Override
    public TransportationMode getMode() {
    	return mode;
    }
    
    /* routemap */
    private IAssignment assignment;
    @Override
    public IAssignment getAssignment() {
    	return assignment;
    }
    public void setAssignment(IAssignment assignment) {
		this.assignment = assignment;
	}
    
    @Override
    public void assign(IDateUnit depatureTime, IMap.ILink ...links){
        this.assignment = new Assignment(depatureTime, links);
    }
    
    @Override
    public void assign(IDateUnit depatureTime, List<IMap.ILink> links){
        this.assignment = new Assignment(depatureTime, links.toArray(new Map.Link[0]));
    }
    
    
    /* assignment of vehicle  */
    class Assignment implements IAssignment {
        
        /* generator method */
        public Assignment(IDateUnit depatureTime, IMap.ILink ...links) {
            this.depatureTime = depatureTime;
            this.links = links;
            this.currentLinkIndex = 0;
            this.lengthOfTravelOnCurrentLink = Length.Meter(0);
            this.links[currentLinkIndex].addVehicle();
            this.x_pos = this.links[currentLinkIndex].getOutNode().getXPos().Copy();
            this.y_pos = this.links[currentLinkIndex].getOutNode().getYPos().Copy();
            Vehicle.this.assignment = this;
            this.state = AssignmentState.Enroute;
        }
        
        /* links of route */
        private final IMap.ILink[] links;
        
        /* depature time */
        private final IDateUnit depatureTime;
        @Override
        public IDateUnit getDepatureTime() {
			return depatureTime;
		}
        
        /* state of assigment */
        private AssignmentState state;
        @Override
        public AssignmentState getState() {
			return state;
		}
        
        /* current location */
        private int currentLinkIndex;
        @Override
        public int getCurrentLinkIndex() {
			return currentLinkIndex;
		}
        @Override
        public ILink getCurrentLink() {
			return links[currentLinkIndex];
		}
        
        private ILength lengthOfTravelOnCurrentLink;
        @Override
        public IMap.ILink CurrentLink(){
            return this.links[this.currentLinkIndex];
        }
        private ILength x_pos;
        @Override
        public ILength getX_pos() {
			return x_pos;
		}
        private ILength y_pos;
        @Override
        public ILength getY_pos() {
			return y_pos;
		} 
        
        @Override
        public void move(ITime time){
            /* if it is end of the link */
            if(this.state.IsEnroute()){
                /* if link is active */
                if(this.CurrentLink().IsActive()){
                    if(this.lengthOfTravelOnCurrentLink.isGreaterThanOrEqualTo(CurrentLink().getLength())){
                        /* if it is end node */
                        if(this.currentLinkIndex == this.links.length - 1){
                            Vehicle.this.scenario.complateVehicleMoving();
                            this.state = AssignmentState.Completed;
                        }
                        else{
                            /* pass to next link and call method again */
                            this.currentLinkIndex++;
                            this.x_pos = this.CurrentLink().getOutNode().getXPos().Copy();
                            this.y_pos = this.CurrentLink().getOutNode().getYPos().Copy();
                            this.lengthOfTravelOnCurrentLink = Length.Meter(0);
                            this.move(time);
                        }
                    }
                    else{
                        /* increase lenght and move */
                        this.lengthOfTravelOnCurrentLink.increase(Vehicle.this.speed().multiplication(time));
                        /* düzenlenecek */
                        this.x_pos.increase(Length.Meter(Vehicle.this.speed().multiplication(time).getMeter() * CurrentLink().getXUnitMoveRate()));
                        this.y_pos.increase(Length.Meter(Vehicle.this.speed().multiplication(time).getMeter()*CurrentLink().getYUnitMoveRate()));
                    }
                }
                else
                    /* replane routemap */
                    replanning();
            }
        }
        
        private void replanning(){
            throw new Error("replanning() method isn't complated!");
        }
    }
}
