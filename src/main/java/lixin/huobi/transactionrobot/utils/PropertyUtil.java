package lixin.huobi.transactionrobot.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component
public class PropertyUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    public static String BASENAME = "config.properties";
    //public static String MEAL = "meal.properties";
    String[] propnames = {BASENAME};
    public static final PropertyUtil instance = new PropertyUtil();
    static {
        instance.loadAll();
    }

    public static String getBase(String key) {
        return instance.propMap.get(BASENAME).get(key);
    }

    public static String getPublic(String file, String key) {
        return instance.propMap.get(file).get(key);
    }

    private HashMap<String, HashMap<String, String>> propMap =
            new HashMap<String,  HashMap<String, String>>();

    private void loadAll() {
        for (String propname : propnames) {
            if (!loadPropMap(propname)) {
                logger.error("Cannot load properties:" + propname);
                System.exit(-1);
            }
        }
    }
    private boolean loadPropMap(String filename) {
        HashMap<String, String> map = new HashMap<String, String>();
        Properties properties = new Properties();
        InputStream in = null;
        in = PropertyUtil.class.getResourceAsStream("/" + filename);
        try{
            properties.load(in);
            Set<Object> set = properties.keySet();
            for (Object key : set) {
                map.put(key.toString(), properties.getProperty(key.toString()));
            }
            properties.clear();
            properties = null;
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        propMap.put(filename, map);
        if (map.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Map<String, String> getAllFieldByProperty(String filename){
        HashMap<String, String> map = new HashMap<String, String>();
        Properties properties = new Properties();
        InputStream in = null;
        in = PropertyUtil.class.getResourceAsStream("/" + filename);
        try{
            properties.load(in);
            Set<Object> set = properties.keySet();
            for (Object key : set) {
                map.put(key.toString(), properties.getProperty(key.toString()));
            }
            properties.clear();
            properties = null;
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        if (map.isEmpty()) {
            return null;
        } else {
            propMap.put(filename, map);
            return map;
        }
    }
}
