public class Bimestre {
    private int id;
    private int numeroBimestre;
    private int anioAcademico;
    
    public Bimestre(int id, int numeroBimestre, int anioAcademico) {
        this.id = id;
        this.numeroBimestre = numeroBimestre;
        this.anioAcademico = anioAcademico;
    }

    // Getters y Setters
    public int getId() { return id; }
    public int getNumeroBimestre() { return numeroBimestre; }
    public int getAnioAcademico() { return anioAcademico; }

    public void setId(int id) { this.id = id; }
    public void setNumeroBimestre(int numeroBimestre) { this.numeroBimestre = numeroBimestre; }
    public void setAnioAcademico(int anioAcademico) { this.anioAcademico = anioAcademico; }

    @Override
    public String toString() {
        return "Bimestre " + numeroBimestre + " - Año: " + anioAcademico;
    }
}