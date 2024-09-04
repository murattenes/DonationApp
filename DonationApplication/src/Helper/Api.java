package Helper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Api {
    public static List<String> getProvinces() {
        String url = "https://turkiyeapi.herokuapp.com/api/v1/provinces?fields=name&sort=name";
        ArrayList<String> provinces = new ArrayList<>();

        try {
            // Create a URL object
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the input stream from the connection and create a BufferedReader to read it
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Read each line from the input stream and append it to the content StringBuilder
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Close the BufferedReader
            in.close();

            // Convert the content to a JSON object
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Iterate over the JSON array and add each province name to the ArrayList
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject provinceObject = dataArray.getJSONObject(i);
                String provinceName = provinceObject.getString("name");
                provinces.add(provinceName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return provinces;
    }
    
    public static List<String> getDistricts(String province) {
        List<String> districtNames = new ArrayList<>();
        
        try {
            // Encode the province name to handle special characters
            String encodedProvince = URLEncoder.encode(province, StandardCharsets.UTF_8.toString());
            String url = "https://turkiyeapi.herokuapp.com/api/v1/provinces?name=" + encodedProvince + "&sort=name";

            // Create a URL object
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Check the response code before proceeding
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Got response code " + responseCode);
                return districtNames;  // return empty list if not successful
            }

            // Get the input stream from the connection and create a BufferedReader to read it
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Read each line from the input stream and append it to the content StringBuilder
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Close the BufferedReader
            in.close();

            // Convert the content to a JSON object
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Assuming there is at least one province in the data array
            if (dataArray.length() > 0) {
                JSONObject provinceObject = dataArray.getJSONObject(0);
                JSONArray districtsArray = provinceObject.getJSONArray("districts");

                // Iterate over the districts array and add each district name to the districtNames list
                for (int i = 0; i < districtsArray.length(); i++) {
                    JSONObject districtObject = districtsArray.getJSONObject(i);
                    String districtName = districtObject.getString("name");
                    districtNames.add(districtName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return districtNames;
    }
    
    public static List<String> getNeighborhoods(String province, String district) {
        List<String> neighborhoodNames = new ArrayList<>();
        
        try {
            // Encode the province name to handle special characters
            String encodedDistrict = URLEncoder.encode(district, StandardCharsets.UTF_8.toString());
            String url = "https://turkiyeapi.herokuapp.com/api/v1/neighborhoods";

            // Create a URL object
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Check the response code before proceeding
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Got response code " + responseCode);
                return neighborhoodNames;  // return empty list if not successful
            }

            // Get the input stream from the connection and create a BufferedReader to read it
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Read each line from the input stream and append it to the content StringBuilder
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Close the BufferedReader
            in.close();

            // Convert the content to a JSON object
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Assuming there is at least one province in the data array
            if (dataArray.length() > 0) {
                for (int i = 0; i < dataArray.length(); i++) {
                	JSONObject aa = dataArray.getJSONObject(i);
                	if(province.equals(aa.get("province"))) {
                		if(district.equals(aa.get("district")))
                		neighborhoodNames.add((String) aa.get("name"));
                	}
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return neighborhoodNames;
    }

}
