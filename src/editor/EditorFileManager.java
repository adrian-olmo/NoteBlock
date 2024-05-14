package editor;

import java.io.*;
import javax.swing.*;

public class EditorFileManager {

    /** GUARDAR EL CONTENIDO DEL ÁREA DE TEXTO EN UN ARCHIVO
    * @param texto Contiene el texto a guardar.
    * @apiNote
    * - Crear y mostrar el cuadro de diálogo para seleccionar la ubicación y el nombre del archivo.
    * - Verificar si el usuario seleccionó un archivo para guardar.
    * - Obtener el archivo seleccionado por el usuario.
    * - Intentar escribir el contenido del área de texto en el archivo seleccionado.
    * - Manejar cualquier excepción de E/S mostrando la traza de la pila.
    */

    public void saveFile(JTextArea texto) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showSaveDialog(null);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return; // El usuario canceló la operación
        }
        File archivo = fileChooser.getSelectedFile();
        try (BufferedWriter salida = new BufferedWriter(new FileWriter(archivo))) {
            salida.write(texto.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** ABRIR UN ARCHIVO Y ESTABLECER SU CONTENIDO EN UN JTextArea
     * @apiNote
     * - Muestra un cuadro de diálogo para que el usuario seleccione un archivo para abrir.
     * - Lee el contenido del archivo seleccionado línea por línea.
     * - Establece el contenido del JTextArea con el contenido del archivo.
     * @param textArea El JTextArea donde se establecerá el contenido del archivo.
     */
    public void openFile(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
                // Leer el contenido del archivo línea por línea
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = entrada.readLine()) != null) {
                    contenido.append(linea).append("\n"); // Agregar cada línea al contenido
                }
                // Establecer el contenido del JTextArea con el contenido del archivo
                textArea.setText(contenido.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
