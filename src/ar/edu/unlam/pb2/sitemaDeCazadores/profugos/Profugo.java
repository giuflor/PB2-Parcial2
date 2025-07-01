package ar.edu.unlam.pb2.sitemaDeCazadores.profugos;


public class Profugo implements IProfugo{
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
	public boolean isNervioso() {
		return nervioso;
	}
	public void reducirInocencia(int puntos) {
		this.inocencia=Math.max(0,this.inocencia-puntos);
	}
	public void reducirHabilidad(int puntos) {
		this.habilidad=Math.max(0,this.habilidad-puntos);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Profugo))return false;
		Profugo otro=(Profugo)o;
		return this.nombre.equals(otro.nombre);	
	}
	
	@Override
	public int hashCode() {
		return this.nombre.hashCode();
	}

}


