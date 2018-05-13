import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * calculates the distance between two addresses
 * @author ashle
 *
 */
public class DistanceCalculator
{
        private static String[] StoresInStateArray;
	private static GeoApiContext GeoApiContex;
	GeoApiContext context = new GeoApiContext().setApiKey("API_KEY");
	String[] origin;
	String[] destination;
	
/**
 * 	takes in an array of origin addresses and destination addresses and calculates the distance between them
 *  and stores the distances in meters in an array of double.
 * @param origin string array of origin addresses
 * @param destination string array of destination addresses
 * @throws Exception
 */
	public double[] distance(String[] origin, ArrayList<String> destination) throws Exception
	{
                StoresInStateArray = destination.toArray (new String[destination.size()]);
                this.origin = origin;
	
		DistanceMatrixApiRequest d = DistanceMatrixApi.getDistanceMatrix(context, origin, StoresInStateArray);
		DistanceMatrix a = d.await();
		DistanceMatrixRow[] r = a.rows;
		
		DistanceMatrixElement[] e = r[0].elements;
		double[] distanceinmeters = new double[StoresInStateArray.length];
		
		String[] destaddress = new String[StoresInStateArray.length];
		for(int i=0; i < StoresInStateArray.length; i++)
		{
			Distance di = e[i].distance;
			long distinmeters = di.inMeters;
			distanceinmeters[i] = distinmeters;
		}
		double[] distanceInMiles = new double[distanceinmeters.length];
                for (int j = 0;j < distanceinmeters.length; j++){
                    distanceInMiles[j] = distanceinmeters[j] * 0.000621371;
                }
                return distanceInMiles;
	}	
/**
 * 	takes in an array of distances in miles and gives an array of distances with in 25 miles.
 * @param distance 
 * @return
 */
	public ArrayList<String> sortdistance(double[] distance, ArrayList<String> StoreAddresses)
	{	
		DistanceMatrixApiRequest d = DistanceMatrixApi.getDistanceMatrix(context, origin, destination);
		for(int i=0; i< distance.length; i++)
		{
			if(distance[i] <= 25)
			{
                            StoreAddresses.set (i, StoreAddresses.get(i) + String.valueOf (distance[i]));
			}else {
                            StoreAddresses.remove(i);
                        }
		}
                StoreAddresses.removeAll (Arrays.asList(null,""));
		return StoreAddresses;
        }
}