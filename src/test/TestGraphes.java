package test;
import representation.GrapheNonOriente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import visuel.*;


public class TestGraphes {

    public static void main(String[] args) throws IOException,InterruptedException {

        GrapheNonOriente<String> gr = new GrapheNonOriente<>();
        ArrayList<LinkedList<String>>listei = new ArrayList<LinkedList<String>>(); 
        listei.add(new LinkedList<String>( Arrays.asList("A","B","E","F")));
        listei.add(new LinkedList<String>(Arrays.asList("B","A","I")));
        listei.add(new LinkedList<String>(Arrays.asList("C","D","G","J")));
        listei.add(new LinkedList<String>(Arrays.asList("D","C","G","K")));
        listei.add(new LinkedList<String>(Arrays.asList("E","A","H")));
        listei.add(new LinkedList<String>(Arrays.asList("F","A","H")));
        listei.add(new LinkedList<String>(Arrays.asList("G","C","D")));
        listei.add(new LinkedList<String>(Arrays.asList("H","E","F","I")));
        listei.add(new LinkedList<String>(Arrays.asList("I","B","H","L")));
        listei.add(new LinkedList<String>(Arrays.asList("J","C","K")));
        listei.add(new LinkedList<String>(Arrays.asList("K","D","J")));
        listei.add(new LinkedList<String>(Arrays.asList("L","I")));
        listei.add(new LinkedList<String>(Arrays.asList("M","N","O")));
        listei.add(new LinkedList<String>(Arrays.asList("N","M","O")));
        listei.add(new LinkedList<String>(Arrays.asList("O","M","N")));

        for(LinkedList<String> ls : listei){
            gr.ajouterSommet(ls.get(0));
        }
        for(LinkedList<String> ls : listei){
            for(int i = 1 ; i < ls.size() ; i++) {
                gr.ajouterArc(ls.get(0),ls.get(i));
            }
        }
        //gr.parcours();
        //gr.composantesConnexes();
        gr.foret();
        //System.out.println(gr.foret());
        DessinGraphe<String> dessin = new DessinGraphe<String>(gr);
		dessin.conversionDot();
        // dot -Tjpg visuel/input2.dot -o visuel/input2.jpg
    }
}
