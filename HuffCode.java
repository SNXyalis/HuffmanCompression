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
import java.util.ArrayDeque;
import java.util.Iterator;

/**
 *  This class creates a CalculateCode Class and calls the necessary methods
 * to traverse the tree and store the coded path of each character in a file.
 * 
 * @author it21966, it21998, it21920
 */
public class HuffCode {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        CalculateCode mainTree = new CalculateCode();
        mainTree.traverse(mainTree.getHuffnode(), ' ');
        mainTree.writeToFile();
        //mainTree.printForConfirmation(mainTree.getHuffnode());
        System.out.println("The file codes.dat is ready!");
        
    
    }
}
