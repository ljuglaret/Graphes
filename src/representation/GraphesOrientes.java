package representation;
import java.util.*;

public class GraphesOrientes<S> extends Graphe<S>  { 
    
    public void supprimerArc(S s1 , S s2){
        Map<S,Boolean> ligne = isoleLigne(s1);
        ligne.remove(s2 , true);    
    }
  
    public void ajouterArc(S s1 , S s2){
        Map<S,Boolean> ligne = isoleLigne(s1);
        ligne.put(s2 , true);
    }
 

    
    /* !!!! A DEFNIR !!!!*/
    boolean estFortementConnexe() {
    	return false;
    }
    
    public List<S> composantesFortementConnexes(){
    	return null;
    }

    public S suivant(List<S> sommetsParcourus, S x){
        S rep = null;
        for(S suc : successeurImmediat(x)){
            if(! sommetsParcourus.contains(suc) ){
                rep = suc;
                break;
            }
        }
        return rep;
    }

    public void parcoursProfondeur(S sommetDepart){
        Stack<S> q              =   new Stack<S>();
        List<S> sommets         =   getSommets();
        List<S> sommetsParcourus  =   new ArrayList<>();
        S sommetCourant         =   sommetDepart;

        while(!(sommets.size() == sommetsParcourus.size())){
            q.push(sommetCourant);                
            System.out.println(q);

           sommetsParcourus.add(sommetCourant);
            while(!q.isEmpty() ){
                System.out.println(q);
                S s = suivant(sommetsParcourus, q.peek());
                if(s != null){
                    q.push(s);
                    sommetCourant = s;    
                    sommetsParcourus.add(s); 
                }
                else{
                    q.pop();
                }
            }
            
            for(S s : sommets){
                if (!sommetsParcourus.contains(s)){
                    sommetCourant = s ;
                    break;
                }
            }
        }          
        for(S s :sommetsParcourus ){
            System.out.print(s + " ");
        } 
    }
}
