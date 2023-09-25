package com.simulation.contracts.transportation.units;

public interface ITime {

    double getSecond();
    double getMinute();
    double getHour();
    double getDay();
    
    void increase(ITime time);
    void decrease(ITime time);
    
    ITime min(ITime time);
    ITime max(ITime time);
    
    ITime sum(ITime time);
    ITime subtraction(ITime time);
    double multiplication(double value);
    ILength multiplication(ISpeed speed);
    double division(ITime time);
    ITime division(double value);
    
    boolean isGreaterThan(ITime time);
    boolean isLessThan(ITime time);
    boolean isEqualTo(ITime time);
    boolean isGreaterThanOrEqualTo(ITime time);
    boolean isLessThanOrEqualTo(ITime time);
    
    Unit getBaseUnit();
    void setBaseUnit(Unit baseUnit);
    double getBase();
    double getPureBase();
    
    enum Unit {
        second,
        minute,
        hour,
        day;
    }
}