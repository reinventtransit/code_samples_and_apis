package cowtriplocationparser;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * @author tmac
 * Simple JSON pull.... using Java because has built in extensions for KML 
 * mapping features and the example worked.
 * 
 */
public class ReInventTransitSimpleExample {
 
    public static void main(String args[]){
        
        System.out.println("Pulling Obfuscated / Commuting Start Locations");
        
        ArrayList<Coordinate>coordinates = new ArrayList<Coordinate>();

        try{

            URL cowtripHome = new URL("http://cowtrip.com/api/trips/home_locations.json");
            URLConnection cowtripConnection = cowtripHome.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(cowtripConnection.getInputStream()));
            String inputLine = in.readLine();
         
            in.close();

            // We have read in the locations, now we want to view them.

            Object obj = JSONValue.parse(inputLine);
            JSONArray array = (JSONArray) obj;

            for (int i = 0; i < array.size(); i++) {

                JSONObject location = (JSONObject) array.get(i);
                String lat = location.get("lat").toString();
                String lon = location.get("lng").toString();
                coordinates.add(new Coordinate(Double.parseDouble(lon), Double.parseDouble(lat)));
                System.out.println(lat + "," + lon);      
            }
        } 
        catch (Exception e){e.printStackTrace();}
        
    }
    
}
