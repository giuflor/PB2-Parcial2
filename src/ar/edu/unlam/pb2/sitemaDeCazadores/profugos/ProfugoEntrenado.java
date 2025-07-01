package ar.edu.unlam.pb2.sitemaDeCazadores.profugos;

public class ProfugoEntrenado implements IProfugo{

	private IProfugo base;
	private boolean artesMarciales=false;
	private boolean elite=false;
	private boolean proteccionLegal=false;
	
	public ProfugoEntrenado(IProfugo base) {
		this.base=base;
	}
	public void entrenarEnArtesMarciales() {
		this.artesMarciales=true;
	}
	public void entrenarEnElite() {
		this.elite=true;
	}
	public void recibirProteccionLegal() {
		this.proteccionLegal=true;
	}
	@Override
	public String getNombre() {
		return base.getNombre();
	}
	@Override
	public Integer getInocencia() {
		int inocencia=base.getInocencia();
		if(proteccionLegal) {
			return Math.max(40, inocencia);
		}
		return inocencia;
	}
	@Override
	public Integer getHabilidad() {
	int habilidad=base.getHabilidad();
	if(artesMarciales) {
		habilidad =Math.min(100,habilidad*2);
	}
	return habilidad;
	}
	@Override
	public boolean isNervioso() {
		if(elite) {
			return false;
		}
		return base.isNervioso();
	}

}
