# Casos de Uso

Los casos de uso representan la capa de aplicación y coordinan la ejecución de la lógica de negocio definida en el dominio, orquestando entidades y repositorios sin contener lógica de negocio compleja.

## Casos de uso de reservas
### CreateReservationUseCase
Orquesta la creación de reservas aplicando las siguientes validaciones de negocio:
- Se valida que el usuario esté activo (precondición externa al dominio de reservas).
- La clase existe y tiene cupo disponible.
- No existe reserva activa previa para el mismo par usuario/clase.

En caso exitoso:
- Crea la reserva.
- Incrementa la cantidad de cupos reservados en la clase.
- Persiste tanto reserva como clase.

### CancelReservationUseCase
- Busca la reserva por ID.
- Invoca el comportamiento de dominio para cancelar la reserva.
- Carga la clase asociada y decrementa el contador de reservados.
- Persiste reserva y clase.

### GetUserReservationsUseCase
Retorna el historial de reservas de un usuario.

## Casos de uso de clases
- `CreateGymClassUseCase`
- `GetAvailableGymClassesForUserUseCase`
- `DeleteGymClassUseCase`
- `UpdateGymClassUseCase`

## Casos de uso de usuarios (módulo simplificado)
- `CreateUserUseCase`
- `LoginUseCase`
- `DeactivateUserUseCase`

El módulo de usuarios es funcional para soportar los flujos principales del sistema, pero se mantiene deliberadamente simplificado y desacoplado, sin integración con proveedores externos de identidad.

Los casos de uso actúan como punto de entrada a la lógica del sistema, garantizando que todas las reglas de negocio se ejecuten de manera consistente antes de interactuar con la infraestructura.
