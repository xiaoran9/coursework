import java.io.File;
import java.util.Random;
import java.util.Scanner;

import org.encog.ml.data.MLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

public class guessEarly {
	public static Scanner reader = new Scanner(System.in);
	public static boolean GuessChar(MLData input,int index){
		String name1=guessOnce(input,index);
		String name2=guessOnce(input,index);
		String name3=guessOnce(input,index);
		if(name1.equals(name3) && name1.equals(name2) && !name1.equals(null)){
				System.out.println("guess the people maybe :"+name1);
				System.out.println("is it the correct guess ");
				String response =  reader.nextLine();
				if(response.toLowerCase().equals("yes"))
					return true;
			}
		return false;
	}
	
	public static String guessOnce(MLData input,int index) {
		BasicNetwork loaded_net = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("part1_network.eg"));
		String name=null;
		Random rand = new Random();
		
		for(int i=index+1;i<7;i++){
		int n = rand.nextInt(2);
		input.add(i, n);
		}
		MLData output = loaded_net.compute(input);
		name = part2.guess(output);
		return name;
	}
	
}
