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
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
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

		assertEquals(2, agencia.obtenerTodosLosProfugosCapturados().size()); // 2 profugos capturados
		assertEquals("Escapista", masHabil.getNombre()); // Escapista es el profugo mas habil capturado
	}

	@Test
	public void queLaAgenciaObtengaElCazadorConMasCapturas() {
		Profugo p1 = new Profugo("Smith", 50, 30, false);
		Profugo p2 = new Profugo("Escapista", 40, 49, true);
		Profugo p3 = new Profugo("Rápido", 30, 20, false);

		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);
		zona.agregarProfugo(p3);

		cazadorUrbano.realizarCaptura(zona); // cazador Rick captura a Smith y Rápido
		cazadorSigiloso.realizarCaptura(zona); // cazador Ghost captura a Escapista

		// Verificamos que el cazador con más capturas sea Rick
		String nombreDelMejorCazador = agencia.obtenerCazadorConMasCapturas().getNombre();

		assertEquals(2, cazadorUrbano.getCapturados().size()); // Rick capturó 2 profugos
		assertEquals(1, cazadorSigiloso.getCapturados().size()); // Ghost capturó 1 profugo
		assertEquals(3, agencia.obtenerTodosLosProfugosCapturados().size()); // Total de profugos capturados
		assertTrue(nombreDelMejorCazador.equals("Rick"));
	}

	@Test
	public void queLaAgenciaDevuelvaNullSiNoHayCapturados() {
		Agencia agencia = new Agencia("Agencia Vacía");

		CazadorUrbano cazador = new CazadorUrbano("Rick", 80);
		agencia.agregarCazador(cazador);

		assertEquals(null, agencia.obtenerProfugoMasHabilCapturado());
	}

	@Test
	public void queLaAgenciaElijaUnoEnCasoDeEmpateDeCapturas() {
		Agencia agencia = new Agencia("Agencia Empate");

		CazadorUrbano cazador1 = new CazadorUrbano("Rick", 80);
		CazadorRural cazador2 = new CazadorRural("Daryl", 80);

		agencia.agregarCazador(cazador1);
		agencia.agregarCazador(cazador2);

		Profugo p1 = new Profugo("Smith", 50, 30, false);
		Profugo p2 = new Profugo("John", 50, 30, true);

		Zona zona1 = new Zona("Zona 1");
		Zona zona2 = new Zona("Zona 2");

		zona1.agregarProfugo(p1);
		zona2.agregarProfugo(p2);

		cazador1.realizarCaptura(zona1);
		cazador2.realizarCaptura(zona2);

		String nombre = agencia.obtenerCazadorConMasCapturas().getNombre();
		assertTrue(nombre.equals("Rick") || nombre.equals("Daryl"));
	}

	@Test
	public void queNoSeAgreguenCazadoresDuplicadosEnLaAgencia() {
		Agencia agencia = new Agencia("Seguridad");

		CazadorUrbano cazador1 = new CazadorUrbano("Rick", 80);
		CazadorUrbano cazador2 = new CazadorUrbano("Rick", 80); // Mismo nombre

		agencia.agregarCazador(cazador1);
		agencia.agregarCazador(cazador2);

		assertEquals(1, agencia.getCazadores().size());
	}
	
	@Test
	public void queLaAgenciaAdministreCazadoresEnVariasZonas() {
	    Agencia agencia = new Agencia("Agencia Global");

	    CazadorUrbano cazador = new CazadorUrbano("Rick", 80);
	    agencia.agregarCazador(cazador);

	    Zona zona1 = new Zona("Zona A");
	    Zona zona2 = new Zona("Zona B");

	    Profugo p1 = new Profugo("Smith", 30, 30, false);
	    Profugo p2 = new Profugo("John", 30, 30, false);

	    zona1.agregarProfugo(p1);
	    zona2.agregarProfugo(p2);

	    cazador.realizarCaptura(zona1);
	    cazador.realizarCaptura(zona2);

	    assertEquals(2, cazador.getCapturados().size());
	    assertTrue(agencia.obtenerTodosLosProfugosCapturados().contains(p1));
	    assertTrue(agencia.obtenerTodosLosProfugosCapturados().contains(p2));
	}
	
	

}
