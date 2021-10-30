/* TCSS 342 - Summer 2021
 * Assignment 1 - Evolved Names
 * Abdullah Enes
 */



import java.util.*;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Population {
    //A list of Genomes
    public MyLinkedList<Genome> population;
    //A data element equivalent to the most-fit Genome in the population.
    public Genome mostFit;
    //size of the list
    private int size;
    //mutationRate
    private Double mutationRate;

    /**
     * Constructors a population list of Genomes
     */
    public Population() {
        population = new MyLinkedList<>();
        Genome genome = new Genome();
        size = 100;
        mutationRate = 0.05;
        for(int i = 0; i < size; i++) {
            population.add(genome);
        }
        mostFit = population.get(size - 1);
    }

    /**
     * Calls every mutation cycle.
     */
    public void nextGeneration() {
        int halfSize = size / 2;
        for(int i = 0; i < halfSize; i++) {
            population.remove(i);
        }

        Random rand = new Random();
        for (int i = 0; i < halfSize; i++) {
            int randomChoice = rand.nextInt(2);
            //Pick a remaining genome at random and clone it (with the copy
            //constructor). Then mutate the clone.
            if (randomChoice == 0) {
                int randPos = rand.nextInt(halfSize);
                Genome clone = new Genome(population.get(randPos));
                clone.mutate(mutationRate);
                population.add(clone);
            }
            // Pick a remaining genome at random and clone it. Then crossover the
            //clone with another remaining genome (selected at random). Then mutate
            //the result.
            else {
                int randPos1 = rand.nextInt(halfSize);
                int randPos2 = rand.nextInt(halfSize);
                while (randPos2 == randPos1) {
                    randPos2 = rand.nextInt(halfSize);
                }
                Genome clone = new Genome(population.get(randPos1));
                clone.crossover(population.get(randPos2));
                clone.mutate(mutationRate);
                population.add(clone);
            }
        }
        population.sort();
        mostFit = population.get(size-1);
    }

    /**
     * Displays the entire population.
     *
     * @return The entire population.
     */
    public String toString() {
        String result = "";
        for(int i = 0; i < population.size(); i++) {
            result += population.get(i);
        }
        return result;
    }

}
