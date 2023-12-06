package ua.lviv.iot.algo.part1.projector.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)

public class ElectricScooter extends AbstractBicycle {

    public ElectricScooter(final Integer id,
                           final String title,
                           final String text,
                           final double price,
                           final String type,
                           final String image,
                           final double timeToDriveOnOneBatteryCharge,
                           final double averageSpeed) {
        super(id,title, text, type,price,image);
        this.timeToDriveOnBatteryCharge = timeToDriveOnOneBatteryCharge;
        this.averageSpeed = averageSpeed;
    }

    private double timeToDriveOnBatteryCharge;
    private double averageSpeed;

    public static final String HEADERS = "timeToDriveOnBatteryCharge,averageSpeed";

    @Override
    public final double getMaxDistance() {
        return timeToDriveOnBatteryCharge * averageSpeed;
    }

    public final String getHeaders() {
        return super.getHeaders() + "," + HEADERS;
    }

    public final String toCSV() {
        return super.toCSV() + "," + getTimeToDriveOnBatteryCharge() + "," + getAverageSpeed();
    }
}
