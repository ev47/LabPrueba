package Ahorcado;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    private ArrayList<String> palabras;

    public JuegoAhorcadoAzar() {
        palabras = new ArrayList<>();
    }

    public void setPalabrasSecretas(ArrayList<String> palabrasSecretas) {
        this.palabras = palabrasSecretas;
    }

    public JuegoAhorcadoAzar(ArrayList<String> palabrasSecretas) {
        this.palabras = palabrasSecretas;
        seleccionarPalabraAzar();
        inicializarPalabraSecreta();
        intentos = 10;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    private void seleccionarPalabraAzar() {
        if (!palabras.isEmpty()) {
            Random random = new Random();
            int indice = random.nextInt(palabras.size());
            palabraSecreta = palabras.get(indice).toUpperCase();
        } else {
            palabraSecreta = generarPalabraAleatoria(5); // Longitud predeterminada si no hay palabras ingresadas por el usuario
        }
    }

    private String generarPalabraAleatoria(int longitud) {
        Random random = new Random();
        StringBuilder palabraAleatoria = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            char letraAleatoria = (char) (random.nextInt(26) + 'a');
            palabraAleatoria.append(letraAleatoria);
        }
        return palabraAleatoria.toString().toUpperCase();
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        boolean letraAdivinada = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                StringBuilder sb = new StringBuilder(palabraActual);
                sb.setCharAt(i, letra);
                palabraActual = sb.toString();
                letraAdivinada = true;
            }
        }
        if (!letraAdivinada) {
            intentos--;
        }
    }

    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) != -1;
    }

    @Override
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void inicializarPalabraSecreta() {
        palabraActual = "_".repeat(palabraSecreta.length());
    }

    @Override
    public void jugar() {
        while (intentos > 0 && !hasGanado()) {
            String hidden = getPalabraSeparada();
            String prueba;
            do {
                prueba = JOptionPane.showInputDialog(null, "Ingrese una letra: \n" + hidden + "\nIntentos restantes: " + intentos, "Ahorcado Azar", JOptionPane.INFORMATION_MESSAGE);
            } while (prueba == null || prueba.isEmpty() || !Character.isLetter(prueba.charAt(0))); // Validación para asegurar que se ingrese solo una letra

            char letra = Character.toUpperCase(prueba.charAt(0)); // Convertir la letra a mayúscula

            if (verificarLetra(letra)) {
                actualizarPalabraActual(letra);
            } else {
                intentos--;
            }
        }
        if (hasGanado()) {
            JOptionPane.showMessageDialog(null, "¡Ganaste!");
        } else {
            JOptionPane.showMessageDialog(null, "¡Perdiste!");
        }
    }

    private String getPalabraSeparada() {
        StringBuilder palabraSeparada = new StringBuilder();
        for (int i = 0; i < palabraActual.length(); i++) {
            palabraSeparada.append(palabraActual.charAt(i));
            if (i < palabraActual.length() - 1) {
                palabraSeparada.append(" ");
            }
        }
        return palabraSeparada.toString();
    }
}
