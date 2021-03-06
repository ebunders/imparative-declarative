package model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class FlowerBed {
    List<Flower> flowers;
}
