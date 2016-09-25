/**
 * sound buffer class for markov generation 
 * @author wmjohnson
 *
 */
public class soundBuffer implements Comparable<soundBuffer>{
	
	private int size;
	private int[] array;
	
	public soundBuffer(int size){
		this.size = size;
		this.array = new int[size];
	}
	
	public int size(){
		return this.size;
	}
	
	/**
	 * return 0 if equals
	 * else return 1
	 */
	@Override
	public int compareTo(soundBuffer o){
		if(this.equals(o)){
			return 0;
		} 
		return 1;
	}
	
	/**
	 * 
	 * @param index
	 * @return value at sound buffer index 
	 */
	public int get(int index){
		return this.array[index];
	}
	
	/**
	 * 
	 * @param index
	 * @param value to set index
	 * @return true 
	 */
	public boolean set(int index, int value){
		this.array[index] = value;
		return true;
	}
	
	/**
	 * 
	 * @param o
	 * @return true if equal size and elements 
	 */
	@Override
	public boolean equals(Object o){
		if(!(o instanceof soundBuffer)){
			//System.out.println("not instanceof");
			return false;
		}
		soundBuffer sb = (soundBuffer) o;
		if(this.size != sb.size){
			//System.out.println("not size");
			System.out.println("size error");

			return false;
		}
		//string equals
//		for(int i = 0; i < this.size; i++){
//			if(this.get(i) != sb.get(i)){
//				System.out.println(this.toString()+sb.toString()+"not value");
//				return false;
//			}
//		}
//		
		// flex equals //
		//this allows two soundbuffers to be equal to each other within a certain amount of deviation 
		
		for(int i = 0; i < this.size; i++){
			int temp = Math.abs(this.get(i) - sb.get(i));
			if(temp > 10000){
				//System.out.println(this.toString()+sb.toString()+"not value");
				System.out.println("range error");

				return false;
			}
		}
		//System.out.println("true");
		return true;
	}
	
	@Override
	public int hashCode(){
		int sum = 0;
		for(int i = 0; i < this.array.length; i++){
			sum += this.array[i];
		}
		return sum;
	}
	
	/**
	 * @return  a string representation of the buffer 
	 */
	public String toString(){
		String s = "[";
		for(int i = 0; i < this.size; i++){
			s += this.get(i) + ", ";
		}
		s += "]";
		return s;
	}
}
