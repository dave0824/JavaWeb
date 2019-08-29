package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Swap {

	@Test
	public void test1() {
		
		int i = 5;
		int j = 4;
		int k;
		k = i;
		i = j;
		j = k;
		System.out.println("i="+i+",j="+j);
	}
	@Test
	public void test2() {

				int i = 5;
				int j = 4;
				i = i + j;
				j = i - j;
				i = i - j;
				System.out.println("i="+i+",j="+j);
		}
	}

