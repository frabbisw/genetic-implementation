package pack1;

public class Genetic {
	Population currentPopulation;
	int N=6,baccharSize=4;
	public Genetic()
	{
		currentPopulation = new Population(N, baccharSize);
		currentPopulation.generateRandomChromosomes();
	}
	public void start(int numberOfIteration)
	{
		System.out.println(currentPopulation.getAverageFitness());
		
		for(int i=0; i<numberOfIteration; i++)
		{
			createGeneration();
			System.out.println(currentPopulation.getAverageFitness()+" "+currentPopulation.getBestFitness());
		}		
	}
	public void createGeneration()
	{
		Population population = new Population(N, baccharSize);
		while(!population.isFull())
		{
			population.add(currentPopulation.production());
		}
		currentPopulation = population;
	}
	public static void main(String [] args)
	{
		Genetic genetic= new Genetic();
		genetic.start(10);
	}
}
