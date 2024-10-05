import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/Control_notas"; // Cambia "localhost" si es necesario
    private static final String USER = "root";  // Cambia esto por tu usuario de MySQL
    private static final String PASSWORD = ""; // Cambia esto por tu contraseña de MySQL

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            // Mostrar los estudiantes
            System.out.println("Estudiantes:");
            resultSet = statement.executeQuery("SELECT * FROM Estudiantes");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String carne = resultSet.getString("carne");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Carne: " + carne);
            }

            // Mostrar los cursos
            System.out.println("\nCursos:");
            resultSet = statement.executeQuery("SELECT * FROM Cursos");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreCurso = resultSet.getString("nombre_curso");
                System.out.println("ID: " + id + ", Nombre del Curso: " + nombreCurso);
            }

            // Mostrar las notas
            System.out.println("\nNotas:");
            resultSet = statement.executeQuery("SELECT n.*, e.nombre, e.apellido, c.nombre_curso FROM Notas n " +
                                               "JOIN Estudiantes e ON n.estudiante_id = e.id " +
                                               "JOIN Cursos c ON n.curso_id = c.id");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int estudianteId = resultSet.getInt("estudiante_id");
                int cursoId = resultSet.getInt("curso_id");
                int bimestreId = resultSet.getInt("bimestre_id");
                double nota = resultSet.getDouble("nota");
                String nombreEstudiante = resultSet.getString("nombre");
                String apellidoEstudiante = resultSet.getString("apellido");
                String nombreCurso = resultSet.getString("nombre_curso");
                System.out.println("ID: " + id + ", Estudiante: " + nombreEstudiante + " " + apellidoEstudiante + 
                                   ", Curso: " + nombreCurso + ", Bimestre: " + bimestreId + ", Nota: " + nota);
            }

            // Mostrar los bimestres
            System.out.println("\nBimestres:");
            resultSet = statement.executeQuery("SELECT * FROM Bimestres");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreBimestre = resultSet.getString("nombre_bimestre");
                System.out.println("ID: " + id + ", Nombre del Bimestre: " + nombreBimestre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
