Ejemplo microservicio dockerizado con arquitectura hexagonal.

Cosas que usa:
Mapper
JPARepository

Uso correcto de la entidad User:
- Controlador usa  UserDto (application)
- Servicio usa User  (dominio)
- Repositorio usa UserEntity (infraestructura)