import org.encog.ml.data.MLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.util.csv.CSVFormat;
import org.encog.util.csv.ReadCSV;

public class point7 {
	public static void computing(BasicNetwork network) {
		String fileName = "char.csv";
		ReadCSV csv = new ReadCSV(fileName, true, CSVFormat.DECIMAL_POINT);
		while(csv.next()){
		MLData data=part1.inputArr(7, csv);
		MLData output = network.compute(data);
		System.out.println("input ="+data.getData(0)+" "+ data.getData(1)+" "+data.getData(2)+" "+data.getData(3)+" "+data.getData(4)+" "+data.getData(5)+" "+data.getData(6));//add more get data
		System.out.println("actual = " + output.getData(0)+ output.getData(1)+ output.getData(2));
		}
		}
	}
