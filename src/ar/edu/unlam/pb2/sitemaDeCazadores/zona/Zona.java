package ar.edu.unlam.pb2.sitemaDeCazadores.zona;

import java.util.HashSet;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;

public class Zona {
	private String nombre;
	private HashSet<Profugo> profugos;

	public Zona(String nombre) {
		this.nombre = nombre;
		this.profugos = new HashSet<>();
	}

	public void agregarProfugo(Profugo profugo) {
		this.profugos.add(profugo);
	}

	public void removerProfugo(Profugo profugo) {
		this.profugos.remove(profugo);
	}

	public HashSet<Profugo> getProfugos() {
		return this.profugos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean contieneProfugo(Profugo profugo) {
		return this.profugos.contains(profugo);
	}
}
