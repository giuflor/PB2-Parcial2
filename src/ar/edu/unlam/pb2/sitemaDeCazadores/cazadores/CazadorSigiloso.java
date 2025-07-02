package ar.edu.unlam.pb2.sitemaDeCazadores.cazadores;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;

public class CazadorSigiloso extends Cazador {

	public CazadorSigiloso(String nombre, Integer experiencia) {
		// TODO Auto-generated constructor stub
		super(nombre, experiencia);
	}

	public CazadorSigiloso(String nombre) {
		super(nombre);
	}

	@Override
	public boolean puedeCapturar(IProfugo profugo) {
		return profugo.getHabilidad() < 50;
	}

	@Override
	protected void intimidar(IProfugo profugo) {
		profugo.reducirInocencia(2);
		profugo.reducirHabilidad(5);
	}
}
