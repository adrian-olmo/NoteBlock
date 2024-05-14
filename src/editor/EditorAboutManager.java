package editor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class EditorAboutManager {

    public static void openGithubPage(String url){
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e){
                e.printStackTrace();
            }
        } else {
            JOptionPane.showConfirmDialog(null, "No se puede abrir el navegador");
        }

    }
}
