import java.util.*;
/**
 * This class represents the Engima machine with the three Rotors and the behaviours of the machine
 *
 * @author Nikki Kobrick
 * @version 6/8/2020
 */
public class Machine
{
    //array of the three Rotors
    private Rotor[] rotors;
    /**
     * Constructs the array and fills it with three Rotors tat are initializes with the type and initial position
     * @param pos1 - starting position for rotor one, pos2 - starting position for rotor two, pos3 - starting position for rotor three
     */
    public Machine(int pos1, int pos2, int pos3)
    {
        rotors = new Rotor[3];
        rotors[0] = new Rotor(1, pos1);
        rotors[1] = new Rotor(2, pos2);
        rotors[2] = new Rotor(3, pos3);
    }
    /**
     * This method is a "turn" by modifying the index through all of the rotors and the reflector mimicking the real machine.
     * @param index - the index of the letter that the user wants to encrypt/decrypt.
     * @return - the index of the encrypted/decrypted letter that was modified within the method
     * Pre-condition - param index will always be between 0 - 25 inclusive
     * Postcondition - the return value will always be between 0 - 25 inclusive
     */
    public String findCodedLetter(int index)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //the letters below represent the pairs that serve as the reflector. Ex. a is wired to q so q is wired to a.
        String pairs = "qyhognecvpuztfdjaxwmkisrbl";
        //passes through all three of the rotors
        index = rotorPass(rotors[0], index);
        index = rotorPass(rotors[1], index);
        index = rotorPass(rotors[2], index);
        //the index output of the rotors is changed to the index of the pair
        index = alphabet.indexOf(pairs.substring(index, index + 1));
        //the index now passes the rotor backwards
        index = rotorBackPass(rotors[2], index);
        index = rotorBackPass(rotors[1], index);
        index = rotorBackPass(rotors[0], index);
        //the call for all necessary turns
        necessaryTurns();
        return alphabet.substring(index, index + 1);
    }
    
    /**
     * Always turns the first rotor and only turns the second and third when first and second rotor complete a turn, respectively.  
     */
    public void necessaryTurns()
    {
        
        if(rotors[0].turn())
        {
            if(rotors[1].turn())
            {
                rotors[2].turn();
            }
        }
        
    }
    /**
     * Adds the wire difference to the index to simulate the "passing of the current" through the wiring of the real machine
     * @param r - the rotor that the index will be passes through, i - the current index
     * @return - the modified index
     */
    public int rotorPass(Rotor r, int i)
    {
        int updateI = 0;
        ArrayList<Integer> rotor = r.getRotorPos()[0][0];
        int num = rotor.get(i);
        //adds the difference while adjusting the index by using mod
        updateI = mod((i + num), 26);
        return updateI;
    }
    /**
     * This method passes the index back through the rotors after going through the reflector
     * @param r - the rotor that the index will be passes through, i - the current index
     * @return - the modified index
     */
    public int rotorBackPass(Rotor r, int i)
    {
        int updatedI = 0;
        ArrayList<Integer> rotor = r.getRotorPos()[0][0];
        for(int x = 0; x < 26; x++)
        {
            //because we don't know the wiring difference between the current index and the modified index that is needed,
            //it is necessary to see what index + difference gives you the current index, essentially working backwards
            if(i == mod((x + rotor.get(x)),  26))
            {
                updatedI = x;
                x = 26;
            }
        }
        return updatedI;
    }
    /**
     * @return - the array of Rotors
     */
    public Rotor[] getRotors()
    {
        return rotors;
    }
    /**
     * As % in java isn't actually mod, but rather gives the remainder, errors occur when dealing with negative number. This method adjusts 
     * for that so that the number would "roll back" if the calculated index was negative.
     * @param a - the dividend, b - the divisor
     * @return - the adjusted "remainder"
     */
    public int mod(int a, int n)
    {
        int num = a % n;
        if(num < 0)
        {
            num = n + num;
        }
        return num;
    }
    
}
