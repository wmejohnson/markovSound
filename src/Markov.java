import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.Random;

/**
 * @author wmjohnson
 *
 * a markov model for characters
 * need to modify so that it can 
 * hold any sort of data 
 * 
 * updated 5/5/16, but untested for sound 
 */
public class Markov {

	public int Counter;
	private soundBuffer samples;
	private TreeMap<Integer ,Integer> following = new TreeMap<Integer ,Integer>();

	public Markov(soundBuffer samples) {
		//Construct a new Markov object representing the string substring 
		this.samples = samples;
		this.Counter = 0;
	}

	/**
	 * add to the tree of following characters 
	 * @param c
	 */
	public void add(Integer s) {
		this.Counter++;
		if(following.containsKey(s)) {
			//if it contains the key increment value
			following.put(s, following.get(s)+1);
		} else {
			//else add the key and instantiate value
			following.put(s, 1);
		}
	}

	/**
	 * increment the counter, how much this occurs 
	 */
	public void add() {
		this.Counter++;
	}

	/**
	 * return a string representation of this object
	 */
	public String toString() {
		return new String(this.samples.toString()+":"+this.Counter+":"+this.following.toString());	
	}

	/**
	 * 
	 * @return a random character based on proper weighting 
	 */
	public Integer random() {
		Collection<Integer> keys = this.following.keySet();
		ArrayList<Integer> table = new ArrayList<Integer>();
		Iterator<Integer> h = keys.iterator();
		Integer toReturn = null;

		//add each character to the array the number of times it appears
		while(h.hasNext()) {
			Integer t = h.next();
			for(int j = 0; j < this.following.get(t); j++) {
				table.add(t);
			}
		}

		//create random object 
		Random rnd = new Random();
		//System.out.println(table.size());
		try {
			int temp = rnd.nextInt(table.size());
			toReturn = table.get(temp);
		} catch (IndexOutOfBoundsException c) {
		} catch (IllegalArgumentException e) {
		}
		return toReturn;
	}
}

