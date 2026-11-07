package co.edu.unicauca.saberpro.domain.repository;

import co.edu.unicauca.saberpro.domain.model.Pregunta;
import java.util.Optional;
public interface PreguntaRepository { Optional<Pregunta> buscarPorId(Long id); void guardar(Pregunta pregunta); }
