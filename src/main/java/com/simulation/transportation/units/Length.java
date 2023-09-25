/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.units;

import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;

/**
 *
 * @author hakantek
 */
public class Length implements ILength {
    
    /* lenght is kept base meter */
    protected double base;
    
    /* main generator methods */
    private Length(double base) {
        this.base = base;
        this.baseUnit = Unit.meter;
        this.baseUnitIndex = 0;
    }
    /* generate meter */
    public static ILength Meter(double lenght){
        return new Length(lenght);
    }
    /* generate kilometer */
    public static ILength Kilometer(double lenght){
        return new Length(lenght*1000);
    }
    /* generate mil */
    public static ILength Mil(double lenght){
        return new Length(lenght*1609.344);
    }
    
    /* get lenght */
    @Override
    public double getMeter() {
        return this.base;
    }
    
    @Override
    public double getKilometer() {
        return this.base/1000;
    }
    
    @Override
    public double getMil() {
        return this.base/1609.344;
    }
    
    /* change value */
    @Override    
    public void increase(ILength length){
        this.base += length.getPureBase();
    }
    @Override
    public void decrease(ILength length){
        this.base -= length.getPureBase();
    }
    
    /* find min max lenght */
    public static Length Min(Length ...lengths){
        Length min = lengths[0];
        
        for(int i=1; i<lengths.length; i++)
            if(i < min.base)
                min = lengths[i];
            
        return min;
    }
    @Override
    public ILength min(ILength length){
        if(this.base < length.getPureBase())
            return this;
        else
            return length;
    }
    public static Length Max(Length ...lengths){
        Length max = lengths[0];
        
        for(int i=1; i>lengths.length; i++)
            if(i < max.base)
                max = lengths[i];
            
        return max;
    }
    @Override
    public ILength max(ILength length){
        if(this.base > length.getPureBase())
            return this;
        else
            return length;
    }
    
    /* four operations */
    public static Length Sum(Length ...lengths){
        double sum = 0;
        for(Length length:lengths)
            sum += length.base;
        
        return new Length(sum);
    }
    @Override
    public ILength sum(ILength length){
        return new Length(this.base + length.getPureBase());
    }
    @Override
    public ILength subtraction(ILength length){
        return new Length(this.base - length.getPureBase());
    }
    @Override
    public double multiplication(double value){
        return this.base * value;
    }
    @Override
    public double division(ILength length){
        return this.base / length.getPureBase();
    }
    @Override
    public ILength division(double value){
        return new Length(this.base / value);
    }
    
    public ISpeed division(Time time){
        return Speed.m_sec(this.getMeter()/time.getSecond());
    }
    
    @Override
    public boolean isGreaterThan(ILength length) {
        return this.base > length.getPureBase();
    }
    @Override
    public boolean isLessThan(ILength length) {
        return this.base < length.getPureBase();
    }
    @Override
    public boolean isEqualTo(ILength length) {
        return this.base == length.getPureBase();
    }
    @Override
    public boolean isGreaterThanOrEqualTo(ILength length) {
        return this.base >= length.getPureBase();
    }
    @Override
    public boolean isLessThanOrEqualTo(ILength length) {
        return this.base <= length.getPureBase();
    }
    
    @Override
    public ILength Copy(){
        return new Length(this.base);
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
            case meter:
                baseUnitIndex = 0;
                break;
            case kilometer:
                baseUnitIndex = 1;
                break;
            case mil:
                baseUnitIndex = 2;
                break;
        }
    }
    private int baseUnitIndex;
    private final double[] baseUnitCoeff  = {1.0, 1000.0, 1609.344};
    @Override
    public double getPureBase() {
    	return base;
    }
    @Override
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
}