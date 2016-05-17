import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wmjohnson
 * open up a wav file and write a 
 * randomly produced wave file based on a markov 
 * model
 */
public class soundGen {

	public static void main(String[] args) throws IOException, WavFileException{
		//the order of the chain 
		int k = Integer.parseInt(args[0]);
		//the number of samples to generate
		int m = Integer.parseInt(args[1]);
		// Open the wav file specified as the first argument
		WavFile wavFile = WavFile.openWavFile(new File(args[2]));
		int sampleRate = (int) wavFile.getSampleRate();

		//the hashmap
		HashMap<soundBuffer, Markov> hm = new HashMap<soundBuffer, Markov>();

		//exit if the file is more than one channel
		if(wavFile.getNumChannels() > 1){
			System.err.println("doesn't support more than mono yet");
		}

		//iterate through the samples and generate markov model

		//get length
		//getNumFrames returns a long, and could be very
		//figure out the maximum size of sound file when casting like this
		
		int length = (int) wavFile.getNumFrames();
		//int length = 4410000;
		
		//create audio buffer
		soundBuffer buffer = new soundBuffer(length);
		
		//read frames into buffer 
		int[] tempBuf = new int[length];
		wavFile.readFrames(tempBuf, length);

		System.out.println("reading file");
		for(int i = 0; i < length; i++){
			//copy frames from temp into buffer object 
			buffer.set(i, tempBuf[i]);
		}
		
		/**
		//min max test
		System.out.println(buffer.toString());
		int max = 0;
		int min = 0;
		//get max and min just to test
		for(int s = 0; s < length){
			//			System.out.print(s);
			if(s<min){
				min = s;
			}
			if(s>max){
				max = s;
			}
			//for each sample in the buffer
		}
		System.out.println();
		System.out.println(max);
		System.out.println(min);
		*/
		
		//add to hash map
		System.out.println("constructing model");
		for(int i = 0; i<length-k+1; i++) {
			//System.out.print("2");
			//word is int[] of size k
			soundBuffer x = new soundBuffer(k);
			for(int h = i; h<i+k; h++) {
				x.set(h - i, buffer.get(h));
				//System.out.println(x.toString());
			}
			//System.out.println(s);
			if(hm.containsKey(x)) {
				try {
					hm.get(x).add(buffer.get(i+k));
				} catch (IndexOutOfBoundsException e) {
					hm.get(x).add();
				}
			} else {
				//System.out.println(HashMap.get(s).Counter);
				Markov temp = new Markov(x);
				try {
					temp.add(buffer.get(i+k));
					hm.put(x, temp);
				} catch (IndexOutOfBoundsException e) {
					temp.add();
					hm.put(x, temp);
				}
			}
			x = null;
		}
		
		//HASH MAP MADE!
		//System.out.println(hm.toString());
		//System.out.println(hm.size());
		//System.out.print(buffer.toString());

		//now reconstruct sound and spit it out 
		System.out.println("reconstruction");
		soundBuffer outputBuffer = new soundBuffer(m+1);
		soundBuffer sub = new soundBuffer(k);
		Markov prev = null;
		for(int i = 0; i < k; i++) {
			//set the first k in the buffer to the original 
			outputBuffer.set(i, buffer.get(i));
		}
		for(int j = 0; j < m -k ; j++) {
			//get the next sub buffer to query for random number
			for(int i = j; i < k + j; i++) {
				sub.set(i-j, outputBuffer.get(i));
			}
			Markov temp = hm.get(sub);
			if(temp != null) {
				outputBuffer.set(j+k, temp.random());
				prev = temp;
				//System.out.println("not null");
			} else {
				//System.out.println("null");
  				
				//What happens when we construct a buffer that 
				//doesn't exist in the model?
				
				//one more radical option would be to build some leeway into the equals method
				//this is the "flex equals in soundBuffer"
				//could write a hm.getSimilar(soundBuffer) method
				
				//get a random value from previously used markov model
				//outputBuffer.set(j+k, prev.random());
				
				//just use the last computed value +1
				outputBuffer.set(j+k, sub.get(k-1)+1);
				
				//could just start it over and run it until it works
				//j = 0;
			}
			sub = new soundBuffer(k);
			//prev = temp;
		}

		int outMax = 0;
		int outMin = 0;
		//System.out.println(outputBuffer.toString());
		WavFile outputFile = WavFile.newWavFile(new File("testout.wav"), 1, m, 16, sampleRate);
		tempBuf = new int[m];
		for(int i = 0; i < m; i ++){
			int sample = outputBuffer.get(i);
			tempBuf[i] = sample;
			if(sample < outMin){
				outMin = sample;
			}
			if(sample > outMax){
				outMax = sample;
			}
		}
		System.out.println("outMin: "+outMin);
		System.out.println("outMax: "+outMax);
		outputFile.writeFrames(tempBuf, m);
		outputFile.close();

	}
}
