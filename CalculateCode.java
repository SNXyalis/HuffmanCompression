/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.huffman;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used to implement the tree preorder traversal, code each
 * character using the appropriate path and store the results in the code.dat
 * file.
 *
 * @author it21966, it21998, it21920
 */
public class CalculateCode {

    private Huffnode node;
    private ArrayDeque<Character> q = new ArrayDeque<>();//Stores path char
    ArrayList<String> codedAlpharithmetic = new ArrayList<String>();//StorePath
    /**
     * The constructor is used to read the root that was stored in the tree.dat
     * and assign it to the node attribute.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public CalculateCode() throws FileNotFoundException, IOException, ClassNotFoundException {
        //BufferedWriter outputStream = new BufferedWriter(new FileWriter("codes.txt"));
        
        FileInputStream fis = new FileInputStream("tree.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        node = (Huffnode) ois.readObject();
        ois.close();
        
    }
    /**
     * Implementation of preorder tree traversal using recursion, we use a char
     * as a parameter, 0 if we move to the left child or 1 if we move to the 
     * right child, and we add the character to the ArrayDeque and find the path
     * ,if the node is a leaf we create a String of the path using the 
     * createPath method and we store the String path to the ArrayList.
     * 
     * @param r
     * @param digit
     * @throws IOException 
     */
    public void traverse(Huffnode r, Character digit)  {
        q.add(digit);
        //If leaf
        if (r.right == null && r.left == null) {

            // q.push(r.c + "\t" + path);

            String path = createPath();
            //System.out.print(r.c + "\t" +path);
           codedAlpharithmetic.add(r.c + "\t" +path);
            
            q.removeLast();
            //System.out.println();

            return;
        }

        traverse(r.left, '0');
        traverse(r.right, '1');
        q.removeLast();
    }
    /*
    public void printForConfirmation(Huffnode t){
        System.out.println("TEST: "+t.right.right.right.c);
        System.out.println("TEST: "+t.left.left.left.c);
        System.out.println("TEST: "+t.left.left.right.left.c);

    }
    */
     /**
     * Iterates through ArrayDeque which stores the characters of the path from 
     * root to leaf and create a string of the path.
     * 
     * @return String 
     */
    private String createPath() {
        Iterator it = q.iterator();
        String path = "";
        while (it.hasNext()) {
            path += it.next();
            
            //System.out.print(it.next());
        }
        
        return path;
    }
     /**
     * Writes each character path that was stored in the ArrayList to a file
     * called codes.dat.
     * 
     * @throws IOException 
     */
 public void writeToFile() throws IOException{
        BufferedWriter  outputStream = new BufferedWriter(new FileWriter("codes.dat"));
        for (int i = 0; i <= 127; i++) {
            outputStream.write(codedAlpharithmetic.get(i));
            outputStream.newLine();
            //System.out.println(codedAplharithmetic.get(i));
        }
        outputStream.close();
    }
 /**
     * Return the root of the tree.
     * 
     * @return 
     */
    public Huffnode getHuffnode() {
        return this.node;
    }
      
}
