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
public class Weight {
    
    /* lenght is kept base gram */
    protected double base;
    
    /* main generator methods */
    private Weight(double base) {
        this.base = base;
    }
    /* generate gram */
    public static Weight Gram(double gram){
        return new Weight(gram);
    }
    /* generate kilogram */
    public static Weight Kilogram(double kilogram){
        return new Weight(kilogram*1000);
    }
    /* generate ton */
    public static Weight Ton(double ton){
        return new Weight(ton*1000000);
    }
    
    /* get weight */
    public double getGram() {
        return base;
    }
    public double getKilogram() {
        return base/1000;
    }
    public double getTon() {
        return base/1000000;
    }

    /* change value */
    public void increase(Weight weight){
        this.base += weight.base;
    }
    public void decrease(Weight weight){
        this.base -= weight.base;
    }
    
    /* find min max lenght */
    public static Weight Min(Weight ...weights){
        Weight min = weights[0];
        
        for(int i=1; i<weights.length; i++)
            if(i < min.base)
                min = weights[i];
            
        return min;
    }
    public Weight min(Weight weight){
        if(this.base < weight.base)
            return this;
        else
            return weight;
    }
    public static Weight Max(Weight ...weights){
        Weight max = weights[0];
        
        for(int i=1; i>weights.length; i++)
            if(i < max.base)
                max = weights[i];
            
        return max;
    }
    public Weight max(Weight weight){
        if(this.base > weight.base)
            return this;
        else
            return weight;
    }
    
    /* four operations */
    public static Weight Sum(Weight ...weights){
        double sum = 0;
        for(Weight weight:weights)
            sum += weight.base;
        
        return new Weight(sum);
    }
    public Weight sum(Weight weight){
        return new Weight(this.base + weight.base);
    }
    public Weight subtraction(Weight weight){
        return new Weight(this.base - weight.base);
    }
    public double multiplication(double value){
        return this.base * value;
    }
    public double division(Weight weight){
        return this.base / weight.base;
    }
    public Weight division(double value){
        return new Weight(this.base / value);
    }
        
    public boolean isGreaterThan(Weight weight) {
        return this.base > weight.base;
    }
    public boolean isLessThan(Weight weight) {
        return this.base < weight.base;
    }
    public boolean isEqualTo(Weight weight) {
        return this.base == weight.base;
    }
    public boolean isGreaterThanOrEqualTo(Weight weight) {
        return this.base >= weight.base;
    }
    public boolean isLessThanOrEqualTo(Weight weight) {
        return this.base <= weight.base;
    }

    private Unit baseUnit;
    public Unit getBaseUnit() {
        return baseUnit;
    }
    public void setBaseUnit(Unit baseUnit) {
        this.baseUnit = baseUnit;
        switch(baseUnit){
            case gram:
                baseUnitIndex = 0;
                break;
            case kilogram:
                baseUnitIndex = 1;
                break;
            case ton:
                baseUnitIndex = 2;
                break;
        }
    }
    private int baseUnitIndex;
    private final double[] baseUnitCoeff  = {1.0, 1000.0, 1000000.0};
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
    
    public enum Unit{
        gram,
        kilogram,
        ton;
    }
}
