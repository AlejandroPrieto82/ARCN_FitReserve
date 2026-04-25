# Decisiones de Diseño

## Monolito modular como decisión principal
Se eligió un monolito modular porque el dominio actual es cohesivo y el tamaño del sistema no justifica la complejidad operativa de microservicios.

Esta decisión permite avanzar más rápido, mantener menor costo de operación y simplificar pruebas, despliegue y observabilidad en etapas tempranas del producto.

## Ventajas del enfoque adoptado
- Menor complejidad de infraestructura y despliegue.
- Flujo de desarrollo más rápido para equipos pequeños.
- Transacciones y consistencia de negocio más simples de gestionar.
- Depuración más directa al ejecutarse en un solo servicio.

## Desventajas y trade-offs
- Escalado menos granular que en microservicios.
- Mayor riesgo de acoplamiento si no se respetan límites internos.
- El crecimiento del código puede afectar mantenibilidad si no se controla la modularidad.

## Por qué Clean Architecture y DDD
Sobre el monolito se aplicó Clean Architecture con enfoque DDD para mantener separadas las responsabilidades y proteger el dominio de detalles técnicos.

## Por qué arquitectura Onion y no otra
Se eligió arquitectura tipo Onion (alineada con Clean Architecture) porque prioriza que el dominio sea el centro del sistema y que las dependencias apunten hacia adentro.

Esto fue preferible frente a otras opciones para este proyecto:
- Frente a arquitectura en capas tradicional: Onion reduce el riesgo de que la lógica de negocio termine mezclada en controladores o repositorios.
- Frente a microservicios: Onion en un monolito modular mantiene bajo costo operativo y menor complejidad de despliegue, sin perder buenas fronteras internas.

## Ventajas de Onion en este proyecto
- El dominio queda aislado de frameworks y detalles de infraestructura.
- Alta testabilidad de reglas de negocio y casos de uso.
- Menor acoplamiento entre capas por inversión de dependencias.
- Facilita refactorización y evolución sin romper reglas del negocio.

## Desventajas de Onion en este proyecto
- Mayor curva de aprendizaje inicial para el equipo.
- Más clases e interfaces que en enfoques rápidos de tipo CRUD.
- Requiere disciplina constante para no romper fronteras entre capas.
- Puede sentirse sobredimensionada en funcionalidades triviales.

En la práctica:
- El dominio concentra reglas de negocio e invariantes.
- La capa de aplicación orquesta casos de uso.
- La infraestructura implementa persistencia e integraciones.
- La capa de interfaces expone API REST sin lógica de negocio.

Este diseño reduce acoplamiento, mejora testabilidad y deja una base preparada para evolucionar a una arquitectura distribuida si el contexto de negocio lo exige.
