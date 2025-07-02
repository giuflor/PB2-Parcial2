package ar.edu.unlam.pb2.sitemaDeCazadores.agencia;
package ar.edu.unlam.pb2.sitemaDeCazadores.agencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.Cazador;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

public class Agencia {

	private String nombre;
	private HashSet<Cazador> cazadores;

	public Agencia(String nombre) {
		this.nombre = nombre;
		this.cazadores = new HashSet<>();
	}

	public void agregarCazador(Cazador cazador) {
		this.cazadores.add(cazador);
	}

	public List<Profugo> obtenerTodosLosProfugosCapturados() {
		List<Profugo> todos = new ArrayList<>();
		for (Cazador cazador : cazadores) {
			todos.addAll(cazador.getCapturados());
		}
		return todos;
	}

	public Profugo obtenerProfugoMasHabilCapturado() {

		Profugo masHabil = null;
		int maxHabilidad = Integer.MIN_VALUE;

		for (Cazador cazador : cazadores) {
			for (Profugo profugo : cazador.getCapturados()) {
				if (profugo.getHabilidad() > maxHabilidad) {
					maxHabilidad = profugo.getHabilidad();
					masHabil = profugo;
				}
			}
		}
		return masHabil;
	}
	

}
