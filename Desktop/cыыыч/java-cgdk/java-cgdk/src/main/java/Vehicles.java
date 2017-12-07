import model.Facility;
import model.Vehicle;
import model.VehicleType;
import model.VehicleUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vehicles {

    public static final String TAG = Vehicles.class.getSimpleName();

    private final Map<VehicleType, List<Vehicle>> mVehiclesByTypes = new HashMap<>();
    private final Map<Long, Vehicle> mVehiclesByIds = new HashMap<>();

    public void clearYetUseds() {
        for (Map.Entry<Long, Vehicle> entry : mVehiclesByIds.entrySet()) {
            entry.getValue().setYetUsed(false);
        }
    }

    public void add(Vehicle[] vehicles) {
        if (vehicles.length > 0) {
            Utils.log(TAG, "add: vehicles.length=" + vehicles.length);
        }
        for (Vehicle vehicle : vehicles) {
            List<Vehicle> vehiclesByType = mVehiclesByTypes.get(vehicle.getType());
            if (vehiclesByType == null) {
                vehiclesByType = new ArrayList<>();
                mVehiclesByTypes.put(vehicle.getType(), vehiclesByType);
            }
            vehiclesByType.add(vehicle);
            mVehiclesByIds.put(vehicle.getId(), vehicle);
        }
    }

    public void addUpdates(VehicleUpdate[] updates) {
        if (updates.length > 0) {
            Utils.log(TAG, "add: updates.length=" + updates.length);
        }
        for (VehicleUpdate update : updates) {
            final Vehicle vehicle = mVehiclesByIds.get(update.getId());
            if (vehicle != null) {
                vehicle.update(update);
            }
        }
    }

    public Vehicle getNearestToXY(double x, double y, long playerId, VehicleType vehicleType) {
        double minDistance = Double.MAX_VALUE;
        Vehicle nearestVehicle = null;
        for (Map.Entry<Long, Vehicle> entry : mVehiclesByIds.entrySet()) {
            if (entry.getValue().getPlayerId() == playerId
                    && !entry.getValue().isYetUsed()
                    && entry.getValue().getType() == vehicleType) {
                final double distance = entry.getValue().getDistanceTo(x, y);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestVehicle = entry.getValue();
                }
            }
        }

        return nearestVehicle;
    }

    public Vehicle getNearestToFacility(Facility facility, long playerId, VehicleType vehicleType) {
        return getNearestToXY(facility.getLeft(), facility.getTop(), playerId, vehicleType);
    }

}
