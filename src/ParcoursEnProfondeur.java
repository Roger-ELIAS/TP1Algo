import java.util.ArrayList;

public class ParcoursEnProfondeur {

    private int[] dates;
    private int[] pred;
    private boolean[] couleurs;
    private int date=0;
    private Graph G;
    public ArrayList<ArrayList<Integer>> composantesConnexes = new ArrayList<ArrayList<Integer>>();
    
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
    
    public ParcoursEnProfondeur(Graph G,int[] datesfin1){
        this.G=G;
        int cardinal = G.order();
        dates = new int[cardinal];
        pred = new int[cardinal];
        int[] ordre = new int[cardinal];
        
        for(int j=0;j<cardinal;++j){
        	int max=0;
        	int index=0;
        	for(int i=0;i<cardinal;++i) {
        		/*
        		System.out.println("max:"+max);
        		System.out.println("datesfin1[i]"+datesfin1[i]);
        		*/
                if(datesfin1[i]>max){
                	max=datesfin1[i];
                	index=i;
                }
        	}
        	ordre[j]=index;
        	datesfin1[index]=0;
        	/*
        	for(int i=0;i<cardinal;++i) {
            	System.out.println(ordre[i]);
            }
        	System.out.println("------------------------");
        	*/
        }
        /*
        for(int i=0;i<cardinal;++i) {
        	System.out.println(ordre[i]);
        }*/
        
        couleurs = new boolean[cardinal];
        for(int i=0;i<cardinal;++i) {
            couleurs[i] = false;
            pred[i]=-1;
        }
        
        boolean[] estComposanteConnexe = new boolean[cardinal];
        for(int j=0;j<cardinal;++j){
        	for(int i=0;i<cardinal;++i) {
        		if (i==ordre[j])
        			if (couleurs[i] == false){
        				visiter(i);
    					ArrayList<Integer> composanteConnexe = new ArrayList<Integer>();
        				for(int k=0;k<cardinal;++k) {
        	        		if(couleurs[k] && !estComposanteConnexe[k]){
        	        			composanteConnexe.add(k);
        	        			estComposanteConnexe[k]=true;
        	        		}
        	        	}
        				composantesConnexes.add(new ArrayList<Integer>(composanteConnexe));
        			}
        	}
        }
        /*
        for(int i=0;i<cardinal;++i) {
        	System.out.println(i + "|dates "+dates[i]+"|pred "+pred[i]+"|couleur "+couleurs[i]);
        }
        System.out.println(date);
    	*/
        System.out.println(composantesConnexes.toString());
    }

    public int[] getDates(){
    	return dates;
    }
    
    public ArrayList<ArrayList<Integer>> getComposantesConnexes(){
    	return composantesConnexes;
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
