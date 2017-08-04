package imperative;

import model.FlowerBed;
import model.Garden;

import java.util.Collections;

/*
 * - 'noisy': door overtollige assignments wordt code onduidelijker
 * - declareer - initialiseer - return: wanneer 'initialiseer' geen expressie is. Vindt initialisatie plaats?
 * - met if heb je twee executie paden. Helaas is if geen expressie, wat kan leiden tot overbodige overhead
 */
public class ImpAssignment {

    private Garden getGarden(){
        Garden garden = Garden.builder().build();

        garden = addaFlowerBed(garden);
        
        return garden.toBuilder().flowerBeds() 
    }

    private Garden addaFlowerBed(Garden garden) {
            return garden
                    .toBuilder()
                    .flowerBeds(Collections.singletonList(FlowerBed.builder().build()))
                    .build()                    ;
    }
}
