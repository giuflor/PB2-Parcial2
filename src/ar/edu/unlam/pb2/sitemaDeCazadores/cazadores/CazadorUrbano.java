package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

public class CazadorUrbano extends Cazador {

	public CazadorUrbano(String nombre, Integer experiencia) {
		// TODO Auto-generated constructor stub
		super(nombre, experiencia);
	}

	public CazadorUrbano(String nombre) {
		super(nombre);
	}

	@Override
	public boolean puedeCapturar(Profugo profugo) {
		return !Profugo.isNervioso();
	}

	@Override
	protected void intimidar(Profugo profugo) {
		profugo.reducirInocencia(2);
		profugo.setNervioso(false);
	}

}
