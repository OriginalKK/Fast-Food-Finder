package com.kriash.mainproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * this class processes the input addresses;
 * by getting the address components
 */
public class Address 
{
	private static GeoApiContext GeoApiContext;
	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBFn3rOMJj9ubGd3FqtgVxDkUddCfmtXmU");
	private String streetnumber;
	private String streetname;
	private String city;
	private String county;
	private String state;
	private String zipcode;
	private LatLng latlng;	
	
/**
 * 	
 * @param address
 */
	public Address(String address)
	{
		GeocodingResult[] results;
		String formattedAddress = null;
		String [] components = new String [7];
		int i = 0;
		try 
		{	
			
			formattedAddress = formatAddress(address);
			results = GeocodingApi.geocode(context, formattedAddress).await();
                        //formatAddress (results[0].formattedAddress);
                        String[] parts1 = results[0].formattedAddress.split (",");
                        String[] parts2 = parts1[2].split (" ");
                        zipcode = parts2[2];
                        /*for(int j=0; j< results[i].addressComponents.length; j++)
			{
                            components[j] = results[i].addressComponents[j].shortName;
					
                        }*/
			streetnumber = components[0];
			streetname = components[1];
			city = components[2];
			county = components[4];
			latlng = coordinates(address);
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
		}
	}
/**
* returns formatted address
* @param address
*  @return
*/
	public String formatAddress(String address)
	{
		GeocodingResult[] results;
		String formattedAddress = null;
		try
		{
			results = GeocodingApi.geocode(context, address).await();
			formattedAddress = results[0].formattedAddress;
			//String formattedAddress1 = results[1].formattedAddress;
			String[] addressarray = formattedAddress.split(",");
			String stateandzipcode = addressarray[2];
			String[] str = stateandzipcode.split(" ");
			state = str[1];
			zipcode = str[2];
			String country = addressarray[3];
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		return formattedAddress;
	}
	//public String lattoZipcode(LatLng)
		

/**
* 
* @param address of the location
* @return the latitude and longitude of an address as a string
* 
*/
	public LatLng coordinates(String address)
	{
		GeocodingResult[] results;
		LatLng latlng = null;
		try
		{
			results = GeocodingApi.geocode(context, address).await();
			//coordinates = results[0].geometry.location.toString();
			latlng = results[0].geometry.location;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return latlng;
	}

/**
 * 
 */
	public String getStreetnumber()
	{
		return streetnumber;
	}
	public String getCity()
	{
		return city;
		
	}
	
	
/**
 * 
 */
	public String getStreetname()
	{
		return streetname;
	}
/**
* 
* @param address
* @return the zipcode of an address as a string
*/
	public String getZipcode()
	{
            return zipcode;
	}
/**	
* 
* @return the state
*/
	public String getState()
	{	
		return state;
	}
/**
* 
* @return the latitude and longitude of an address
*/
	public LatLng getLatLng()
	{
		return latlng;
	}	
}
