/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.units;

import com.simulation.contracts.transportation.units.IWeight;

/**
 *
 * @author hakantek
 */
public class Weight implements IWeight {
    
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
    @Override
    public double getGram() {
        return base;
    }
    @Override
    public double getKilogram() {
        return base/1000;
    }
    @Override
    public double getTon() {
        return base/1000000;
    }

    /* change value */
    @Override
    public void increase(IWeight weight){
        this.base += weight.getPureBase();
    }
    @Override
    public void decrease(IWeight weight){
        this.base -= weight.getPureBase();
    }
    
    /* find min max lenght */
    public static Weight Min(Weight ...weights){
        Weight min = weights[0];
        
        for(int i=1; i<weights.length; i++)
            if(i < min.base)
                min = weights[i];
            
        return min;
    }
    @Override
    public IWeight min(IWeight weight){
        if(this.base < weight.getPureBase())
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
    @Override
    public IWeight max(IWeight weight){
        if(this.base > weight.getPureBase())
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
    @Override
    public IWeight sum(IWeight weight){
        return new Weight(this.base + weight.getPureBase());
    }
    @Override
    public IWeight subtraction(IWeight weight){
        return new Weight(this.base - weight.getPureBase());
    }
    @Override
    public double multiplication(double value){
        return this.base * value;
    }
    @Override
    public double division(IWeight weight){
        return this.base / weight.getPureBase();
    }
    @Override
    public Weight division(double value){
        return new Weight(this.base / value);
    }

    @Override
    public boolean isGreaterThan(IWeight weight) {
        return this.base > weight.getPureBase();
    }
    @Override
    public boolean isLessThan(IWeight weight) {
        return this.base < weight.getPureBase();
    }
    @Override
    public boolean isEqualTo(IWeight weight) {
        return this.base == weight.getPureBase();
    }
    @Override
    public boolean isGreaterThanOrEqualTo(IWeight weight) {
        return this.base >= weight.getPureBase();
    }
    @Override
    public boolean isLessThanOrEqualTo(IWeight weight) {
        return this.base <= weight.getPureBase();
    }

    private Unit baseUnit;
    @Override
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
    @Override
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
    @Override
    public double getPureBase() {
    	return base;
    }
}
