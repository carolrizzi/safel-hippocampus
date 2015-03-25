package main.rule;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.junit.Test;

import uk.ac.kent.cs.Hippocampus;

public class GeneralTestRuleTest {

	@Test
	public final void test() {
		try{
			Hippocampus h = new Hippocampus();
			
			ArrayList<Integer[]> array = new ArrayList<Integer[]>();
			array.add(new Integer [] {1,0,0});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,1,4});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,1,6});
			array.add(new Integer [] {0,1,7});
			array.add(new Integer [] {0,1,8});
			array.add(new Integer [] {0,1,-1});
			array.add(new Integer [] {0,1,-1});
			array.add(new Integer [] {0,1,9});
			array.add(new Integer [] {1,0,6});
			array.add(new Integer [] {1,0,5});
			array.add(new Integer [] {1,0,3});
			array.add(new Integer [] {1,0,0});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {1,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,0,-1});
			array.add(new Integer [] {0,1,7});
			array.add(new Integer [] {0,1,9});
			array.add(new Integer [] {0,1,-1});
			array.add(new Integer [] {0,1,-1});
			array.add(new Integer [] {0,1,-1});
			array.add(new Integer [] {0,1,1});
			
			int count = 0;
			for (Integer[] a : array){
				System.out.println("Starting loop " + (++count));
				byte [] b1 = ByteBuffer.allocate(4).putInt(a[0]).array();
				byte [] b2 = ByteBuffer.allocate(4).putInt(a[1]).array();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
				outputStream.write(b1);
				outputStream.write(b2);
				byte [] b = outputStream.toByteArray( );
				h.insertEnvironmentalCue(b);
				if(a[2] >= 0){
					h.insertAdrenaline((double)a[2]/10);
				}
			}
			
			h.disposeSession();
		}catch(Exception e){
			e.printStackTrace();
			fail("Exception Caught.");
		}
	}

}
