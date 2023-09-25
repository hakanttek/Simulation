package com.simulation.contracts.transportation.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ITime;

public interface ISimulationVisualize {

    void run(double leftSpace, double rightSpace, double topSpace, double bottomSpace,
             ILength nodeR, Color nodeColor, Font nodeTextFont, Color nodeTextColor,
             Color roadColor, Color railColor, Color seaColor);

    void stop();

    void setTimerDelay(int delay);

    void setLenght(ILength length);
    
    void setMovingTime(ITime movingTime);

    void paint(Graphics g);

    void actionPerformed(ActionEvent e);
    
    int x_pix(ILength x_pos);
    
    int x_pix(ILength x_pos, ILength shift);
    
    int y_pix(ILength y_pos);
    
    int y_pix(ILength y_pos, ILength shift);
}