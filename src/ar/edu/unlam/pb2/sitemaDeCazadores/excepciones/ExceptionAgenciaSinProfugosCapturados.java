package ar.edu.unlam.pb2.sitemaDeCazadores.excepciones;

public class ExceptionAgenciaSinProfugosCapturados extends RuntimeException {

	public ExceptionAgenciaSinProfugosCapturados() {
		super("La agencia no tiene profugos capturados");
	}

	public ExceptionAgenciaSinProfugosCapturados(String mensaje) {
		super(mensaje);
	}

}
