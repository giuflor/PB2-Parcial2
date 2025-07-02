package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.agencia.Agencia;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorSigiloso;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorUrbano;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public class AgenciaTest {

	private Agencia agencia;
	private CazadorUrbano cazadorUrbano;
	private CazadorRural cazadorRural;
	private CazadorSigiloso cazadorSigiloso;

	@Before
	public void setUp() {
		agencia = new Agencia("Agencia Central");

		cazadorUrbano = new CazadorUrbano("Rick", 80);
		cazadorRural = new CazadorRural("Daryl", 70);
		cazadorSigiloso = new CazadorSigiloso("Ghost", 90);

		agencia.agregarCazador(cazadorUrbano);
		agencia.agregarCazador(cazadorRural);
		agencia.agregarCazador(cazadorSigiloso);
	}

	@Test
	public void queLaAgenciaObtengaTodosLosProfugosCapturados() {
		IProfugo p1 = new Profugo("Smith", 50, 30, true);
		IProfugo p2 = new Profugo("Escapista", 40, 70, false);
		IProfugo p3 = new Profugo("Smith 2.0", 60, 40, true);
		IProfugo p4 = new Profugo("Escapista 2.0", 20, 70, false);
		// los cazadores tienen un mayor nivel de experiencia
		// que la inocencia de los profugos de arriba
		IProfugo noAtrapado = new Profugo("Libre", 80, 50, true);

		Zona zona = new Zona("Ciudad");
		zona.agregarProfugos(List.of(p1, p2, p3, p4, noAtrapado));

		// cazador sigiloso captura a Smith y Smith 2.0
		// porque su nivel de habilidad es menor a 50 e intimida a los demas
		cazadorSigiloso.realizarCaptura(zona);
		// cazador urbano captura a Escapista y ...2.0 porque no estan nerviosos
		cazadorUrbano.realizarCaptura(zona);

		List<IProfugo> capturados = agencia.obtenerTodosLosProfugosCapturados();
		assertEquals(1, zona.getProfugos().size()); // solo queda el profugo libre
		assertEquals(4, capturados.size()); // los 4 profugos fueron capturados
	}

	@Test
	public void queLaAgenciaObtengaElProfugoMasHabilCapturado() {
		Profugo p1 = new Profugo("Smith", 50, 30, true);
		Profugo p2 = new Profugo("Escapista", 40, 70, false);

		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		// cazador sigiloso captura a Smith porque su nivel de habilidad es menor a 50
		cazadorSigiloso.realizarCaptura(zona);
		// cazador urbano captura a Escapista porque no esta nervioso
		cazadorUrbano.realizarCaptura(zona);

		IProfugo masHabil = agencia.obtenerProfugoMasHabilCapturado();
		assertEquals("Escapista", masHabil.getNombre());
	}

	@Test
	public void queLaAgenciaObtengaElCazadorConMasCapturas() {
		Profugo p1 = new Profugo("Smith", 50, 30, false);
		Profugo p2 = new Profugo("Escapista", 40, 70, true);
		Profugo p3 = new Profugo("Rápido", 30, 20, false);

		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p3);

		cazadorUrbano.realizarCaptura(zona);
		cazadorSigiloso.realizarCaptura(zona);

		// Verificamos que el cazador con más capturas sea Rick o Ghost según resultado
		String nombreDelMejorCazador = agencia.obtenerCazadorConMasCapturas().getNombre();

		assertTrue(nombreDelMejorCazador.equals("Rick") || nombreDelMejorCazador.equals("Ghost"));
	}
}