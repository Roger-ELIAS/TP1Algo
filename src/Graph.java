
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }

        public int getDestination() {
            return destination;
        }

        public int getSource() {
            return source;
        }

        public Label getLabel() {
            return label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public String toString() {
        String result = new String("");
        result+=(cardinal + "\n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result+=(e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;

    }

    public ArrayList<Integer> getVoisins(int source) {
        ArrayList<Integer> result = new ArrayList();
        for (Edge edge : incidency.get(source)){
            result.add(edge.getDestination());
        }
        return result;
    }


    public interface ArcFunction<Label,K> {
        public K apply(int source, int dest, Label label, K accu);
    }

    public interface ArcConsumer<Label> {
        public void apply(int source, int dest, Label label);
    }

    public <K> K foldEdges(ArcFunction<Label,K> f, K init) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                init = f.apply(e.source, e.destination, e.label, init);
            }
        };
        return init;
    }

    public void iterEdges(ArcConsumer<Label> f) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                f.apply(e.source, e.destination, e.label);
            }
        }
    }

    public Graph GraphT() {
        Graph GraphT = new Graph(this.cardinal);
        for (int i = 0;i<cardinal;i++) {
            for (Edge edge : this.incidency.get(i)){
                GraphT.addArc(edge.getDestination(),edge.getSource(),edge.getLabel());
            }
        }
        return GraphT;
    }

    public boolean Verif(ArrayList<ArrayList<Integer>> i){
        for (ArrayList test : i ){
            for(int j = 0 ; j<test.size(); j++){
                int jj = negation(j,cardinal/2);
                for(int k=0;k<test.size();k++)
                {
                    if(k==jj)
                        return false;
                }
            }
        }
        return true;
    }

    public int negation(int source, int n) {
        //for( int i=0;i<2*nb;++i)
        //    System.out.println(i+":"+negation(i,nb));
        return n*2 - source -1;
    }
}
