package pack1;

public class Chromosome implements Comparable<Chromosome>{
	private int value,size;
	private double fitness;
	public Chromosome(int value, int size, FitnessFunction function)
	{
		this.value=value;
		fitness=function.calculateFitness(value);
		this.size=size;
	}
	public double getFitness()
	{
		return fitness;
	}
	public int compareTo(Chromosome o)
	{
		return this.fitness >= o.fitness ? -1 : 1;
	}
	public int getValue() 
	{
		return value;
	}
	public String toString()
	{
		String ret = "";
		for(int i=0; i<size; i++)
		{
			if(((1<<i)&value)!=0)	ret="1"+ret;
			else ret="0"+ret;
		}
		return ret;
	}
}