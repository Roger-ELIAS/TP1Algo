import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){
        try {
            int nb;
            int source;
            int dest;
            Scanner fichier = new Scanner(new File("formula1.txt"));
            //while (sc.hasNextLong()) {
            //    long aLong = sc.nextLong();
            //}
            nb = fichier.nextInt();
            System.out.println(nb);
            Graph<Integer> G = new Graph<Integer>(nb*2);
            while (fichier.hasNextInt()) {
                source = fichier.nextInt();
                if(source<0)
                    source = source + nb*2;
                dest = fichier.nextInt();
                if(dest<0)
                    dest = dest + nb*2;
                G.addArc(source,dest,0);
                System.out.println(source + " | "+ dest);
            }

            System.out.println(G.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
