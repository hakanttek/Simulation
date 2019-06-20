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
public class Speed {
    
    /* lenght is kept base m/sec */
    protected double base;
    /* main generator methods */
    private Speed(double base) {
        this.base = base;
    }
    /* generate m/sec */
    public static Speed m_sec(double speed){
        return new Speed(speed);
    }
    /* generate km/hr */
    public static Speed km_hr(double speed){
        return new Speed(speed*0.2777777777777778);
    }
    /* generate mph */
    public static Speed mph(double lenght){
        return new Speed(lenght*0.44704);
    }
    
    /* get lenght */
    public double getM_sec(){
        return this.base;
    }
    /* generate km/hr */
    public double getKm_hr(){
        return base/0.2777777777777778;
    }
    /* generate mph */
    public double getMph(){
        return this.base/0.44704;
    }
    
    /* change value */
    public void increase(Speed speed){
        this.base += speed.base;
    }
    public void decrease(Speed speed){
        this.base -= speed.base;
    }
    
    /* find min max lenght */
    public static Speed Min(Speed ...speeds){
        Speed min = speeds[0];
        
        for(int i=1; i<speeds.length; i++)
            if(i < min.base)
                min = speeds[i];
            
        return min;
    }
    public Speed min(Speed speed){
        if(this.base < speed.base)
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
    public Speed max(Speed speed){
        if(this.base > speed.base)
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
    public Speed sum(Speed speed){
        return new Speed(this.base + speed.base);
    }
    public Speed subtraction(Speed speed){
        return new Speed(this.base - speed.base);
    }
    public double multiplication(double value){
        return this.base * value;
    }
    public Length multiplication(Time time) {
        return Length.Meter(this.getM_sec()*time.getSecond());
    }
    public double division(Speed speed){
        return this.base / speed.base;
    }
    public Speed division(double value){
        return new Speed(this.base / value);
    }
        
    public boolean isGreaterThan(Speed speed) {
        return this.base > speed.base;
    }
    public boolean isLessThan(Speed speed) {
        return this.base < speed.base;
    }
    public boolean isEqualTo(Speed speed) {
        return this.base == speed.base;
    }
    public boolean isGreaterThanOrEqualTo(Speed speed) {
        return this.base >= speed.base;
    }
    public boolean isLessThanOrEqualTo(Speed speed) {
        return this.base <= speed.base;
    }

    private Unit baseUnit;
    public Unit getBaseUnit() {
        return baseUnit;
    }
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
    public double getBase() {
        return base/this.baseUnitCoeff[baseUnitIndex];
    }
    
    public enum Unit{
        m_sec,
        km_hr,
        mph;
    }
}