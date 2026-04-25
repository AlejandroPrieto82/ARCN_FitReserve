# Uso de IA

La IA se utilizó como copiloto técnico bajo un enfoque dirigido por contexto. En lugar de solicitar cambios genéricos, primero se compartió un documento base de contexto del proyecto (arquitectura, buenas prácticas y reglas de implementación), y luego se hicieron pedidos puntuales por tarea.

Este enfoque permitió mantener consistencia técnica con Clean Architecture y DDD, evitando respuestas ambiguas o implementaciones fuera del diseño del sistema.

## Base de contexto utilizada
La interacción con IA partió del documento [context.md](./context.md), usado como README de contexto del proyecto. Ese documento definió, antes de pedir implementación, un marco técnico explícito con:
- Contexto funcional del negocio (reservas, validación de cupos, conflicto de horarios, estado de usuario).
- Arquitectura objetivo (Clean Architecture por capas con separación estricta de responsabilidades).
- Modelo DDD (entidades, agregado principal, reglas de negocio y eventos de dominio).
- Estrategia de calidad (TDD con patrón AAA, pruebas de invariantes y edge cases).
- Principios de diseño y desarrollo (Clean Code, SOLID, DRY, KISS, YAGNI, prácticas XP).
- Reglas operativas para IA (no mezclar capas, no lógica en controladores, validaciones en dominio/casos de uso, uso de DTOs y repositorios en capa de aplicación).

Esto convirtió cada solicitud en una tarea contextualizada y verificable, en lugar de una generación aislada de código.

## Flujo real utilizado en el proyecto
1. Se cargó el contexto del proyecto desde el README/contexto (arquitectura, convenciones y reglas).
2. Se construyó un prompt maestro con instrucciones explícitas del rol esperado, por ejemplo: actuar como ingeniero senior en backend Java con Clean Architecture.
3. Se definieron tareas acotadas y verificables (por ejemplo: implementar una capa de dominio con invariantes concretas).
4. Se validó cada resultado contra código, comportamiento esperado y pruebas.
5. Se integraron cambios en commits cortos y atómicos por unidad funcional, en lugar de mezclar múltiples temas en un solo commit.

En la práctica, el prompt maestro incluyó instrucciones del tipo: "actúa como profesional en Clean Architecture/DDD, lee primero el contexto del proyecto y luego implementa solo la capa solicitada con reglas concretas y sin romper fronteras de arquitectura".

## Ejemplo de estilo de solicitud
- Contexto: lee el README/contexto del proyecto antes de proponer cambios.
- Rol: actúa como especialista en Clean Architecture y DDD.
- Tarea: implementa un caso de uso o una regla de dominio específica.
- Restricciones: respeta capas, no mover lógica de negocio a infraestructura, y mantener contratos existentes.
- Entrega: cambios mínimos, explicación breve y validación técnica.

## Criterios de calidad aplicados
- La IA no definió la arquitectura: la arquitectura ya estaba definida en el proyecto y en el contexto compartido.
- Toda propuesta se aceptó solo si era coherente con entidades, casos de uso y repositorios ya existentes.
- No se aceptaron cambios sin trazabilidad técnica o sin encaje en las reglas del dominio.
- Se priorizó legibilidad y mantenibilidad sobre soluciones "rápidas" que rompieran el diseño.

## Gobernanza aplicada en este proyecto
- La salida de IA se valida contra código fuente y pruebas.
- Las afirmaciones de arquitectura solo se documentan cuando hay evidencia en código.
- Las guías de despliegue se validan con ejecuciones reales en contenedor.

## Estrategia de commits con apoyo de IA
- Commits pequeños, descriptivos y orientados a una sola capacidad.
- Secuencia por capas cuando aplica: dominio -> aplicación -> infraestructura -> pruebas.
- Revisión de impacto antes de cada commit para evitar mezclar cambios no relacionados.

La IA se usó para acelerar ejecución, no para reemplazar validación de ingeniería.
