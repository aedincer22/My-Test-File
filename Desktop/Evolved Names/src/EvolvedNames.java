/* TCSS 342 - Summer 2021
 * Assignment 1 - Evolved Names
 * Abdullah Enes
 */

import java.net.Proxy;

/** Main class for Evolved Names**/
public class EvolvedNames {

    public static Population population;


    /**
     * @param args The args.
     */
    public static void main(String[] args) {

        //tests add(index) method
        MyLinkedList<Integer> ll= new MyLinkedList();
            ll.add(0);
            ll.add(1);
            ll.add(2);
            ll.add(3);
            String st = ll.toString();
            System.out.println("add simple: " + st);

        // tests add(index, element) method
        MyLinkedList<Character> ll_2= new MyLinkedList();
        ll_2.add(0, 'A');
        ll_2.add(0, 'B');
        ll_2.add(1, 'C');
        ll_2.add(3, 'D');
        String st_2 = ll_2.toString();
        System.out.println("add index " + st_2);

        // tests remove(index)
        MyLinkedList<Character> ll_3= new MyLinkedList();
        ll_3.add('A');
        ll_3.add('B');
        ll_3.add('C');
        ll_3.remove(2);
        //ll_3.remove(4);
        String st_3 = ll_3.toString();
        System.out.println("remove: "+ st_3);


        // tests get(index)
        MyLinkedList<String> ll_4= new MyLinkedList();
        ll_4.add("AB");
        ll_4.add("BC");
        ll_4.add("DE");
        ll_4.add("FG");
        ll_4.get(2);
        ll_4.get(3);
        String st_4 = ll_4.toString();
        System.out.println("get: " + st_4);

        // tests set(index, value)
        MyLinkedList<Character> ll_5= new MyLinkedList();
        ll_5.add('A');
        ll_5.add('B');
        ll_5.add('C');
        ll_5.add('D');
        ll_5.set(3, 'F');
        ll_5.set(1, 'Z');
        String st_5 = ll_5.toString();
        System.out.println("set: " + st_5);

        // tests size()
        MyLinkedList<Character> ll_6= new MyLinkedList();
        ll_6.add('A');
        ll_6.add('B');
        ll_6.add('C');
        ll_6.add('D');
        System.out.println("size: " + ll_6.size());

        // tests bubbleSort()
        MyLinkedList<Character> ll_8= new MyLinkedList();
        ll_8.add('C');
        ll_8.add('A');
        ll_8.add('B');
        ll_8.add('D');
        ll_8.sort();
        String st_8 = ll_8.toString();
        System.out.println("sort: " + st_8);

        // tests isEmpty()
        MyLinkedList<Character> ll_7= new MyLinkedList();
        ll_7.add('A');
        ll_7.add('B');
        ll_7.add('C');
        ll_7.add('D');
        System.out.println("empty: " + ll_7.isEmpty());

        // tests Iterator
        MyLinkedList<Character> ll_9= new MyLinkedList();
        ll_9.add('C');
        ll_9.add('A');
        ll_9.add('B');
        ll_9.add('D');
        ll_9.iterator();
        String st_9 = ll_9.toString();
        System.out.println("Iterator: " + st_9);

        MyLinkedList<Genome> newGeneration = new MyLinkedList<>();



        //method call for GenomeTest
        GenomeTest();
        //method call for PopulationTest
        PopulationTest();
    }

    /**
     * Tests Genome class.
     */
      public static void GenomeTest() {
          Genome gen = new Genome();
          for (int i = 0; i <= 256; i++) {
              gen.mutate(0.5);
          }
          String st = gen.toString();
          System.out.println(gen.fitness());
          System.out.println(st);
      }

    /**
     * Tests Population class.
     */
      public static void PopulationTest() {
          long start = System.currentTimeMillis();
          long end;
          Population pop = new Population();
          int count = 0;
          while(pop.mostFit.fitness() != 0) {
              pop.nextGeneration();
              System.out.println("most fitness: " + pop.mostFit);
              System.out.println("fitness: " + pop.mostFit.fitness());
              count++;
          }
          end = System.currentTimeMillis();
          System.out.println("count: " + count);
          System.out.println("execution time: " + (end- start));
      }



}

