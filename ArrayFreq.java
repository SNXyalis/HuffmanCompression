package org.hua.huffman;

/**
 *
 * @author it21966, it21998, it21920
 */
import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class ArrayFreq {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        /**
        * Three file objects for each file of the assignment.
        */

        File firstfile = new File("2701-0.txt");
        File secondfile = new File("1342-0.txt");
        File thirdfile = new File("11-0.txt");
        
        /**
        * Three BufferedReader objects that are assigned to each file 
        * and work as input streams.
        */


        BufferedReader inputStream = new BufferedReader(new FileReader(firstfile));
        BufferedReader inputStream2 = new BufferedReader(new FileReader(secondfile));
        BufferedReader inputStream3 = new BufferedReader(new FileReader(thirdfile));

         /**
         * A BufferedWriter Object that will be used to
         *pass the data in the frequencies.dat file.
         */


        BufferedWriter outputStream = new BufferedWriter(new FileWriter("frequencies.dat"));
        //System.out.println("Letter Frequency:");

         /**
        *An integer array to count each character's frequency. 
        */
         int[] frequency = new int[128];

        /**
        *An integer that will be used to read each file until EOF.
        */        int counterchar;


        
        /**
         * In the next three while loops
         * we assign the read method of the
         * BufferedReader object to the counterchar,
         * and we loop through the file until EOF.
         */


        while ((counterchar = inputStream.read()) != -1) {
            if( counterchar>=0 && counterchar<=127){
             /**
             * We increment the frequency of the characters 
             * that are in range of 0-127 
             */
            
                frequency[counterchar]++;
            }
            
        }

        while ((counterchar = inputStream2.read()) != -1) {
            if( counterchar>=0 && counterchar<=127){
                frequency[counterchar]++;

            }
        }

        while ((counterchar = inputStream3.read()) != -1) {
            if( counterchar>=0 && counterchar<=127){
      
                frequency[counterchar]++;
            }
            
        }
        
         /**
         * A loop that prints the output for 
         * debugging purposes.
         */

        
         /**
          * for (int i = 0; i <=127; i++) {
          
                System.out.println("CHARACTER: " + (char) i + " FREQUENCY: " + frequency[i] + "");
          * }
          */
          
        
        
         /**
         * Loop through the array and pass the number of the 
         * character and the character's frequency in the output 
         * file using the write method of outputStream. 
         */
        
        
        for (int i = 0; i<=127; i++) {
           

                outputStream.write(  i + "\t" + frequency[i] + "" );
                outputStream.newLine();
            }
        /**
         * A message for the success of the program
         */
        System.out.println("The file 'frequencies.dat' is ready");
        inputStream.close();
        outputStream.close();
    }
}