# Estrategia de Testing

## Alcance
Las pruebas se enfocan en casos de uso de aplicación, que son las unidades principales de orquestación de negocio.

## Enfoque
El proyecto sigue estructura AAA en pruebas unitarias:
- Arrange: preparar mocks y datos de entrada.
- Act: ejecutar el caso de uso.
- Assert: validar resultado e interacciones.

## Herramientas
- JUnit 5
- Mockito
- Maven Surefire

## Cobertura
JaCoCo está configurado en Maven con:
- `prepare-agent`
- `report` en la fase `verify`

La salida de cobertura se genera en CI y puede ser consumida por SonarCloud.
