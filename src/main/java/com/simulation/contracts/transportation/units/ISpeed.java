package com.simulation.contracts.transportation.units;

public interface ISpeed {

    double getM_sec();
    double getKm_hr();
    double getMph();
    
    void increase(ISpeed speed);
    void decrease(ISpeed speed);
    
    ISpeed min(ISpeed speed);
    ISpeed max(ISpeed speed);
    
    ISpeed sum(ISpeed speed);
    ISpeed subtraction(ISpeed speed);
    double multiplication(double value);
    ILength multiplication(ITime time);
    double division(ISpeed speed);
    ISpeed division(double value);
    
    boolean isGreaterThan(ISpeed speed);
    boolean isLessThan(ISpeed speed);
    boolean isEqualTo(ISpeed speed);
    boolean isGreaterThanOrEqualTo(ISpeed speed);
    boolean isLessThanOrEqualTo(ISpeed speed);
    
    Unit getBaseUnit();
    void setBaseUnit(Unit baseUnit);
    double getBase();
    double getPureBase();
    
    enum Unit {
        m_sec,
        km_hr,
        mph;
    }
}