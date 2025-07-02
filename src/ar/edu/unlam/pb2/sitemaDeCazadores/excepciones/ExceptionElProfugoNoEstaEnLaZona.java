package ar.edu.unlam.pb2.sitemaDeCazadores.excepciones;

public class ExceptionElProfugoNoEstaEnLaZona extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionElProfugoNoEstaEnLaZona(String mensaje) {
		super(mensaje);
	}

	public ExceptionElProfugoNoEstaEnLaZona() {
		super("El profugo no se encuentra en la zona");
	}

}
