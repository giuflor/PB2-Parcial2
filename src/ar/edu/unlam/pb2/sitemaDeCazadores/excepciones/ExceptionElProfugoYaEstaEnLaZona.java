package ar.edu.unlam.pb2.sitemaDeCazadores.excepciones;

public class ExceptionElProfugoYaEstaEnLaZona extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionElProfugoYaEstaEnLaZona(String mensaje) {
		super(mensaje);
	}

	public ExceptionElProfugoYaEstaEnLaZona() {
		super("El profugo ya se encuentra en la zona");
	}
}
