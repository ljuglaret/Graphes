package representation;
import java.util.Map;
import java.util.List;
public class GrapheNonOriente<S> extends Graphe<S>  { 
	    
	    public void supprimerArc(S s1 , S s2){
	        Map<S,Boolean> ligneS1 = isoleLigne(s1);
	        Map<S,Boolean> ligneS2 = isoleLigne(s2);
	        ligneS1.remove(s2 , true);    
	        ligneS2.remove(s1 , true);
	    }
	  
	    public void ajouterArc(S s1 , S s2){
	        Map<S,Boolean> ligneS1 = isoleLigne(s1);
	        Map<S,Boolean> ligneS2 = isoleLigne(s2);
	        ligneS1.put(s2 , true);
	        ligneS2.put(s1 , true);
	    }
	    
	    public List<List<S>> chaines(){
	    	return null;
	    }
	    
	    /* !!!! A DEFNIR !!!!*/
	    
	    public boolean estConnexe() {
	    	return false;
	    }
	    
	    public List<S> composantesConnexes(){
	    	return null;
	    }
	   
	}


