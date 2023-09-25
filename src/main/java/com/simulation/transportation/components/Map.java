/*
Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.components;

import com.simulation.contracts.transportation.components.IMap;
import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hakantek
 */
public class Map implements IMap {
    /* generator method */
    protected Map(String name, IDateUnit currentDate) {

        this.name = name;
        this.dateUnit = currentDate;
        this.nodes = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.rails = new ArrayList<>();
        this.seas = new ArrayList<>();
    }
    
    /* index - name */
    public final String name;
    
    /* date in map */
    private IDateUnit dateUnit;
    @Override
    public IDateUnit getDate() {
        return dateUnit;
    }
    @Override
    public void setCurrentDate(IDateUnit dateUnit) {
        this.dateUnit = dateUnit;
    }
    
    /* nodes of map */
    private List<INode> nodes;
    @Override
    public List<INode> getNodes() {
		return nodes;
	}
    @Override
    public INode getNode(int index){
        return this.nodes.get(index);
    }
    @Override
    public int numberOfNodes(){
        return this.nodes.size();
    } 
    
    /* roads of map */
    protected List<ILink> roads;
    @Override
    public List<ILink> getRoads() {
		return roads;
	}
    @Override
    public ILink getRoad(int index){
        return this.roads.get(index);
    }
    @Override
    public int numberOfRoads(){
        return this.roads.size();
    }
    
    /* rails of map */
    protected ArrayList<ILink> rails;
    @Override
    public ArrayList<ILink> getRails() {
		return rails;
	}
    @Override
    public ILink getRail(int index){
        return this.rails.get(index);
    }
    @Override
    public int numberOfRails(){
        return this.rails.size();
    }
    
    /* seas of map */
    protected ArrayList<ILink> seas;
    @Override
    public ArrayList<ILink> getSeas() {
		return seas;
	}
    @Override
    public ILink getSea(int index){
        return seas.get(index);
    }
    @Override
    public int numberOfSeas(){
        return this.seas.size();
    }
  
