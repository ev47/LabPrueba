/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ahorcado;


import javax.swing.JOptionPane;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {
    
    public JuegoAhorcadoFijo(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta.toUpperCase();
        inicializarPalabraSecreta();
        intentos = 10;
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
    public void inicializarPalabraSecreta() {

        palabraActual = "_".repeat(palabraSecreta.length());
    }

    @Override
    public boolean hasGanado() {

        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void jugar() {

        while (intentos > 0) {
            String escondido=getPalabraSeparada();
            String prueba;

            do {
                prueba = JOptionPane.showInputDialog(null, "Ingrese una letra: \n" + escondido + "\nIntentos restantes: " + intentos, "Ahorcado Fijo", JOptionPane.INFORMATION_MESSAGE);
            } while (prueba == null);

            prueba = prueba.toUpperCase();

            if (!prueba.isEmpty()) {
                char letra = prueba.charAt(0);
                actualizarPalabraActual(letra);

                if (hasGanado() && intentos > 0) {
                    JOptionPane.showMessageDialog(null, "¡Ganaste!");
                    intentos = 0; // Salir del bucle
                } else if (intentos == 0) {
                    JOptionPane.showMessageDialog(null, "¡Perdiste!");
                }
            }
        }
    }

    private String getPalabraSeparada() {

        return String.join(" ", palabraActual.split(""));
    }
}

