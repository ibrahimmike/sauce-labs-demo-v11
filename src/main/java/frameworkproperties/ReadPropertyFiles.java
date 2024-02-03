package frameworkproperties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ReadPropertyFiles {
    //private static Properties properties = new Properties();
    private static Properties properties;
    private static Map<String, String> configParam = new HashMap<>();

    private static String configPath = "frameworkconfig/default.properties";

    //src/test/resources/
            //"frameworkconfig/default.properties";
    //src/test/resources/
            //"frameworkconfig/default.properties";
    //Constants.getConfigParams();

    static{
        try{
            //  FileInputStream reader = new FileInputStream(configPath);
            properties = readProperties();
            // properties.load(reader);
            for(Map.Entry prop : properties.entrySet() ){
                if(System.getProperties().stringPropertyNames().contains(prop.getKey().toString())){
                    properties.setProperty(prop.getKey().toString(), System.getProperty(prop.getKey().toString()));
                }

            }
            properties.entrySet().forEach(
                    entry->configParam.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()).trim()));




        }catch (Exception e){
            throw  new RuntimeException("The file is not found");

        }
    }
    public static String getPropertyValue(String key){
        String value = null;
        if( Objects.nonNull(configParam.get(key))){

            value = configParam.get(key);
            System.out.println(value);
        }else{
            throw new RuntimeException("The value of the " + key + " does not exit in the configparams file");
        }
        return value;
    }
    private static Properties readProperties(){
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(configPath)){
            properties.load(input);
        }catch(Exception e){
            throw new RuntimeException("Unable to read the property file " + configPath );
            //  log.error("Unable to read the property file {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }

}
