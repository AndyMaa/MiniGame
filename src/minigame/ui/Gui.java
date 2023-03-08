package minigame.ui;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import minigame.core.Util;
import minigame.core.server.MainServer;

public class Gui {
    /**
     * 会造成UI线程阻塞
     */
    public static void info(String message){
        if (Toolkit.getToolkit().isFxUserThread()){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(message);
            alert.show();
        }else {
            Platform.runLater(()->info(message));
        }
    }
    public static String input(String message){
        TextInputDialog dialog=new TextInputDialog();
        dialog.setHeaderText(message);
        dialog.showAndWait();
        return dialog.getResult();
    }
}
