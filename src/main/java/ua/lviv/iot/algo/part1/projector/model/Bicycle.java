package ua.lviv.iot.algo.part1.projector.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString (callSuper = true)
public class Bicycle extends AbstractBicycle {
//    private Integer id;


    public Bicycle(Integer id,String title, String text, double price,String type,String image) {
        super(id,title, text, type,price,image);
    }

    @Override
    public double getMaxDistance() {
        return Integer.MAX_VALUE;
    }
}