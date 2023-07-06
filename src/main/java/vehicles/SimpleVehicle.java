package vehicles;

import java.util.List;
import java.util.ArrayList;

//
public class SimpleVehicle extends Vehicle {

    //
    public SimpleVehicle() {
        super();
    }

    //
    @Override
    List<VehiclePart> getInitialParts() {
        return new ArrayList<>() {{
            add(new Cockpit());
            add(new Storage());
            add(new FuelTanks());
            add(new Engines());
        }};
    }

    //
    private static class Cockpit extends VehiclePart {
        //
        Cockpit() {
            super("Cockpit", 10, 2, 2);
        }
    }

    //
    private static class Storage extends VehiclePart {
        //
        Storage() {
            super("Storage", 10, 2, 2);
        }
    }

    //
    private static class FuelTanks extends VehiclePart {
        //
        FuelTanks() {
            super("Fuel Tanks", 10, 2, 2);
        }
    }

    //
    private static class Engines extends VehiclePart {
        //
        Engines() {
            super("Engines", 10, 2, 2);
        }
    }
}