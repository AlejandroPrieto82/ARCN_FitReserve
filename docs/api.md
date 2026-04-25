# API

## Comportamiento base
- Los endpoints REST exponen payloads JSON.
- Las respuestas usan un wrapper comun via `ApiResponse`.
- Los IDs son UUID en formato string.

## Salud
### GET `/health`
Retorna informacion de disponibilidad del servicio.

## Usuarios
### POST `/users`
Crear usuario.

### POST `/users/login`
Login con email y password.

### DELETE `/users/{id}`
Desactivar usuario.

## Clases de Gimnasio
### GET `/classes?userId={uuid}`
Lista clases disponibles para un usuario (excluye clases con reserva activa de ese usuario).

### POST `/classes`
Crear clase.

Ejemplo de payload:

```json
{
  "name": "Yoga matutino",
  "type": "YOGA",
  "startTime": "2026-04-20T09:00:00",
  "endTime": "2026-04-20T10:00:00",
  "capacity": 20
}
```

### DELETE `/classes/{id}`
Eliminar clase.

## Reservas
### POST `/reservations`
Crear reserva con `userId` y `classId`.

### DELETE `/reservations/{id}`
Cancelar reserva.

### GET `/reservations/user/{userId}`
Obtener historial de reservas de un usuario.

## Notas operativas de error
- Valores invalidos en `type` (ejemplo: `SPINNING`) generan error porque no pertenecen al enum `ClassType`.
- UUIDs invalidos son rechazados en la conversion de value objects de ID.
