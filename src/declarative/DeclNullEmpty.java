package declarative;

import model.FlowerBed;
import model.Garden;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

/**
 * In declaratief programeren probeer je om je methode uit een expressie te laten bestaan.
 * In dit geval begint je methode met 'return...'
 *
 * In java zijn statements als 'try', 'if' en 'switch' helaas geen expressies...
 */
public class DeclNullEmpty {


    /*
     * Simple null check:
     * - geen assignment
     * - geen 'noise'
     * - sequentiele flow in plaats van losse statements
     */
    public Garden simpleNullCheck() {
        return ofNullable(Garden.gardenOrNull())
                .orElse(Garden.builder().build());
    }


    /*
     * Nested null check
     * - door te mappen maken we de relatie tussen garden en flowerbeds expliciet.
     * - doordat de data doorvloeit hebben we slechts een expressie.
     */
    public List<FlowerBed> nestedNullCheck() {
        return ofNullable(Garden.gardenOrNull())
                .flatMap(garden -> ofNullable(garden.getFlowerBeds()))
                .orElse(emptyList());
    }



    /*
     * Rekening houdend met een lege lijst:
     * - we kunnen een lijst flatmappen naar een Optional van het eerste element in de lijst
     * - Na twee transformaties is er nog maar één geval van 'none'
     */
    public FlowerBed nestedNullAndIndex() {
        return ofNullable(Garden.gardenOrNull())
                .flatMap(garden -> ofNullable(garden.getFlowerBeds()))
                .flatMap(flowerBeds -> flowerBeds.stream().findFirst())
                .orElse(FlowerBed.builder().build());

    }

    /*
     * Als je wilt dat de client van deze methode invloed krijgt op het geval van 'geen data',
     * is een Exceptie niet handig:
     * - misleidend: Exceptie suggereert dat er een fout is opgetreden.
     * - complexiteit door 'alternatief executiepad'
     *
     * Er is een betere manier...
     */
    public FlowerBed nestedNullAndIndex(Supplier<FlowerBed> defaultValue) {
        return ofNullable(Garden.gardenOrNull())
                .flatMap(garden -> ofNullable(garden.getFlowerBeds()))
                .flatMap(flowerBeds -> flowerBeds.stream().findFirst())
                .orElse(defaultValue.get());

    }

}
