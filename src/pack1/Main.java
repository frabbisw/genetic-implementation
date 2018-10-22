package pack1;

import java.util.Random;

public class Main {
	public static void main(String [] args)
	{
		int baccharSize=8,genes=5, a, b, c;
		Random random = new Random();
		
		c=random.nextInt(baccharSize);
		
		b = 1<<c;
		a = (1<<baccharSize)-1-b;
		
		if((genes&b)!=0)	genes = genes&a;
		else	genes = genes|b;
		
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(c);
		System.out.println(Integer.toBinaryString(genes));
		System.out.println(Integer.toBinaryString(genes));
	}
}
