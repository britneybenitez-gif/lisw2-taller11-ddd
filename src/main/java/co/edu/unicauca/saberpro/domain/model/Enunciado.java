package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.domain.exception.ReglaNegocioException;
import java.util.Objects;
public final class Enunciado {
 private final String texto;
 public Enunciado(String texto) { if(texto==null || texto.isBlank()) throw new ReglaNegocioException("El enunciado es obligatorio."); this.texto=texto.trim(); }
 public String getTexto(){return texto;}
 @Override public boolean equals(Object o){return o instanceof Enunciado e && texto.equals(e.texto);}
 @Override public int hashCode(){return Objects.hash(texto);}
}
