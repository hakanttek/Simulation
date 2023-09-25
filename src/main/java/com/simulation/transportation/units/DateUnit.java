/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.units;

import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ITime;

/**
 *
 * @author hakantek
 */
public class DateUnit extends java.util.Date implements IDateUnit {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public DateUnit(int year, int month, int date, int hrs, int min, int sec) {
        super(year - 1900, month - 1, date, hrs, min, sec);
    }

    public DateUnit(int year, int month, int date, int hrs, int min) {
        this(year, month, date, hrs, min, 0);
    }

    public DateUnit(int year, int month, int date, int hrs) {
        this(year, month, date, hrs, 0, 0);
    }

    public DateUnit(int year, int month, int date) {
        this(year, month, date, 0, 0, 0);
    }
    
    /* spend time */
    @Override
    public void spendTime(ITime time){
        super.setTime(super.getTime() + (long)(1000*time.getSecond()));
    }
}