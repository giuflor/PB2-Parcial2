package ar.edu.unlam.pb2.sitemaDeCazadores.profugos;

public class Profugo {
	private String nombre;
	private int inocencia;
	private int habilidad;
	private boolean nervioso;
	
	public Profugo (String nombre,int inocencia,int habilidad,boolean nervioso) {
		this.nombre = nombre;
		this.inocencia = inocencia;
		this.habilidad = habilidad;
		this.nervioso = nervioso;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getInocencia() {
		return inocencia;
	}
	public Integer getHabilidad() {
		return habilidad;
	}
	public boleean isNervioso() {
		return nervioso;
	}
}
