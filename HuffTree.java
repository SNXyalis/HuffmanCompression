
package org.hua.huffman;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author it21966, it21998, it21920
 * 
 */

public class HuffTree {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        /**
         * We create an ArrayList of Huffnode type
         * called nodeList that will be used in the
         * Huffman algorithm.
         */
        ArrayList<Huffnode> nodeList = new ArrayList<Huffnode>();

        
        /**
         * The character and frequency arrays of Integer type
         * will store the data that will be read from the frequency.dat
         * file.
         */
        final int ARRAY_SIZE = 128;
        Integer[] character = new Integer[ARRAY_SIZE];
        Integer[] frequency = new Integer[ARRAY_SIZE];
        
        
        /**
         * Since the data were stored in a formatted way, we 
         * use scanner to read the data from the frequencies.dat
         * file.
         */
        Scanner frequenciesDataFile = new Scanner(new File("frequencies.dat"));
        int i = 0;
        int j = 0;
        while (frequenciesDataFile.hasNextInt()) {
            //c1 is used for the letters and c2 is used for the frequency
            int c1 = frequenciesDataFile.nextInt();
            int c2 = frequenciesDataFile.nextInt();
            character[i++] = c1;
            frequency[j++] = c2;
        }
        
        
        /**
         * In this for loop we create a Huffnode and we use a constructor
         * to assign the values of frequency and the character per node.
         * Then we add it in the nodeList.
         */
        for (i = 0; i <= 127; i++) {
            Huffnode tmpNode = new Huffnode(frequency[i], character[i]);
            nodeList.add(tmpNode);
        }
        
        
        /**
         * This loop was used to assure that the data
         * were stored successfully.
         */
        
        /*for (i=0; i<=127; i++) {
            Huffnode k = tmp.get(i);
            System.out.println(k.c + "\t" + k.freq);
        }*/
          

        
        
        /**
         * This is the start of the Huffman algorithm.
         * Until one tree(Huffnode) remains in the nodeList,
         * Find the the first minimal node and assign the values
         * to a t1 node, then remove the node, find the minimal
         * "second" minimal node and assign the values to a t2 node
         * ,then remove the second minimal. Create a new node called
         * t3 that has t1 and t2 as left and right child of the node
         * and assign the the sum of the t1 and t2 frequencies to the 
         * t3 Huffnode and then add it to the nodeList.
         */
        while (nodeList.size() > 1) {
            //A loop to find the first minimal node
            Huffnode minF = nodeList.get(0);
            int flag = 0;
            
            for (i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).freq < minF.freq) {
                    minF = nodeList.get(i);
                }
            }

            flag = nodeList.indexOf(minF);

            Huffnode t1 = nodeList.get(flag);
            //System.out.println("One");
            //System.out.println(t1.freq);
            nodeList.remove(flag);
            
            //A loop to find the second minimal node
            minF = nodeList.get(0);
            flag = 0;

            for (i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).freq < minF.freq) {
                    minF = nodeList.get(i);
                }
            }

            flag = nodeList.indexOf(minF);

            Huffnode t2 = nodeList.get(flag);
            //System.out.println("Two");
            //System.out.println(t2.freq);
            nodeList.remove(flag);
            
            //We initialize the character value of node with 0 
            //because it's not a leaf
            Huffnode t3 = new Huffnode(0, -1);
            t3.setFreq(t1.freq + t2.freq);
            //System.out.println("Three");
            //System.out.println(t3.freq);
            t3.setLeft(t1);
            t3.setRight(t2);
            nodeList.add(t3);
        }
        
        
        //This was used to check if the data was successfully passed
       //System.out.println(nodeList.get(0).left.freq);
        //System.out.println(nodeList.get(0).left.right.left.left.freq);
        //System.out.println(nodeList.get(0).right.left.left.right.c);
        //System.out.println(nodeList.get(0).left.right.left.left.freq);
        //System.out.println(nodeList.get(0).left.right.left.right.freq);

        /**
         * We store the root object in a tree.dat
         * file using the Java Object Serialization.
         */
        FileOutputStream fos = new FileOutputStream("tree.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
      
        oos.writeObject(nodeList.get(0));
        
        FileInputStream fis = new FileInputStream("tree.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Huffnode retrive = (Huffnode)ois.readObject();
        
        oos.close();
    
        System.out.println("The file tree.dat is ready");
        
        
        }
        
    }
        
    
     

