import java.util.Scanner;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.neural.networks.BasicNetwork;

public class addCharacter {
	String fileName = "char.csv";
	public static Scanner reader = new Scanner(System.in);
	public static MLDataPair newChar(){
		MLDataPair pair = null;
		MLData input = new BasicMLData(7);
		for (int i = 0; i < 7; i++) {
			part2.question(i);
			input.add(i, part2.readUser());
		}
		if(newCharName()){
			 pair =add(input);
		}
		return pair;
	}
	public static boolean newCharName(){
		System.out.println("the cahracter's name is:");
		String response =  reader.nextLine();
		if(response.toLowerCase().equals("Alex")
				||response.toLowerCase().equals( "alfred")
				||response.toLowerCase().equals("anita")
				||response.toLowerCase().equals("anne")
				||response.toLowerCase().equals("bernard"))
		{
			System.out.println("the cahracter's name is exist pleas input again");
			newCharName();
		}else{
			return true;
		}
		return false;
	}
	public static MLDataPair add(MLData input){
		MLData output = new BasicMLData(1);
		double newIndex[] ={0,0,0}; 
		output.setData(newIndex);
		MLDataPair pair = new BasicMLDataPair(input, output);
		return pair;
	}
	
	
	
}