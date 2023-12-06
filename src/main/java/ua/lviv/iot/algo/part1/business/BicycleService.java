package ua.lviv.iot.algo.part1.business;

import org.springframework.stereotype.Service;
import ua.lviv.iot.algo.part1.projector.model.Bicycle;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BicycleService {
    private Map<Integer, Bicycle> bicycles = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Bicycle createBicycle(Bicycle bicycle) {
        bicycle.setId(idCounter.incrementAndGet());
        bicycles.put(bicycle.getId(), bicycle);
        return bicycle;
    }
    public List<Bicycle> getFilteredBicycles(String name,String type,String price,String title){
        List<Bicycle> allBicycles= getAllBicycles();
        return allBicycles.stream().filter(bicycle ->
                        (bicycle.getTitle().toLowerCase().contains(title.toLowerCase()))&&
                        (name.equals("all") || bicycle.getTitle().toLowerCase().contains(name.toLowerCase())) &&
                                (type.equals("all") || bicycle.getType().toLowerCase().contains(type.toLowerCase())) &&
                                ("all".equals(String.valueOf(price)) ||
                                        ("cheap".equals(String.valueOf(price)) && bicycle.getPrice() < 500) ||
                                        ("average".equals(String.valueOf(price)) && bicycle.getPrice() >= 500 && bicycle.getPrice() < 1000) ||
                                        ("expensive".equals(String.valueOf(price)) && bicycle.getPrice() >= 1000)))
                .collect(Collectors.toList());}
    public Bicycle getBicycle(Integer bicycleId) {
        return bicycles.get(bicycleId);
    }

    public List<Bicycle> getAllBicycles() {
        return new LinkedList<Bicycle>(bicycles.values());
    }

    public boolean deleteBicycle(Integer bicycleId) {
        return bicycles.remove(bicycleId) != null;
    }

    public Bicycle updateBicycle(Integer bicycleId, Bicycle bicycle) {
        bicycle.setId(bicycleId);
        bicycles.put(bicycleId, bicycle);
        return bicycle;
    }

}
