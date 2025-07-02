package ar.edu.unlam.pb2.sitemaDeCazadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.IProfugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.Profugo;
import ar.edu.unlam.pb2.sitemaDeCazadores.profugos.ProfugoEntrenado;

public class ProfugoTest {
	@Test
	public void queSePuedaCrearUnProfugo() {
		Profugo profugo = new Profugo("Juan", 30, 75, true);
		assertEquals("Juan", profugo.getNombre());
		assertEquals(Integer.valueOf(30), profugo.getInocencia());
		assertEquals(Integer.valueOf(75), profugo.getHabilidad());
		assertTrue(profugo.isNervioso());

	}

	@Test
	public void queSePuedaReducirLaInocenciaYHabilidad() {
		Profugo profugo = new Profugo("Ana", 50, 60, false);
		profugo.reducirInocencia(10);
		profugo.reducirHabilidad(15);
		assertEquals(Integer.valueOf(40), profugo.getInocencia());
		assertEquals(Integer.valueOf(45), profugo.getHabilidad());
	}

	@Test
	public void queSePuedaEvolucionarConEntrenamientos() {
		IProfugo base = new Profugo("Leo", 40, 70, false);
		ProfugoEntrenado evolucionado = new ProfugoEntrenado(base);
		evolucionado.recibirProteccionLegal();
		evolucionado.entrenarEnElite();
		evolucionado.entrenarEnArtesMarciales();
		assertTrue(evolucionado.getHabilidad() > base.getHabilidad());
		assertEquals(Integer.valueOf(40), evolucionado.getInocencia());
		assertFalse(evolucionado.isNervioso());
	}
	
	@Test
	public void queLaInocenciaYHabilidadNoSeanMenoresACero() {
		Profugo profugo = new Profugo("Ana",50,60,false);
		profugo.reducirInocencia(100);
		profugo.reducirHabilidad(100);
		assertEquals(Integer.valueOf(0),profugo.getInocencia());
		assertEquals(Integer.valueOf(0),profugo.getHabilidad());
	}

	
	@Test
	public void queLaInocenciaNoSeaNegativa() {
	    Profugo profugo = new Profugo("Smith", 1, 50, false);
	    profugo.reducirInocencia(10);
	    assertEquals(Integer.valueOf(0), profugo.getInocencia());
	}
	
	@Test
	public void queLaHabilidadNoSeaNegativa() {
	    Profugo profugo = new Profugo("John", 50, 4, false);
	    profugo.reducirHabilidad(10);
	    assertEquals(Integer.valueOf(0), profugo.getHabilidad());
	}
	@Test
	public void queSePuedaCambiarElEstadoDeNerviosismo() {
	    Profugo profugo = new Profugo("Jane", 30, 60, true);
	    profugo.setIsNervioso(false);
	    assertFalse(profugo.isNervioso());
	}
	@Test
	public void queDosProfugosConElMismoNombreSeanIguales() {
	    Profugo profugo1 = new Profugo("Smith", 30, 50, false);
	    Profugo profugo2 = new Profugo("Smith", 40, 60, true);

	    assertTrue(profugo1.equals(profugo2));
	}
	@Test
	public void queElHashCodeDeProfugosConElMismoNombreSeaIgual() {
	    Profugo profugo1 = new Profugo("Smith", 30, 50, false);
	    Profugo profugo2 = new Profugo("Smith", 40, 60, true);

	    assertEquals(profugo1.hashCode(), profugo2.hashCode());
	}
	@Test
	public void queLosAtributosSeGuardenCorrectamente() {
	    Profugo profugo = new Profugo("Ana", 50, 80, true);

	    assertEquals("Ana", profugo.getNombre());
	    assertEquals(Integer.valueOf(50), profugo.getInocencia());
	    assertEquals(Integer.valueOf(80), profugo.getHabilidad());
	    assertTrue(profugo.isNervioso());
	}

}