    /* general link methods */
    @Override
    public ILink getLink(int index, TransportationMode mode){
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
    @Override
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
    public class Component implements IComponent {
        
        /* generator methods */
        public Component(String name) {
            this.name = name;
            this.operationalState = OperationalState.Operating;
        }
        
		/* map */
        @Override
        public IMap Map(){
            return Map.this;
        }
        
        /* name */
        private String name;
        
        /* operational state of link */
        protected Map.OperationalState operationalState;
        @Override
        public Map.OperationalState getOperationalState() {
            return operationalState;
        }
        @Override
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
        @Override
        public void Activate(){
            this.operationalState = Map.OperationalState.Operating;
        }
        @Override
        public void Cancel(){
            this.operationalState = Map.OperationalState.NonOperating;
        }

		@Override
		public String getName() {
			return this.name;
		}
    }
    
    /* map component */
    /* link -> |Node| -> Link */
    public class Node extends Map.Component implements INode {

        /* generator method */
        public Node(String name, ILength x_pos, ILength y_pos) {
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
        private int index;

        /* location of node */
        public final ILength x_pos;
        public final ILength y_pos;

        /* entryLinks -> NODE -> outLinks */
            /* roads */
        protected ArrayList<ILink> entryRoads;
        @Override
        public ILink getEntryRoads(int index){
            return this.entryRoads.get(index);
        }
        @Override
        public int numberOfEntryRoads(){
            return this.entryRoads.size();
        } 

        protected ArrayList<ILink> outRoads;
        @Override
        public ILink getOutRoads(int index){
            return this.outRoads.get(index);
        }
        @Override
        public int numberOfOutRoads(){
            return this.outRoads.size();
        }
            /* rails */
        protected ArrayList<ILink> entryRails;
        @Override
        public ILink getEntryRails(int index){
            return this.entryRails.get(index);
        }
        @Override
        public int numberOfEntryRails(){
            return this.entryRails.size();
        } 

        protected ArrayList<ILink> outRails;
        @Override
        public ILink getOutRails(int index){
            return this.outRails.get(index);
        }
        @Override
        public int numberOfOutRails(){
            return this.outRails.size();
        }

            /* seas */
        protected ArrayList<ILink> entrySeas;
        @Override
        public ILink getEntrySeas(int index){
            return this.entrySeas.get(index);
        }
        @Override
        public int numberOfEntrySeas(){
            return this.entrySeas.size();
        } 

        protected ArrayList<ILink> outSeas;
        @Override
        public ILink getOutSeas(int index){
            return this.outSeas.get(index);
        }
        @Override
        public int numberOfOutSeas(){
            return this.outSeas.size();
        }

        /* general link methods */
            /* entry links */
        @Override
        public ArrayList<ILink> getEntryLinks(){
            ArrayList<ILink> entryLinks = new ArrayList<>();

            entryLinks.addAll(this.entryRoads);
            entryLinks.addAll(this.entryRails);
            entryLinks.addAll(this.entrySeas);

            return entryLinks;
        }
        @Override
        public ArrayList<ILink> getEntryLinks(TransportationMode mode){
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
        @Override
        public int numberOfEntryLinks(){
            return this.entryRoads.size() + this.entryRails.size() + this.entrySeas.size();
        }
            /* out links */
        @Override
        public ArrayList<ILink> getOutLinks(TransportationMode mode){
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
        @Override
        public ArrayList<ILink> getOutLinks(){
            ArrayList<ILink> outLinks = new ArrayList<>();

            outLinks.addAll(this.outRoads);
            outLinks.addAll(this.outRails);
            outLinks.addAll(this.outSeas);

            return outLinks;
        }
        @Override
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
                if(link.getOutNode().IsActive())
                    link.Activate();
            });
            this.entryRails.stream().forEach((link) -> {
                if(link.getOutNode().IsActive())
                    link.Activate();
            });
            this.entrySeas.stream().forEach((link) -> {
                if(link.getOutNode().IsActive())
                    link.Activate();
            });
            this.outRoads.stream().forEach((link) -> {
                if(link.getEntryNode().IsActive())
                    link.Activate();
            });
            this.outRails.stream().forEach((link) -> {
                if(link.getEntryNode().IsActive())
                    link.Activate();
            });
            this.outSeas.stream().forEach((link) -> {
                if(link.getEntryNode().IsActive())
                    link.Activate();
            });
        }

		@Override
		public int getIndex() {
			return this.index;
		}
		@Override
		public ILength getXPos() {
			return this.x_pos;
		}
		@Override
		public ILength getYPos() {
			return this.y_pos;
		}
    }

    public class Link extends Map.Component implements ILink {

        /* generator method */
        private Link(String name, INode outNode, INode entryNode, ILength length, ISpeed legalSpeedLimit, TransportationMode mode) {
            super(name);
            this.outNode = outNode;
            this.entryNode = entryNode;
            this.length = length;
            this.legalSpeedLimit = legalSpeedLimit;

            /* add link on nodes */
            this.outNode.getOutLinks(mode).add(this);
            this.entryNode.getEntryLinks(mode).add(this);

            /* calculate unit move */
            this.x_unitMoveRate = entryNode.getXPos().subtraction(outNode.getXPos()).division(length);
            this.y_unitlMoveRate = entryNode.getYPos().subtraction(outNode.getYPos()).division(length);
            this.mode = mode;
            Map.this.roads.add(this);
        }

        /* outNode -|link|-> entryNode  */
        public final INode outNode;
        public final INode entryNode;

        /* properties */
        private final ILength length;
        private final ISpeed legalSpeedLimit;
        @Override
        public ISpeed getLegalSpeedLimit() {
            //traffic volume - speed limit balance will be added
            return legalSpeedLimit;
        }
        
        public final double x_unitMoveRate;
        public final double y_unitlMoveRate;
        public final TransportationMode mode;
        
        /* number of vehicle of link */
        private int numberOfVehicle;
        @Override
        public int getNumberOfVehicle() {
            return numberOfVehicle;
        }
        @Override
        public void addVehicle() {
            this.numberOfVehicle++;
        }
        @Override
        public void removeVehicle() {
            this.numberOfVehicle--;
            if(this.numberOfVehicle < 0)
                throw new Error("Number of vehicle of Link " + this.getName() + "is negative!");
        }

        /* create return of the link */
        @Override
        public ILink returnLink(){
            return Map.this.new Link(name + "_return", entryNode, outNode, length, legalSpeedLimit, mode);
        }
		@Override
		public String getName() {
			return name;
		}
		@Override
		public INode getOutNode() {
			return this.outNode;
		}
		@Override
		public INode getEntryNode() {
			return this.entryNode;
		}
		@Override
		public ILength getLength() {
			return length;
		}
		@Override
		public double getXUnitMoveRate() {
			return x_unitMoveRate;
		}
		@Override
		public double getYUnitMoveRate() {
			return y_unitlMoveRate;
		}
		@Override
		public TransportationMode getMode() {
			return mode;
		}
    }
 
    /* road generator */
    @Override
    public ILink Road(String name, int outNodeIndexID, int entryNodeID, ILength length, ISpeed speedLimit){
        if(outNodeIndexID>=0 && outNodeIndexID < this.nodes.size() && entryNodeID>=0 && entryNodeID < this.nodes.size())
            return this.new Link(name, this.nodes.get(outNodeIndexID), this.nodes.get(entryNodeID), length, speedLimit, TransportationMode.Road);
        else
            throw new Error("Node ID error in road generator!");
    }
    /* rail generator */
    @Override
    public ILink Rail(String name, int outNodeIndexID, int entryNodeID, ILength length, ISpeed speedLimit){
        if(outNodeIndexID>=0 && outNodeIndexID < this.nodes.size() && entryNodeID>=0 && entryNodeID < this.nodes.size())
            return this.new Link(name, this.nodes.get(outNodeIndexID), this.nodes.get(entryNodeID), length, speedLimit, TransportationMode.Rail);
        else
            throw new Error("Node ID error in rail generator!");
    }
    /* sea generator */
    @Override
    public ILink Sea(String name, int outNodeIndexID, int entryNodeID, ILength length, ISpeed speedLimit){
        if(outNodeIndexID>=0 && outNodeIndexID < this.nodes.size() && entryNodeID>=0 && entryNodeID < this.nodes.size())
            return this.new Link(name, this.nodes.get(outNodeIndexID), this.nodes.get(entryNodeID), length, speedLimit, TransportationMode.Sea);
        else
            throw new Error("Node ID error in sea generator!");
    }
	@Override
	public String getName() {
		return this.name;
	}
 
}