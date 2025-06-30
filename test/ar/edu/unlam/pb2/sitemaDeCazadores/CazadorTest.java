package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class CazadorTest {
	protected CazadorUrbano cazadorUrbano;
	protected CazadorRural cazadorRural;
	protected CazadorSigiloso cazadorSigiloso;
	protected Profugo profugo;

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

		assertEquals((Integer) (58), this.profugo.getNivelInocencia());
		assertTrue(this.profugo.esNervioso());
	}

	@Test
	public void queElCazadorSumeExperienciaCorrectamente() {
		Profugo profugoNoCapturable = new Profugo("Dos", 60, 70, true);
		Zona zona = new Zona("Subterraneo");

		zona.agregarProfugo(this.profugo);
		zona.agregarProfugo(profugoNoCapturable);

		this.cazadorSigiloso.realizarCaptura(zona);

		// Captura p1 (+2), intimida profugoNoCapturable (mínimo habilidad 70)
		assertEquals((Integer) (90 + 2 + 70), cazadorSigiloso.getExperiencia());
	}

	@Test(expected = ExceptionCapturaFallida.class)
	public void queLanceExcepcionSiZonaEsNula() throws ExceptionCapturaFallida {
		Zona zona = null;
		this.cazadorUrbano.realizarCaptura(zona);
	}

	@Test
	public void queNoSeIntimideNiSeCaptureUnProfugoConMayorInocenciaQueLaExperienciaDelCazador() {
		Zona zona = new Zona("Ciudad");

		zona.agregarProfugo(this.profugo);

		this.cazadorUrbano.realizarCaptura(zona);

		assertFalse(this.cazadorUrbano.getCapturados().contains(this.profugo));
		assertTrue(zona.getProfugos().contains(this.profugo));
	}

	@Test
	public void queUnCazadorNoRepitaCapturaDelMismoProfugo() {
		Zona zona = new Zona("Centro");

		zona.agregarProfugo(this.profugo);

		this.cazadorUrbano.realizarCaptura(zona);

		zona.agregarProfugo(this.profugo); // lo volvemos a agregar (no debería volver a capturarlo)
		this.cazadorUrbano.realizarCaptura(zona);

		assertEquals(1, this.cazadorUrbano.getCapturados().size()); // solo una vez
	}

	@Test
	public void queSeMantengaElOrdenDeCapturas() {
		Profugo profugo1 = new Profugo("Uno", 50, 30, false);
		Profugo profugo2 = new Profugo("Dos", 40, 20, false);
		Zona zona = new Zona("Villa");

		zona.agregarProfugo(profugo1);
		zona.agregarProfugo(profugo2);

		this.cazadorUrbano.realizarCaptura(zona);

		List<Profugo> capturados = this.cazadorUrbano.getCapturados();
		assertEquals("Uno", capturados.get(0).getNombre());
		assertEquals("Dos", capturados.get(1).getNombre());
	}

	@Test
	public void queNoSeIntimideAUnProfugoYaIntimidadoEnOtraZona() {
		CazadorSigiloso cazador = new CazadorSigiloso("Sombra", 100);
		Profugo profugo = new Profugo("Invisible", 90, 90, true);
		Zona zona1 = new Zona("Zona A");
		Zona zona2 = new Zona("Zona B");

		zona1.agregarProfugo(profugo);
		cazador.realizarCaptura(zona1);

		int habilidadDespues = profugo.getNivelHabilidad();

		zona2.agregarProfugo(profugo);
		cazador.realizarCaptura(zona2);

		// No debería volver a intimidar
		assertEquals((Integer) habilidadDespues, profugo.getNivelHabilidad());
	}

}
