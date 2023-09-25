package com.simulation.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "out_node")
    private int outNode; // Represented as an integer

    @Column(name = "entry_node")
    private int entryNode; // Represented as an integer

    @Column(name = "speed_limit")
    private double speedLimit;

    @Column(name = "length")
    private double length;

    @Column(name = "transportation_mode")
    private int transportationMode; // Represented as an integer

    // Constructors

    public Link() {
        // Default constructor
    }

    public Link(int outNode, int entryNode, double speedLimit, double length, int transportationMode) {
        this.outNode = outNode;
        this.entryNode = entryNode;
        this.speedLimit = speedLimit;
        this.length = length;
        this.transportationMode = transportationMode;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOutNode() {
        return outNode;
    }

    public void setOutNode(int outNode) {
        this.outNode = outNode;
    }

    public int getEntryNode() {
        return entryNode;
    }

    public void setEntryNode(int entryNode) {
        this.entryNode = entryNode;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getTransportationMode() {
        return transportationMode;
    }

    public void setTransportationMode(int transportationMode) {
        this.transportationMode = transportationMode;
    }
}