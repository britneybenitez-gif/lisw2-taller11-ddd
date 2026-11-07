package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.domain.exception.ReglaNegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public final class Pregunta {
 private final Long id; private Enunciado enunciado; private Justificacion justificacion;
 private final List<OpcionRespuesta> opciones=new ArrayList<>();
 private EstadoPregunta estado=EstadoPregunta.BORRADOR;
 public Pregunta(Long id,Enunciado enunciado,Justificacion justificacion){
  if(id==null || id<=0)throw new ReglaNegocioException("El ID debe ser positivo.");
  this.id=id;this.enunciado=Objects.requireNonNull(enunciado,"Enunciado obligatorio");this.justificacion=Objects.requireNonNull(justificacion,"Justificación obligatoria");
 }
 public Long getId(){return id;} public Enunciado getEnunciado(){return enunciado;} public Justificacion getJustificacion(){return justificacion;}
 public EstadoPregunta getEstado(){return estado;} public List<OpcionRespuesta> getOpciones(){return List.copyOf(opciones);}
 private void verificarBorrador(){if(estado!=EstadoPregunta.BORRADOR)throw new ReglaNegocioException("Solo se permite modificar en Borrador.");}
 public void cambiarEnunciado(Enunciado nuevo){verificarBorrador();enunciado=Objects.requireNonNull(nuevo);}
 public void cambiarJustificacion(Justificacion nueva){verificarBorrador();justificacion=Objects.requireNonNull(nueva);}
 public void agregarOpcion(String texto,boolean esCorrecta){verificarBorrador();if(opciones.size()>=4)throw new ReglaNegocioException("Una pregunta no puede tener más de 4 opciones.");opciones.add(new OpcionRespuesta(texto,esCorrecta));}
 public void enviarARevision(){verificarBorrador();if(opciones.size()!=4)throw new ReglaNegocioException("Debe tener exactamente 4 opciones de respuesta.");
  if(opciones.stream().filter(OpcionRespuesta::isEsCorrecta).count()!=1)throw new ReglaNegocioException("Debe existir exactamente 1 opción correcta.");
  estado=EstadoPregunta.PENDIENTE_REVISION;
 }
}
