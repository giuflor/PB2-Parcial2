package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorRural;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorSigiloso;
import ar.edu.unlam.pb2.sitemaDeCazadores.cazadores.CazadorUrbano;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionCapturaFallida;
import ar.edu.unlam.pb2.sitemaDeCazadores.excepciones.ExceptionElProfugoYaFueCapturado;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.zona.Zona;

public class CazadorTest {
	protected CazadorUrbano cazadorUrbano;
	protected CazadorRural cazadorRural;
	protected CazadorSigiloso cazadorSigiloso;
	protected IProfugo profugo;

	@Before
	public void setUp() {
		this.cazadorUrbano = new CazadorUrbano("Rick", 80);
		this.cazadorRural = new CazadorRural("Daryl", 70);
		this.cazadorSigiloso = new CazadorSigiloso("Ghost", 90);
		this.profugo = new Profugo("Smith", 60, 30, false);
	}

	@Test
	public void queUnCazadorUrbanoPuedaCapturarUnProfugoNoNerviosoConMenorInocencia() {
		Zona zona = new Zona("Ciudad");
		zona.agregarProfugo(profugo);

		this.cazadorUrbano.realizarCaptura(zona);

		assertTrue(this.cazadorUrbano.getCapturados().contains(this.profugo));
		assertFalse(zona.getProfugos().contains(this.profugo));
	}

	@Test
	public void queUnCazadorIntimideSiNoPuedeCapturar() {
		Zona zona = new Zona("Campo");
		zona.agregarProfugo(this.profugo);

		this.cazadorRural.realizarCaptura(zona);

		assertEquals((Integer) (58), this.profugo.getInocencia());
		assertTrue(this.profugo.isNervioso());
	}

	@Test
	public void queElCazadorSumeExperienciaCorrectamente() {
		Profugo profugoNoCapturable = new Profugo("Escapista", 60, 70, true);
		Zona zona = new Zona("Subterraneo");
		zona.agregarProfugo(this.profugo);
		zona.agregarProfugo(profugoNoCapturable);

		this.cazadorSigiloso.realizarCaptura(zona);

		// 90 del cazador, +2 por captura de this.profugo,
		// +70 por intimidación
		// - 5 por que la intimidación le baja 5 de habilidad al profugoNoCapturable
		Integer experienciaEsperada = 90 + 2 + 70 - 5;

		assertEquals(experienciaEsperada, cazadorSigiloso.getExperiencia());
	}

	@Test(expected = ExceptionCapturaFallida.class)
	public void queLanceExcepcionSiZonaEsNula() throws ExceptionCapturaFallida {
		Zona zona = null;
		this.cazadorUrbano.realizarCaptura(zona);
	}

	@Test
	public void queNoSeIntimideNiSeCaptureUnProfugoConMayorInocenciaQueLaExperienciaDelCazador() {
		Zona zona = new Zona("Ciudad");
		Profugo profugo = new Profugo("Super Inocente", 99, 30, false);

		zona.agregarProfugo(profugo);

		this.cazadorUrbano.realizarCaptura(zona);

		assertFalse(this.cazadorUrbano.contieneCaptura(profugo));
		assertTrue(zona.contieneProfugo(profugo));
	}

	@Test(expected = ExceptionElProfugoYaFueCapturado.class)
	public void queLanceExceptionSiUnCazadorIntentaCapturarUnMismoProfugoCapturado() throws ExceptionElProfugoYaFueCapturado{
		Zona zona = new Zona("Centro");

		zona.agregarProfugo(this.profugo);

		this.cazadorUrbano.realizarCaptura(zona);

		// lo volvemos a agregar (no debería volver a capturarlo)
		zona.agregarProfugo(this.profugo);
		this.cazadorUrbano.realizarCaptura(zona);
	}

	@Test
	public void queSeMantengaElOrdenDeCapturas() {
		Profugo profugo1 = new Profugo("Uno", 50, 30, false);
		Profugo profugo2 = new Profugo("Dos", 40, 20, false);
		Zona zona = new Zona("Villa");

		zona.agregarProfugo(profugo1);
		zona.agregarProfugo(profugo2);

		this.cazadorUrbano.realizarCaptura(zona);

		List<IProfugo> capturados = this.cazadorUrbano.getCapturados();
		assertEquals("Uno", capturados.get(0).getNombre());
		assertEquals("Dos", capturados.get(1).getNombre());
	}

