package representation;
import java.lang.reflect.Array;
import java.util.*;

import javax.management.Query;
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
	    
		public void composantesConnexes() {
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
					//composante(sommet) = p
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
			for (Map.Entry<S,Integer> entry : composantes.entrySet()) {
				//System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
		}


		public List<Queue<S>> foret() {
			Queue<S> q              =   new ArrayDeque<S>();
			List<S> sommets         =   getSommets();
			List<S> sommetsParcourus  =   new ArrayList<>();
			List<Queue<S>> etatsDeLaFile = new ArrayList<Queue<S>>();
			S sommetCourant         =   sommets.get(0);
			while(!(sommets.size() == sommetsParcourus.size())){
				sommetsParcourus.add(sommetCourant);
				q.add(sommetCourant);
				Queue<S> q2 = new ArrayDeque<>(q);
				//System.out.println(q);
				//etatsDeLaFile.add(q2);

				while(!q.isEmpty()){
					q2 = new ArrayDeque<>(q);

					q.remove();
					for(S s : successeurImmediat(sommetCourant)){
						if(!sommetsParcourus.contains(s)){
							q.add(s);	
							sommetCourant = q.peek(); 
							sommetsParcourus.add(s); 
						}	
						else{
							sommetCourant = s;
							sommetCourant = q.peek();
						}			
					}					
					//System.out.println(q);
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
			System.out.println(etatsDeLaFile);

			return etatsDeLaFile;
		}
	}
