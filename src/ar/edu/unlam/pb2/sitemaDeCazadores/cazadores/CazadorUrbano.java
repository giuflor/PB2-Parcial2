package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;

public class CazadorUrbano extends Cazador {

	public CazadorUrbano(String nombre, Integer experiencia) {
		// TODO Auto-generated constructor stub
		super(nombre, experiencia);
	}

	public CazadorUrbano(String nombre) {
		super(nombre);
	}

	@Override
	public boolean puedeCapturar(IProfugo profugo) {
		return !profugo.isNervioso();
	}

	@Override
	protected void intimidar(IProfugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setIsNervioso(false);
	}
}
