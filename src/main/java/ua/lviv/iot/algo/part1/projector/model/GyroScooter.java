package ua.lviv.iot.algo.part1.projector.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@ToString (callSuper = true)
@NoArgsConstructor
public class GyroScooter extends AbstractBicycle {

    public GyroScooter(final Integer id,
                       final String title,
                       final String text,
                       final double price,
                       final String type,
                       final String image,
                       final double capacityOfBattery,
                       final double batteryVoltage,
                       final double energyConsumptionPer1km) {
        super(id,title, text, type,price,image);
        this.batteryVoltage = batteryVoltage;
        this.capacityOfBattery = capacityOfBattery;
        this.energyConsumptionPer1km = energyConsumptionPer1km;
    }

    private double capacityOfBattery;
    private double batteryVoltage;
    private double energyConsumptionPer1km;

    public static final String HEADERS= "capacityOfBattery,batteryVoltage,energyConsumptionPer1km";
    @Override
    public final double getMaxDistance() {
        return (capacityOfBattery * batteryVoltage) / energyConsumptionPer1km;
    }

    public String getHeaders() {
        return super.getHeaders()+ "," + HEADERS;
    }
    public String toCSV(){
        return super.toCSV() + "," + getCapacityOfBattery() +","+ getBatteryVoltage() + ","+ getEnergyConsumptionPer1km();
    }
}