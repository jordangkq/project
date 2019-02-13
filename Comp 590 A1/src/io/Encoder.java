package io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Encoder {

	public Encoder() {

	}

	public static void main(String[] args) throws IOException, InsufficientBitsLeftException {
		InputStream nf= new FileInputStream("jordan.txt");
		InputStreamBitSource x = new InputStreamBitSource(nf);
		OutputStream sp = new FileOutputStream("compressed.txt");
		OutputStreamBitSink a = new OutputStreamBitSink(sp);
		//jordan.txt
		List<Entry> test = new ArrayList<>();
		//  

		//		test = Entry.list_of_Freq(x);
		//		  test.get(0);
		//		int size =  test.size();
		//		OutputStreamBitSink bitsink = null;
		List<HuffNode> test2 = new ArrayList<>();
		//		for(int i = 0; i<256; i++) {
		//			System.out.println("This is the frequency: " + test2.get(i).getnodefreq() + " This is the letter: " + i);
		//		}
		//		
		HuffNode f = HuffNode.buildHuffTree(x);
		
				System.out.println("TREE: " + f.isFull());


//		HuffNode.start_encode(x);
//		HuffNode.fillcodemap(x);
//		HuffNode.encode(65,a);
//		System.out.println("This is a: " + a);
//
//		OutputStream outputStream = new FileOutputStream("./test.txt");
//		OutputStream bufferedOutputStream = new BufferedOutputStream(outputStream); //not necessary but better
//		BitSink bitSink = new OutputStreamBitSink(bufferedOutputStream);
//		for (int i = 0; i < 11; i++) {
//			bitSink.write(101, 8);
//		}
//		bitSink.padToWord();
//		bufferedOutputStream.flush();
//		outputStream.close();
//	}

	//		System.out.println("TREE: " + lookup);
	//		System.out.println("TESTING: " + f.getnodefreq());




	// build tree

	// print out results



	//		
	//		test= Entry.list_of_Freq(x);
	//		Entry b = test.get(255); //
	//		int length = b.getlength();
	//		int index = b.getindex();
	//

	//		for(int i =0; i<256;i++) {
	//			Entry z = test.get(i);
	//			double freq = z.getfreq();
	//			System.out.println("This is the letter: " + i + " This is the Frequency: " + freq);
	//		}
	//		
	//		System.out.println("This is the length: " + length);
	//		System.out.println("This is the index: " + index);
	//		System.out.println("This is the size: " + size);
	//  


	//1. stream through text file and determine frequency
	//insert 256 nodes as leaf nodes
	//take first two combine and make parent node that combines them
	// PriorityQueue<Node> -make this comparable, implements comparable
	//take lengths from optimal tree and use them as lengths for first 256 bytes

	//FIRST GOAL IS TO GENERATE LENGTHS 
	//SECOND GOAL IS TO GENERATE OPTIMAL TREE
	//THIRD GOAL IS TO TAKE LENGTHS GENERATED AND USE FOR FIRST 256 BYTES
	//FOURTH GOAL IS TO USE DECODER TO GENERATE HUFFMAN TREE
	//FIFTH GOAL IS TO WRITE BYTES TO ENCODE

	//use decoder algorithm inside encoder to use length to generate huffman tree

}
}

