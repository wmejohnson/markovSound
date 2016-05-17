import static org.junit.Assert.*;

import org.junit.Test;

public class soundBufferTest {

	@Test
	public void testSoundBuffer() {
		soundBuffer test = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test.set(i,i);
		}
	}

	@Test
	public void testCompareTo() {
		soundBuffer test = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test.set(i,i);
		}
		soundBuffer test2 = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test2.set(i,i);
		}
		assertEquals("test compare", 0, test.compareTo(test2));
		test.set(5,  100);
		assertEquals("test compare", 1, test.compareTo(test2));
	}

	@Test
	public void testGet() {
		soundBuffer test = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test.set(i,i);
		}
		assertEquals("test get", 1, test.get(1));
	}

	@Test
	public void testSet() {
		soundBuffer test = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test.set(i,i);
		}
		test.set(5, 100);
		assertEquals("test set", 100, test.get(5));
	}

	@Test
	public void testEqualsSoundBuffer() {
		soundBuffer test = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test.set(i,i);
		}
		soundBuffer test2 = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test2.set(i,i);
		}
		assertEquals("test equals", true, test.equals(test2));
	}

	@Test
	public void testToString() {
		soundBuffer test = new soundBuffer(10);
		for(int i = 0; i < 10; i++){
			test.set(i,i);
		}
		System.out.println(test.toString());
	}

}
