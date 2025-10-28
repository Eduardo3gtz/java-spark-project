package com.example.collectiblestore;

// Importa las clases estáticas de Spark para definir rutas
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        // Configuramos el puerto en el que escuchará nuestra aplicación Spark
        // Por defecto, Spark usa el puerto 4567. Si quieres otro, lo pones aquí.
        // En este caso, usaremos el predeterminado, pero es bueno saber que se puede configurar.
        // port(8080); // Si quisieras que corra en el 8080

        // Configuramos la carpeta de recursos estáticos (HTML, CSS, JS)
        // Aunque no los usaremos en este Sprint, es una buena práctica configurarlo desde ya.
        staticFiles.location("/public");

        // --- Definición de las rutas del Sprint 1 para la gestión de usuarios ---

        // GET /users - Recuperar la lista de todos los usuarios
        get("/users", (req, res) -> {
            res.type("application/json"); // Establecemos el tipo de contenido de la respuesta a JSON
            // Por ahora, devolvemos una lista de usuarios de ejemplo.
            // Más adelante, aquí iría la lógica para obtener usuarios de una base de datos o servicio.
            return "[{\"id\": 1, \"name\": \"Alice\"}, {\"id\": 2, \"name\": \"Bob\"}]";
        });

        // GET /users/:id - Recuperar un usuario por el ID dado
        get("/users/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params(":id"); // Obtenemos el ID de la URL
            // Lógica de ejemplo: Si el ID es 1, devolvemos a Alice. Si es 2, a Bob.
            // En un caso real, buscaríamos en la base de datos.
            if ("1".equals(id)) {
                return "{\"id\": 1, \"name\": \"Alice\"}";
            } else if ("2".equals(id)) {
                return "{\"id\": 2, \"name\": \"Bob\"}";
            } else {
                res.status(404); // Si no se encuentra, establecemos el status HTTP 404 (Not Found)
                return "{\"message\": \"User not found\"}";
            }
        });

        // POST /users - Añadir un usuario
        post("/users", (req, res) -> {
            res.type("application/json");
            // En un POST, esperamos datos en el cuerpo de la petición.
            // req.body() contendrá el JSON enviado.
            String requestBody = req.body();
            // Aquí iría la lógica para guardar el nuevo usuario.
            // Por ahora, solo confirmamos que recibimos algo y devolvemos un ID ficticio.
            System.out.println("Received POST request with body: " + requestBody);
            res.status(201); // 201 Created es el status apropiado para una creación exitosa
            return "{\"message\": \"User added successfully\", \"id\": 3, \"receivedData\": " + requestBody + "}";
        });

        // PUT /users/:id - Editar un usuario específico
        put("/users/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params(":id");
            String requestBody = req.body();
            // Lógica para actualizar el usuario con el ID dado.
            System.out.println("Received PUT request for ID: " + id + " with body: " + requestBody);
            // Si el usuario existe y se actualiza:
            res.status(200); // 200 OK es el status para una actualización exitosa
            return "{\"message\": \"User " + id + " updated successfully\", \"updatedData\": " + requestBody + "}";
            // Si el usuario no existe, se podría devolver un 404 o 200 con un mensaje de no encontrado, dependiendo de la política de la API.
        });

        // DELETE /users/:id - Eliminar un usuario específico
        delete("/users/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params(":id");
            // Lógica para eliminar el usuario con el ID dado.
            System.out.println("Received DELETE request for ID: " + id);
            // Si el usuario existe y se elimina:
            res.status(204); // 204 No Content es el status para una eliminación exitosa sin devolver contenido
            return ""; // No Content significa que no hay cuerpo de respuesta
            // Si el usuario no existe, se podría devolver un 404 Not Found.
        });

        // OPTIONS /users/:id - Verificar si un usuario con el ID dado existe (o qué métodos están disponibles)
        options("/users/:id", (req, res) -> {
            // Este método se usa a menudo para solicitudes "preflight" de CORS o para consultar capacidades.
            // Para este reto, podemos simplemente confirmar la existencia o devolver métodos permitidos.
            res.header("Allow", "GET, POST, PUT, DELETE, OPTIONS"); // Indicar los métodos permitidos
            String id = req.params(":id");
            if ("1".equals(id) || "2".equals(id)) {
                res.status(200); // OK, el recurso existe y/o se pueden realizar operaciones.
                return ""; // No Content suele ser adecuado para OPTIONS
            } else {
                res.status(404); // Not Found si el ID no corresponde a un usuario conocido.
                return "";
            }
        });

        // Ruta de bienvenida o base (opcional)
        get("/", (req, res) -> {
            return "Welcome to the Collectible Store API!";
        });

        // Mensaje de inicio en la consola
        System.out.println("Spark API is running. Access it at http://localhost:4567");
        System.out.println("Test endpoints:");
        System.out.println("  GET /users");
        System.out.println("  GET /users/1");
        System.out.println("  POST /users (with JSON body like {\"name\": \"Carlos\"})");
        System.out.println("  PUT /users/1 (with JSON body like {\"name\": \"Alicia Updated\"})");
        System.out.println("  DELETE /users/1");
        System.out.println("  OPTIONS /users/1");
    }
}