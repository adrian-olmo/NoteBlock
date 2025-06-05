package editor;

import lang.Messages;

import javax.swing.*;
import java.io.*;

public class EditorFileManager {

    /** GUARDAR EL CONTENIDO DEL ÁREA DE TEXTO EN UN ARCHIVO
    * @param text Contiene el texto a guardar.
    * @apiNote
    * - Crear y mostrar el cuadro de diálogo para seleccionar la ubicación y el nombre del archivo.
    * - Verificar si el usuario seleccionó un archivo para guardar.
    * - Obtener el archivo seleccionado por el usuario.
    * - Intentar escribir el contenido del área de texto en el archivo seleccionado.
    * - Manejar cualquier excepción de E/S mostrando la traza de la pila.
    */
    public void saveFile(JTextArea text) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(Messages.SAVE_FILE.getText());

        int result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return; // El usuario canceló la operación
        }

        File file = fileChooser.getSelectedFile();

        // Agregar la extensión .txt si no está presente
        if (!file.getName().endsWith(".txt")) {
            file = new File(file.getAbsolutePath() + ".txt");
        }

        // Preguntar si desea sobrescribir el archivo si ya existe
        if (file.exists()) {
            int overwrite = JOptionPane.showConfirmDialog(null,
                "El archivo ya existe. ¿Desea sobrescribirlo?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
            if (overwrite != JOptionPane.YES_OPTION) {
                return; // El usuario no desea sobrescribir el archivo
            }
        }

        try (BufferedWriter output = new BufferedWriter(new FileWriter(file))) {
            output.write(text.getText());
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
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader input = new BufferedReader(new FileReader(file))) {
                // Leer el contenido del archivo línea por línea
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = input.readLine()) != null) {
                    content.append(line).append("\n"); // Agregar cada línea al contenido
                }
                // Establecer el contenido del JTextArea con el contenido del archivo
                textArea.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}