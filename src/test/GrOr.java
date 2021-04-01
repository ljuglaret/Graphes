package test;
import representation.*;

import java.io.IOException;
import java.util.*;
import visuel.*;


public class GrOr {

    public static void main(String[] args) throws IOException,InterruptedException {

        
        GraphesOrientes<String> gr = new GraphesOrientes<>();

        ArrayList<LinkedList<String>>listei = new ArrayList<LinkedList<String>>(); 
        listei.add(new LinkedList<String>( Arrays.asList("x1", "x2")));
        listei.add(new LinkedList<String>(Arrays.asList("x2","x1")));
        listei.add(new LinkedList<String>(Arrays.asList("x3", "x5")));
        listei.add(new LinkedList<String>(Arrays.asList("x4","x2","x3","x5")));
        listei.add(new LinkedList<String>(Arrays.asList("x5","x3","x6")));
        listei.add(new LinkedList<String>(Arrays.asList("x6","x7")));
        listei.add(new LinkedList<String>(Arrays.asList("x7","x1","x3")));

        for(LinkedList<String> ls : listei){
            gr.ajouterSommet(ls.get(0));
        }
        for(LinkedList<String> ls : listei){
            for(int i = 1 ; i < ls.size() ; i++) {
                gr.ajouterArc(ls.get(0),ls.get(i));
            }
        }
      
        gr.parcoursProfondeur("x4");
       
        DessinGraphe<String> dessin = new DessinGraphe<String>(gr);
		dessin.conversionDot();
        // dot -Tjpg visuel/input2.dot -o visuel/input2.jpg
    }
}
