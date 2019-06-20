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
import java.util.ArrayList;

/**
 *
 * @author hakantek
 */
public class Vehicle {
    protected Vehicle(Scenario scenario, String name, Speed maxSpeed, Map.TransportationMode mode) {
        this.scenario = scenario;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.mode = mode;
    }

    /* name - scenario */
    public final String name;
    public final Scenario scenario;
    
    /* max speed of vehicle */
    private final Speed maxSpeed;
    public Speed speed(){
        return this.maxSpeed.Min(this.assignment.CurrentLink().getLegalSpeedLimit());
    }
    
    /* location of node */
    public Length getX_pos() {
        return this.assignment.x_pos;
    }
    public Length getY_pos() {
        return this.assignment.y_pos;
    }
    
    /* properties */
    public final Map.TransportationMode mode;
    
    /* routemap */
    protected Assignment assignment;
    public void assign(Date depatureTime, Map.Link ...links){
        this.assignment = new Assignment(depatureTime, links);
    }
    public void assign(Date depatureTime, ArrayList<Map.Link> links){
        this.assignment = new Assignment(depatureTime, links.toArray(new Map.Link[0]));
    }
    
    
    /* assignment of vehicle  */
    class Assignment {
        
        /* generator method */
        public Assignment(Date depatureTime, Map.Link ...links) {
            this.depatureTime = depatureTime;
            this.links = links;
            this.currentLinkIndex = 0;
            this.lengthOfTravelOnCurrentLink = Length.Meter(0);
            this.links[currentLinkIndex].addVehicle();
            this.x_pos = this.links[currentLinkIndex].outNode.x_pos.Copy();
            this.y_pos = this.links[currentLinkIndex].outNode.y_pos.Copy();
            Vehicle.this.assignment = this;
            this.state = AssignmentState.Enroute;
        }
        
        /* links of route */
        private final Map.Link[] links;
        
        /* depature time */
        public final Date depatureTime;
        
        /* state of assigment */
        AssignmentState state;
        
        /* current location */
        private int currentLinkIndex;
        private Length lengthOfTravelOnCurrentLink;
        public Map.Link CurrentLink(){
            return this.links[this.currentLinkIndex];
        }
        protected Length x_pos;
        private Length y_pos;
        
        public void move(Time time){
            /* if it is end of the link */
            if(this.state.IsEnroute()){
                /* if link is active */
                if(this.CurrentLink().IsActive()){
                    if(this.lengthOfTravelOnCurrentLink.isGreaterThanOrEqualTo(CurrentLink().length)){
                        /* if it is end node */
                        if(this.currentLinkIndex == this.links.length - 1){
                            Vehicle.this.scenario.complateVehicleMoving();
                            this.state = AssignmentState.Completed;
                        }
                        else{
                            /* pass to next link and call method again */
                            this.currentLinkIndex++;
                            this.x_pos = this.CurrentLink().outNode.x_pos.Copy();
                            this.y_pos = this.CurrentLink().outNode.y_pos.Copy();
                            this.lengthOfTravelOnCurrentLink = Length.Meter(0);
                            this.move(time);
                        }
                    }
                    else{
                        /* increase lenght and move */
                        this.lengthOfTravelOnCurrentLink.increase(Vehicle.this.speed().multiplication(time));
                        /* düzenlenecek */
                        this.x_pos.increase(Length.Meter(Vehicle.this.speed().multiplication(time).getMeter() * CurrentLink().x_unitMoveRate));
                        this.y_pos.increase(Length.Meter(Vehicle.this.speed().multiplication(time).getMeter()*CurrentLink().y_unitlMoveRate));
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
}
