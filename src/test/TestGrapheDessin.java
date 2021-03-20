package test;
import matrice.MatriceB;
import visuel.*;
import java.io.IOException;
import java.util.*;


public class TestGrapheDessin {

    public static void main(String[] args) throws IOException,InterruptedException {

        MatriceB m1 = new MatriceB(5,5);
        m1.ajoutLigne(Arrays.asList(false,true,false,true,false));
        m1.ajoutLigne(Arrays.asList(false,true,true,false,false));
        m1.ajoutLigne(Arrays.asList(false,false,false,false,true));
        m1.ajoutLigne(Arrays.asList(false,false,true,false,true));
        m1.ajoutLigne(Arrays.asList(false,false,false,true,true));

        m1.fermetureTranstive().afficher();
        m1.royW().afficher();
        DessinGraphe<String> dessin = new DessinGraphe<String>(m1.fermetureTranstive().litDepuisListe());
		dessin.conversionDot();
    }
}
