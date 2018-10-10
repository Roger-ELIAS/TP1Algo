import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main (String[] args){

        try {
            int nb;
            int source;
            int dest;
            Scanner fichier = new Scanner(new File("sat10"));
            nb = fichier.nextInt();
            //System.out.println(nb);
            Graph<String> G = new Graph<String>(nb*2);
            System.out.println("Implications des clauses");
            while (fichier.hasNextInt()) {
                source = fichier.nextInt(); //(1,2,....,n,-n,-n+1,....,-1)
                if(source<0)
                    source =  nb*2 + source;
                else
                    --source;
                dest = fichier.nextInt();
                if(dest<0)
                    dest = nb*2 + dest ;
                else
                    --dest;
                System.out.println(source+"u"+dest+" == ("+negation(source,nb)+"=>"+dest+")^("+negation(dest,nb)+"=>"+source+")");
                G.addArc(negation(source,nb),dest,"("+(source)+" U "+(dest)+")");
                G.addArc(negation(dest,nb),source,"("+(source)+" U "+(dest)+")");
            }

            System.out.println("\nGraph des implications: \n"+G.toString());
            ParcoursEnProfondeur parc = new ParcoursEnProfondeur(G);
            Graph Gtranspose = G.GraphT();
            System.out.println("Graph transposé des implications: \n"+Gtranspose.toString());
            ParcoursEnProfondeur parc2 = new ParcoursEnProfondeur(Gtranspose,parc.getDates());
            if(G.Verif(parc2.getComposantesConnexes()))
                System.out.println("Le problème SAT-2 est satisfiable");
            else
                System.out.println("Le problème SAT-2 n'est pas satisfiable");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static int negation(int source, int n) {
        //for( int i=0;i<2*nb;++i)
        //    System.out.println(i+":"+negation(i,nb));
        return n*2 - source -1;
    }

}
