package com.simulation.contracts.transportation.units;

public interface ILength {

    double getMeter();
    double getKilometer();
    double getMil();
    
    void increase(ILength length);
    void decrease(ILength length);
    
    ILength min(ILength length);
    ILength max(ILength length);
    
    ILength sum(ILength length);
    ILength subtraction(ILength length);
    double multiplication(double value);
    double division(ILength length);
    ILength division(double value);
    
    boolean isGreaterThan(ILength length);
    boolean isLessThan(ILength length);
    boolean isEqualTo(ILength length);
    boolean isGreaterThanOrEqualTo(ILength length);
    boolean isLessThanOrEqualTo(ILength length);
    
    ILength Copy();

    Unit getBaseUnit();
    void setBaseUnit(Unit baseUnit);
    public double getPureBase();
    double getBase();
    
    enum Unit {
        meter,
        kilometer,
        mil;
    }
}