# Taller 11 — Domain-Driven Design (DDD) | Banco de Preguntas Saber PRO

Proyecto académico de **dominio Java puro** (Java 17). No utiliza Spring, JPA ni anotaciones en `src/main`.

## Ejecutar pruebas

```bash
mvn clean test
```

## Diseño estratégico

- **Autoría**: pregunta, enunciado, distractores, clave y justificación.
- **Validación**: reglas estructurales y pedagógicas; el agregado valida invariantes antes del envío.
- **Ciclo de vida**: BORRADOR → PENDIENTE_REVISION.
- **Revisión por pares**: revisor, asignación, evaluación y dictamen; contexto identificado, no implementado en este ejercicio de núcleo.

## Diseño táctico

`Pregunta` es raíz del agregado; `Enunciado`, `Justificacion` y `OpcionRespuesta` son objetos de valor inmutables. `PreguntaRepository` es un puerto sin implementación técnica. Las violaciones se expresan con `ReglaNegocioException`.

**Reglas:** justificación >= 15 caracteres después de trim; enunciado y opciones no vacíos; máximo 4 opciones durante la edición; exactamente 4 y exactamente 1 correcta para enviar; después del envío no se modifica el agregado.
