package visuel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import representation.*;

public class DessinGraphe<E> {

	private Graphe<E> graphe;
	public DessinGraphe(Graphe<E> graphe){
		this.graphe = graphe;
	}

	public  void conversionDot( String typeGraphe )  throws IOException, InterruptedException {
		String motClef ="";
		String fleche="";
		
		if (typeGraphe.equals("oriente")){
			motClef = "digraph";
			fleche = "->";
		}
		if(typeGraphe.equals("nonoriente")){
			motClef = "graph";
			fleche = "--";
		}

		String output = "visuel/input2.dot";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		writer.write(motClef + " mygraph{" + System.lineSeparator());
		writer.write(" node [shape=plaintext]" + System.lineSeparator());		 
		
		for(Map.Entry<E, Map<E , Boolean>> arc : graphe.getGraphe().entrySet()) {
			for(Map.Entry<E , Boolean> sommet2 : arc.getValue().entrySet()) {
				if(sommet2.getValue()) {
					writer.write(arc.getKey() + fleche + sommet2.getKey() +  "\n");
				}
			} 	
		}
		writer.write("}");
		writer.close();
	}
	
}
