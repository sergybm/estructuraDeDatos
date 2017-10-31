import java.util.Comparator;

public class Comparador implements Comparator<Caja> {

	@Override
	public int compare(Caja c1, Caja c2) {
		int r=0;
		if (c1.getCola().size() < c2.getCola().size()) {
			return -1;
		}
		if (c1.getCola().size() > c2.getCola().size() ) {
			return 1;
		}
		return 0;
	}


}
