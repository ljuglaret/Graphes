package visuel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import representation.Graphe;

public class DessinGraphe<E> {

	private Graphe<E> graphe;
	public DessinGraphe(Graphe<E> graphe){
		this.graphe = graphe;
	}

	public  void conversionDot()  throws IOException, InterruptedException {
			
		String output = "visuel/input2.dot";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		writer.write("digraph mygraph{" + System.lineSeparator());
		writer.write(" node [shape=plaintext]" + System.lineSeparator());		 
		
		for(Map.Entry<E, Map<E , Boolean>> arc : graphe.getGraphe().entrySet()) {
			for(Map.Entry<E , Boolean> sommet2 : arc.getValue().entrySet()) {
				if(sommet2.getValue()) {
					writer.write(arc.getKey() + "->"+ sommet2.getKey() +  "\n");
				}
			} 	
		}
		writer.write("}");
		writer.close();
	}
	
}
