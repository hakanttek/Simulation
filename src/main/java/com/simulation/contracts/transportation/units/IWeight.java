package com.simulation.contracts.transportation.units;

public interface IWeight {

    double getGram();
    double getKilogram();
    double getTon();
    
    void increase(IWeight weight);
    void decrease(IWeight weight);
    
    IWeight min(IWeight weight);
    IWeight max(IWeight weight);
    
    IWeight sum(IWeight weight);
    IWeight subtraction(IWeight weight);
    double multiplication(double value);
    double division(IWeight weight);
    IWeight division(double value);
    
    boolean isGreaterThan(IWeight weight);
    boolean isLessThan(IWeight weight);
    boolean isEqualTo(IWeight weight);
    boolean isGreaterThanOrEqualTo(IWeight weight);
    boolean isLessThanOrEqualTo(IWeight weight);
    
    Unit getBaseUnit();
    void setBaseUnit(Unit baseUnit);
    double getBase();
    double getPureBase();
    
    enum Unit {
        gram,
        kilogram,
        ton;
    }
}