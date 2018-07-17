import org.encog.ConsoleStatusReportable;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.pattern.FeedForwardPattern;
import org.encog.neural.prune.PruneIncremental;
import org.encog.util.csv.CSVFormat;
import org.encog.util.csv.ReadCSV;

public class point8 {
	public static void test() {
		//define a pattern architecture
		FeedForwardPattern net_test = new FeedForwardPattern();
		//Create the Input Layer
		net_test.setInputNeurons(7);
		//Output Layer
		net_test.setOutputNeurons(3);
		//set activation function
		net_test.setActivationFunction(new ActivationSigmoid());
		MLDataSet trainingSet = part1.readCSV();
		PruneIncremental prune=new PruneIncremental(trainingSet, net_test, 1000, 1, 10,new ConsoleStatusReportable());
				//try from 1 to 5 hidden units
				prune.addHiddenLayer(1, 10);
				prune.process();
				BasicNetwork network = prune.getBestNetwork();
				System.out.println("Neural Network created: "+ network.getLayerNeuronCount(0) + "-" + network.getLayerNeuronCount(1) + "-" + network.getLayerNeuronCount(2));
	}
	
}
