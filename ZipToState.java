package com.kriash.mainproject;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class ZipToState {
    
    private String zipcode1;
    private String state;
/**	
 * finds the state of a zipcode and 
 * stores the value of state and returns it
 * @param zipcode the value of zipcode as a string
 */
    public String ziptostate(String zipcode){
        GeoApiContext context = new GeoApiContext().setApiKey("API_KEY");
	zipcode1 = zipcode;
	GeocodingResult[] results;
	String formattedAddress = null;
	try{
            results = GeocodingApi.geocode(context, zipcode).await();
            formattedAddress = results[0].formattedAddress;
            String[] addressarray = formattedAddress.split(",");
            String stateandzipcode = addressarray[1];
            String[] standzc = stateandzipcode.split(" ");
            state = standzc[1];
	}
	catch(Exception e){
            //e.printStackTrace();
	}
        return state;
    }

}