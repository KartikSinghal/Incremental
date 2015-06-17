package SocialGroupAccretion;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class traintestfilesgenerate{
    private Map<Integer, Integer> edgecount = new HashMap<Integer, Integer>();
    public traintestfilesgenerate(BufferedReader in,int split) throws IOException{
      String line="";
      while(!((line=in.readLine())==null)){
          StringTokenizer st = new StringTokenizer(line,",");
          int index=Integer.parseInt(st.nextToken());
          int year = Integer.parseInt(st.nextToken());
          if(year<=split){
              if(!this.edgecount.containsKey(index)){
                  edgecount.put(index, 1);
              }
          }
      }
      System.out.println(edgecount.size());
    }
    
}
