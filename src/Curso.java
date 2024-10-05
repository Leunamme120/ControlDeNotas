public class Curso {
    private int id;
    private String nombreCurso;
    private String descripcion;
    
    public Curso(int id, String nombreCurso, String descripcion) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombreCurso() { return nombreCurso; }
    public String getDescripcion() { return descripcion; }

    public void setId(int id) { this.id = id; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return nombreCurso;
    }
}