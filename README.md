# Microservicios de Ejemplo
- Orquesta 4 microservicios Spring Boot y cada uno gestiona una entidad
- Bases de datos en memoria H2
- docker-compose con todo preparado
- nginx.conf para la gestión de los puertos dentro de los contenedores


## 1 User 
Micro servicio CRUD de User

### Ejemplos
Tiene arquitectura Hexagonal

### Tecnologías
Lombox
MapStrut
Lombox y MapStrut juntos (conf pom.xml)

### Mejoras
Mejorar el uso de DTO que viene por controller y "entra" a la aplicación

## 2 Product
Crud de product, normalito

## 3 Shopping-Cart
Crud de Shoppong-Cart

### Ejemplos
- Realiza llamadas a otros servicios (User)
- Produce eventos kafka

### Tecnologías
- Kafka

## 4 Orders
Consume eventos de kafka,
no tiene ni controller.

### Ejemplos
kafka consumer

### Tecnologías
Kafka
