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
public class Date extends java.util.Date{

    /* generator methods */
    public Date(int year, int month, int date, int hrs, int min, int sec) {
        super(year, month, date, hrs, min, sec);
    }
    public Date(int year, int month, int date, int hrs, int min) {
        super(year, month, date, hrs, min, 0);
    }
    public Date(int year, int month, int date, int hrs) {
        super(year, month, date, hrs, 0, 0);
    }
    public Date(int year, int month, int date) {
        super(year, month, date, 0, 0, 0);
    }
    
    /* spend time */
    public void spendTime(Time time){
        super.setTime(super.getTime() + (long)(1000*time.getSecond()));
    }
}
