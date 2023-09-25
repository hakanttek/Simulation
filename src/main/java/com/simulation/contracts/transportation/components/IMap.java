package com.simulation.contracts.transportation.components;

import java.util.ArrayList;
import java.util.List;

import com.simulation.contracts.transportation.units.IDateUnit;
import com.simulation.contracts.transportation.units.ILength;
import com.simulation.contracts.transportation.units.ISpeed;

public interface IMap {
    String getName();
    IDateUnit getDate();
    void setCurrentDate(IDateUnit dateUnit);

    INode getNode(int index);
    int numberOfNodes();

    ILink getRoad(int index);
    int numberOfRoads();

    ILink getRail(int index);
    int numberOfRails();

    ILink getSea(int index);
    int numberOfSeas();

    ILink getLink(int index, TransportationMode mode);
    int numberOfLink(TransportationMode mode);

    interface IComponent {
    	public IMap Map();
        String getName();
        OperationalState getOperationalState();
		boolean IsActive();
		void Activate();
		void Cancel();
    }

    interface INode extends IComponent {
        int getIndex();
        ILength getXPos();
        ILength getYPos();

        ILink getEntryRoads(int index);
        int numberOfEntryRoads();

        ILink getOutRoads(int index);
        int numberOfOutRoads();

        ILink getEntryRails(int index);
        int numberOfEntryRails();

        ILink getOutRails(int index);
        int numberOfOutRails();

        ILink getEntrySeas(int index);
        int numberOfEntrySeas();

        ILink getOutSeas(int index);
        int numberOfOutSeas();

        List<ILink> getEntryLinks();
        List<ILink> getEntryLinks(TransportationMode mode);
        int numberOfEntryLinks();

        List<ILink> getOutLinks();
        List<ILink> getOutLinks(TransportationMode mode);
        int numberOfOutLinks();
    }

    interface ILink extends IComponent {
        INode getOutNode();
        INode getEntryNode();
        ILength getLength();
        ISpeed getLegalSpeedLimit();

        double getXUnitMoveRate();
        double getYUnitMoveRate();
        TransportationMode getMode();

        int getNumberOfVehicle();
        void addVehicle();
        void removeVehicle();

        ILink returnLink();
    }

    enum OperationalState {
        Operating,
        NonOperating
    }

    enum TransportationMode {
        Road,
        Rail,
        Sea
    }

    enum NodeMode {
        Road_Rail,
        Road_Sea,
        Rail_Sea,
        Road_Rail_Sea
    }

	ILink Road(String name, int outNodeIndexID, int entryNodeID, ILength length, ISpeed speedLimit);
	ILink Rail(String name, int outNodeIndexID, int entryNodeID, ILength length, ISpeed speedLimit);
	ILink Sea(String name, int outNodeIndexID, int entryNodeID, ILength length, ISpeed speedLimit);
	List<INode> getNodes();
	List<ILink> getRoads();
	List<ILink> getSeas();
	List<ILink> getRails();
}
