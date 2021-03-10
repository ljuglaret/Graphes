package representation;
import java.util.Map;
import java.util.List;

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
}