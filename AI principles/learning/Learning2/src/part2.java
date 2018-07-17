import java.io.File;
import java.util.Scanner;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

public class part2 {

	public static Scanner reader = new Scanner(System.in);
	static boolean early=false;
	public static int readUser() {
		
		String response =  reader.nextLine();
		
		if(response.toLowerCase().equals("yes")){
			return 1;
		}else if(response.toLowerCase().equals("no"))
			return 0;
		return -1;
	}
	public static void question(int i) { 
		switch (i){
		case 0:
			System.out.println("dose your character have cirly hair");
			break;
		case 1:
			System.out.println("is your character blonde");
			break;
		case 2:
			System.out.println("dose your character have red cheecks");
			break;
		case 3:
			System.out.println("dose your character have moustache");
			break;
		case 4:
			System.out.println("dose your character have beard");
			break;
		case 5:
			System.out.println("dose your character have rings");
			break;
		case 6:
			System.out.println("is your character female");
			break;
		}
	}
	public static MLData systemRead() {
		MLData input = new BasicMLData(7);
		int read;
			for (int i = 0; i < 7; i++) {
				question(i);
				read =readUser();
				if(read!=-1){
				input.add(i, read);
				early=guessEarly.GuessChar(input,i);
				if(early){break;}
				}else
				{i--;
				System.out.println("only yes or no is valid input");
				}
			}
		
		return input;
		
	}
	
	public static String guess(MLData output){ 
		StringBuilder a = new StringBuilder();
		for(int i =0;i<3;i++){
			if(output.getData(i)>0.5){
				a.append(1);
			}else
				a.append(0);
		}
		String index = a.toString();
		String name = checkWho(index);
		return name;
	}
	public static String checkWho(String index){
		String name=null;
		switch(index){
		case "100": 
			name = "Alex";
			break;
		case "110": 
			name = "Alfred";
			break;
		case "111": 
			name = "Anita";
			break;
		case "011": 
			name = "Anne";
			break;
		case "001": 
			name = "Bernard";
			break;
		
		}
		return name;
	}
	public static void palyGame(String name){
		System.out.println("Loading network "+name);
		BasicNetwork loaded_net = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(name));
		MLData data=systemRead();
		if(!early){
		MLData output = loaded_net.compute(data);
		name = guess(output);
		if(name==null){
			System.out.println("the character not exist");
		}else
			System.out.println("guess the people is:"+name);
		}
	}

	public static void main(String[] args) {
		String name ="part1_network.eg";
		palyGame(name);
		System.out.println("do you want to input a new character");
		String response =  reader.nextLine();
		if(response.toLowerCase().equals("yes")){
			part1_newChar.setNewNet();
			System.out.println("do you want to paly the new game");
		    response =  reader.nextLine();
		    if(response.toLowerCase().equals("yes")){
				 name="AddCha_network.eg";
						 palyGame(name);
				}
		}
		
       }
		
	}
