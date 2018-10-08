public class ParcoursEnProfondeur {

    private int[] dates;
    private int[] pred;
    private boolean[] couleurs;
    private int date=1;
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

        
        /*test
        for(int i=0;i<cardinal;++i) {
        	System.out.println(i + "|dates "+dates[i]+"|pred "+pred[i]+"|couleur "+couleurs[i]);
        }
        System.out.println(date);
    	*/
    }

    private void visiter(int i) {
        ++date;
        couleurs[i] = true;
        for(int ii=0; ii< G.getVoisins(i).size();++ii)
        	if (couleurs[(int) G.getVoisins(i).get(ii)]==false){
        		visiter((int) G.getVoisins(i).get(ii));
        		pred[(int) G.getVoisins(i).get(ii)]=i;
        	}
        dates[i]=date;
        ++date;
    }
}
