# API Items
> Este proyecto es un ejemplo básico de una API Rest (Items) para su utilización desde Angular. Se gestionan las funciones CRUD de Items
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *Back-end con Tecnologías de Código Abierto: Spring*


### Tecnologías necesarias
* Java
* Maven
* Eclipse
* GitHub
* Travis-CI
* Spring

### Importar el proyecto mediante Eclipse
1. Clonar mediante Eclipse este repositorio en la máquina local: https://youtu.be/rQNixJQQ25g
1. Importar el repositorio clonado: https://youtu.be/yYvD8ZJtWwI
1. Crear en Eclipse las ramas locales asociadas a las remotas

### Ejecutar el jar
1. Bajarse la última [Release (*.jar)](https://github.com/miw-upm/FENW-items/releases)
1. En consola, en la ruta del jar, ejercutar: `java -jar FENW-items-1.x.0.jar`

### Swagger
[Cliente Swagger: http://localhost:8080/api/v0/swagger-ui.html](http://localhost:8080/api/v0/swagger-ui.html)
### Documentación
#### Mensajes de error
Ejemplo: `{"exception":"ItemIdNotFoundException", "message":"Id de item no encontrado. 881", "path":"/api/v0/items/881"}`
#### Lee los items. `GET /items`
> Se puede añadir un filtro con el nombre del item. Si `description `fuera null, no la devuelve

Ejemplos: `GET /items` , `GET /items?name=uno`

 Parámetros | Respuesta
--|--
`name`<br>Nombre del item | `OK (200)`<br>[{"id":3,"name":"uno"}{"id":4,"name":"n"}]

#### Lee un item `GET /items/{id}`  
> Si `description `fuera null, no la devuelve  

Ejemplo: `GET /items/2`

Parámetros | Respuesta | Respuesta si<br>_id_ no existe<br>_id_ no entero
--|--|--
-- | `OK (200) {"id":4,"name":"n","description":"d"}` | `NOT_FOUND(404)` `{"exception":"ThemeIdNotFoundException"}`<br> `{"exception":"NumberFormatException"}` 

#### Crea un item `POST /items`  
> Devuelve el `id` del item creado  

Ejemplos: `POST /items` `{"name":"uno"}`, `POST /items` `{"name":"uno","description":"..."}`

Entrada| Respuesta
--|--
`name: string`<br>`description?: string` | `OK (201) {5}` 

#### Actualizad de forma completa un item `PUT /items/{id}`  
Ejemplo: `PUT /items/2` `{"name":"uno"}`, `PUT /items/2` `{"name":"uno","description":"..."}`

Parámetros | Respuesta | Respuesta si<br>_id_ no existe<br>_id_ no entero
--|--|--
-- | `OK (200) {"id":4,"name":"n","description":"d"}` | `NOT_FOUND(404)` `{"exception":"ThemeIdNotFoundException"}`<br> `{"exception":"NumberFormatException"}`

#### Actualizad de forma parcial un item `PATH /items/{id}`  
> Actualiza `description` de un item  

Ejemplo: `PATH /items/2` `{"description":"..."}`

Parámetros | Respuesta | Respuesta si<br>_id_ no existe<br>_id_ no entero
--|--|--
-- | `OK (200) {"id":4,"name":"n","description":"d"}` | `NOT_FOUND(404)` `{"exception":"ThemeIdNotFoundException"}`<br> `{"exception":"NumberFormatException"}`
