package matrice;

import java.util.*;

public class Matrice<E>{

    ArrayList<List<E>> matrice = new ArrayList<List<E>>();
    private int nbLignes;
    private int nbColonnes;



    public Matrice(int nl , int nc){
        nbLignes   = nl;
        nbColonnes = nc;
    }

    public ArrayList<List<E>> getMatrice() {
        return matrice;
    }

    public void ajoutLigne(List<E> ligne){
        matrice.add(ligne);
    }
  

    public Matrice<E> transposee(){
        Matrice<E> matT = new Matrice<E>(nbColonnes, nbLignes);
        for (int i = 0; i < matrice.get(0).size(); i++) {
            ArrayList<E> col = new ArrayList<E>();
            for (List<E> row : matrice) {
                col.add(row.get(i));
            }
            matT.ajoutLigne(col);
        }
      
        return matT;
    } 


    public void afficher(){
            for (int i = 0; i < nbLignes; i++) {
                for (int j = 0 ; j < nbColonnes ; j++) {
                   System.out.print(matrice.get(i).get(j) + " ");
                }
                System.out.println();
            }
            System.out.println();
    }
}
