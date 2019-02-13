package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Decoder {

	public Decoder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		// TODO Auto-generated method stub
		InputStream newfile = new FileInputStream("compressed.dat");

		InputStreamBitSource s = new InputStreamBitSource(newfile);
		//		int [] x = retrieve(s);
		//		System.out.println(x);

		Node.decode(s);
	

		//		List<Entry> newlist = Entry.createList(s);
		//	Node f = Node.buildTree(newlist);
		//	System.out.println("BREAK");
		//	
		//        int size =  newlist.size();
		//            Entry a = newlist.get(0);
		//            int length = a.getlength();
		//            int index = a.getindex();
		//            System.out.println("This is the length: " + length);
		//            System.out.println("This is the index: " + index);
		//            System.out.println("This is the size: " + size);
		//            Collections.sort(newlist, Collections.reverseOrder()); 
		//          Entry.sort(newlist);
		//            System.out.println("List after the use of" + 
		//                    " Collection.sort():" + newlist.get(50).getlength());
		//            for(int i =0; i<newlist.size(); i++) {
		//    				System.out.println("This is the index: " + newlist.get(i).getindex() + " , " + "This is the length: " + newlist.get(i).getlength());
		//    			}
		//        Node tree = Node.buildTree(newlist);

		//          System.out.println("This is a symbol: " + tree.getSymbol());
		//          Node t = Node.buildTree(newlist);
		//          System.out.println("This is a symbol: " + t.getSymbol());

		//          decodeCode(newlist)
		//          String x = ;
		//          Node.decode(, t);
		//		System.out.println(a);
		//		 File myFile = new File("compressed.dat");
		//	        System.out.println("Attempting to read from file in: "+ myFile.getCanonicalPath());


	}



}
