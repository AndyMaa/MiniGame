package minigame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minigame.core.Game;
import minigame.core.ai.NormalAI;
import minigame.core.players.AIPlayer;
import minigame.core.server.GhostServer;
import minigame.core.server.LocalServer;
import minigame.core.server.MainServer;
import minigame.core.server.Server;
import minigame.ui.ChessUI;
import minigame.ui.FXChessUI;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

public final class App extends Application {
    public static final HashMap<String, Node> map=new HashMap<>();

    /**
     * 通过Id获取组件
     */
    public static Node getNodeById(String id){
        return map.get(id);
    }

    public final Scene rootScene;
    public final Scene gameScene;
    private Stage stage;

    public App() throws IOException {
        Parent root= FXMLLoader.load(MiniGame.class.getClassLoader().getResource("res/fxml/test.fxml"));

        //注册id
        regNodes(root);
        registerHandles();

        rootScene =new Scene(root);
        gameScene=new Scene(createGamePane());
    }
    @Override
    public void start(Stage primaryStage) {
        stage=primaryStage;
        primaryStage.setTitle("MiniGame");
        primaryStage.setScene(rootScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private Parent createGamePane() {
        VBox vBox=new VBox();
        vBox.setPrefSize(550,600);
        vBox.setAlignment(Pos.CENTER);
        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(281,45);
        box.setSpacing(20);
        box.setLayoutX(134);
        box.setLayoutY(14);
        Button b1=new Button("提示");
        map.put("button$tip",b1);
        box.getChildren().add(b1);
        b1=new Button("退出");
        box.getChildren().add(b1);
        map.put("button$exit",b1);

        vBox.getChildren().add(box);
        vBox.getChildren().add(new FXChessUI());
        return vBox;
    }

    /**
     * 递归找按钮
     */
    private void regNodes(Parent parent){
        for (Node node:parent.getChildrenUnmodifiable()) {
            if (node instanceof Parent) {
                regNodes(((Parent) node));
            }
            if (node.getId() != null) {
                map.put(node.getId(), node);
            }
        }
    }

    private void registerHandles(){
        getNodeById("button$ai").setOnMouseClicked(event -> {
            Server server=new LocalServer(10);
            Game.setServer(server);
            FXChessUI.instance.setChess(server.getChess());
            Game.thePlayer.join(server);
            AIPlayer ai=new AIPlayer(new NormalAI());
            ai.join(server);
            stage.setScene(gameScene);
            System.out.println("ai mode");
        });
        getNodeById("button$local").setOnMouseClicked(event -> {

        });
        getNodeById("button$create").setOnMouseClicked(event -> {
            Server server=new MainServer(10);
            Game.setServer(server);
            ChessUI.instance.setChess(server.getChess());
            Game.thePlayer.join(server);
            stage.setScene(gameScene);
            System.out.println("Create!");
        });
        getNodeById("button$join").setOnMouseClicked(event -> {
            String get= JOptionPane.showInputDialog("请输入ip和端口:");
            String[] result=get.split(":");
            String ip=result[0];
            String p=result[1];
            int port=Integer.parseInt(p);
            GhostServer ghost=new GhostServer(ip, port);
            Game.setServer(ghost);
            Game.thePlayer.join(ghost);
            System.out.println("join");
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
