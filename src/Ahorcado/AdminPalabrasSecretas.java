package Ahorcado;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
public class AdminPalabrasSecretas {
private List<String> palabraSecreta;
    public AdminPalabrasSecretas() {
        palabraSecreta = new ArrayList<>();
    }
    public void agregarPalabra(String palabra) {
        palabraSecreta.add(palabra);
    }
    public void agregarPalabrasNuevas() {
        for (int contador=0;contador<5;contador++) {
            String palabra = JOptionPane.showInputDialog(null, "Ingrese la palabra secreta #" + (contador+ 1) + ":");
            palabraSecreta.add(palabra);
        }
    }
    public String seleccionarPalabraAleatoria() {
        if (palabraSecreta.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int indice = random.nextInt(palabraSecreta.size());
        return palabraSecreta.get(indice);
    }

}
