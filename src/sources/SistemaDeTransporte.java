package sources;

import java.util.ArrayList;
import java.util.List;

public class SistemaDeTransporte {
	List<List<Double>> minimosCostosDirectos = new ArrayList<>();
	
	public void addEstacion() {
		minimosCostosDirectos.add(new ArrayList<>());
		for(int i = 0; i<minimosCostosDirectos.size(); i++) {
			minimosCostosDirectos.get(i).add(Double.valueOf(0));
		}
	}
	
	public Boolean existePathCostos(int a, int b) {
		if(minimosCostosDirectos.get(a) != null)
			if(minimosCostosDirectos.get(a).get(b) != null) return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
