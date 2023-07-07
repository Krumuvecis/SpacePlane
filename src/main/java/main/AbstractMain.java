package main;

import main.graphics.Window;
import vehicles.Vehicle;
import vehicles.VehicleAdapter;

//
abstract class AbstractMain {
    //
    protected AbstractMain() {
        startGraphics(getVehicleAdapter(this));
    }

    private VehicleAdapter getVehicleAdapter(AbstractMain main) {
        return new VehicleAdapter() {{
            setVehicle(main.getVehicle());
        }};
    }

    //
    protected abstract Vehicle getVehicle();

    private void startGraphics(VehicleAdapter vehicleAdapter) {
        new Window(vehicleAdapter);
    }
}