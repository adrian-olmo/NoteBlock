package editor;

import java.awt.*;
import java.net.URI;
import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class EditorAboutManager {
    public static void openPage(String url){
        if ( Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch ( IOException | URISyntaxException e){
                e.printStackTrace();
            }
        } else {
            JOptionPane.showConfirmDialog(null, "No se puede abrir el navegador");
        }

    }
}
