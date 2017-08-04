package model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

import static java.lang.Math.random;
import static java.util.Collections.emptyList;

@Value @Builder(toBuilder = true)
public class Garden {
    private AppleTree appleTree;
    private List<FlowerBed> flowerBeds;

    public static Garden gardenOrNull(){
        return random() > 0.5 ? gardenWithNulls() : null;
    }


    public static Garden gardenWithNulls(){
        return Garden.builder()
                .flowerbeds(emptyList())
                .build();
    }
}


