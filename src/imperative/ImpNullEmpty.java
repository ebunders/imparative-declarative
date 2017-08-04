package imperative;

import model.FlowerBed;
import model.Garden;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

/**
 * Imperatieve manier van null afhandeling
 *
 * De fouten die hier optreden zijn eigenlijk helemaal geen fouten, het zijn eigenschappen
 * van onze data. Het is dus niet handig om hier excepties voor in te zetten.
 *
 * Java maakt geen onderscheid...
 */
public class ImpNullEmpty {


    //a simple nullcheck
    public Garden simpleNullCheck() {
        Garden g = Garden.gardenOrNull();

        if (nonNull(g)) {
            return g;
        }

        return Garden.builder().build();
    }


    /*
    * Maar als we spul gaan nesten...
     */
    public List<FlowerBed> nestedNullCheck() {
        Garden g = Garden.gardenOrNull();

        if (nonNull(g)) {
            if (nonNull(g.getFlowerBeds())) {
                return g.getFlowerBeds();
            } else {
                //wat te doen?
                return emptyList();
            }
        } else {
            //wat te doen?
            return emptyList();
        }
    }

    /*
     * We kunnen de boel vereenvoudigen...
     */
    public List<FlowerBed> nestedNullCheck2() {
        Garden g = Garden.gardenOrNull();

        if (nonNull(g) && nonNull(g.getFlowerBeds())) {
            return g.getFlowerBeds(); // may be null
        } else {
            // Maar dan weten we niet wat er null was...
            return emptyList();
        }
    }

    /*
     * We kunnen het probleem natuurlijk overdragen aan de client...
     *
     * Maar wat moet de client ermee?
     * De exceptie bevat geen informatie over wat null was
     *
     */
    public List<FlowerBed> nestedNullCheck3() throws NullPointerException{
        return Garden.gardenOrNull().getFlowerBeds();
    }







    /*
     * Het probleem wordt nog groter als we ook nog moeten gaan checken op lege lijsten.
     */
    public FlowerBed nestedNullAndIndex() {
        Garden g = Garden.gardenOrNull();

        if (nonNull(g)) {
            if (nonNull(g.getFlowerBeds())) {
                if(g.getFlowerBeds().isEmpty()){
                    //specifieke afhandeling lege collectie
                    return FlowerBed.builder().build();
                }else{
                    return g.getFlowerBeds().get(0);
                }
            } else {
                //specifieke afhandeling geen flowerbeds
                return FlowerBed.builder().build();
            }
        } else {
            //specifieke afhandeling geen garden
            return FlowerBed.builder().build();
        }
    }


    /*
     * Dit kunnnen we natuurlijk ook 'vereenvoudigen'
     */
    public FlowerBed nestedNullAndIndex1() {
        try {
            return Garden.gardenOrNull().getFlowerBeds().get(0);
        } catch (NullPointerException e) {

            System.out.println("Help! Garden of Flowerbeds was null!!!");
            return FlowerBed.builder().build();

        } catch (IndexOutOfBoundsException e1){

            System.out.println("Ouch! Flowerbeds was een lege collectie");
            return FlowerBed.builder().build();
        }
    }

    /*
     * En we kunnen dit natuurlijk ook allemaal in de schoenen van de client schuiven...
     */
    public FlowerBed nestedNullAndIndex2() throws NullPointerException, IndexOutOfBoundsException{
        return Garden.gardenOrNull().getFlowerBeds().get(0);
    }
}
