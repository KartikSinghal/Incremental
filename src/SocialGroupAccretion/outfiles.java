package SocialGroupAccretion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class outfiles {
		
		public static void main(String[] args) {
			

		     try { 
		    	 Map<Integer,List<Integer>> time = new HashMap<Integer, List<Integer>>();
		    	 Map<Integer,Integer> train = new HashMap<Integer, Integer>();
		    	 Map<Integer,Integer> test = new HashMap<Integer, Integer>();
		    	 List<Integer> arrayList = new ArrayList<Integer>();

		    	 List<Integer> arrayList3 = new ArrayList<Integer>();
		    	 BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		 	    
		    	 
		    	 String csvFile1 = "./resources/tr(2001-07)test(08-10)/collab_sparse_tuples_out.txt";
		    	 String csvFile2 = "./resources/tr(2001-07)test(08-10)/collab_time_out.txt";
		    	
		    	 int split=2007;
                         FileWriter f1 = new FileWriter("./resources/tr(2001-07)test(08-10)/trainnew.txt");
		    	 BufferedWriter out1 = new BufferedWriter(f1);
		    	 FileWriter f2 = new FileWriter("./resources/tr(2001-07)test(08-10)/testnew.txt");
		    	 BufferedWriter out2 = new BufferedWriter(f2);
		    	 BufferedReader br1 = new BufferedReader(new FileReader(csvFile1));
		    	 BufferedReader br2= new BufferedReader(new FileReader(csvFile2));
		    	 /*--------------------------------------------------*/
		    	 String line1 = "";
		    	 String line2 = "";
		    	 StringTokenizer st1 = null;
		    	 StringTokenizer st2 = null;
		    	 /*--------------------------------------------------*/
		    	 String key="",value="";
		    	 String keyweight="",valueweight="";
		    	 int current;
		    	 int previous=-2;
		    	 int linenumber=0;
		    	 int linenumber1=0;
		    	// StringBuilder key = new StringBuilder();
		    	 while ((line2 = br2.readLine()) != null) {
		    		 st2=new StringTokenizer(line2,",");
		    		 linenumber++;
		    		 
		    		 key=st2.nextToken();
		    		 value=st2.nextToken();
                                 
                                 
		    		 /////***Change the year here***/////
		    		 if(Integer.parseInt(value)<=split){
		    			 train.put(Integer.parseInt(key), 1);
		    		 }
		    		 else
		    			 test.put(Integer.parseInt(key), 1);
		    		 
		    	 }
		    	 while((line1=br1.readLine())!=null){
		    		 linenumber1++;
		    		 st1=new StringTokenizer(line1,",");
		    		 keyweight=st1.nextToken();
		    		 valueweight=st1.nextToken();
			    			if(train.containsKey(Integer.parseInt(keyweight))){
					    		 out1.write(line1);
				    			 out1.flush();
				    			 out1.newLine();
				    			 out1.flush();
					    	 }
			    			if(test.containsKey(Integer.parseInt(keyweight)))
			    			{
			    				out2.write(line1);
				    			 out2.flush();
				    			 out2.newLine();
				    			 out2.flush();
			    			}
//			    			
		    	 }
		    	 
		    		br1.close();
			    	 br2.close();
			    	// out.close();
			    	 out1.close();
			    	 out2.close();
		    	 
		     } catch (Exception e) {
		    	 e.printStackTrace(System.out);
		     }
		     }

	
}