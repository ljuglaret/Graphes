package matrice;

import java.util.*;

public class MatriceN extends Matrice<Double>{


    private int nbLignes;
    private int nbColonnes;

    public MatriceN(int nl , int nc){
        super(nl, nc);
        nbLignes = nl;
        nbColonnes = nc;
    }

    public MatriceN somme(MatriceN m2){
        MatriceN matS = new MatriceN(nbLignes , nbColonnes);
        for (int i = 0; i < nbLignes; i++) {
            ArrayList<Double> ligne = new ArrayList<Double>();
            for (int j = 0 ; j < nbColonnes ; j++) {
                ligne.add ( matrice.get(i).get(j) + m2.getMatrice().get(i).get(j));
            }
            matS.ajoutLigne(ligne);
        }
        return matS;
    }

    
    public double produitScalaire(List<Double> l1 , List<Double> l2) {
        List<Double> ps = new ArrayList<>();
        double somme = 0;
        for(int i = 0 ; i < l1.size() ; i++) {
            ps.add(l1.get(i) * l2.get(i));
        }
        for(Double s : ps){
            somme = somme +  s;
        }
        return somme;
    }

    public MatriceN produit (MatriceN m2){
        MatriceN matS = new MatriceN(nbLignes , nbColonnes);
        Matrice<Double> mT =  m2.transposee();
        for (int i = 0; i < nbLignes; i++) {
            ArrayList<Double> ligne = new ArrayList<Double>();
            for (int j = 0 ; j < nbColonnes ; j++) {
                ligne.add(produitScalaire((List<Double>)matrice.get(i), mT.getMatrice().get(j)));
            }
            matS.ajoutLigne(ligne);
        }
        return matS;
    }

    public MatriceN puissance (int exposant){
        MatriceN matP = this;
        int cpt = 1 ;
        while (cpt < exposant) {
            matP = matP.produit(this);
            cpt++;
        } 
        return matP;
    }
}

