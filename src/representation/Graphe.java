package representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Queue;
import java.util.ArrayDeque;


public abstract class Graphe<S> {
	
	protected Map <S, Map<S,Boolean>>listei = new HashMap<S,Map<S,Boolean>>(); 
	   


	public Map<S,Boolean> isoleLigne(S s){
        Map<S,Boolean>ligneSommetEntree = new HashMap<>();
        for(Map.Entry <S, Map<S,Boolean>>ligne : listei.entrySet()){
            if (ligne.getKey().equals(s)){
               ligneSommetEntree = ligne.getValue();
            }
        }
        return ligneSommetEntree;
    }

    public Map <S,Boolean> isoleColonne(S s){
        Map <S,Boolean> ligneSommetColonne = new HashMap<>();
        for(Map.Entry <S, Map<S,Boolean>>ligne : listei.entrySet()){
            Map<S,Boolean> colonne = ligne.getValue();
                for(Map.Entry<S,Boolean>cellule : colonne.entrySet()){
                   if(cellule.getKey().equals(s)){
                       ligneSommetColonne.put(cellule.getKey(),cellule.getValue());
                   }
                }
            }         
        return ligneSommetColonne;   
    }
	


    public int demiDegreExt(S s){
        return isoleLigne(s).size();
    }

    public int demiDegreInt(S s){
        return isoleColonne(s).size();
    } 

    public int degre(S  s){
        return demiDegreExt(s) + demiDegreInt(s);
    }

    public List<S> successeurImmediat(S s){
        List<S> suImmediat = new ArrayList<>();
        for(Map.Entry <S, Map<S,Boolean>>ligne : listei.entrySet()){
            if(ligne.getKey().equals(s)){
                Map<S,Boolean> colonne = ligne.getValue();

                for(Map.Entry<S,Boolean>cellule : colonne.entrySet()){
                   if(cellule.getValue()){
                    suImmediat.add(cellule.getKey());
                   }
                }
            }
        }
        return suImmediat;
    }

    public List<S>iemeSuccesseur(int i , S s){
        List<S> s1 = new ArrayList<S>();
        s1  = successeurImmediat(s);
        for(int j = 2 ; j <= i ; j++){
            List<S> temp = new ArrayList<S>();

            for(S suivant : s1) {
                temp.addAll(successeurImmediat(suivant)); 
                s1 = temp;
            }
        }
        return s1;
    }
       
    
    public int ordre(){
        return listei.size();
    }


    public void ajouterSommet(S s){ 
        Map<S,Boolean> anciennes = new HashMap<>();
        anciennes.put(s,false);

        for(Map.Entry <S, Map<S,Boolean>>ligne : listei.entrySet()){
               ligne.getValue().put(s,false);
               anciennes.put(ligne.getKey(),false);

        }
        listei.put(s,anciennes);        
    }
    
    public void enleverSommet(S s){
        listei.remove(s) ;
    }

    public void afficher(){
        TreeMap<S, Map<S,Boolean>> treeMap = new TreeMap<>(listei);

        for(Map.Entry <S, Map<S,Boolean>>ligne : treeMap.entrySet()){
            System.out.println(ligne.getKey());
            Map<S,Boolean> colonne = ligne.getValue();
            TreeMap<S,Boolean> treeColonne = new TreeMap<>(colonne);
            for(Map.Entry<S,Boolean>cellule : treeColonne.entrySet()){
                System.out.println(cellule.getKey() +"  " + cellule.getValue() );
            }
            System.out.println();
        }      
    }
    
    public Map <S, Map<S,Boolean>> getGraphe(){
    	return listei;
    }
    
    public List<S> getSommets(){
    	List <S> sommets = new ArrayList<S>();
    	for(Map.Entry <S, Map<S,Boolean>>ligne : listei.entrySet()){
    		sommets.add(ligne.getKey());
    	}
    	return sommets;
    }

    public void parcours() {
        Queue<S> q              =   new ArrayDeque<S>();
        List<S> sommets         =   getSommets();
        List<S> sommetsMarques  =   new ArrayList<>();
        S sommetCourant         =   sommets.get(0);
       
        while(!(sommets.size() == sommetsMarques.size())){
            q.add(sommetCourant);
            sommetsMarques.add(sommetCourant);
            while(!q.isEmpty()){
                q.remove();
                for(S s : successeurImmediat(sommetCourant)){
                    if(!sommetsMarques.contains(s)){
                        q.add(s);
                        sommetCourant = q.peek();      
                        sommetsMarques.add(s);  
                    }
                }
            }
            for(S s : sommets){
                if (!sommetsMarques.contains(s)){
                    sommetCourant = s ;
                    break;
                }
            }
        }          
        for(S s :sommetsMarques ){
            System.out.print(s + " ");
        } 
    }
}  