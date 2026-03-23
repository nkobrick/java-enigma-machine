import java.util.*;
/**
 * This class runs the machine by creating a Machine and a Database as well as taking input
 *
 * @author Nikki Kobrick
 * @version 6/8/2020
 */
public class MachineRunner
{
    public static void main(String args[])
    {
        Scanner keyboard = new Scanner(System.in);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Database d;
        introduction();
        String input = keyboard.nextLine();
        String one = "";
        String two = "";
        String three = "";
        if(input.equals("e"))
        {
            //initializes Database with the saved rotor settings for encryption
            d = new Database();
            one = d.getSetting()[0];
            two = d.getSetting()[1];
            three =  d.getSetting()[2];
        }
        else
        {
            //takes input from user for rotor settings for decryption
            System.out.println("Type the valid 1 - 26 setting of the first rotor. Ex. if 24-13-8, type 8.");
            one = keyboard.nextLine();
            System.out.println("Type the valid 1 - 26 setting of the second rotor. Ex. if 24-13-8, type 13.");
            two = keyboard.nextLine();
            System.out.println("Type the valid 1 - 26 setting of the second rotor. Ex. if 24-13-8, type 24.");
            three = keyboard.nextLine();
            //initliazes Databse with those user rotor settings
            d = new Database(one, two, three);
        }
        //initializes Machine with appropriate settings, depending on decryption or encryption
        Machine m = new Machine(Integer.parseInt(one),Integer.parseInt(two),Integer.parseInt(three));
        System.out.println("Type in an individual letter to get that letter of the ciphertext.");
        System.out.println("Or - Type \"exit\" to leave.");
        //takes in letter to be encrypted/decrypted until the user desires to exit the program
        String letter = keyboard.nextLine();
        while((!letter.equals("exit")))
        {
            if(alphabet.indexOf(letter) == -1)
            {
                System.out.println("Invalid. Try again");
            }
            else 
            {
                //prints the current rotor settings of the turn
                System.out.println("Rotor positions: " + m.getRotors()[2].getRotorPos()[1][0].get(0) + "-" + 
                m.getRotors()[1].getRotorPos()[1][0].get(0) + "-" + m.getRotors()[0].getRotorPos()[1][0].get(0));
                //prints the encrypted/decrypted letter
                System.out.println(m.findCodedLetter(alphabet.indexOf(letter)));
            }
            letter = keyboard.nextLine();
        }
        //modifies the rotor settings of the Database
        d.setSetting(m.getRotors()[0].getRotorPos()[1][0].get(0).toString(), m.getRotors()[1].getRotorPos()[1][0].get(0).toString(),
        m.getRotors()[2].getRotorPos()[1][0].get(0).toString());
        //saves those rotor settings
        d.saveData();
        System.out.println("Your position has been saved.");
        System.out.println("Stay safe, Lieutenant. We are counting on you.");
    }
    
    public static void introduction()
    {
        System.out.println("*---* CONFIDENTIAL *---*");
        System.out.println("Greetings, Lieutenant. This is a recreation of the Enigma machine, a German encryption device.");
        System.out.println("This machine uses the 3 rotors and reflector of the German Railway (Rocket) model, and there is no plugboard.");
        System.out.println("If you are encypting a message, type \"e\". If you are decrypting a message, type \"d\".");
    }
}
