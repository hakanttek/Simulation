/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.transportation.gui;

import com.simulation.data.importing.ExcelColumn;
import com.simulation.transportation.components.Map;
import com.simulation.transportation.components.Map.TransportationMode;
import com.simulation.transportation.components.Scenario;
import com.simulation.transportation.components.Vehicle;
import com.simulation.transportation.components.VehicleFleet;
import com.simulation.transportation.units.Date;
import com.simulation.transportation.units.Length;
import com.simulation.transportation.units.Speed;
import com.simulation.transportation.units.Time;
import com.simulation.transportation.units.Weight;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author hakantek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        /* import data from excel */
        String fileLocation = System.getProperty("user.dir");
        Double[] nodeX_pos = ExcelColumn.Decimal(fileLocation, "data", "node", "x_pos").toArray(new Double[0]);
        Double[] nodeY_pos = ExcelColumn.Decimal(fileLocation, "data", "node", "y_pos").toArray(new Double[0]);
        
        Integer[] linkOutNodeID = ExcelColumn.Integer(fileLocation, "data", "link", "out_node").toArray(new Integer[0]);
        Integer[] linkEntryNodeID = ExcelColumn.Integer(fileLocation, "data", "link", "entry_node").toArray(new Integer[0]);
        Double[] linkSpeedLimit = ExcelColumn.Decimal(fileLocation, "data", "link", "speedLimit(m/min)").toArray(new Double[0]);
        Double[] linkLenght = ExcelColumn.Decimal(fileLocation, "data", "link", "lenght(m)").toArray(new Double[0]);
        Double[] transportationMode = ExcelColumn.Decimal(fileLocation, "data", "link", "transportationMode").toArray(new Double[0]);
        
        Integer[] timeFrame = ExcelColumn.Integer(fileLocation, "data", "vehicle", "Time Frame").toArray(new Integer[0]);
        Integer[] roadMap = ExcelColumn.Integer(fileLocation, "data", "vehicle", "roadMap (Links)").toArray(new Integer[0]);
                
        Scenario scenario = new Scenario("myScenario", new Date(2019, 6, 17, 0, 0, 0), 80, Time.Minute(1));
        
        Scenario.Node[] nodes = new Scenario.Node[nodeX_pos.length];
        for(int i=0; i<nodes.length; i++){
            nodes[i] = scenario.new Node("", Length.Meter(nodeX_pos[i]*9), Length.Meter(nodeY_pos[i]*9));
        }
        
        Scenario.Link link[] = new Map.Link[linkEntryNodeID.length];
        for(int i=0; i<linkOutNodeID.length; i++){
            link[i] = scenario.Road("", linkOutNodeID[i], linkEntryNodeID[i], Length.Kilometer(100), Speed.km_hr(60));
        }
        
        Vehicle[] vehicle = new Vehicle[timeFrame.length];
        for(int i=0; i<vehicle.length; i++){
            vehicle[i] = scenario.vehicle("", Speed.km_hr(90), TransportationMode.Road,new Date(2019, 6, 17, timeFrame[i], 0), link[roadMap[i]]);
        }

        scenario.run(100, 100, 100, 100,
                Length.Meter(40), Color.GREEN, new Font("TimesRoman", Font.PLAIN, 12), Color.BLACK,
            Color.GRAY, Color.BLACK, Color.BLUE);
    }
}
