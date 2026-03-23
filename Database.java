import java.io.*;
/**
 * This class is meant to load and save data from a txt file
 *
 * @author Nikki Kobrick
 * @version 6/8/2020
 */
public class Database
{
    //the rotor settings of the Machine
    private String[] setting;
    /**
     * Creates a Database with the user settings
     */
     public Database(String one, String two, String three)
    {
        setting = new String[3];
        setting[0] = one;
        setting[1] = two;
        setting[2] = three;
        
    }
    /**
     * Creates a Database with the previously saved rotor settings by loading the data
     */
    public Database()
    {
            setting = new String[3];
            String fileName = "settings.txt";
            try
            {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                setting[0] = bufferedReader.readLine();
                setting[1] = bufferedReader.readLine();
                setting[2] = bufferedReader.readLine();
                bufferedReader.close();
            }
            catch(FileNotFoundException ex) 
            {
                System.out.println("Unable to open file '" + fileName + "'");
            }
            catch(IOException ex) 
            {
                System.out.println("Error reading file '" + fileName + "'");    

            
            }
    }
    /**
     * Saves the rotor settings back into the txt file
     */
    public void saveData()
    {
        String fileName = "settings.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(setting[0]);
            bufferedWriter.newLine();
            bufferedWriter.write(setting[1]);
            bufferedWriter.newLine();
            bufferedWriter.write(setting[2]);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
            catch(IOException ex) 
        {
            System.out.println("Error writing to file '" + fileName + "'");
        }    
    }
    /**
     * @return - the array of rotor settings
     */
    public String[] getSetting()
    {
        return setting;
    }
    /**
     * This method sets the new rotor settings to the setting variable.
     * @param one - the rotor one setting, two - the rotor two setting, three - the rotor three setting.
     */
    public void setSetting(String one, String two, String three)
    {
        setting[0] = one;
        setting[1] = two;
        setting[2] = three;
    }
}