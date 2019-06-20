/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.units;

/**
 *
 * @author hakantek
 */
public class Time {
    
    /* time is kept base second */
    protected double base;
    
    /* main generator methods */
    private Time(double base) {
        this.base = base;
    }
    /* generate second */
    public static Time Second(double time){
        return new Time(time);
    }
    /* generate minute */
    public static Time Minute(double time){
        return new Time(time*60.0);
    }
    /* generate hour */
    public static Time Hour(double time){
        return new Time(time*3600.0);
    }
    /* generate day */
    public static Time Day(double time){
        return new Time(time*86400);
    }
        
    /* get time */
    public double getSecond() {
        return this.base;
    }
    public double getMinute() {
        return this.base/60.0;
    }
    public double getHour() {
        return this.base/3600.0;
    }
    public double getDay() {
        return this.base/86400.0;
    }
    
    /* change value */
    public void increase(Time time){
        this.base += time.base;
    }
    public void decrease(Time time){
        this.base -= time.base;
    }
    
    /* find min max lenght */
    public static Time Min(Time ...times){
        Time min = times[0];
        
        for(int i=1; i<times.length; i++)
            if(i < min.base)
                min = times[i];
            
        return min;
    }
    public Time min(Time time){
        if(this.base < time.base)
            return this;
        else
            return time;
    }
    public static Time Max(Time ...times){
        Time max = times[0];
        
        for(int i=1; i>times.length; i++)
            if(i < max.base)
                max = times[i];
            
        return max;
    }
    public Time max(Time time){
        if(this.base > time.base)
            return this;
        else
            return time;
    }
    
    /* four operations */
    public static Time Sum(Time ...times){
        double sum = 0;
        for(Time time:times)
            sum += time.base;
        
        return new Time(sum);
    }
    public Time sum(Time time){
        return new Time(this.base + time.base);
    }
    public Time subtraction(Time time){
        return new Time(this.base - time.base);
    }
    public double multiplication(double value){
        return this.base * value;
    }
    public Length multiplication(Speed speed) {
        return Length.Meter(this.getSecond()*speed.getM_sec());
    }
    public double division(Time time){
        return this.base / time.base;
    }
    public Time division(double value){
        return new Time(this.base / value);
    }
        
    public boolean isGreaterThan(Time time) {
        return this.base > time.base;
    }
    public boolean isLessThan(Time time) {
        return this.base < time.base;
    }
    public boolean isEqualTo(Time time) {
        return this.base == time.base;
    }
    public boolean isGreaterThanOrEqualTo(Time time) {
        return this.base >= time.base;
    }
    public boolean isLessThanOrEqualTo(Time time) {
        return this.base <= time.base;
    }

    private Unit baseUnit;
    public Unit getBaseUnit() {
        return baseUnit;
    }
    public void setBaseUnit(Unit baseUnit) {
        this.baseUnit = baseUnit;
        switch(baseUnit){
            case second:
                baseUnitIndex = 0;
                break;
            case minute:
                baseUnitIndex = 1;
                break;
            case hour:
                baseUnitIndex = 2;
                break;
            case day:
                baseUnitIndex = 3;
                break;
        }
    }
    private int baseUnitIndex;
    private final double[] baseUnitCoeff  = {1.0, 60.0, 3600.0, 86400};
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
    
    public enum Unit{
        second,
        minute,
        hour,
        day;
    }
}
