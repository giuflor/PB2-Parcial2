package ar.edu.unlam.pb2.sitemaDeCazadores;
package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.agencia.Agencia;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorSigiloso;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorUrbano;
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
		Profugo p1 = new Profugo("Smith", 50, 30, false);
		Profugo p2 = new Profugo("Escapista", 40, 70, true);

		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		cazadorUrbano.realizarCaptura(zona);
		cazadorSigiloso.realizarCaptura(zona);

		List<Profugo> capturados = agencia.obtenerTodosLosProfugosCapturados();
		assertEquals(2, capturados.size());
	}
	
	@Test
    public void queLaAgenciaObtengaElProfugoMasHabilCapturado() {
        Profugo p1 = new Profugo("Smith", 50, 30, false);
        Profugo p2 = new Profugo("Escapista", 40, 70, true);

        Zona zona = new Zona("Ciudad");
        zona.agregarProfugo(p1);
        zona.agregarProfugo(p2);

        cazadorUrbano.realizarCaptura(zona);
        cazadorSigiloso.realizarCaptura(zona);

        Profugo masHabil = agencia.obtenerProfugoMasHabilCapturado();
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















}
