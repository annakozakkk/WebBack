package ua.lviv.iot.algo.part1.projector.writer;

import ua.lviv.iot.algo.part1.projector.model.AbstractBicycle;

import java.io.*;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;

public class BicycleWriter {

    public void write(List<AbstractBicycle> bicycles) throws FileNotFoundException {

        String defaultFileName = "result.csv";
        try (PrintWriter printWriter = new PrintWriter(defaultFileName)) {
            Collections.sort(bicycles, Comparator.comparing(o -> o.getClass().getName()));
            Class current = null;
            for (AbstractBicycle bicycle : bicycles) {
                if (bicycle.getClass() != current) {
                    printWriter.println(bicycle.getHeaders());
                    current = bicycle.getClass();
                }
                printWriter.println(bicycle.toCSV());

            }
        } catch (IOException e) {
        }

    }
}

