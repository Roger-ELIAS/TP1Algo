public class ParcoursEnProfondeur {

    private int[] dates;
    private int[] pred;
    private boolean[] couleurs;
    private int date=0;
    private Graph G;
    public ParcoursEnProfondeur(Graph G){
        this.G=G;
        int cardinal = G.order();
        dates = new int[cardinal];
        pred = new int[cardinal];
        couleurs = new boolean[cardinal];
        for(int i=0;i<cardinal;++i) {
            couleurs[i] = false;
            pred[i]=-1;
        }
        for(int i=0;i<cardinal;++i) {
            if (couleurs[i] == false)
                visiter(i);
        }
    }

    private void visiter(int i) {
        ++date;
        //G.iterEdges();
    }
}
