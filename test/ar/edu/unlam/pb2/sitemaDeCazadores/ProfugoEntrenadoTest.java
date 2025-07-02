package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorSigiloso;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorUrbano;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.ProfugoEntrenado;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public class ProfugoEntrenadoTest {
	@Test
	public void queCazadorIntimideAProfugoEntrenadoYSeReduzcaCorrectamenteLosAtributos() {
		Profugo base = new Profugo("Entrena", 50, 80, true);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.entrenarEnElite();// Ya no podra ser nervioso
		entrenado.recibirProteccionLegal();// Su inocencia no puede bajar de 40
		entrenado.entrenarEnArtesMarciales();// Duplica la habilidad (hasta 100)
		Zona zona = new Zona("Zona Protegida");
		zona.agregarProfugo(entrenado);
		CazadorRural cazador = new CazadorRural("Rural Pro", 70);
		cazador.realizarCaptura(zona);
		// la inocencia se reduce pero no puede bajar de 40
		assertEquals(Integer.valueOf(40), entrenado.getInocencia());
		// El cazador rural vuelve nervioso al profugo,pero como tiene elite,sigue sin
		// ser nervioso
		assertFalse(entrenado.isNervioso());
		// La habilidad se duplica con el entrenamiento y deberia seguir duplicada
		assertEquals(Integer.valueOf(100), entrenado.getHabilidad());
	}

	@Test
	public void queCazadorPuedaCapturarAProfugoEntrenadoConHabilidadDuplicada() {
		Profugo base = new Profugo("Entrenado 2", 40, 20, false);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.entrenarEnArtesMarciales();// habilidad ahora es 40
		Zona zona = new Zona("Zona B");
		zona.agregarProfugo(entrenado);
		CazadorUrbano cazador = new CazadorUrbano("Urban Elite", 60);
		cazador.realizarCaptura(zona);
		// deberia capturarlo
		assertTrue(cazador.getCapturados().contains(entrenado));
		assertTrue(zona.getProfugos().isEmpty());
	}

	@Test
	public void queCazadorNoCaptureNiIntimideSiInocenciaSuperaExperienciaAunConEntrenamiento() {
		Profugo base = new Profugo("Inmune", 90, 10, true);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.recibirProteccionLegal();// No puede bajar de 40 inocencia
		Zona zona = new Zona("Zona C");
		zona.agregarProfugo(entrenado);
		CazadorSigiloso cazador = new CazadorSigiloso("Sigiloso Bajo", 60);
		cazador.realizarCaptura(zona);
		// no debe capturar ni intimidarlo
		assertFalse(cazador.getCapturados().contains(entrenado));
		assertTrue(zona.getProfugos().contains(entrenado));

	}

	@Test
	public void queProteccionLegalMantengaMinimoDeInocenciaAlIntimidar() {
		Profugo base = new Profugo("Protegido", 42, 50, true);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.recibirProteccionLegal();// Inocencia no puede bajar de 40
		Zona zona = new Zona("Zona D");
		zona.agregarProfugo(entrenado);
		CazadorRural cazador = new CazadorRural("Rural Bravo", 80);
		cazador.realizarCaptura(zona);
		// La inocencia se reduce pero no puede bajar de 40
		assertEquals(Integer.valueOf(40), entrenado.getInocencia());
	}

	@Test
	public void queLaHabilidadNoSupereElMaximoDe100ConArtesMarciales() {
		Profugo base = new Profugo("Guerrero", 30, 60, false);
		ProfugoEntrenado entrenado = new ProfugoEntrenado(base);
		entrenado.entrenarEnArtesMarciales();// habilidad seria 120,pero debe ser como maximo 100
		assertEquals(Integer.valueOf(100), entrenado.getHabilidad());
	}

}