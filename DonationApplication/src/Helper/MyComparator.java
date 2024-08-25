package Helper;

import java.util.Comparator;

public class MyComparator {
	
	public static Comparator<Integer> compInteger = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
		      return Integer.compare(o1, o2);
		  }
	};
	
	
	public static Comparator<Float> compFloat = new Comparator<Float>() {
		@Override
		  public int compare(Float o1, Float o2) {
		      return Float.compare(o1, o2);
		  }
	};

}
