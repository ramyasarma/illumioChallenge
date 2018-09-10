package illumioChallenge;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fireWall {
		String csv;
		static HashMap<String, HashMap<String, String>> map = new HashMap<>();
		fireWall(String csv)
		{
			this.csv = csv;
			// Initializing the HashMap from the rules file
	        String line = "";
	        String cvsSplitBy = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] rules = line.split(cvsSplitBy);
	                String direction = rules[0];
	                String protocol = rules[1]; 
	                String port = rules[2];
	                String ip_address = rules[3];
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
	                    //System.out.println(lowl + " " + highl);

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
	                    //System.out.println(lowl + " " + highl);

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
	                        //Integer lowPort = Integer.parseInt(port.split("-")[0]);
	                        //Integer highPort = Integer.parseInt(port.split("-")[1]);
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
		// TODO : Accept function goes here
		// TODO : Remove this before commit; Printing
		public static void print()
		{
			for(Map.Entry<String, HashMap<String, String>> entry : map.entrySet())
			{
				System.out.print("Key:" + entry.getKey());
				System.out.print("Value:" + entry.getValue());
					
			}
		}
		public static boolean accept_packet(String direction, String protocol,Integer port,String ip_address)
		{
			if(map.containsKey(ip_address+port))
			{
				HashMap<String, String> submap = map.get(ip_address+port);
				if(submap.containsKey(direction))
				{
					if(submap.get(direction).equals(protocol))
						return true;
					else 
						return false;
				}
				else return false;
				
			}
			else 
				return false;
			
		}
		public static void main(String[] args)
		{
			fireWall fw = new fireWall("C:\\Users\\rsarm\\eclipse-workspace\\illumioChallenge\\src\\illumioChallenge\\fw.csv");
			//print();
			System.out.println(accept_packet("inbound", "tcp", 80, "256.174.130.100"));
			System.out.println(accept_packet("inbound", "tcp", 80, "192.174.130.100"));
		}
}
