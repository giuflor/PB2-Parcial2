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
		Profugo p= new Profugo("Juan",30,75,true);
		assertEquals("Juan",p.getNombre());
		assertEquals(Integer.valueOf(30),p.getInocencia());
		assertEquals(Integer.valueOf(75),p.getHabilidad());
		assertTrue(p.isNervioso());
		
	}
	@Test
	public void queSePuedaReducirLaInocenciaYHabilidad() {
		Profugo p= new Profugo("Ana",50,60,false);
		p.reducirInocencia(10);
		p.reducirHabilidad(15);
		assertEquals(Integer.valueOf(40),p.getInocencia());
		assertEquals(Integer.valueOf(45),p.getHabilidad());
	}
	@Test
	public void queSePuedaEvolucionarConEntrenamientos() {
		IProfugo base= new Profugo("Leo",40,70,false);
		ProfugoEntrenado evolucionado=new ProfugoEntrenado(base);
		evolucionado.recibirProteccionLegal();
		evolucionado.entrenarEnElite();
		evolucionado.entrenarEnArtesMarciales();
		assertTrue(evolucionado.getHabilidad()>base.getHabilidad());
		assertEquals(Integer.valueOf(40),evolucionado.getInocencia());
		assertFalse(evolucionado.isNervioso());
	}
	@Test
	public void queLaInocenciaYHabilidadNoSeanMenoresACero() {
		Profugo p= new Profugo("Ana",50,60,false);
		p.reducirInocencia(100);
		p.reducirHabilidad(100);
		assertEquals(Integer.valueOf(0),p.getInocencia());
		assertEquals(Integer.valueOf(0),p.getHabilidad());
	}
}
