# Arquitectura

## Monolito Modular con Clean Architecture (estilo Onion) basada en DDD
FitReserve es un servicio Spring Boot desplegable como una sola unidad, organizado como monolito modular.

La arquitectura sigue los principios de Clean Architecture, donde las dependencias apuntan hacia el dominio, permitiendo mantener la lógica de negocio independiente de frameworks, base de datos y detalles técnicos.

El backend sigue una estructura tipo Onion, donde el dominio se encuentra en el núcleo y las demás capas dependen de él:
- **Domain**: contiene entidades de negocio, value objects, excepciones de dominio e interfaces de repositorio.
- **Application**: define los casos de uso que orquestan la lógica de negocio.
- **Infrastructure**: implementa detalles técnicos como persistencia (JPA), mappers y configuración del framework.
- **Interfaces**: expone controladores REST y contratos de entrada/salida (DTOs).

## Por qué importa
Esta estructura mantiene la lógica de negocio independiente de controladores, persistencia y detalles internos de Spring.

Esta separación facilita la evolución del sistema y permite escalar hacia arquitecturas distribuidas en el futuro si es necesario.

Efectos prácticos en este proyecto:
- Los casos de uso se prueban de forma unitaria con repositorios mockeados.
- Los adaptadores de persistencia pueden cambiar sin reescribir reglas de negocio.
- Los controladores se mantienen delgados y principalmente mapean HTTP a casos de uso.

## Mapa actual de módulos
- Flujo de reservas: crear, cancelar, listar por usuario.
- Clases de gimnasio: crear, listar disponibles por usuario, eliminar.
- Usuarios: crear, login y desactivar (módulo simplificado).
- Endpoint de salud: verificación operativa de liveness.
