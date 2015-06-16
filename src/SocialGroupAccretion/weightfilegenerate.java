
package SocialGroupAccretion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class weightfilegenerate {
    
    public static void main(String[] args) throws IOException {
        
        String CollabSparsetuples = "/home/enigmatus/Incremental/Incremental Work/resources/tr(2001-07)test(08-10)/collab_sparse_tuples_out.txt";
        String CollabTime = "/home/enigmatus/Incremental/Incremental Work/resources/tr(2001-07)test(08-10)/collab_time_out.txt";
        String weightfile = "/home/enigmatus/Incremental/Incremental Work/resources/tr(2001-07)test(08-10)/collab_weighttrain.txt";
        int split = 2007;
        
        BufferedReader tuples = new BufferedReader(new FileReader(CollabSparsetuples));
        BufferedReader time = new BufferedReader(new FileReader(CollabTime));
        BufferedWriter out = new BufferedWriter (new FileWriter(weightfile));
        
        new weightfiletrain(tuples,time,out,split);
    }
}

class Hyperedgedatastruct{
    int count;
    int vertices;
    int index;
    public Hyperedgedatastruct(int index){
     this.count=0;
     this.vertices=0;
     this.index=index;
    }
    public int getCount(){return this.count;}
    public int getVertices(){return this.vertices;}
    public int getIndex(){return this.index;}
    
    public void setCount(int count){this.count=count;}
    public void setVertices(int vertices){this.vertices=vertices;}
    public void setIndex(int index){this.index=index;}
}

class weightfiletrain{
    private Map<Integer,Hyperedgedatastruct> hyperedgedatalist= new LinkedHashMap<Integer,Hyperedgedatastruct>();
    
    public weightfiletrain(BufferedReader union, BufferedReader time, BufferedWriter out,int split) throws IOException{
       updateedgecount(time,split);
       updateverticescount(union);
       writetofile(out);
    }
    
    public void writetofile(BufferedWriter out) throws IOException{
        Double weight;
        for(Map.Entry<Integer, Hyperedgedatastruct> edge : this.hyperedgedatalist.entrySet()){
            int index = edge.getKey();
            Hyperedgedatastruct ed = edge.getValue();
            weight=Math.log(ed.getCount() + 1)/ed.getVertices();
            out.write(String.valueOf(index)+","+String.valueOf(ed.getCount())+","+String.valueOf(ed.getVertices()+","+String.valueOf(weight)));
            out.newLine();
        }   
    }
    
    public void updateedgecount(BufferedReader time, int split) throws IOException{
        String line;
        while(!((line=time.readLine())==null)){
            StringTokenizer st=new StringTokenizer(line,",");
            int index=Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            if(year<=split){
                System.out.println(index+" "+year);
                if(this.hyperedgedatalist.containsKey(index)){
                    this.hyperedgedatalist.get(index).setCount(this.hyperedgedatalist.get(index).getCount()+1);
                }
                else{
                  Hyperedgedatastruct newedge = new Hyperedgedatastruct(index);
                  newedge.setCount(newedge.getCount()+1);
                  this.hyperedgedatalist.put(index, newedge);
                }
            }
        }
    }
    
    public void updateverticescount(BufferedReader union) throws IOException{
        String line;
        while(!((line=union.readLine())==null)){
            StringTokenizer st=new StringTokenizer(line,",");
            int index=Integer.parseInt(st.nextToken());
            if(this.hyperedgedatalist.containsKey(index)){
                this.hyperedgedatalist.get(index).setVertices(this.hyperedgedatalist.get(index).getVertices()+1);
            }
        }
    }
    
    
}
