package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import constants.LogLevel;
import model.Country;
import model.Player;

public class Utilities {
        
    /**
     * This method gets the integer value for the string that is passed to it.
     *
     * @param s The string that is to be converted to integer.
     * @return The integer value.
     */
    public static Integer getIntegerValue(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray())
        sb.append((int)c);

        Integer mInt = new Integer(sb.toString());
        return mInt;
    }
    
    /**
     * This method is used to maintain the logs in a text file for the game.
     * @param message
     */
    public static void gameLog(String message, LogLevel l) {
        String FilePath = "D:\\RiskLogs\\GAME-LOG";
        try {
            Utilities.createDirectoryIfNotExist("D:\\RiskLogs");
            File logFile = new File(FilePath);
            logFile.createNewFile();
            BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
            
            
            System.out.println(l+": "+message);
            wr.newLine();
            wr.write(l+": "+message);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method creates a directory to save the text file if it does not exist.
     * @param path
     */
    public static void createDirectoryIfNotExist(String path){
        System.out.println(path);
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("Creating dir" + path);
           System.out.println(file.mkdirs());

       }
    }
    
    public static Country getCountryFromPlayer(Player player,String countryName) {
        for(Country country: player.getCountries()) {
            if(countryName.equals(country.getName())) {
                return country;
            }
        }
        return null;
        
    }
    
    public static ArrayList<Country> getNeighborList(Player player, Country country) {
        
        ArrayList<Country> neighborList=new  ArrayList<Country>();
        System.out.println("shivani");
        System.out.println(country.getNeighborCounties().size());
        for(int g=0;g<country.getNeighborCounties().size();g++) {
            System.out.println(country.getNeighborCounties().get(g).getName());
            
        }
      
        for(Country neighbor: country.getNeighborCounties()){
        
         
            if(neighbor.getOwner().getName().equals(player.getName())) {
               
                neighborList.add(neighbor);
               
            }
        }
            return neighborList;
        }
}