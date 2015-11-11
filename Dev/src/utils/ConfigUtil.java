/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class ConfigUtil
{
    private static String m_file = "config";

    public static List<Map<String, String>> getConfiguration()
    {
        List<Map<String, String>> configuration = new ArrayList<Map<String, String>>();

        try
        {
            FileInputStream fis = new FileInputStream(m_file);
            Properties config = new Properties();
            config.load(fis);

            for(String property : config.stringPropertyNames())
            {
                Map<String, String> map = new HashMap<String, String>();
                map.put(property, config.getProperty(property));
                configuration.add(map);
            }

            fis.close();

            fis = null;
            config = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return configuration;
    }

    public static String getConfiguration(String key)
    {
        String value = new String();
        try
        {
            FileInputStream fis = new FileInputStream(m_file);
            Properties config = new Properties();
            config.load(fis);

            value = config.getProperty(key);
            fis.close();

            fis = null;
            config = null;

            if (value == null)
                throw new Exception("La valeur correspondant à '" + key + "' n'existe pas dans le fichier '" + m_file+"'");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return value;
    }
    
    public static char getCharOf(String key)
    {
    	return ConfigUtil.getConfiguration(key).charAt(0);
    }

    public static void setConfiguration(String key, String value)
    {
        try
        {
            Properties config = new Properties();
            FileInputStream fis = new FileInputStream(m_file);
            config.load (fis);
            fis.close();
            FileOutputStream fos = new FileOutputStream(m_file);

            config.setProperty(key, value);

            config.store (fos, "Last update :");

            fos.close();
            fos = null;
            fis = null;
            config = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
