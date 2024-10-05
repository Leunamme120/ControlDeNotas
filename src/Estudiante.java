
import java.util.ArrayList;

class Estudiante {
    private String nombre;
    private String apellido;
    private String carne;
    private ArrayList<String> cursos;
    private ArrayList<Double> notas;

    public Estudiante(String nombre, String apellido, String carne, ArrayList<String> cursos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carne = carne;
        this.cursos = new ArrayList<>(cursos);  // Asignar todos los cursos automáticamente
        this.notas = new ArrayList<>();
    }

    public void agregarNota(String curso, double nota) {
        notas.add(nota);
    }

    public double calcularPromedio(String curso) {
        double suma = 0;
        int count = 0;
        for (double nota : notas) {
            suma += nota;
            count++;
        }
        return count == 0 ? 0 : suma / count;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCarne() {
        return carne;
    }

    public ArrayList<String> getCursos() {
        return cursos;
    }

    @Override
    public String toString() {
        return "Estudiante: " + nombre + " " + apellido + ", Carné: " + carne + ", Cursos: " + cursos;
    }
}
