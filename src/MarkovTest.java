import static org.junit.Assert.*;

import org.junit.Test;

public class MarkovTest {

	@Test
	public void testMarkov() {
		soundBuffer sb = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			sb.set(i,i);
		}
		Markov test = new Markov(sb);
	}

	@Test
	public void testAddInteger() {
		soundBuffer sb = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			sb.set(i,i);
		}
		Markov test = new Markov(sb);
		test.add(1);
		test.add(2);
		test.add(1);
		System.out.println(test.toString());
	}

	@Test
	public void testAdd() {
		soundBuffer sb = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			sb.set(i,i);
		}
		Markov test = new Markov(sb);
		test.add();
		test.add();
		test.add();
		System.out.println(test.toString());
	}

	@Test
	public void testToString() {
		soundBuffer sb = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			sb.set(i,i);
		}
		Markov test = new Markov(sb);
		test.add();
		test.add();
		test.add();
		System.out.println(test.toString());
	}

	@Test
	public void testRandom() {
		soundBuffer sb = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			sb.set(i,i);
		}
		Markov test = new Markov(sb);
		test.add(1);
		test.add(2);
		test.add(1);
		System.out.println(test.random());
		System.out.println(test.random());
		System.out.println(test.random());
		System.out.println(test.random());
		System.out.println(test.random());
	}

}
