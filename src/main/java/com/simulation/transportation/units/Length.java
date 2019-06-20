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
public class Length {
    
    /* lenght is kept base meter */
    protected double base;
    
    /* main generator methods */
    private Length(double base) {
        this.base = base;
        this.baseUnit = Unit.meter;
        this.baseUnitIndex = 0;
    }
    /* generate meter */
    public static Length Meter(double lenght){
        return new Length(lenght);
    }
    /* generate kilometer */
    public static Length Kilometer(double lenght){
        return new Length(lenght*1000);
    }
    /* generate mil */
    public static Length Mil(double lenght){
        return new Length(lenght*1609.344);
    }
    
    /* get lenght */
    public double getMeter() {
        return this.base;
    }
    public double getKilometer() {
        return this.base/1000;
    }
    public double getMil() {
        return this.base/1609.344;
    }
    
    /* change value */
    public void increase(Length length){
        this.base += length.base;
    }
    public void decrease(Length length){
        this.base -= length.base;
    }
    
    /* find min max lenght */
    public static Length Min(Length ...lengths){
        Length min = lengths[0];
        
        for(int i=1; i<lengths.length; i++)
            if(i < min.base)
                min = lengths[i];
            
        return min;
    }
    public Length min(Length length){
        if(this.base < length.base)
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
    public Length max(Length length){
        if(this.base > length.base)
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
    public Length sum(Length length){
        return new Length(this.base + length.base);
    }
    public Length subtraction(Length length){
        return new Length(this.base - length.base);
    }
    public double multiplication(double value){
        return this.base * value;
    }
    public double division(Length length){
        return this.base / length.base;
    }
    public Length division(double value){
        return new Length(this.base / value);
    }
    public Speed division(Time time){
        return Speed.m_sec(this.getMeter()/time.getSecond());
    }
    
    
    public boolean isGreaterThan(Length length) {
        return this.base > length.base;
    }
    public boolean isLessThan(Length length) {
        return this.base < length.base;
    }
    public boolean isEqualTo(Length length) {
        return this.base == length.base;
    }
    public boolean isGreaterThanOrEqualTo(Length length) {
        return this.base >= length.base;
    }
    public boolean isLessThanOrEqualTo(Length length) {
        return this.base <= length.base;
    }
    
    
    public Length Copy(){
        return new Length(this.base);
    }
    
    private Unit baseUnit;
    public Unit getBaseUnit() {
        return baseUnit;
    }
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
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
    
    public enum Unit{
        meter,
        kilometer,
        mil;
    }
}