package com.example.bank.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.bean.Newplayerregbean;
import com.example.bank.service.WettopupService;
import com.example.bank.service.WetwithdrawService;

@RestController
@RequestMapping("/api")
public class WeeklyLuckyController {
	
	@Autowired
	WettopupService wettopupService;
	
	@Autowired
	WetwithdrawService wetwithdrawService;
	
	@PostMapping("weekly-generate-sg")
	public ResponseEntity<Object> weeklygenerate(){
		
		
		Newplayerregbean newplayerregBean = new Newplayerregbean();
		newplayerregBean.setCompanyid1(1);
		newplayerregBean.setIssuedby("AdminTest-SG");
		
		Date currentdate = new Date();
		Calendar cf1 = Calendar.getInstance(); 
		cf1.setTime(currentdate); 
		cf1.add(Calendar.DATE, -8);
		currentdate = cf1.getTime();
		
		Date dtf = new Date();
		Calendar cf = Calendar.getInstance(); 
		cf.setTime(dtf); 
		cf.add(Calendar.DATE, -1);
		dtf = cf.getTime();
		
	Map<String,Double> top_useridlist = new HashMap<>();
		
		
		List<String> weeklytopupuserid = wettopupService.weeklyuserid(newplayerregBean.getCompanyid1(), currentdate, dtf);
		
		
		
          Iterator weeklytopupuserid_itr = weeklytopupuserid.iterator();
		
		while(weeklytopupuserid_itr.hasNext()) {
			
			Object[] obj = (Object[]) weeklytopupuserid_itr.next();
			
			List<String> weeklywithdrawuserid = wetwithdrawService.weeklywithdrawuserid(newplayerregBean.getCompanyid1(), currentdate, dtf,  String.valueOf(obj[0]), String.valueOf(obj[1]));
			
			if(weeklywithdrawuserid.isEmpty()) {
				
				top_useridlist.put(String.valueOf(obj[0]), Double.parseDouble(String.valueOf(obj[1])));
				
				
			}else {
				Iterator weeklywithdrawuserid_itr = weeklywithdrawuserid.iterator();
				
				while(weeklywithdrawuserid_itr.hasNext()) {
					
					Object[] obj1 = (Object[]) weeklywithdrawuserid_itr.next();
					
					double d1 = Double.parseDouble(String.valueOf(obj[2]));
					
					double d2 = Double.parseDouble(String.valueOf(obj1[2]));
					
					
					if (Double.compare(d1, d2) == 0) { 
						  
			            System.out.println("d1=d2"); 
			        } 
			        else if (Double.compare(d1, d2) < 0) { 
			  
			            System.out.println("d1<d2"); 
			        } 
			        else { 
			  
			            System.out.println("d1>d2"); 
			            
			            Double balance = d1-d2;
			            
			            top_useridlist.put(String.valueOf(obj[0]), balance);
			            
			            
			            
			        } 
					
					
				}
				
			}
			
			
		}
		
		
		Map<String, Double> top_useridlist_sort = sortHashMapByValues(top_useridlist);
		
		List<Entry<String, Double>> descending_sort = entriesSortedByValues(top_useridlist_sort);
		
		if(!descending_sort.isEmpty()) {
			
		}
		
		  int i =0 ;
		  
		  
		 /* Iterator<Entry<String, Double>> itr = ((Map<String, Double>) descending_sort).entrySet().iterator(); 
          
	        while(itr.hasNext()) 
	        { 
	             Map.Entry<String, String> entry = itr.next(); 
	             System.out.println("Key = " + entry.getKey() +  
	                                 ", Value = " + entry.getValue()); 
	        } 
		  
		  List<Map<String, Double>> list  = (List<Map<String, Double>>) descending_sort;// this is what you have already
 
		  for (Entry<String, Double> map : descending_sort) {
		      for (Entry<String, Double> entry : ((Map<String, Double>) map).entrySet()) {
		          String key = entry.getKey();
		          Object value = entry.getValue();
		      }
		  }*/
		  
		  
		  
		for (Entry<String, Double> map : descending_sort) {
		    for (Entry<String, Double> entry : ((Map<String, Double>) map).entrySet()) {
		    	
		    	if(i < 10) {
		        System.out.println(entry.getKey() + " - " + entry.getValue());
		    	}
		        
		        i++;
		    }
		}
		
		
		
		
		System.out.println("fsdgfggf");
		
		System.out.println("fsdgfggf");
		
		return null;
	}
	
	
	
	public LinkedHashMap<String, Double> sortHashMapByValues(
	        Map<String, Double> passedMap) {
	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<Double> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<String, Double> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Double> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	    	Double val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	        	String key = keyIt.next();
	        	Double comp1 = passedMap.get(key);
	        	Double comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}
	
	
	static <K,V extends Comparable<? super V>> 
    List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

Collections.sort(sortedEntries, 
    new Comparator<Entry<K,V>>() {
        @Override
        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
            return e2.getValue().compareTo(e1.getValue());
        }
    }
);

return sortedEntries;
}

}
