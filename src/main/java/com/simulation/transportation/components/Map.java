/*
Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.components;

import com.simulation.transportation.units.Date;
import com.simulation.transportation.units.Length;
import com.simulation.transportation.units.Speed;
import java.util.ArrayList;

/**
 *
 * @author hakantek
 */
public class Map {
    /* generator method */
    protected Map(String name, Date currentDate) {

        this.name = name;
        this.date = currentDate;
        this.nodes = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.rails = new ArrayList<>();
        this.seas = new ArrayList<>();
    }
    
    /* index - name */
    public final String name;
    
    /* date in map */
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setCurrentDate(Date date) {
        this.date = date;
    }
    
    /* nodes of map */
    protected ArrayList<Node> nodes;
    public Node getNode(int index){
        return this.nodes.get(index);
    }
    public int numberOfNodes(){
        return this.nodes.size();
    } 
    
    /* roads of map */
    protected ArrayList<Link> roads;
    public Link getRoad(int index){
        return this.roads.get(index);
    }
    public int numberOfRoads(){
        return this.roads.size();
    }
    
    /* rails of map */
    protected ArrayList<Link> rails;
    public Link getRail(int index){
        return this.rails.get(index);
    }
    public int numberOfRails(){
        return this.rails.size();
    }
    
    /* seas of map */
    protected ArrayList<Link> seas;
    public Link getSea(int index){
        return this.seas.get(index);
    }
    public int numberOfSeas(){
        return this.seas.size();
    }
  
    /* general link methods */
    public Link getLink(int index, TransportationMode mode){
        switch(mode){
            case Road:
                return this.roads.get(index);
            case Rail:
                return this.rails.get(index);
            case Sea:
                return this.seas.get(index);
            default:
                throw new Error("Undefined mode error in getLink()!");
        }
    }
    public int numberOfLink(TransportationMode mode){
        switch(mode){
            case Road:
                return this.roads.size();
            case Rail:
                return this.rails.size();
            case Sea:
                return this.seas.size();
            default:
                throw new Error("Undefined mode error in numberOfLink()!");
        }
    }
        
    /* main class for components of map */
    public class Component {
        
        /* generator methods */
        public Component(String name) {
            this.name = name;
            this.operationalState = OperationalState.Operating;
        }
        
        /* map */
        public Map Map(){
            return Map.this;
        }
        
        /* name */
        public final String name;
        
        /* operational state of link */
        protected Map.OperationalState operationalState;
        public Map.OperationalState getOperationalState() {
            return operationalState;
        }
        public boolean IsActive(){
            switch(this.operationalState){
                case Operating:
                    return true;
                case NonOperating:
                    return false;
                default:
                    throw new Error("Operational state of " + this.name + " is undefined!");
            }
        }
        public void Activate(){
            this.operationalState = Map.OperationalState.Operating;
        }
        public void Cancel(){
            this.operationalState = Map.OperationalState.NonOperating;
        }
    }
    
    /* map component */
    /* link -> |Node| -> Link */
    public class Node extends Map.Component {

        /* generator method */
        public Node(String name, Length x_pos, Length y_pos) {
            super(name);
            this.x_pos = x_pos;
            this.y_pos = y_pos;
            this.index = Map.this.nodes.size();
            Map.this.nodes.add(this);
            
            this.outRails = new ArrayList<>();
            this.outRoads = new ArrayList<>();
            this.outSeas = new ArrayList<>();
            
            this.entryRoads = new ArrayList<>();
            this.entryRails = new ArrayList<>();
            this.entrySeas = new ArrayList<>();
        }

        /* index of node */
        public final int index;

        /* location of node */
        public final Length x_pos;
        public final Length y_pos;

        /* entryLinks -> NODE -> outLinks */
            /* roads */
        protected ArrayList<Link> entryRoads;
        public Link getEntryRoads(int index){
            return this.entryRoads.get(index);
        }
        public int numberOfEntryRoads(){
            return this.entryRoads.size();
        } 

