public class Nota {
    private Estudiante estudiante;
    private Curso curso;
    private Bimestre bimestre;
    private double nota;

    public Nota(Estudiante estudiante, Curso curso, Bimestre bimestre, double nota) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.bimestre = bimestre;
        this.nota = nota;
    }

    // Getters y Setters
    public Estudiante getEstudiante() { return estudiante; }
    public Curso getCurso() { return curso; }
    public Bimestre getBimestre() { return bimestre; }
    public double getNota() { return nota; }

    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public void setBimestre(Bimestre bimestre) { this.bimestre = bimestre; }
    public void setNota(double nota) { this.nota = nota; }

    @Override
    public String toString() {
        return "Nota: " + nota + " - Curso: " + curso.getNombreCurso();
    }
}