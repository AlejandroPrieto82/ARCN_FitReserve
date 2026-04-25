---
layout: home

title: Wiki Backend FitReserve

tagline: Documentación del backend de reservas de gimnasio
actions:
  - theme: brand
    text: Explorar Arquitectura
    link: /architecture
  - theme: alt
    text: Referencia API
    link: /api

hero:
  name: FitReserve Backend
  text: Monolito Modular con Clean Architecture
  tagline: Backend orientado a producción para gestionar clases y reservas, construido con Java y Spring Boot.

features:
  - title: Arquitectura Primero
    details: Monolito modular estilo Onion con separación explícita entre dominio, aplicación, infraestructura e interfaces.
  - title: Modelo Centrado en Negocio
    details: Entidades y value objects inspirados en DDD, con reglas de reserva aplicadas en casos de uso.
  - title: Testing por Caso de Uso
    details: Pruebas unitarias con patrón AAA usando JUnit + Mockito y cobertura con JaCoCo.
  - title: Despliegue Listo para Nube
    details: Backend dockerizado con endpoint de salud, configuración por variables de entorno y CI para calidad.
---

## Alcance del Proyecto

Esta documentación cubre el backend actualmente implementado en este repositorio.

## Introducción Arquitectónica

FitReserve está implementado como un **monolito modular**.

El estilo arquitectónico es **Clean Architecture** con estructura **Onion**:

- El dominio y los casos de uso están al centro.
- Infraestructura y web quedan en capas externas.
- Las dependencias apuntan hacia adentro, protegiendo la lógica de negocio.

Modulos incluidos:

- Usuarios (flujo simplificado: crear, login, desactivar)
- Clases de gimnasio (crear, listar, eliminar)
- Reservas (crear, cancelar, historial por usuario)
- Endpoint operativo de salud (`/health`)

## Demo

Aquí puedes ver una demostración interactiva del proyecto en funcionamiento y su flujo completo:

https://youtu.be/Yz_aBCBNhgM

## Navegación Rápida

- [Sobre Nosotros](./about-us)
- [Arquitectura](./architecture)
- [Modelo de Dominio](./domain-model)
- [Casos de Uso](./use-cases)
- [API](./api)
- [Estrategia de Testing](./testing)
- [Uso de IA](./use-of-ai)
- [Despliegue](./deployment)
- [Decisiones de Diseño](./design-decisions)
