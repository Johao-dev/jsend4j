# JSend4j

**JSend4j** es una biblioteca Java ligera, tipada y fácil de usar para implementar la especificación
[JSend](https://github.com/omniti-labs/jsend).

Esta librería permite estandarizar las respuestas JSON de tu API (especialmente útil en Spring Boot o Jakarta EE)
siguiendo estrictamente los formatos `success`, `fail` y `error`, aprovechando la potencia de **Jackson** para la
serialización.

## Características

- **Ligera**: Solo depende de `jackson-databind`.
- **Type-safe**: Uso de Generics `<T>` para garantizar el tipo de datos en la respuesta.
- **Fachada Simple**: API fluida y estática a través de la clase `JSend`.
- **Estándar JSend**:
  - `success`: Todo salió bien
  - `fail`: Hubo un problema con los datos (ej. validación)
  - `error`: Ocurrió un error en el servidor.
- **Java 21**: Aprovecha las características modernas del lenguaje.

## Requisitos

* Java 21 o superior.
* Maven 3.9.6+ (para compilar).

## Instalación

### Opción A: JitPack (Recomendado)

Puede usar esta librería directamente desde GitHub usando JitPack. Añade esto a tu `pom.xml`:

1. Añade el repositorio:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

2. Añade la dependencia:

```xml
<dependency>
    <groupId>com.github.Johao-dev</groupId>
    <artifactId>jsend4j</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Opción B: Instalación Local

Si prefieres instalarlo manualmente en tu repositorio local `.m2`:

```bash
git clone https://github.com/Johao-dev/jsend4j.git
cd jsend4j
mvn clean install
```

Luego agregála a tu proyecto como cualquier dependencia normal:

```xml
<dependency>
    <groupId>zuzz.projects</groupId>
    <artifactId>jsend4j</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Uso

La biblioteca expone una fachada estática `JSend` para crear las respuestas.

### 1. Respuesta Exitosa (Success)

Se usa cuando la llamada a la API se completó con éxito.

```java
import zuzz.projects.jsend4j.JSend;
import zuzz.projects.jsend4j.JSendResponse;

// Con datos
Map<String, String> user = Map.of("id", "1", "name", "zuzz");
JSendResponse<Map<String, String>> response = JSend.success(user);

// Sin datos (null)
JSendResponse<Object> emptyResponse = JSend.success();
```

Salida JSON:

```json
{
    "status": "success",
    "data": {
        "id": "1",
        "name": "Johao"
    }
}
```

### 2. Respuesta Fallida (Fail)

Se usa cuando hay un problema con los datos enviados por el cliente (ej. error de validación)

```java
Map<String, String> errors = Map.of("email", "Formato inválido");
JSendResponse<Map<String, String>> response = JSend.fail(errors);
```

Salida JSON:

```json
{
    "status": "fail",
    "data": {
        "email": "Formato inválido"
    }
}
```

### 3. Respuesta de Error (Error)

Se usa cuando ocurre un error en el servidor (excepción).

```java
// Solo mensaje
JSendResponse<Object> err1 = JSend.error("Error de conexión a BD");

// Mensaje y código
JSendResponse<Object> err2 = JSend.error("Recurso no encontrado", 404);

// Mensaje, código y datos opcionales
JSendResponse<String> err3 = JSend.error("Error crítico", 500, "Stacktrace details...");
```

Salida JSON:

```json
{
    "status": "error",
    "message": "Recurso no encontrado",
    "code": 404
}
```

## Desarrollo y Contribución

¡Las contribuciones son bienvenidas! Si deseas mejorar la librería:

1. Haz un Fork del repositorio.
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`).
3. Asegúrate de ejecutar las pruebas:
    ```bash
    mvn test
    ```
4. Haz commits de tus cambios.
5. Abre un Pull Request.

### Generar Javadocs y Sources

Para generar los JARs de documentación y código fuente:

```bash
mvn package
```

Los archivos se generarán en la carpeta `/target`.

## Licencia

Este proyecto está bajo la licencia MIT - mira el archivo [LICENSE](LICENSE) para más detalles.