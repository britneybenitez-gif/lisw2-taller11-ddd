# Taller 11 — Domain-Driven Design (DDD)
## Banco de Preguntas Saber PRO

**Universidad del Cauca**  
**Facultad de Ingeniería Electrónica y Telecomunicaciones**  
**Programa:** Ingeniería de Sistemas  
**Asignatura:** Laboratorio de Ingeniería de Software II  
**Docente:** Paola Andrea Bedoya Toro  
**Grupo:** 4A

**Integrantes:**
- Britney Benitez Ordoñez
- Yineidy Mamian

## 1. Descripción del proyecto

Implementación del modelo de dominio del Banco de Preguntas Saber PRO mediante los principios de Domain-Driven Design (DDD), utilizando Java 17.

El proyecto busca encapsular las reglas pedagógicas y estructurales de una pregunta de selección múltiple en componentes de dominio independientes de frameworks y tecnologías de persistencia.

## 2. Objetivos

- Identificar los Bounded Contexts del sistema.
- Implementar el agregado `Pregunta` como raíz del agregado.
- Crear objetos de valor inmutables.
- Garantizar las invariantes del negocio.
- Implementar pruebas unitarias con JUnit 5.
- Mantener el dominio independiente de Spring y JPA.

## 3. Tecnologías utilizadas

| Tecnología | Propósito |
|---|---|
| Java 17 | Implementación del dominio |
| Maven | Gestión del proyecto |
| JUnit 5 | Pruebas unitarias |
| Git y GitHub | Control de versiones |

## 4. Diseño estratégico

Se identificaron cuatro contextos delimitados:

| Bounded Context | Responsabilidad |
|---|---|
| Autoría | Creación y edición de preguntas |
| Validación | Verificación de reglas estructurales y pedagógicas |
| Ciclo de vida | Control de estados y transiciones |
| Revisión por pares | Evaluaciones y dictámenes de revisores |

La implementación de este taller se concentra en el núcleo del agregado `Pregunta`. La revisión por pares se identifica conceptualmente, pero no se implementa como módulo independiente.

## 5. Diseño táctico

### Raíz del agregado

`Pregunta` administra el enunciado, la justificación, las opciones de respuesta y el estado.

### Objetos de valor

- `Enunciado`: texto obligatorio e inmutable.
- `Justificacion`: texto inmutable con un mínimo de 15 caracteres.
- `OpcionRespuesta`: texto e indicador de respuesta correcta.

### Excepciones del dominio

- `DomainException`
- `ReglaNegocioException`

### Repositorio

`PreguntaRepository` define las operaciones `buscarPorId` y `guardar` mediante una interfaz independiente de la infraestructura.

## 6. Reglas de negocio

1. El identificador de una pregunta debe ser positivo.
2. El enunciado no puede estar vacío.
3. La justificación debe tener al menos 15 caracteres después de eliminar espacios exteriores.
4. Las opciones no pueden tener texto vacío.
5. Una pregunta puede contener como máximo cuatro opciones.
6. Para enviarse a revisión, debe tener exactamente cuatro opciones.
7. Debe existir exactamente una respuesta correcta.
8. Las modificaciones solo están permitidas en estado `BORRADOR`.
9. Una pregunta enviada no puede enviarse nuevamente.
10. El incumplimiento de las reglas de negocio produce una excepción.

## 7. Ciclo de vida

`BORRADOR → PENDIENTE_REVISION`

La transición se realiza mediante `enviarARevision()`, que valida previamente las invariantes.

## 8. Estructura del proyecto

```text
src/
├── main/java/co/edu/unicauca/saberpro/domain/
│   ├── exception/
│   │   ├── DomainException.java
│   │   └── ReglaNegocioException.java
│   ├── model/
│   │   ├── Enunciado.java
│   │   ├── EstadoPregunta.java
│   │   ├── Justificacion.java
│   │   ├── OpcionRespuesta.java
│   │   └── Pregunta.java
│   └── repository/
│       └── PreguntaRepository.java
└── test/java/co/edu/unicauca/saberpro/domain/
    └── model/
        └── PreguntaTest.java
```

## 9. Pruebas unitarias

El proyecto contiene 16 pruebas JUnit 5 que cubren:

- Validaciones de enunciados, justificaciones y opciones.
- Límites de cantidad de opciones.
- Ausencia o multiplicidad de respuestas correctas.
- Transición válida a revisión.
- Restricciones de modificación y reenvío.
- Inmutabilidad de colecciones expuestas.
- Identificadores inválidos.
- Igualdad por valor.

### Ejecutar las pruebas

Desde la raíz del proyecto:

```bash
mvn clean test
```

Maven compila el proyecto y ejecuta las pruebas unitarias.

El resultado esperado, si todas las pruebas son exitosas, es `BUILD SUCCESS`.

## 10. Independencia del dominio

Las clases de producción están implementadas con Java puro, sin anotaciones de Spring o JPA.

JUnit 5 se utiliza exclusivamente en el código de pruebas.

## 11. Conclusiones

La implementación demuestra cómo Domain-Driven Design permite concentrar las reglas de negocio dentro de un modelo coherente, utilizando agregados, objetos de valor y excepciones propias del dominio.

El agregado `Pregunta` protege sus invariantes y controla la transición al estado de revisión. La separación entre dominio e infraestructura facilita la evolución y las pruebas del sistema.

## 12. Repositorio

https://github.com/britneybenitez-gif/lisw2-taller11-ddd

**Universidad del Cauca — 2026**
