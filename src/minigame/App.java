package minigame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import minigame.core.Game;
import minigame.core.Util;
import minigame.core.ai.NormalAI;
import minigame.core.players.AIPlayer;
import minigame.core.players.LocalPlayer;
import minigame.core.server.GhostServer;
import minigame.core.server.LocalServer;
import minigame.core.server.MainServer;
import minigame.core.server.Server;
import minigame.ui.FXChessUI;
import minigame.ui.Gui;
import minigame.ui.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public final class App extends Application {
    /**
     * 一个哈希表,相当于组件库,方便取用
     */
    public static final HashMap<String, EventTarget> map=new HashMap<>();

    private int count=0;
    /**
     * 通过Id获取组件
     */
    public static Node getNodeById(String id){
        return (Node) map.get(id);
    }
    public static MenuItem getMenuById(String id){
        return (MenuItem) map.get(id);
    }
    public static URL getUrl(String path){
        return App.class.getClassLoader().getResource(path);
    }

    public final Scene rootScene;
    public final Scene gameScene;
    private Stage stage;
    //private final ToggleGroup group=new ToggleGroup();

    public App() throws IOException {
        Parent root= FXMLLoader.load(getUrl("res/fxml/test.fxml"));
        //注册id
        regNodes(root);
        rootScene =new Scene(root);
        gameScene=new Scene(createGamePane());
        registerHandles();
        look();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage=primaryStage;
        primaryStage.getIcons().add(new Image(getUrl("res/img/icon.png").openStream()));
        primaryStage.setTitle("MiniGame");
        primaryStage.setScene(rootScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.addEventHandler(WindowEvent.WINDOW_HIDDEN,event -> System.exit(0));
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

//        box.getChildren().add(new Label("您的邀请码:"));
//        box.getChildren().add(MainServer.invite);
//        map.put("invite", MainServer.invite);

        /*
        Label l1=new Label("您是");
        Label l2=new Label();
        if (Game.thePlayer.getId()==Server.turn){
            l2.setText("先手");
        }else if (Game.thePlayer.getId()==2){
            l2.setText("后手");
        }
        box.getChildren().add(l1);
        box.getChildren().add(l2);
        map.put("label$t1", l1);
        map.put("label$t2", l2);
        */

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
                if (node instanceof MenuBar){
                    MenuBar m = (MenuBar) node;
                    for (Menu menu:m.getMenus()){
                        for (MenuItem item:menu.getItems()){
                            if (item.getId()!=null){
                                map.put(item.getId(),item);
                            }
                        }
                    }
                }else {
                    regNodes(((Parent) node));
                }
            }
            if (node.getId() != null) {
                map.put(node.getId(), node);
            }
        }
    }

    private void registerHandles(){
        getNodeById("button$ai").setOnMouseClicked(event -> {
            Server server=new LocalServer(Game.size);
            Game.setServer(server);
            FXChessUI.instance.setChess(server.getChess());
            Game.thePlayer.join(server);
            AIPlayer ai=new AIPlayer(new NormalAI());
            ai.join(server);
            stage.setScene(gameScene);
            System.out.println("ai mode");
        });
        getNodeById("button$local").setOnMouseClicked(event -> {
            Server server=new LocalServer(Game.size);
            Game.setServer(server);
            FXChessUI.instance.setChess(server.getChess());
            Game.thePlayer.join(server);
            new LocalPlayer().join(server);
            stage.setScene(gameScene);
        });
        getNodeById("button$create").setOnMouseClicked(event -> {
            MainServer server=new MainServer(Game.size);
            Game.setServer(server);
            FXChessUI.instance.setChess(server.getChess());
            Game.thePlayer.join(server);
            stage.setScene(gameScene);
        });
        getNodeById("button$join").setOnMouseClicked(event -> {
            String get= Gui.input("请输入邀请码：");
            if (get==null||get.equals("")) return;
            try {
                Object[] result=Util.unZipAddress(get);
                GhostServer ghost=new GhostServer(((String) result[0]), ((Integer) result[1]));
                Game.setServer(ghost);
                Game.thePlayer.join(ghost);
                System.out.println("join");
                stage.setScene(gameScene);
            }catch (IllegalArgumentException e){
                Gui.info("无效的邀请码！");
            }catch (IOException ioE){
                Gui.info("无法连接至服务器");
            }
        });
        getNodeById("button$tip").setOnMouseClicked(event -> {
            NormalAI normalAI=new NormalAI();
            if (count<5) {
                int[] pos=normalAI.nextStep(Game.getServer().getChess(), Game.thePlayer.getId());
                if (pos!=null){
                    int p1=pos[0]+1;
                    int p2=-pos[1]-1;
                    //真实坐标(左上角0,0)
                    Gui.info("AI建议你下"+p1+", "+ p2);
                }
            }
            else JOptionPane.showInputDialog("您的试用次数已结束!请充值");
            count+=1;
        });
        getNodeById("button$exit").setOnMouseClicked(event -> {
            stage.setScene(rootScene);
            Game.exit();
        });  //返回主页面
        getNodeById("button$radio").setOnMouseClicked(event -> {
            RadioButton radioButton= (RadioButton) getNodeById("button$radio");
            if (radioButton.isSelected()){
                MusicPlayer.playBackground();
            }else {
                MusicPlayer.stopBgm();
            }
        });
        getMenuById("menu$resize").setOnAction(event -> {
            String input=Gui.input("请输入棋盘大小：（偶数）（4-20）\n当前棋盘大小："+Game.size);
            if (input==null) return;
            int size;
            try {
                size=Integer.parseInt(input);
            }catch (NumberFormatException e){
                Gui.info("请输入一个数字！");
                return;
            }
            if (size%2==1){
                Gui.info("请输入一个偶数！");
                return;
            }
            if (size<4||size>20){
                Gui.info("请输入一个4-20间的数");
                return;
            }
            Game.size=size;
        });
        getMenuById("menu$github").setOnAction(event -> {
        });
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
        ai.addEventHandler(MouseEvent.MOUSE_ENTERED, new EffectListener(ai));
        create.addEventHandler(MouseEvent.MOUSE_ENTERED, new EffectListener(create));
        join.addEventHandler(MouseEvent.MOUSE_ENTERED, new EffectListener(join));
        local.addEventHandler(MouseEvent.MOUSE_ENTERED, new EffectListener(local));

        //当鼠标离开按钮时移除阴影效果
        ai.addEventHandler(MouseEvent.MOUSE_EXITED, new EffectListener(ai));
        create.addEventHandler(MouseEvent.MOUSE_EXITED, new EffectListener(create));
        join.addEventHandler(MouseEvent.MOUSE_EXITED, new EffectListener(join));
        local.addEventHandler(MouseEvent.MOUSE_EXITED, new EffectListener(local));
    }
    private static final class EffectListener implements EventHandler<MouseEvent>{
        private static final DropShadow shadow=new DropShadow();
        private final Node node;

        public EffectListener(Node node) {
            this.node = node;
        }

        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType()==MouseEvent.MOUSE_ENTERED){
                node.setEffect(shadow);
                node.setScaleX(1.3);
                node.setScaleY(1.3);
            }else if (event.getEventType()==MouseEvent.MOUSE_EXITED){
                node.setEffect(null);
                node.setScaleX(1);
                node.setScaleY(1);
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
