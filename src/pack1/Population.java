package pack1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
	private Random random = new Random();
	private int N,baccharSize;
	private ArrayList<Chromosome>chromosomes;
	private FitnessFunction function;
	private double avgFitness=0, bestFitness=0;
	private double crossoverProbability=.7,mutationProbability=.001;
	private boolean sort=true;
	
	public Population(int N, int baccharSize)
	{
		chromosomes=new ArrayList<>();
		this.N=N;
		this.baccharSize=baccharSize;
		function = new FitnessFunction() 
		{
			public double calculateFitness(int value) 
			{
				return 15*value-value*value;
			}
		};
	}
	public void generateRandomChromosomes()
	{
		for(int i=0; i<N; i++)
		{
			Chromosome chromosome = new Chromosome(random.nextInt(1<<baccharSize), baccharSize, function);
			avgFitness += chromosome.getFitness()/N;
			bestFitness = Math.max(bestFitness, chromosome.getFitness());
			chromosomes.add(chromosome);
		}		
		Collections.sort(chromosomes);
	}
	public Chromosome production()
	{
		int breakpoint = random.nextInt(baccharSize-1);
		//int a = ((N-1)*100)/(100+random.nextInt(30));
		//int b = ((N-1)*100)/(100+random.nextInt(30));
		
		int a = random.nextInt(N);//(1+random.nextInt(1));
		int b = random.nextInt(N);//(1+random.nextInt(1));
		
		Chromosome p1 = chromosomes.get(a);
		Chromosome p2 = chromosomes.get(b);
		
		int genes=0;
		for(int i=0; i<=breakpoint; i++)
			genes = genes | (((1<<i)&p1.getValue()));
		for(int i=breakpoint+1; i<baccharSize; i++)
			genes = genes | (((1<<i)&p2.getValue()));
		
		b = 1<<random.nextInt(baccharSize);
		a = (1<<baccharSize)-1-b;
		
		if((genes&b)!=0)	genes = genes&a;
		else	genes = genes|b;
		
		Chromosome child = new Chromosome(genes, baccharSize, function);
		System.out.print(child.getValue()+" -- ");
		return child;
	}
	public void setFitnessFunction(FitnessFunction function)
	{
		this.function=function;
	}
	public void add(Chromosome chromosome)
	{
		if(chromosomes.size()==N)	return;
		chromosomes.add(chromosome);
		avgFitness += chromosome.getFitness()/N;
		bestFitness = Math.max(bestFitness, chromosome.getFitness());
		if(chromosomes.size()==N)	Collections.sort(chromosomes);
	}
	public int populationSize()
	{
		return chromosomes.size();
	}
	public double getAverageFitness()
	{
		return avgFitness;
	}
	public double getBestFitness()
	{
		return bestFitness;
	}
	public boolean isFull()
	{
		return chromosomes.size() == N;
	}
}