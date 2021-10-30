/* TCSS 342 - Summer 2021
 * Assignment 1 - Evolved Names
 * Abdullah Enes
 */

import java.util.*;
import java.util.Random;
import java.util.Comparator;


public class Genome implements Comparable<Genome> {

    protected MyLinkedList<Character> genes;
    private MyLinkedList<Character> target;


    private Character[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '_', '-', '\''};

    /**
     * constructs Genome
     */
    public Genome() {
        genes = new MyLinkedList<>();
        target = new MyLinkedList<>();
        String t = "CHRISTOPHER_PAUL_MARRIOTT";
        for(int i = 0; i < t.length(); i++) {
            target.add(i, t.charAt(i));
        }
    }

    /**
     * A copy constructor that initializes a Genome with the same values given
     *
     * @param gene The Genome.
     */
    public Genome(Genome gene) {
        genes = new MyLinkedList<>();
        target = new MyLinkedList<>();
        for (int i = 0; i < gene.genes.size(); i++) {
            genes.add(gene.genes.get(i));
        }
        for (int i = 0; i < gene.target.size(); i++) {
            target.add(gene.target.get(i));
        }
    }

    /**
     * Mutates the string in this Genome by  adding or deleting
     * randomly selected character, or changing a character.
     */
    public void mutate(double mutationRate) {
        Random rand = new Random();

        //Add a random Char to a random position
        int randomAdd = rand.nextInt(100)+1;
        if (randomAdd <= mutationRate * 100) {
            int randomChar = rand.nextInt(characters.length);
            int randomPos = rand.nextInt(genes.size() + 1);
            genes.add(randomPos, characters[randomChar]);
        }


        //Remove a Char from a random index in the list
        int randomRemove = rand.nextInt(100)+1;
        if (randomRemove <= mutationRate * 100 && genes.size() >= 1) {
            int randomPos = rand.nextInt(genes.size());
            genes.remove(randomPos);
        }

        //Replace a random char in the list
        for (int i = 0; i < genes.size(); i++) {
            int randomReplace = rand.nextInt(100)+1;
            if (randomReplace <= mutationRate * 100) {
                int randomChar = rand.nextInt(characters.length);
                genes.set(i, characters[randomChar]);
            }
        }
    }

    /**
     * Updates the current Genome by crossing it over with other.
     *
     * @param parent The other Genome.
     */
    public void crossover(Genome parent) {
        Random rand = new Random();

        MyLinkedList<Character> newList = new MyLinkedList<>();

        for(int i = 0; i < genes.size(); i++) {
            int randomChoose = rand.nextInt(2);
            if(randomChoose == 0 && i < genes.size()) {
                newList.add(genes.get(i));
            } else if (randomChoose == 1 && i < parent.genes.size()) {
                newList.add(parent.genes.get(i));
            } else {
                break;
            }
        }
        genes = newList;
    }

    /**
     * Returns the fitness of the Genome.
     *
     * @return The fitness of the Genome.
     */
    public int fitness() {
        int genesSize = genes.size();
        int targetSize = target.size();
        int l = Math.abs(genesSize-targetSize);
        int min = Math.min(genesSize, targetSize);
        int d = 0;

        for(int i = 0; i < min; i++) {
            if(genes.get(i) != target.get(i)) {
                d++;
            }
        }

        d += Math.abs(genesSize - targetSize);

        return - (l + d);
    }

    //compare fitness of the parent and target
    public int compareTo(Genome target) {
        if (this.fitness() > target.fitness()) {
            return 1;
        } else if (this.fitness() < target.fitness()) {
            return -1;
        }
        return 0;
    }

    /**
     * Displays genes list
     *
     * @return to genes.
     */
    public String toString() {
        String gene = genes.toString();
        return gene;
    }
}
