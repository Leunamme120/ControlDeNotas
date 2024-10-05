import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainControl {
    private JFrame frame;
    private JTextArea textArea;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<String> cursos;

  
    public MainControl() {
        // Inicialización de estudiantes y cursos
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();

        
        frame = new JFrame("Control de Notas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);
        

        
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(25, 180, 25));

        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setForeground(new Color(21, 67, 96));
        JScrollPane scrollPane = new JScrollPane(textArea);

        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBackground(new Color(60, 179, 113));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JButton btnCrearCurso = new JButton("Crear Curso");
        btnCrearCurso.setBackground(new Color(47, 79, 79));
        btnCrearCurso.setForeground(Color.WHITE);
        
        JButton btnAgregarEstudiante = new JButton("Agregar Estudiante");
        btnAgregarEstudiante.setBackground(new Color(47, 79, 79));
        btnAgregarEstudiante.setForeground(Color.WHITE);
        
        JButton btnIngresarNotas = new JButton("Ingresar Notas");
        btnIngresarNotas.setBackground(new Color(47, 79, 79));
        btnIngresarNotas.setForeground(Color.WHITE);
        
        JButton btnMostrarNotas = new JButton("Mostrar Notas");
        btnMostrarNotas.setBackground(new Color(47, 79, 79));
        btnMostrarNotas.setForeground(Color.WHITE);
        
        JButton btnMostrarInfo = new JButton("Mostrar Información");
        btnMostrarInfo.setBackground(new Color(47, 79, 79));
        btnMostrarInfo.setForeground(Color.WHITE);
        
       
        buttonPanel.add(btnCrearCurso);
        buttonPanel.add(btnAgregarEstudiante);
        buttonPanel.add(btnIngresarNotas);
        buttonPanel.add(btnMostrarNotas);
        buttonPanel.add(btnMostrarInfo);

        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        
        btnCrearCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearCurso();
            }
        });

        btnAgregarEstudiante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });

        btnIngresarNotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingresarNotas();
            }
        });

        btnMostrarNotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarNotas();
            }
        });

        btnMostrarInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInformacion();
            }
        });
    }

    // Método crear curso
    private void crearCurso() {
        String curso = JOptionPane.showInputDialog(frame, "Ingrese el nombre del curso:");
        if (curso != null && !curso.trim().isEmpty()) {
            cursos.add(curso);
            textArea.append("Curso agregado: " + curso + "\n");
        } else {
            JOptionPane.showMessageDialog(frame, "El nombre del curso no puede estar vacío.");
        }
    }

    private void agregarEstudiante() {
        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Primero debe crear un curso antes de agregar estudiantes.");
            return;
        }

        String nombre = JOptionPane.showInputDialog(frame, "Nombre del Estudiante:");
        String apellido = JOptionPane.showInputDialog(frame, "Apellido del Estudiante:");
        String carne = JOptionPane.showInputDialog(frame, "Carné del Estudiante:");

        // Asignar automáticamente todos los cursos creados
        Estudiante estudiante = new Estudiante(nombre, apellido, carne, cursos);
        estudiantes.add(estudiante);

        textArea.append("Estudiante agregado: " + estudiante.toString() + "\n");
    }

    // Método ingresar notas
    private void ingresarNotas() {
        if (estudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay estudiantes registrados.");
            return;
        }

        String carne = JOptionPane.showInputDialog(frame, "Ingrese el carné del estudiante:");
        Estudiante estudiante = buscarEstudiantePorCarne(carne);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(frame, "No se encontró el estudiante con carné: " + carne);
            return;
        }

        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay cursos disponibles. Cree un curso primero.");
            return;
        }

        String curso = (String) JOptionPane.showInputDialog(frame, "Seleccione un curso:", "Curso",
                JOptionPane.QUESTION_MESSAGE, null, cursos.toArray(), cursos.get(0));

        for (int i = 1; i <= 4; i++) {  // Ingresar notas para 4 bimestres
            String notaStr = JOptionPane.showInputDialog(frame, "Ingrese la nota del Bimestre " + i + " para el curso " + curso + ":");
            double nota = Double.parseDouble(notaStr);
            estudiante.agregarNota(curso, nota);
        }

        textArea.append("Notas ingresadas para el estudiante: " + estudiante.toString() + "\n");
    }

    // Método buscar un estudiante - carné
    private Estudiante buscarEstudiantePorCarne(String carne) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCarne().equals(carne)) {
                return estudiante;
            }
        }
        return null;
    }

    private void mostrarNotas() {
        String carne = JOptionPane.showInputDialog(frame, "Ingrese el carné del estudiante:");
        textArea.setText("");

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCarne().equals(carne)) {
                textArea.append(estudiante.toString() + "\n");
                for (String curso : cursos) {
                    double promedio = estudiante.calcularPromedio(curso);
                    textArea.append("Promedio en " + curso + ": " + promedio + "\n");
                    if (promedio >= 60) {
                        textArea.append("El estudiante ha ganado el curso " + curso + ".\n");
                    } else {
                        textArea.append("El estudiante no ha ganado el curso " + curso + ".\n");
                    }
                }
                return;
            }
        }
        textArea.append("No se encontró el estudiante con carné: " + carne + ".\n");
    }

    private void mostrarInformacion() {
        textArea.setText("");

        if (estudiantes.isEmpty()) {
            textArea.append("No hay estudiantes registrados.\n");
            return;
        }

        for (Estudiante estudiante : estudiantes) {
            textArea.append("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n");
            textArea.append("Carné: " + estudiante.getCarne() + "\n");
            textArea.append("Cursos: " + estudiante.getCursos() + "\n");

            for (String curso : cursos) {
                double promedio = estudiante.calcularPromedio(curso);
                textArea.append("Promedio en " + curso + ": " + promedio + "\n");
                if (promedio >= 60) {
                    textArea.append("El estudiante ha ganado el curso " + curso + ".\n");
                } else {
                    textArea.append("El estudiante no ha ganado el curso " + curso + ".\n");
                }
            }
            textArea.append("\n");
        }
    }

    public static void main(String[] args) {
        new MainControl();
    }
}