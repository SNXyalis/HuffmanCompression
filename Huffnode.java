
package org.hua.huffman;


import java.io.Serializable;

/**
 *  This class is used as the nodes
 * that store values of the frequency,
 * the character, the left and right 
 * child of the node. The methods of 
 * the class consist of the setter
 * and the getter of the attributes 
 * and one contructor.
 * @author it21966, it21998, it21920
 */

public class Huffnode implements Serializable{

    int freq;
    int c;
    Huffnode left;
    Huffnode right;
    
    public Huffnode(int freq, int c) {
        this.freq = freq;
        this.c = c;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
    
    public int getFreq() {
        return freq;
    }

    public void setC(int c) {
        this.c = c;
    }
    
    public void setRight(Huffnode right) {
        this.right = right;
    }
    
    public void setLeft(Huffnode left) {
        this.left = left;
    }
}

