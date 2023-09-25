/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation;

import com.simulation.contracts.transportation.components.IMap;
import com.simulation.contracts.transportation.components.IMap.ILink;
import com.simulation.contracts.transportation.components.IVehicle;
import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;
import com.simulation.infrastructure.data.ExcelReader;
import com.simulation.transportation.components.Map;
import com.simulation.transportation.components.Scenario;
import com.simulation.transportation.components.Vehicle;
import com.simulation.transportation.units.DateUnit;
import com.simulation.transportation.units.Length;
import com.simulation.transportation.units.Speed;
import com.simulation.transportation.units.Time;
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
        ExcelReader column = new ExcelReader(System.getProperty("user.dir") + "\\data.xlsx");
        
        Double[] nodeX_pos = column.fromRowIndexOf(1).readDecimal("node", "x_pos").toArray(new Double[0]);
        Double[] nodeY_pos = column.fromRowIndexOf(1).readDecimal("node", "y_pos").toArray(new Double[0]);
        
        Integer[] linkOutNodeID = column.fromRowIndexOf(1).readInteger("link", "out_node").toArray(new Integer[0]);
        Integer[] linkEntryNodeID = column.fromRowIndexOf(1).readInteger("link", "entry_node").toArray(new Integer[0]);
        Double[] linkSpeedLimit = column.fromRowIndexOf(1).readDecimal("link", "speedLimit(m/min)").toArray(new Double[0]);
        Double[] linkLenght = column.fromRowIndexOf(1).readDecimal("link", "lenght(m)").toArray(new Double[0]);
        Double[] transportationMode = column.fromRowIndexOf(1).readDecimal("link", "transportationMode").toArray(new Double[0]);
        
        Integer[] timeFrame = column.fromRowIndexOf(1).readInteger("vehicle", "Time Frame").toArray(new Integer[0]);
        Integer[] roadMap = column.fromRowIndexOf(1).readInteger("vehicle", "roadMap (Links)").toArray(new Integer[0]);
                
        Scenario scenario = new Scenario("myScenario", new DateUnit(2019, 6, 17, 0, 0, 0), 80, Time.Minute(1));
        
        Scenario.Node[] nodes = new Scenario.Node[nodeX_pos.length];
        for(int i=0; i<nodes.length; i++){
            nodes[i] = scenario.new Node("", Length.Meter(nodeX_pos[i]*9), Length.Meter(nodeY_pos[i]*9));
        }
        
        ILink link[] = new ILink[linkEntryNodeID.length];
        for(int i=0; i<linkOutNodeID.length; i++){
          link[i] = scenario.Road("", linkOutNodeID[i], linkEntryNodeID[i], Length.Kilometer(100), Speed.km_hr(60));
        }
        
        IVehicle[] vehicle = new IVehicle[timeFrame.length];
        for(int i=0; i<vehicle.length; i++){
            vehicle[i] = scenario.vehicle("", Speed.km_hr(90), IMap.TransportationMode.Road,new DateUnit(2019, 6, 17, timeFrame[i], 0), link[roadMap[i]]);
        }

        scenario.run(100, 100, 100, 100,
                Length.Meter(40), Color.GREEN, new Font("TimesRoman", Font.PLAIN, 12), Color.BLACK,
            Color.GRAY, Color.BLACK, Color.BLUE);
    }
}
