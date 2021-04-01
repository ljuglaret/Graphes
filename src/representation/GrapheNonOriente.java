package representation;
import java.lang.reflect.Array;
import java.util.*;

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
	    

	public boolean estConnexe() {	
		boolean connexe = true;
		for( Map.Entry<S,Integer> compo : composantesConnexes().entrySet() ){	
			if(compo.getValue() !=1) {
				connexe = false;
			}
		}
	    return connexe;
	}
	    
	public Map<S,Integer> composantesConnexes() {
		Queue<S> q              =   new ArrayDeque<S>();
		List<S> sommets         =   getSommets();
		List<S> sommetsParcourus  =   new ArrayList<>();
		Map<S,Integer> composantes      = new HashMap<>();
		S sommetCourant         =   sommets.get(0);
		int p = 0 ;
		while(!(sommets.size() == sommetsParcourus.size())){
			q.add(sommetCourant);
			sommetsParcourus.add(sommetCourant);
			p++;
			while(!q.isEmpty()){
				composantes.put(q.peek(),p);
				q.remove();
				for(S s : successeurImmediat(sommetCourant)){
					if(!sommetsParcourus.contains(s)){
						q.add(s);	
						sommetCourant = q.peek();    
						sommetsParcourus.add(s);  
					}
				}
			}
			for(S s : sommets){
				if (!sommetsParcourus.contains(s)){
					sommetCourant = s ;
					break;
				}
			}
		}          
	return composantes;
	}

	public List<Queue<S>> etatsFile() {
			Queue<S> q              =   new ArrayDeque<S>();
			List<S> sommets         =   getSommets();
			List<S> sommetsParcourus  =   new ArrayList<>();
			List<Queue<S>> etatsDeLaFile = new ArrayList<Queue<S>>();
			S sommetCourant         =   sommets.get(0);
			while(!(sommets.size() == sommetsParcourus.size())){
				sommetsParcourus.add(sommetCourant);
				q.add(sommetCourant);
				Queue<S> q2 = new ArrayDeque<>(q);
				while(!q.isEmpty()){
					q2 = new ArrayDeque<>(q);
					q.remove();
					for(S s : successeurImmediat(sommetCourant)){
						if(!sommetsParcourus.contains(s)){
							q.add(s);	
							sommetsParcourus.add(s); 
						}	
						sommetCourant = q.peek();							
					}
					etatsDeLaFile.add(q2);
				}		
				etatsDeLaFile.add(q);
				for(S s : sommets){
					if (!sommetsParcourus.contains(s)){
						sommetCourant = s ;
						break;
					}				
				} 				
			} 
			return etatsDeLaFile;
		}

	public List<Queue<S>>  ordreParcoursSommets(){
		List<Queue<S>> f = new ArrayList<>(etatsFile()) ;
		List<Queue<S>> sommets = new ArrayList<>();
		Queue<S> temp = new ArrayDeque<>();
		temp.add(f.get(0).peek());
		sommets.add(temp);
		for(int i = 1 ; i < f.size() ; i++){
			temp = new ArrayDeque<>();
 			for(int j = 0 ; j < f.get(i).size() ; j++){
				 ArrayList<S> toA = new ArrayList<S>(f.get(i));
				if(!f.get(i - 1).contains(toA.get(j))){
					temp.add(toA.get(j));
				}
			}
			sommets.add(temp);
		}
		return sommets;
	}

}

	/*
	
	[[A], [B, E, F], [I], [H], [], [L], [], [], [C], [D, G, J], [K], [], [], [], [M], [N, O], [], []]
	
	A--B     1
	A--E     1
	A--F     1

	B--I     2 
	
	E--H     2

	I--L     3

	C--D
	C--G
	C--J

	D--K

	M--N
	M--O

	*/
