package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;

public class CazadorRural extends Cazador {

	public CazadorRural(String nombre, Integer experiencia) {
		// TODO Auto-generated constructor stub
		super(nombre, experiencia);
	}

	public CazadorRural(String nombre) {
		super(nombre);
	}

	@Override
	public boolean puedeCapturar(IProfugo profugo) {
		return profugo.isNervioso();
	}

	@Override
	protected void intimidar(IProfugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setIsNervioso(true);
	}

}
