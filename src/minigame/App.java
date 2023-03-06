package minigame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import minigame.core.Game;
import minigame.core.ai.NormalAI;
import minigame.core.players.AIPlayer;
import minigame.core.server.LocalServer;
import minigame.core.server.Server;
import minigame.ui.ChessUI;
import minigame.ui.ChessUIAdapter;
import minigame.ui.GameFrame;

public class App extends Application {
    public Button aiButton;
    public Button localButton;
    public Button joinButton;
    public Button createButton;

    public Scene rootScene;
    public Scene gameScene;
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        Parent root= FXMLLoader.load(MiniGame.class.getClassLoader().getResource("res/fxml/test.fxml"));

        //通过文本获取按钮
        findButton(root);

        registerHandles();
        primaryStage.setTitle("MiniGame");
        rootScene =new Scene(root, 800, 600);
        gameScene=new Scene(createGamePane(),600,600);
        primaryStage.setScene(rootScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private Parent createGamePane(){
        AnchorPane pane=new AnchorPane();
        pane.getChildren().add(new ChessUIAdapter());
        return pane;
    }

    /**
     * 递归找按钮
     */
    private void findButton(Parent parent){
        Button b;
        for (Node node:parent.getChildrenUnmodifiable()){
            if (node instanceof Button){
                b= ((Button) node);
                switch (b.getText()){
                    case "玩家 VS AI":
                        aiButton=b;
                        break;
                    case "玩家 VS 玩家":
                        localButton=b;
                        break;
                    case "创建服务器":
                        createButton=b;
                        break;
                    case "加入服务器":
                        joinButton=b;
                        break;
                }
            }else if (node instanceof Parent){
                findButton(((Parent) node));
            }
        }
    }

    private void registerHandles(){
        aiButton.setOnMouseClicked(event -> {
            Server server=new LocalServer(10);
            Game.setServer(server);
            ChessUI.instance.setChess(server.getChess());
            Game.thePlayer.join(server);
            AIPlayer ai=new AIPlayer(new NormalAI());
            ai.join(server);
            stage.setScene(gameScene);
//            GameFrame.instance.setMode("game");
            System.out.println("ai mode");
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
