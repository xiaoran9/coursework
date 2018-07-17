package part3;
import java.util.Comparator;

 class PriorityComparator implements Comparator<Position>{
	
	public int compare(Position p1,Position p2){
		if(p1.getPriority()<p2.getPriority()){
			return -1;
		}
		if(p1.getPriority() > p2.getPriority()){
			return 1;
		}
		return 0;
	}

	
}
