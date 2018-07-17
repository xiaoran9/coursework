import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.util.csv.CSVFormat;
import org.encog.util.csv.ReadCSV;

public class testM {
	public static  BasicNetwork netwokrSet( ) {
		int inputUnits =7;
		int hiddenUnits =12;
		int outputUnits = 3;
		
		BasicNetwork network = new BasicNetwork();
		
		network.addLayer(new BasicLayer(null,false,inputUnits));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true,hiddenUnits));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false,outputUnits));
		
		//finalise the structure
		network.getStructure().finalizeStructure();
		//initialise the weights to a random value
		network.reset();
		
		//the feedforward neural network
		
				return network;
		
	}
	public static int epoch(BasicNetwork network,double learningRate,double momentum){
		
		Backpropagation train = new Backpropagation(network,readCSV(),learningRate,momentum);
		int epoch = 1;
		//train loop stop when the error less then 0.01
		double lastError=0;
		int crash=0;
		do{
			train.iteration();
			if(train.getError()-lastError>0){
				crash++;
			}
			lastError =train.getError();
			epoch++;
		}while(train.getError()>0.01);
		train.finishTraining();
		
		return crash;
		}
	
	public static MLData inputArr(int inputSize,ReadCSV csv){
		MLData input = null;
		input = new BasicMLData(inputSize);
		double number = 0;
		for(int i = 0;i<inputSize;i++){
			String a = csv.get(i);
			if(a.toLowerCase().equals("yes")){
				number = 1;
			}else if(a.toLowerCase().equals("no")){
				number= 0;
				}
			input.setData(i,number);
		}
		return input;
	}
	
	public static MLData outputArr(int outputSize,ReadCSV csv){
		MLData output = null;
		output = new BasicMLData(outputSize);
		for(int i =0 ;i<outputSize;i++){
			String b = csv.get(csv.getColumnCount()-1);
			switch(b){
			case"Alex":
				double Alex[]={1,0,0};
				output.setData(Alex);
			    break;
			case"Alfred":
				double Alfred[]={1,1,0};
				output.setData(Alfred);
			    break;
			case"Anita":
				double Anita[] = {1,1,1};
				output.setData(Anita);
			    break;
			case"Anne":
				double Anne[]={0,1,1};
				output.setData(Anne);
			    break;
			case"Bernard":
				double Bernard[]={0,0,1};
				output.setData(Bernard);
			    break;
			}
		}
		return output;
	}

	public static MLDataSet readCSV(){
		//https://github.com/encog/encog-java-core/blob/master/src/main/java/org/encog/util/simple/TrainingSetUtil.java
		String fileName = "char.csv";
		MLDataSet result = new BasicMLDataSet();
		ReadCSV csv = new ReadCSV(fileName, true, CSVFormat.DECIMAL_POINT);
		int inputSize = 7;
		int outputSize = 1;
		
		while(csv.next()){
			MLData input = inputArr(inputSize,csv);
			MLData output =outputArr(outputSize,csv);
			
			MLDataPair pair = new BasicMLDataPair(input, output);
			result.add(pair);
		}
		return result;
	}
	
	public static void main(String[] args) {
		double learningRate=0.3;
		double momentum=0.2;
		int sum = 0;
		double average;
		
		while(momentum<0.7){
			sum =0;
			for (int i=0;i<100;i++){
				BasicNetwork network = netwokrSet();
				 sum += epoch(network,learningRate,momentum);
			}
		average = sum/100;
		System.out.println("the momentum is:"+momentum+" the average crash is"+average);
		momentum+=0.05;
		}

	}
}