	@Test
	public void queNoSeIntimideAUnProfugoYaIntimidadoEnOtraZona() {
		Profugo profugo = new Profugo("Invisible", 80, 80, true);
		Zona zona1 = new Zona("Zona A");
		Zona zona2 = new Zona("Zona B");

		zona1.agregarProfugo(profugo);
		this.cazadorSigiloso.realizarCaptura(zona1);

		int habilidadDespues = profugo.getHabilidad();

		zona2.agregarProfugo(profugo);
		this.cazadorSigiloso.realizarCaptura(zona2);

		// No debería volver a intimidar
		assertEquals((Integer) habilidadDespues, profugo.getHabilidad());
	}
	
	
	@Test
	public void queElCazadorCaptureEIntimideEnLaMismaZonaYCalculeBienLaExperiencia() {
	    // Cazador con experiencia 80
	    CazadorUrbano cazador = new CazadorUrbano("Rick", 80);

	    // Profugo capturable (inocencia 50 < experiencia 80, no nervioso)
	    Profugo capturable = new Profugo("Capturable", 50, 40, false);

		// Profugo no capturable pero intimidable (inocencia 50 < experiencia 80, es nervioso)
	    Profugo intimidar = new Profugo("Intimidar", 50, 60, true);

	    Zona zona = new Zona("Zona Centro");
	    zona.agregarProfugo(capturable);
	    zona.agregarProfugo(intimidar);

	    cazador.realizarCaptura(zona);

	    // El capturable debería estar capturado
	    assertTrue(cazador.getCapturados().contains(capturable));
	    // El intimidado debería seguir en la zona
	    assertTrue(zona.getProfugos().contains(intimidar));
	    // La experiencia esperada es: min habilidad de intimidados (60) + 2 * cantidad de capturas (2 * 1)
	    Integer experienciaEsperada = 80 + 60 + 2;

	    assertEquals(experienciaEsperada, cazador.getExperiencia());
	}

	@Test
	public void queUnCazadorNoPuedaCapturarUnProfugoConHabilidadCero() {
		Profugo profugoIncapaz = new Profugo("Incapaz", 0, 0, false);
		Zona zona = new Zona("Desierto");

		zona.agregarProfugo(profugoIncapaz);

		this.cazadorRural.realizarCaptura(zona);

		// El profugo no debería ser capturado ni intimidado
		assertEquals((Integer) 0, profugoIncapaz.getInocencia());
		assertEquals((Integer) 0, profugoIncapaz.getHabilidad());
		assertFalse(this.cazadorRural.contieneCaptura(profugoIncapaz));
		assertTrue(zona.contieneProfugo(profugoIncapaz));
	}

	@Test
	public void queUnCazadorNoCaptureSiSuExperienciaEsIgualALaInocenciaDelProfugo() {
		Profugo profugo = new Profugo("Empate", 80, 50, false);
		Zona zona = new Zona("Plaza");

		zona.agregarProfugo(profugo);

		this.cazadorUrbano.realizarCaptura(zona);

		assertFalse(this.cazadorUrbano.contieneCaptura(profugo));
		assertTrue(zona.contieneProfugo(profugo));
	}

	@Test
	public void queUnCazadorUrbanoNoSeaIgualQueUnCazadorRuralAunqueTenganElMismoNombre() {
		CazadorUrbano cazadorUrbano = new CazadorUrbano("Hunter", 100);
		CazadorRural cazadorRural = new CazadorRural("Hunter", 100);

		assertNotEquals(cazadorUrbano, cazadorRural);
	}

	@Test
	public void queUnCazadorUrbanoSeaIgualQueOtroConElMismoNombre() {
		CazadorUrbano cazadorUrbano = new CazadorUrbano("Hunter", 100);
		CazadorUrbano cazadorUrbano2 = new CazadorUrbano("Hunter", 100);

		assertEquals(cazadorUrbano, cazadorUrbano2);
	}
}
