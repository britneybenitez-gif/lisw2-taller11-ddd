package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.domain.exception.ReglaNegocioException;
import java.util.Objects;
public final class OpcionRespuesta {
 private final String texto; private final boolean esCorrecta;
 public OpcionRespuesta(String texto,boolean esCorrecta){ if(texto==null || texto.isBlank()) throw new ReglaNegocioException("El texto de la opción es obligatorio."); this.texto=texto.trim();this.esCorrecta=esCorrecta; }
 public String getTexto(){return texto;} public boolean isEsCorrecta(){return esCorrecta;}
 @Override public boolean equals(Object o){return o instanceof OpcionRespuesta r && esCorrecta==r.esCorrecta && texto.equals(r.texto);}
 @Override public int hashCode(){return Objects.hash(texto,esCorrecta);}
}
