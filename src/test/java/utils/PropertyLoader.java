package utils;

import java.io.*;
import java.util.Properties;

public class PropertyLoader extends CommonUtil{

    Properties properties;
    public PropertyLoader(String fileName)  {
       String filePath = System.getProperty("user.dir")+propertyPath+fileName + propExtension;
        try {
            FileReader reader=new FileReader(filePath);
            this.properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException i) {
            i.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}


