package matrice;

import representation.*;
import java.util.*;

public class MatriceB extends Matrice<Boolean>{



    private int nbLignes;
    private int nbColonnes;

    public MatriceB(int nl , int nc){
        super(nl, nc);
        nbLignes = nl;
        nbColonnes = nc;
    }

 
    public MatriceB somme(MatriceB m2){
        MatriceB matS = new MatriceB(nbLignes , nbColonnes);

        for (int i = 0; i < nbLignes; i++) {
            ArrayList<Boolean> ligne = new ArrayList<Boolean>();
            for (int j = 0 ; j < nbColonnes ; j++) {
                ligne.add ( matrice.get(i).get(j) || m2.getMatrice().get(i).get(j)); 

            } 
            matS.ajoutLigne(ligne);
        }

        return matS;
    }

    
    public boolean produitScalaire(List<Boolean> l1 , List<Boolean> l2) {
        List<Boolean> ps = new ArrayList<>();
        boolean somme = false;
        for(int i = 0 ; i < l1.size() ; i++) {
            ps.add(l1.get(i) && l2.get(i));
        }
        for(Boolean s : ps){
            somme = somme || s;
        }
        return somme;
    }

    public MatriceB produit (Matrice<Boolean> m2){
        MatriceB matS = new MatriceB(nbLignes , nbColonnes);
        Matrice<Boolean> mT = m2.transposee();
        for (int i = 0; i < nbLignes; i++) {
            ArrayList<Boolean> ligne = new ArrayList<Boolean>();
            for (int j = 0 ; j < nbColonnes ; j++) {
                ligne.add(produitScalaire((List<Boolean>)matrice.get(i), mT.getMatrice().get(j)));
            }
            matS.ajoutLigne(ligne);
        }
        return matS;
    }

    public MatriceB puissance (int exposant){
        MatriceB matP = this;
        int cpt = 1 ;
        while (cpt < exposant) {
            matP = matP.produit(this);
            cpt++;
        } 
        return matP;
    }
   
    public MatriceB identite(int rang){
        MatriceB matId = new MatriceB(nbLignes , nbColonnes);
        for(int i = 0 ; i < rang ; i ++){
            ArrayList<Boolean> ligne = new ArrayList<Boolean>();
            for(int j = 0 ; j < rang ; j++){
                if(i==j){
                    ligne.add(true);
                }
                else{
                    ligne.add(false);
                }
            }
            matId.ajoutLigne(ligne);
        }
        return matId;
    }

    public MatriceB fermetureTranstive(){
        return somme(identite(nbLignes)).puissance(nbLignes - 1);
    }


	public Graphe<String> litDepuisListe(){
		GraphesOrientes<String> graphe = new GraphesOrientes<String>();
        List<String> sommets = new ArrayList<>();
        for(int i = 0; i < 5  ; i++){
            sommets.add("x"+(i+1));
            graphe.ajouterSommet("x"+(i+1));

        }
		for(int i = 0 ; i < sommets.size() ; i++){
			for(int j = 0  ; j <  sommets.size(); j ++)  {
				if(matrice.get(i).get(j)){  
					graphe.ajouterArc(sommets.get(i), sommets.get(j));
				}	
            }
		}
		return graphe;
	}

}

