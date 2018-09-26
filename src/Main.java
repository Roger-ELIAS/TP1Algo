import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){

        try {
            int nb;
            int source;
            int dest;
            Scanner fichier = new Scanner(new File("formula1.txt"));
            nb = fichier.nextInt();
            System.out.println(nb);
            Graph<String> G = new Graph<String>(nb*2);
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
                //System.out.println(source+"u"+dest+" == ("+negation(source,nb)+"=>"+dest+")^("+negation(dest,nb)+"=>"+source+")");
                G.addArc(negation(source,nb),dest,(source)+"u"+(dest));
                G.addArc(negation(dest,nb),source,(source)+"u"+(dest));
            }
            System.out.println(G.toString());

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
