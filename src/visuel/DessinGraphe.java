package visuel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import representation.*;

public class DessinGraphe<E> {

	protected String fleche;
	protected String motClef;
	private Graphe<E> graphe;
	public DessinGraphe(Graphe<E> graphe){
		this.graphe = graphe;
		if(graphe instanceof GrapheNonOriente){
			fleche = "--";
			motClef = "graph";
		}

		if(graphe instanceof GraphesOrientes){
			fleche = "->";
			motClef = "digraph";
		}
	}

	public  void conversionDot()  throws IOException, InterruptedException {
		
		String output = "visuel/input2.dot";
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		writer.write(motClef+" mygraph{" + System.lineSeparator());
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
