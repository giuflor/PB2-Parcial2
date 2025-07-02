package ar.edu.unlam.pb2.sitemaDeCazadores.zona;

import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionElProfugoYaEstaEnLaZona;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;

public class Zona {
	private String nombre;
	private HashSet<IProfugo> profugos;

	public Zona(String nombre) {
		this.nombre = nombre;
		this.profugos = new HashSet<>();
	}

	public void agregarProfugo(IProfugo profugo) {
		this.profugos.add(profugo);
	}

	public void agregarProfugos(List<IProfugo> profugos) throws ExceptionElProfugoYaEstaEnLaZona {
		for (IProfugo profugo : profugos) {
			if (profugos.contains(profugo)) {
				throw new ExceptionElProfugoYaEstaEnLaZona(profugo.getNombre() + " ya se encuentra en la zona");
			}
		}
		this.profugos.addAll(profugos);
	}

	public void removerProfugo(IProfugo profugo) {
		this.profugos.remove(profugo);
	}

	public HashSet<IProfugo> getProfugos() {
		return this.profugos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean contieneProfugo(IProfugo profugo) {
		return this.profugos.contains(profugo);
	}
}
