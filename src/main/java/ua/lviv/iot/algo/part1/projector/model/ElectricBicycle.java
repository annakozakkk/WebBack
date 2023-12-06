package ua.lviv.iot.algo.part1.projector.model;

import lombok.ToString;

@ToString(callSuper = true)
public class ElectricBicycle extends AbstractBicycle {
    //public ElectricBicycle(){}
    public ElectricBicycle(Integer id,String title, String text, double price, String type,String image,  double capacityOfBattery,double energyConsumptionPer100meters) {
        super(id, title,text, type,price,image);
        this.capacityOfBattery = capacityOfBattery;
        this.energyConsumptionPer100meters=energyConsumptionPer100meters;
    }
     private final double capacityOfBattery;
    private final double energyConsumptionPer100meters;
    public static final String HEADERS = "capacityOfBattery,energyConsumptionPer100meters";
    @Override
    public double getMaxDistance() {
        return capacityOfBattery*energyConsumptionPer100meters;
    }
    public final String getHeaders() {
        return super.getHeaders() + "," + HEADERS;
    }

    public final String toCSV() {
        return super.toCSV() + "," +capacityOfBattery +","+energyConsumptionPer100meters;
    }
}
