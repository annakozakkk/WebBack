package ua.lviv.iot.algo.part1.projector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BicycleDTO {

    private String title;
    private String text;
    private double price;
    private String type;
    private Integer id;
    private String image;
}


