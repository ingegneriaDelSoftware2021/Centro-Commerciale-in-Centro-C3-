package it.unicam.cs.ids.c3;

import com.sun.source.tree.AssertTree;
import it.unicam.cs.ids.c3.model.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.Commerciante;
import it.unicam.cs.ids.c3.model.Esercente.CommercianteInterface;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommercianteTest {


    @Test
    public void addPromozioneTest(){
        try {
            CommercianteInterface c = DBLocale.getInstance().
                    getAllCommercianti().stream().
                    filter(x->x.getIDCommerciante()==1).
                    findFirst().orElse(null);
            Objects.requireNonNull(c).addPromozioni(1,11,19,true);
            assertEquals(1, DBLocale.getInstance().getAllPromozioni().stream().filter(x -> x.getSconto() == 19).count());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
