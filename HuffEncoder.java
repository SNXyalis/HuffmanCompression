
package org.hua.huffman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.Scanner;


public class HuffEncoder {
      public static void main(String[] args) throws FileNotFoundException, IOException {
        
        if (args.length < 2) {
            System.out.println("Usage: program filename1 filename2");
            System.exit(0);
        }
          
        /**
         * We create two arrays named character and codedChar, character will be
         * used to store the characters and codedChar will store the coded char.
         * 
         */
        final int ARRAY_SIZE = 128;
        Integer[] character = new Integer[ARRAY_SIZE];
        String[] codedChar = new String[ARRAY_SIZE];

        Scanner codedDataFile = new Scanner(new File("codes.dat"));
        int i = 0;
        int j = 0;

        /**
         * We pass the data from the codes.dat file to the arrays.
         * 
         */
        while (codedDataFile.hasNextInt()) {
            int c1 = codedDataFile.nextInt();
            String c2 = codedDataFile.next();
            character[i++] = c1;
            codedChar[j++] = c2;
        }

        // This was used to check if the data were passed successfully in
        // the arrays.
        
        /*for (i = 0; i <= 127; i++) {
            System.out.println(character[i] + "\t" + codedChar[i]);
        }*/
        
        String code = null;
        BitSet bits = new BitSet();
        int seat = 0;

        BufferedReader inputStreams = new BufferedReader(new FileReader("test.txt"));

        int counter;

        /**
         * While the file contains characters, for each character in the 
         * character array, if the file character is the same as the array 
         * character, store the character's code in a String and then for 
         * each character of the string, add a bit in the bitset with the 
         * value of true if the character is '1' and false if the character 
         * is '0'.
         * 
         */
        while ((counter = inputStreams.read()) != -1) {
            if (counter >= 0 && counter <= 127) {

                //System.out.println(characterr);
                for (int c = 0; c < character.length; c++) {
                    if ((int) counter == character[c]) {
                        code = codedChar[c];
                    }
                }

                for (Character k : code.toCharArray()) {
                    if (k == '1') {
                        bits.set(seat, true);
                    } else if (k == '0') {
                        bits.set(seat, false);
                    }
                    
                    seat++;
                }
                
                }else {
                System.out.println("Character is out of ASCII range.");
            }
            }

            //We add a true bit as a flag
            bits.set(seat, true);
            
            /**
             * We store the BitSet in a ByteArray that will be passed in a file.
             */
            byte[] byteArray = bits.toByteArray();
            
            
            /**
             * We use DataOutputStream because it can handle binary I/O of 
             * primitive data type and String values
             * 
             */
            FileOutputStream fos = new FileOutputStream("out.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            
            /**
             * We store the size of the BitSet -1 because the last bit is a 
             * flag and then we store the byteArray.
             * 
             */
            dos.writeInt(bits.length() - 1);
            fos.write(byteArray);
            
            dos.close();
            fos.close();
          /*
            /**
             * EXAMPLE: This part is used to read the data from the file and 
             * check the accuracy of the data.
             * 
             */
             /*
            FileInputStream fis = new FileInputStream("out.txt");
            DataInputStream dis = new DataInputStream(fis);

            //Read the size
            final int SIZE = dis.readInt();
            System.out.println(SIZE);
            //Read the bytes (SIZE is excluded)
            
            int byteArraySize = (SIZE + 7) / 8;
            
            //Read the bytes (SIZE is excluded)
            byte[] retrievedByteArray = new byte[byteArraySize];
            dis.read(retrievedByteArray);
            dis.close();
            fis.close();
            
            //We pass all the bits from the given byte array to a BitSet.
            BitSet newBs = BitSet.valueOf(retrievedByteArray);
            */
            
            /**
             * We print 1 or 0 depending on the value of the original Bitset 
             * that we stored.
             */
            /*
            System.out.println("Original BitSet: ");

            for (int k = 0; k < bits.length() - 1; k++) {
                if (bits.get(k) == true) {
                    System.out.print("1");
                } else {
                    System.out.print("0");

                }
            }

            System.out.println();
            */
            /**
             * We print 1 or 0 depending on the value of the Bitset that was
             * made from the stored bytes.
             */
            /*
            System.out.println("Bitset that is made from the saved data in the file: ");

            for (int k = 0; SIZE > k; k++) {
                if (newBs.get(k) == true) {
                    System.out.print("1");
                } else {
                    System.out.print("0");

                }
            }
            */
            System.out.println("The first file has been encoded and stored to the second file!");
            System.out.println();
          
    }
        
        
        
        
        
        
}


    