        protected ArrayList<Link> outRoads;
        public Link getOutRoads(int index){
            return this.outRoads.get(index);
        }
        public int numberOfOutRoads(){
            return this.outRoads.size();
        }
            /* rails */
        protected ArrayList<Link> entryRails;
        public Link getEntryRails(int index){
            return this.entryRails.get(index);
        }
        public int numberOfEntryRails(){
            return this.entryRails.size();
        } 

        protected ArrayList<Link> outRails;
        public Link getOutRails(int index){
            return this.outRails.get(index);
        }
        public int numberOfOutRails(){
            return this.outRails.size();
        }

            /* seas */
        protected ArrayList<Link> entrySeas;
        public Link getEntrySeas(int index){
            return this.entrySeas.get(index);
        }
        public int numberOfEntrySeas(){
            return this.entrySeas.size();
        } 

        protected ArrayList<Link> outSeas;
        public Link getOutSeas(int index){
            return this.outSeas.get(index);
        }
        public int numberOfOutSeas(){
            return this.outSeas.size();
        }

        /* general link methods */
            /* entry links */
        public ArrayList<Link> getEntryLinks(){
            ArrayList<Link> entryLinks = new ArrayList<>();

            entryLinks.addAll(this.entryRoads);
            entryLinks.addAll(this.entryRails);
            entryLinks.addAll(this.entrySeas);

            return entryLinks;
        }
        public ArrayList<Link> getEntryLinks(TransportationMode mode){
            switch(mode){
                case Road:
                    return this.entryRoads;
                case Rail:
                    return this.entryRails;
                case Sea:
                    return this.entrySeas;
                default:
                    throw new Error("Undefined mode error in getEntryLinks()!");
            }
        }
            public int numberOfEntryLinks(){
            return this.entryRoads.size() + this.entryRails.size() + this.entrySeas.size();
        }
            /* out links */
        public ArrayList<Link> getOutLinks(TransportationMode mode){
            switch(mode){
                case Road:
                    return this.outRoads;
                case Rail:
                    return this.outRails;
                case Sea:
                    return this.outSeas;
                default:
                    throw new Error("Undefined mode error in getOutLinks()!");
            }
        }
        public ArrayList<Link> getOutLinks(){
            ArrayList<Link> outLinks = new ArrayList<>();

            outLinks.addAll(this.outRoads);
            outLinks.addAll(this.outRails);
            outLinks.addAll(this.outSeas);

            return outLinks;
        }
        public int numberOfOutLinks(){
            return this.outRoads.size() + this.outRails.size() + this.outSeas.size();
        }

        @Override
        public void Cancel() {
            super.Cancel(); //To change body of generated methods, choose Tools | Templates.
            /* cancel connected links */
            this.entryRoads.stream().forEach((link) -> {
                link.Cancel();
            });
            this.entryRails.stream().forEach((link) -> {
                link.Cancel();
            });
            this.entrySeas.stream().forEach((link) -> {
                link.Cancel();
            });
            this.outRoads.stream().forEach((link) -> {
                link.Cancel();
            });
            this.outRails.stream().forEach((link) -> {
                link.Cancel();
            });
            this.outSeas.stream().forEach((link) -> {
                link.Cancel();
            });
        }

        @Override
        public void Activate() {
            super.Activate(); //To change body of generated methods, choose Tools | Templates.
            /* active connected links */
            this.entryRoads.stream().forEach((link) -> {
                if(link.outNode.IsActive())
                    link.Activate();
            });
            this.entryRails.stream().forEach((link) -> {
                if(link.outNode.IsActive())
                    link.Activate();
            });
            this.entrySeas.stream().forEach((link) -> {
                if(link.outNode.IsActive())
                    link.Activate();
            });
            this.outRoads.stream().forEach((link) -> {
                if(link.entryNode.IsActive())
                    link.Activate();
            });
            this.outRails.stream().forEach((link) -> {
                if(link.entryNode.IsActive())
                    link.Activate();
            });
            this.outSeas.stream().forEach((link) -> {
                if(link.entryNode.IsActive())
                    link.Activate();
            });
        }
    }
    
