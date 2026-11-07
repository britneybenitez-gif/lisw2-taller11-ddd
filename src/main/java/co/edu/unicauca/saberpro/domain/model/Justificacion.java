package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.domain.exception.ReglaNegocioException;
import java.util.Objects;
public final class Justificacion {
 private final String texto;
 public Justificacion(String texto) { if(texto==null || texto.trim().length()<15) throw new ReglaNegocioException("La justificación debe contener al menos 15 caracteres."); this.texto=texto.trim(); }
 public String getTexto(){return texto;}
 @Override public boolean equals(Object o){return o instanceof Justificacion j && texto.equals(j.texto);}
 @Override public int hashCode(){return Objects.hash(texto);}
}
