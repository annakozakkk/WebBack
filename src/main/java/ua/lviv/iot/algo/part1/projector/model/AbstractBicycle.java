package ua.lviv.iot.algo.part1.projector.model;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractBicycle {
    private Integer id;
    private String title;
    private String text;
    private String type;
    private double price;
    private String image;

    public static final String HEADERS = "brand,maxSpeed,type,price";

    public abstract double getMaxDistance();

    public String getHeaders() {
        return HEADERS;
    }

    public String toCSV() {
        return   "," + "," + getType() + "," + getPrice();
    }
}