    public class Link extends Map.Component {

        /* generator method */
        private Link(String name, Node outNode, Node entryNode, Length length, Speed legalSpeedLimit, TransportationMode mode) {
            super(name);
            this.outNode = outNode;
            this.entryNode = entryNode;
            this.length = length;
            this.legalSpeedLimit = legalSpeedLimit;

            /* add link on nodes */
            this.outNode.getOutLinks(mode).add(this);
            this.entryNode.getEntryLinks(mode).add(this);

            /* calculate unit move */
            this.x_unitMoveRate = entryNode.x_pos.subtraction(outNode.x_pos).division(length);
            this.y_unitlMoveRate = entryNode.y_pos.subtraction(outNode.y_pos).division(length);
            this.mode = mode;
            Map.this.roads.add(this);
        }

        /* outNode -|link|-> entryNode  */
        public final Node outNode;
        public final Node entryNode;

        /* properties */
        public final Length length;
        private final Speed legalSpeedLimit;
        public Speed getLegalSpeedLimit() {
            //traffic volume - speed limit balance will be added
            return legalSpeedLimit;
        }
        
        public final double x_unitMoveRate;
        public final double y_unitlMoveRate;
        public final TransportationMode mode;
        
        /* number of vehicle of link */
        private int numberOfVehicle;
        public int getNumberOfVehicle() {
            return numberOfVehicle;
        }
        public void addVehicle() {
            this.numberOfVehicle++;
        }
        public void removeVehicle() {
            this.numberOfVehicle--;
            if(this.numberOfVehicle < 0)
                throw new Error("Number of vehicle of Link " + this.name + "is negative!");
        }
        
        
        /* create return of the link */
        public Link ReturnLink(){
            return Map.this.new Link(name + "_return", entryNode, outNode, length, legalSpeedLimit, mode);
        }
    }
 
    /* road generator */
    public Link Road(String name, int outNodeIndexID, int entryNodeID, Length length, Speed speedLimit){
        if(outNodeIndexID>=0 && outNodeIndexID < this.nodes.size() && entryNodeID>=0 && entryNodeID < this.nodes.size())
            return this.new Link(name, this.nodes.get(outNodeIndexID), this.nodes.get(entryNodeID), length, speedLimit, TransportationMode.Road);
        else
            throw new Error("Node ID error in road generator!");
    }
    /* rail generator */
    public Link Rail(String name, int outNodeIndexID, int entryNodeID, Length length, Speed speedLimit){
        if(outNodeIndexID>=0 && outNodeIndexID < this.nodes.size() && entryNodeID>=0 && entryNodeID < this.nodes.size())
            return this.new Link(name, this.nodes.get(outNodeIndexID), this.nodes.get(entryNodeID), length, speedLimit, TransportationMode.Rail);
        else
            throw new Error("Node ID error in rail generator!");
    }
    /* sea generator */
    public Link Sea(String name, int outNodeIndexID, int entryNodeID, Length length, Speed speedLimit){
        if(outNodeIndexID>=0 && outNodeIndexID < this.nodes.size() && entryNodeID>=0 && entryNodeID < this.nodes.size())
            return this.new Link(name, this.nodes.get(outNodeIndexID), this.nodes.get(entryNodeID), length, speedLimit, TransportationMode.Sea);
        else
            throw new Error("Node ID error in sea generator!");
    }
 
    /* operational states of nodes and links as a results of closure of borders, road accident etc. */
    enum OperationalState {
        Operating,
        NonOperating;
    }
    /* modes of links */
    public enum TransportationMode{
        Road,
        Rail,
        Sea;
    }
    /* modes of nodes */
    enum NodeMode{
        Road_Rail,
        Road_Sea,
        Rail_Sea,
        Road_Rail_Sea;
    }
}