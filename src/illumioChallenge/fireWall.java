package illumioChallenge;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fireWall {
		String csv;
		HashMap<String, HashMap<String, String>> map = new HashMap<>();
		fireWall(String csv)
		{
			this.csv = csv;
			// Initializing the HashMap from the rules file
		    //String csvFile = "/Users/mkyong/csv/country.csv";
	        String line = "";
	        String cvsSplitBy = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] rules = line.split(cvsSplitBy);
	                String direction = rules[0];
	                String protocol = rules[0]; 
	                String port = rules[0];
	                String ip_address = rules[0];
	                if(ip_address.contains("-") && port.contains("-"))
	                {
	                    String lowIP=ip_address.split("-")[0];
	                    String highIP=ip_address.split("-")[1];
	                    String[] lowparts = lowIP.split("\\.");
	                    double low = Double.parseDouble(lowparts[3])*Math.pow(256,0)+Double.parseDouble(lowparts[2])*Math.pow(256,1)+Double.parseDouble(lowparts[1])*Math.pow(256,2)+Double.parseDouble(lowparts[0])*Math.pow(256,3);

	                    String[] highparts = highIP.split("\\.");
	                    double high = Double.parseDouble(highparts[3])*Math.pow(256,0)+Double.parseDouble(highparts[2])*Math.pow(256,1)+Double.parseDouble(highparts[1])*Math.pow(256,2)+Double.parseDouble(highparts[0])*Math.pow(256,3);

	                    long lowl = (long) low;
	                    long highl = (long) high;
	                    System.out.println(lowl + " " + highl);

	                    for(long i=lowl;i<=highl;i++){
	                        List<String> sb = new ArrayList();
	                        long x = i;
	                        int count = 4;
	                        while(x>0){
	                            sb.add(0,x%256+".");
	                            x = x/256;
	                            count--;
	                        }
	                        while(count>0) {
	                            sb.add(0, 0 + ".");
	                            count--;
	                        }

	                        StringBuilder sb1 = new StringBuilder();
	                        for(int j=0;j<sb.size();j++) {
	                            sb1.append(sb.get(j));
	                        }
	                        String ip = sb1.toString().substring(0,sb1.length()-1);
	                        //System.out.println(sb1.toString().substring(0,sb1.length()-1));
	                        Integer lowPort = Integer.parseInt(port.split("-")[0]);
	                        Integer highPort = Integer.parseInt(port.split("-")[1]);
	                        for(Integer i1=lowPort; i1<=highPort;i1++)
	                        {
	                        	HashMap<String,String> submap = new HashMap<String,String>();
	                        	submap.put(direction, protocol);
	                        	map.put(ip + String.valueOf(i1), submap);
	                        }
	                        
	                    }
	                }
	                else if(ip_address.contains("-") && !port.contains("-")) {
	                    String lowIP=ip_address.split("-")[0];
	                    String highIP=ip_address.split("-")[1];
	                    String[] lowparts = lowIP.split("\\.");
	                    double low = Double.parseDouble(lowparts[3])*Math.pow(256,0)+Double.parseDouble(lowparts[2])*Math.pow(256,1)+Double.parseDouble(lowparts[1])*Math.pow(256,2)+Double.parseDouble(lowparts[0])*Math.pow(256,3);

	                    String[] highparts = highIP.split("\\.");
	                    double high = Double.parseDouble(highparts[3])*Math.pow(256,0)+Double.parseDouble(highparts[2])*Math.pow(256,1)+Double.parseDouble(highparts[1])*Math.pow(256,2)+Double.parseDouble(highparts[0])*Math.pow(256,3);

	                    long lowl = (long) low;
	                    long highl = (long) high;
	                    System.out.println(lowl + " " + highl);

	                    for(long i=lowl;i<=highl;i++){
	                        List<String> sb = new ArrayList();
	                        long x = i;
	                        int count = 4;
	                        while(x>0){
	                            sb.add(0,x%256+".");
	                            x = x/256;
	                            count--;
	                        }
	                        while(count>0) {
	                            sb.add(0, 0 + ".");
	                            count--;
	                        }

	                        StringBuilder sb1 = new StringBuilder();
	                        for(int j=0;j<sb.size();j++) {
	                            sb1.append(sb.get(j));
	                        }
	                        String ip = sb1.toString().substring(0,sb1.length()-1);
	                        //System.out.println(sb1.toString().substring(0,sb1.length()-1));
	                        Integer lowPort = Integer.parseInt(port.split("-")[0]);
	                        Integer highPort = Integer.parseInt(port.split("-")[1]);
	                        HashMap<String,String> submap = new HashMap<String,String>();
	                        submap.put(direction, protocol);
                        	map.put(ip + String.valueOf(port), submap);
	                    }
	                	
	                }
	                else if(!ip_address.contains("-") && port.contains("-")) {
                        Integer lowPort = Integer.parseInt(port.split("-")[0]);
                        Integer highPort = Integer.parseInt(port.split("-")[1]);
                        for(Integer i1=lowPort; i1<=highPort;i1++)
                        {
                        	HashMap<String,String> submap = new HashMap<String,String>();
                        	submap.put(direction, protocol);
                        	map.put(ip_address + String.valueOf(i1), submap);
                        }
	                }
	                else //!ip_address.contains("-") && !port.contains("-")
	                {
                       	HashMap<String,String> submap = new HashMap<String,String>();
                    	submap.put(direction, protocol);
                    	map.put(ip_address + port, submap);
	                	
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
		}
		// Accept function goes here
		
		public static void main(String[] args)
		{
			fireWall fw = new fireWall("C:\\Users\\rsarm\\eclipse-workspace\\illumioChallenge\\src\\illumioChallenge\\fw.csv");
			
		}
}
