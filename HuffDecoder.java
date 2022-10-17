
package org.hua.huffman;



import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.BitSet;

/**
 *
 * @author it21966,it21998,it21920
 */
public class HuffDecoder {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        if (args.length < 2) {
            System.out.println("Usage: program filename1 filename2");
            System.exit(0);
        }
        
        
        //Create a DataInputStream to read primitive Java data types
        FileInputStream fis = new FileInputStream(args[0]);
        DataInputStream dis = new DataInputStream(fis);

        //Read the size
        final int SIZE = dis.readInt();

        //Read the bytes after the size
            int byteArraySize = (SIZE + 7) / 8;

         byte[] retrievedByteArray = new byte[byteArraySize];   
          dis.read(retrievedByteArray);
         dis.close();
         fis.close();
        //BitSet that has retrieved the bits of the byte array
        BitSet newBs = BitSet.valueOf(retrievedByteArray);

        //Check data for accuracy
        
        for (int k = 0; SIZE > k; k++) {
            if (newBs.get(k) == true) {
                System.out.print("1");
            } else {
                System.out.print("0");

            }
        }
        
        System.out.println();
        
        /**
         * ObjectInputStream is used to read the Huffman Tree from tree.dat and
         *store it in a Huffnode.
         */
        fis = new FileInputStream("tree.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Huffnode node = (Huffnode) ois.readObject();
        ois.close();
        fis.close();
        
        //BufferedWriter will be used to write the character in the file.
        BufferedWriter outputStream = new BufferedWriter(new FileWriter(args[1]));
        
        /**
         * For each bit in the Huffman coded file move to the right if the bit
         * is true(1) move to the right child, else if it's false(0) move to 
         * the left child, if the node is a leaf, store the character of the 
         * node.
         * 
         */
        Huffnode tmp = node;
        for(int i = 0; i < SIZE;i++) {
            
            if (newBs.get(i) == true) {
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
            
            if(tmp.right == null && tmp.left == null) {
                System.out.println((char)tmp.c);
                outputStream.write(tmp.c);
                tmp = node;
            }
        }
        outputStream.close();
    }
}

