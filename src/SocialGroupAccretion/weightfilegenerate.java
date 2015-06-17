
package SocialGroupAccretion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class weightfilegenerate {
    
    public static void main(String[] args) throws IOException {
        
        String CollabSparsetuples = "./resources/boundary_2005/train_2003-2005 test_2006-2009/collab_sparse_tuples_out.txt";
        String CollabTime = "./resources/boundary_2005/train_2003-2005 test_2006-2009/collab_time_out.txt";
        String weightfile = "./resources/boundary_2005/train_2003-2005 test_2006-2009/collab_weighttrain.txt";
        int split = 2005;
        
        BufferedReader tuples = new BufferedReader(new FileReader(CollabSparsetuples));
        BufferedReader time = new BufferedReader(new FileReader(CollabTime));
        BufferedWriter out = new BufferedWriter (new FileWriter(weightfile));
        
        weightfiletrain weightfiletrain = new weightfiletrain(tuples,time,out,split);
        BufferedReader time1 = new BufferedReader(new FileReader(CollabTime));
        new traintestfilesgenerate(time1,split);
    }
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
        out.close();
    }
    
    public void updateedgecount(BufferedReader time, int split) throws IOException{
        String line;
        while(!((line=time.readLine())==null)){
            StringTokenizer st=new StringTokenizer(line,",");
            int index=Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            if(year<=split){
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
    
    public Map<Integer, Hyperedgedatastruct> getList(){return this.hyperedgedatalist;}
    
}
