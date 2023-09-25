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
public class Speed implements ISpeed {
    
    /* lenght is kept base m/sec */
    protected double base;
    /* main generator methods */
    private Speed(double base) {
        this.base = base;
    }
    /* generate m/sec */
    public static ISpeed m_sec(double speed){
        return new Speed(speed);
    }
    /* generate km/hr */
    public static ISpeed km_hr(double speed){
        return new Speed(speed*0.2777777777777778);
    }
    /* generate mph */
    public static ISpeed mph(double lenght){
        return new Speed(lenght*0.44704);
    }
    
    /* get lenght */
    @Override
    public double getM_sec(){
        return this.base;
    }
    /* generate km/hr */
    @Override
    public double getKm_hr(){
        return base/0.2777777777777778;
    }
    /* generate mph */
    @Override
    public double getMph(){
        return this.base/0.44704;
    }
    
    /* change value */
    @Override
    public void increase(ISpeed speed){
        this.base += speed.getBase();
    }
    @Override
    public void decrease(ISpeed speed){
        this.base -= speed.getPureBase();
    }
    
    /* find min max lenght */
    public static ISpeed Min(ISpeed ...speeds){
    	ISpeed min = speeds[0];
        
        for(int i=1; i<speeds.length; i++)
            if(i < min.getPureBase())
                min = speeds[i];
            
        return min;
    }
    @Override
    public ISpeed min(ISpeed speed){
        if(this.base < speed.getPureBase())
            return this;
        else
            return speed;
    }
    public static Speed Max(Speed ...speeds){
        Speed max = speeds[0];
        
        for(int i=1; i>speeds.length; i++)
            if(i < max.base)
                max = speeds[i];
            
        return max;
    }
    @Override
    public ISpeed max(ISpeed speed){
        if(this.base > speed.getPureBase())
            return this;
        else
            return speed;
    }
    
    /* four operations */
    public static Speed Sum(Speed ...speeds){
        double sum = 0;
        for(Speed speed:speeds)
            sum += speed.base;
        
        return new Speed(sum);
    }
    @Override
    public ISpeed sum(ISpeed speed){
        return new Speed(this.base + speed.getPureBase());
    }
    @Override
    public ISpeed subtraction(ISpeed speed){
        return new Speed(this.base - speed.getPureBase());
    }
    @Override
    public double multiplication(double value){
        return this.base * value;
    }
    @Override
    public ILength multiplication(ITime time) {
        return Length.Meter(this.getM_sec()*time.getSecond());
    }
    @Override
    public double division(ISpeed speed){
        return this.base / speed.getPureBase();
    }
    @Override
    public Speed division(double value){
        return new Speed(this.base / value);
    }
        
    @Override
    public boolean isGreaterThan(ISpeed speed) {
        return this.base > speed.getPureBase();
    }
    @Override
    public boolean isLessThan(ISpeed speed) {
        return this.base < speed.getPureBase();
    }
    @Override
    public boolean isEqualTo(ISpeed speed) {
        return this.base == speed.getPureBase();
    }
    @Override
    public boolean isGreaterThanOrEqualTo(ISpeed speed) {
        return this.base >= speed.getPureBase();
    }
    @Override
    public boolean isLessThanOrEqualTo(ISpeed speed) {
        return this.base <= speed.getPureBase();
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
            case m_sec:
                baseUnitIndex = 0;
                break;
            case km_hr:
                baseUnitIndex = 1;
                break;
            case mph:
                baseUnitIndex = 2;
                break;
        }
    }
    private int baseUnitIndex;
    private final double[] baseUnitCoeff  = {1.0, 0.2777777777777778, 0.44704};
    @Override
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
    @Override
    public double getPureBase() {
    	return base;
    }
}