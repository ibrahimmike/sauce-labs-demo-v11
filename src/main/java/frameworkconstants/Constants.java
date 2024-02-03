package frameworkconstants;

import frameworkproperties.ReadPropertyFiles;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    private Constants(){

    }

    private  static DateTime time = new DateTime();

    private static DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd--HH_mm_ss");
    private static String dtStr = fmt.print(time);
    private static String path;



    private static final String CONFIGPARAMS = "configparams";
    //System.getProperty("usr.dir","src/test/java/frameworkConfig/configparams");
    private static String EXTENTREPORT = "testOutput";
    //System.getProperty("usr.dir","src/testsOutput");

    public static String GRID_URL_FORMAT = "selenium.grid.urlformat";
    public static String GRID_HUB_HOST = "selenium.grid.hubHost";
    public static String OVERRIDETESTS = "overrideTests";

    public static String RUNMODE = "runMode";

    public static String BROWSER = "browser";

    public static String URL="url";



    public static String getConfigParams(){
        return CONFIGPARAMS;
    }

    public static String getExtentReportPath(){
        if(ReadPropertyFiles.getPropertyValue(OVERRIDETESTS).equalsIgnoreCase("no")){
            path = EXTENTREPORT + "/"+ dtStr + ".html";
        }else {
            path = EXTENTREPORT +"/index.html";
        }
        return path;
    }
    public static Map<String, String> createUserData(){
        Map<String, String> data = new HashMap<>();
        try {
            URI uri = new URI("https://dummyjson.com/users");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int statusCode = connection.getResponseCode();
            System.out.println(statusCode);
            JSONArray ja ;
            String line;
            StringBuffer bl = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                bl.append(line);
            }
            Object fo  = new JSONObject(bl.toString());
            reader.close();

            JSONObject jfo = (JSONObject) fo;
            ja = jfo.getJSONArray("users");
            JSONObject userOneData = ja.getJSONObject(0);
            String name = userOneData.get("firstName").toString().trim();
            String lastName = userOneData.getString("lastName").toString().trim();
            String zipCode = userOneData.getJSONObject("address").getString("postalCode");
            data.put("name", name);
            data.put("lastName", lastName);
            data.put("zipCode", zipCode);

        }catch(URISyntaxException e){

        }catch (IllegalArgumentException e ){

        }catch(MalformedURLException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return data;

    }
}
