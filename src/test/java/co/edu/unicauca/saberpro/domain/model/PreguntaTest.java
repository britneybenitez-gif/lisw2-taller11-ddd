package co.edu.unicauca.saberpro.domain.model;
import co.edu.unicauca.saberpro.domain.exception.ReglaNegocioException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PreguntaTest {
 private Pregunta nueva(){return new Pregunta(1L,new Enunciado("¿Cuál es la respuesta?"),new Justificacion("Porque esta es la respuesta correcta."));}
 private void opcionesValidas(Pregunta p){p.agregarOpcion("A",true);p.agregarOpcion("B",false);p.agregarOpcion("C",false);p.agregarOpcion("D",false);}
 @Test void enunciadoVacio(){assertThrows(ReglaNegocioException.class,()->new Enunciado("  "));}
 @Test void justificacionNula(){assertThrows(ReglaNegocioException.class,()->new Justificacion(null));}
 @Test void justificacionCorta(){assertThrows(ReglaNegocioException.class,()->new Justificacion("Muy corta"));}
 @Test void justificacionMinima(){assertDoesNotThrow(()->new Justificacion("123456789012345"));}
 @Test void opcionVacia(){assertThrows(ReglaNegocioException.class,()->new OpcionRespuesta(" ",false));}
 @Test void noEnviarConUnaOpcion(){Pregunta p=nueva();p.agregarOpcion("A",true);assertThrows(ReglaNegocioException.class,p::enviarARevision);}
 @Test void noEnviarConTresOpciones(){Pregunta p=nueva();p.agregarOpcion("A",true);p.agregarOpcion("B",false);p.agregarOpcion("C",false);assertThrows(ReglaNegocioException.class,p::enviarARevision);}
 @Test void noAgregarQuintaOpcion(){Pregunta p=nueva();opcionesValidas(p);assertThrows(ReglaNegocioException.class,()->p.agregarOpcion("E",false));}
 @Test void noEnviarSinCorrecta(){Pregunta p=nueva();for(int i=0;i<4;i++)p.agregarOpcion("O"+i,false);assertThrows(ReglaNegocioException.class,p::enviarARevision);}
 @Test void noEnviarConDosCorrectas(){Pregunta p=nueva();p.agregarOpcion("A",true);p.agregarOpcion("B",true);p.agregarOpcion("C",false);p.agregarOpcion("D",false);assertThrows(ReglaNegocioException.class,p::enviarARevision);}
 @Test void envioValido(){Pregunta p=nueva();opcionesValidas(p);p.enviarARevision();assertEquals(EstadoPregunta.PENDIENTE_REVISION,p.getEstado());}
 @Test void noModificarDespuesDeEnviar(){Pregunta p=nueva();opcionesValidas(p);p.enviarARevision();assertThrows(ReglaNegocioException.class,()->p.agregarOpcion("Otra",false));}
 @Test void noReenviar(){Pregunta p=nueva();opcionesValidas(p);p.enviarARevision();assertThrows(ReglaNegocioException.class,p::enviarARevision);}
 @Test void listaNoModificable(){Pregunta p=nueva();assertThrows(UnsupportedOperationException.class,()->p.getOpciones().add(new OpcionRespuesta("X",true)));}
 @Test void idInvalido(){assertThrows(ReglaNegocioException.class,()->new Pregunta(0L,new Enunciado("X"),new Justificacion("123456789012345")));}
 @Test void valueObjectsIguales(){assertEquals(new Enunciado(" Texto "),new Enunciado("Texto"));}
}
