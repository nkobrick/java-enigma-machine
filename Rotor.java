import java.util.*;
/**
 * This a Rotor class that is meant to represent a rotor within the machine. 
 *
 * @author Nikki Kobrick
 * @version 6/8/2020
 */
public class Rotor
{
    //2D Array with two ArrayList cells. One for holding the wire difference and the other for holding its number.
    private ArrayList<Integer>[][] rotorPos; 
    /**
     * Constructs rotor with correct type and position
     * @param num - the type of rotor, pos - the starting position
     * Precondition - num will always be either 1,2, or 3. 
    */
    public Rotor(int num, int pos)
    {
        //initlialsing 2D array
        rotorPos = new ArrayList[2][1];
        //initialising ArrayList holding the wire difference
        rotorPos[0][0] = new ArrayList<Integer>();
        //initialising ArrayList holding the corresponding number
        rotorPos[1][0] = new ArrayList<Integer>();
        String wiring = "";
        if(num == 1)
        {
            //below is the wiring pairs for each rotor. Ex. for rotor one, a is matched to j when the current position is 1.
            wiring = "jgdqoxuscamifrvtpnewkblzyh";
        }
        else if(num == 2)
        {
            wiring = "ntzpsfbokmwrcjdivlaeyuxhgq";
        }
        else
        {
            wiring = "jviubhtcdyakeqzposgxnrmwfl";
        }
        setRotorPos(wiring, pos);
    }
    /**
     * This method fills the ArrayLists with the wiring differences as well as the corresponding numbers in the given starting position.
     * @param wiring - the String with the wiring pairs, pos - the starting position for the rotor
     */
    public void setRotorPos(String wiring, int pos)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        pos = pos - 1;
        //beginning with the starting position.
        for(int i = 0; i < 26; i++)
        {
            String pos1 = alphabet.substring(pos, pos + 1);
            String pos2 = wiring.substring(pos, pos + 1);
            //calculates, adds each difference into one ArrayList
            rotorPos[0][0].add(pos2.compareTo(pos1));
            //and the corresponding number in the other 
            rotorPos[1][0].add(pos + 1);
            pos = (pos + 1) % 26;
        }    
    }
    /**
     * @return - the 2D array of the wire differences and the corresponding numbers
     */
    public ArrayList[][] getRotorPos()
    {
        return rotorPos;
    }
    /**
     * Changes the order of the ArrayLists by putting the object at index 0 to the last index, shifting the order by one
     * (like how a rotor would turn).
     * @return - returns true if the rotor has returned to 1, signifying a full turn.
     */
    public boolean turn()
    {
        int diff = rotorPos[0][0].remove(0);
        int num = rotorPos[1][0].remove(0);
        rotorPos[0][0].add(diff);
        rotorPos[1][0].add(num);
        if(rotorPos[1][0].get(0) == 1)
        {
            return true;
        }
        return false;
    }
}

