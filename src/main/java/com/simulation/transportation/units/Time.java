/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.units;

import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.contracts.transportation.units.ITime;

/**
 *
 * @author hakantek
 */
public class Time implements ITime {
    
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
    @Override
    public double getSecond() {
        return this.base;
    }
    @Override
    public double getMinute() {
        return this.base/60.0;
    }
    @Override
    public double getHour() {
        return this.base/3600.0;
    }
    @Override
    public double getDay() {
        return this.base/86400.0;
    }
    
    /* change value */
    @Override
    public void increase(ITime time){
        this.base += time.getBase();
    }
    @Override
    public void decrease(ITime time){
        this.base -= time.getBase();
    }
    
    /* find min max lenght */
    public static Time Min(Time ...times){
        Time min = times[0];
        
        for(int i=1; i<times.length; i++)
            if(i < min.base)
                min = times[i];
            
        return min;
    }
    @Override
    public ITime min(ITime time){
        if(this.base < time.getPureBase())
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
    @Override
    public ITime max(ITime time){
        if(this.base > time.getPureBase())
            return this;
        else
            return time;
    }
    
    /* four operations */
    public static ITime Sum(ITime ...times){
        double sum = 0;
        for(ITime time:times)
            sum += time.getPureBase();
        
        return new Time(sum);
    }
    @Override
    public ITime sum(ITime time){
        return new Time(this.base + time.getPureBase());
    }
    @Override
    public ITime subtraction(ITime time){
        return new Time(this.base - time.getPureBase());
    }
    @Override
    public double multiplication(double value){
        return this.base * value;
    }
    @Override
    public ILength multiplication(ISpeed speed) {
        return Length.Meter(this.getSecond()*speed.getM_sec());
    }
    @Override
    public double division(ITime time){
        return this.base / time.getPureBase();
    }
    @Override
    public Time division(double value){
        return new Time(this.base / value);
    }

    @Override
    public boolean isGreaterThan(ITime time) {
        return this.base > time.getPureBase();
    }
    @Override
    public boolean isLessThan(ITime time) {
        return this.base < time.getPureBase();
    }
    @Override
    public boolean isEqualTo(ITime time) {
        return this.base == time.getPureBase();
    }
    @Override
    public boolean isGreaterThanOrEqualTo(ITime time) {
        return this.base >= time.getPureBase();
    }
    @Override
    public boolean isLessThanOrEqualTo(ITime time) {
        return this.base <= time.getPureBase();
    }

    private Unit baseUnit;
    @Override
    public Unit getBaseUnit() {
        return baseUnit;
    }
    @Override
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
    @Override
    public double getPureBase() {
    	return base;
    }
}
