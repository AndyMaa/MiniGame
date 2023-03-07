package minigame;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
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
import minigame.ui.FXChessUI;
import minigame.ui.GameFrame;
import minigame.ui.MusicPlayer;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

public final class App extends Application {
    /**
     * 一个哈希表,相当于组件库,方便取用
     */
    public static final HashMap<String, Node> map=new HashMap<>();

    private int count=0;
    /**
     * 通过Id获取组件
     */
    public static Node getNodeById(String id){
        return map.get(id);
    }

    public final Scene rootScene;
    public final Scene gameScene;
    private Stage stage;
    //private final ToggleGroup group=new ToggleGroup();

    public App() throws IOException {
        Parent root= FXMLLoader.load(MiniGame.class.getClassLoader().getResource("res/fxml/test.fxml"));
        //注册id
        regNodes(root);
        rootScene =new Scene(root);
        gameScene=new Scene(createGamePane());
        registerHandles();
        look();
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
        box.getChildren().add(b1);
        map.put("button$tip",b1);

        Button b2=new Button("退出");
        box.getChildren().add(b2);
        map.put("button$exit",b2);

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
            //TODO
        });
        getNodeById("button$create").setOnMouseClicked(event -> {
            Server server=new MainServer(10);
            Game.setServer(server);
            FXChessUI.instance.setChess(server.getChess());
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
        getNodeById("button$tip").setOnMouseClicked(event -> {
            NormalAI normalAI=new NormalAI();
            if (count<5) {
                int[] pos=normalAI.nextStep(Game.getServer().getChess(), Game.thePlayer.getId());
                if (pos!=null){
                    int p1=pos[0]+1;
                    int p2=-pos[1]-1;
                    //真实坐标(左上角0,0)

                    JOptionPane.showMessageDialog(null,"AI建议你下"+p1+", "+ p2);
                }
            }
            else JOptionPane.showInputDialog("您的试用次数已结束!请充值");
            count+=1;
        });
        getNodeById("button$exit").setOnMouseClicked(event -> stage.setScene(rootScene));  //返回主页面
        getNodeById("button$radio").setOnMouseClicked(event -> MusicPlayer.playBackground());  //播放音乐
        //RadioButton rb =(RadioButton) getNodeById("button$radio");
        //if (rb.isSelected()) getNodeById("button$exit").setOnMouseClicked(event -> MusicPlayer.playBackground());
    }

    /**
     * 添加阴影效果
     */
    private void look(){
        DropShadow shadow = new DropShadow();
        Button ai=(Button) getNodeById("button$ai");
        Button create=(Button) getNodeById("button$create");
        Button join=(Button) getNodeById("button$join");
        Button local=(Button) getNodeById("button$local");
        //当鼠标进入按钮时添加阴影特效
        ai.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> ai.setEffect(shadow));
        create.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> create.setEffect(shadow));
        join.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> join.setEffect(shadow));
        local.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> local.setEffect(shadow));

        //当鼠标离开按钮时移除阴影效果
        ai.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> ai.setEffect(null));
        create.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> create.setEffect(null));
        join.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> join.setEffect(null));
        local.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> local.setEffect(null));
    }
    public static void main(String[] args) {
        launch(args);
    }
}
