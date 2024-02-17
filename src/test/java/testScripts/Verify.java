package testScripts;

import java.util.ArrayList;

public class Verify {
 public static void main(String[] args) {
	ArrayList<Integer> al= new ArrayList();
	al.add(10);
	al.add(30);
	al.add(50);
	al.add(1);
	 int First_larget=Integer.MIN_VALUE;
	 int second_larget=Integer.MIN_VALUE;
	  for(int i:al) {
		  if(i>First_larget) {
			  second_larget=First_larget;
			  First_larget=i;
			  
		  }else if(i>second_larget && i!=First_larget) {
			  second_larget=i;
		  }
	  }
	  System.out.println(second_larget);
}
}
