package ca.uwaterloo.cs349;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MiniGame extends Application {

//    public static void main(String[] args) {
//        System.out.println("Main");
//        launch();
//    }

//    @Override
//    public void init() throws Exception {
//        // executed after launch() is called above
//        super.init();
//        System.out.println("Init");
//    }
    KeyCode pressedKey;
    GameState currentState = GameState.STARTSCREEN;
    int curLevel = 1;
    boolean isLeft = true;
    int spawnTimer = 1000;
    int enemiesLeft = 20;
    int score = 0;
    int livesLeft = 3;

    @Override
    public void start(Stage stage) throws Exception {
        // executed after init() method
        String sound1 = getClass().getClassLoader().getResource("sound\\fail.wav").toString();
        AudioClip clip = new AudioClip(sound1);

        String sound2= getClass().getClassLoader().getResource("sound\\firecast.mp3").toString();
        AudioClip clip2 = new AudioClip(sound2);

        String sound3 = getClass().getClassLoader().getResource("sound\\kill_sound.mp3").toString();
        AudioClip clip3 = new AudioClip(sound3);

        String sound4 = getClass().getClassLoader().getResource("sound\\won.mp3").toString();
        AudioClip clip4 = new AudioClip(sound4);

        Label label = new Label("Controls");
        label.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 40));
        label.setTextFill(Color.web("#F5F5DC"));
        label.relocate(550, 330);

        Label label2 = new Label("Press <- or -> to Fire");
        label2.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 40));
        label2.setTextFill(Color.web("#F5F5DC"));
        label2.relocate(450, 365);

        Label label3 = new Label("Start Game - Enter");
        label3.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 40));
        label3.setTextFill(Color.web("#F5F5DC"));
        label3.relocate(470, 440);

        Label label4 = new Label("Quit - Q");
        label4.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 40));
        label4.setTextFill(Color.web("#F5F5DC"));
        label4.relocate(560, 475);

        Label label5 = new Label("Current Score: " + Integer.toString(score));
        label5.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label5.setTextFill(Color.web("#F5F5DC"));
        label5.relocate(0, 50);

        Label label6 = new Label("Lives left: " + Integer.toString(livesLeft));
        label6.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label6.setTextFill(Color.web("#F5F5DC"));
        label6.relocate(0, 85);

        Label label7 = new Label("Enemies left: " + Integer.toString(enemiesLeft));
        label7.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label7.setTextFill(Color.web("#F5F5DC"));
        label7.relocate(0, 600);

        Label label8 = new Label("Game Over");
        label8.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 80));
        label8.setTextFill(Color.web("#F5F5DC"));
        label8.relocate(450, 150);

        Label label9 = new Label("Final Score: " + Integer.toString(score));
        label9.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label9.setTextFill(Color.web("#F5F5DC"));
        label9.relocate(575, 250);

        Label label10 = new Label("Press 'Enter' to return to main menu or 'Q' to quit");
        label10.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label10.setTextFill(Color.web("#F5F5DC"));
        label10.relocate(325, 280);

        Label label11 = new Label("Current Score: " + Integer.toString(score));
        label11.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label11.setTextFill(Color.web("#F5F5DC"));
        label11.relocate(575, 250);

        Label label12 = new Label("Press 'Enter' to continue to next lvl or 'Q' to quit");
        label12.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label12.setTextFill(Color.web("#F5F5DC"));
        label12.relocate(325, 280);

        Label label13 = new Label("Congratulations on finishing this lvl!");
        label13.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 40));
        label13.setTextFill(Color.web("#F5F5DC"));
        label13.relocate(325, 200);

        Label label14 = new Label("Current Level: " + Integer.toString(curLevel));
        label14.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label14.setTextFill(Color.web("#F5F5DC"));
        label14.relocate(0, 120);

        Label label15 = new Label("Congratulations on beating the game!");
        label15.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 60));
        label15.setTextFill(Color.web("#F5F5DC"));
        label15.relocate(125, 150);

        Label label16 = new Label("Final Score: " + Integer.toString(score));
        label16.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label16.setTextFill(Color.web("#F5F5DC"));
        label16.relocate(575, 250);

        Label label17 = new Label("Press 'Enter' to play again or 'Q' to quit");
        label17.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 30));
        label17.setTextFill(Color.web("#F5F5DC"));
        label17.relocate(375, 280);

        Label label18 = new Label("Fan Jiang");
        label18.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 15));
        label18.setTextFill(Color.web("#F5F5DC"));
        label18.relocate(0, 0);

        Label label19 = new Label("20617280");
        label19.setFont(Font.font("Verdana Pro", FontWeight.BOLD, 15));
        label19.setTextFill(Color.web("#F5F5DC"));
        label19.relocate(0, 15);

        FileInputStream inputstream = new FileInputStream(".\\src\\resources\\img\\background2.png");
        Image img1 = new Image(inputstream);
        FileInputStream inputstream2 = new FileInputStream(".\\src\\resources\\img\\logo.png");
        Image img2 = new Image(inputstream2);
        FileInputStream inputstream3 = new FileInputStream(".\\src\\resources\\img\\idle.png");
        Image img3 = new Image(inputstream3);
        FileInputStream inputstream4 = new FileInputStream(".\\src\\resources\\img\\fireball2.gif");
        Image img4 = new Image(inputstream4);
        FileInputStream inputstream5 = new FileInputStream(".\\src\\resources\\img\\enemy.png");
        Image img5 = new Image(inputstream5);

        ImageView background = new ImageView(img1);
        background.setFitHeight(640);
        background.setFitWidth(1280);
        ImageView logo = new ImageView(img2);
        logo.setPreserveRatio(true);
        logo.setFitHeight(250);
        logo.relocate(365,15);
        ImageView playerImage = new ImageView(img3);

//        StackPane stackPane = new StackPane();
//        Scene scene = new Scene(stackPane, 1280, 640);
//
//        stackPane.getChildren().add(background);
//        stackPane.getChildren().add(logo);
//        stackPane.getChildren().add(label);
//        stackPane.getChildren().add(label2);
//        stackPane.setAlignment(logo, Pos.TOP_CENTER);
//        stage.setTitle("Monsters Vs Monsters");
//        stage.setScene(scene);
//        stage.setResizable(false);

        Pane startMenu = new Pane();
        Scene scene = new Scene(startMenu, 1280, 640);
        BackgroundImage myBI= new BackgroundImage(img1,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        startMenu.setBackground(new Background(myBI));
        startMenu.getChildren().add(logo);
        startMenu.getChildren().add(label);
        startMenu.getChildren().add(label2);
        startMenu.getChildren().add(label3);
        startMenu.getChildren().add(label4);
        startMenu.getChildren().add(label18);
        startMenu.getChildren().add(label19);
        stage.setTitle("Monsters Vs Monsters");
        stage.setScene(scene);
        stage.setResizable(false);

        List<Entity> entityList = new ArrayList<Entity>();
        List<Entity> fireList = new ArrayList<Entity>();
        List<Entity> monsterList = new ArrayList<Entity>();


        Pane lvlPane = new Pane();
        Scene lvl = new Scene(lvlPane, 1280, 640);
        lvlPane.setBackground(new Background(myBI));
        //lvlPane.getChildren().add(playerImage);
        Player player = new Player(525,350, playerImage, lvlPane);
        entityList.add(player);
        player.draw();
        lvlPane.getChildren().add(label5);
        lvlPane.getChildren().add(label6);
        lvlPane.getChildren().add(label7);
        lvlPane.getChildren().add(label14);

        Pane losePane = new Pane();
        Scene lose = new Scene(losePane, 1280, 640);
        losePane.setBackground(new Background(myBI));
        losePane.getChildren().add(label8);
        losePane.getChildren().add(label9);
        losePane.getChildren().add(label10);

        Pane continuePane = new Pane();
        Scene cont = new Scene(continuePane, 1280, 640);
        continuePane.setBackground(new Background(myBI));
        continuePane.getChildren().add(label11);
        continuePane.getChildren().add(label12);
        continuePane.getChildren().add(label13);

        Pane winPane = new Pane();
        Scene win = new Scene(winPane, 1280, 640);
        winPane.setBackground(new Background(myBI));
        winPane.getChildren().add(label15);
        winPane.getChildren().add(label16);
        winPane.getChildren().add(label17);

        AnimationTimer timer = new AnimationTimer() {


            @Override
            public void handle(long now) {
                switch (currentState) {
                    case STARTSCREEN:
                        break;
                    case lvl:
                        if (monsterList.isEmpty() && enemiesLeft == 0) {
                            if (curLevel == 3) {
                                clip4.play();
                                currentState = GameState.WINSCREEN;
                                stage.setScene(win);
                                label16.setText("Final Score: " + Integer.toString(score));
                                label17.setText("Press 'Enter' to play again or 'Q' to quit");
                            } else  {
                                currentState = GameState.CONTINUE;
                                stage.setScene(cont);
                                label11.setText("Current Score: " + Integer.toString(score));
                                label12.setText("Press 'Enter' to continue to next lvl or 'Q' to quit");
                            }
                        }
                        if (spawnTimer >= 0) {
                            if (curLevel == 1) {
                                spawnTimer -= 5;
                            } else if (curLevel == 2) {
                                spawnTimer -= 10;
                            } else {
                                spawnTimer -= 20;
                            }
                            //System.out.println(spawnTimer);
                        } else {
                            spawnTimer = 1000;
                            if (enemiesLeft > 0) {
                                enemiesLeft -= 1;
                                label7.setText("Enemies left: " + Integer.toString(enemiesLeft));
                                ImageView monsterImage = new ImageView(img5);
                                Monster monster = new Monster(0, 400, curLevel, Math.random() < 0.5, monsterImage, lvlPane);
                                monsterList.add(monster);
                                monster.draw();
                            }

                        }
                        entityList.forEach(e -> e.processInput(isLeft));
                        entityList.forEach(e -> e.update());
                        fireList.forEach(e -> e.processInput(isLeft));
                        fireList.forEach(e -> e.update());
                        monsterList.forEach(e -> e.processInput(isLeft));
                        monsterList.forEach(e -> e.update());
                        for (Iterator<Entity> it = fireList.iterator(); it.hasNext();) {
                            Entity entity = it.next();
                            if (entity.getClass() == FireBall.class) {
                                FireBall fireball = (FireBall) entity;
                                if (fireball.getLifeTime() <= 0) {
                                    lvlPane.getChildren().remove(fireball.sprite);
                                    score -= 1;
                                    label5.setText("Current Score: " + Integer.toString(score));
                                    it.remove();
                                }
                            }
                        }
                        for (Iterator<Entity> it = fireList.iterator(); it.hasNext();) {
                            Entity entity = it.next();
                            boolean intersect = false;
                            if (entity.getClass() == FireBall.class) {
                                FireBall fireball = (FireBall) entity;
                                for (Iterator<Entity> it2 = monsterList.iterator(); it2.hasNext();) {
                                    Entity entity2 = it2.next();
                                    if (entity2.getClass() == Monster.class) {
                                        Monster monster = (Monster) entity2;
                                        Rectangle rec1 = new Rectangle((int) entity.sprite.getLayoutX(), (int) entity.sprite.getLayoutY(), (int) entity.sprite.getImage().getWidth(), (int) entity.sprite.getImage().getHeight());
                                        Rectangle rec2 = new Rectangle((int) entity2.sprite.getLayoutX(), (int) entity2.sprite.getLayoutY(), (int) entity2.sprite.getImage().getWidth(), (int) entity2.sprite.getImage().getHeight());
                                        //if (entity.sprite.intersects(entity2.sprite.getLayoutX(), entity2.sprite.getLayoutY(), entity2.sprite.getImage().getWidth(), entity2.sprite.getImage().getHeight())) {
                                        if (rec1.intersects(rec2)) {
                                            clip3.play();
                                            score++;
                                            intersect = true;
                                            label5.setText("Current Score: " + Integer.toString(score));
                                            lvlPane.getChildren().remove(monster.sprite);
                                            it2.remove();
                                            break;
                                        }
                                    }
                                }
                                if (intersect) {
                                    lvlPane.getChildren().remove(fireball.sprite);
                                    it.remove();
                                    break;
                                }
                            }
                        }
                        for (Iterator<Entity> it = monsterList.iterator(); it.hasNext();) {
                            Entity entity = it.next();
                            if (entity.getClass() == Monster.class) {
                                Monster monster = (Monster) entity;
                                Rectangle rec1 = new Rectangle((int) entity.sprite.getLayoutX(), (int) entity.sprite.getLayoutY(), (int) entity.sprite.getImage().getWidth(), (int) entity.sprite.getImage().getHeight());
                                Rectangle rec2 = new Rectangle((int) entityList.get(0).sprite.getLayoutX() + 50, (int) entityList.get(0).sprite.getLayoutY(), (int) entityList.get(0).sprite.getImage().getWidth() - 100, (int) entityList.get(0).sprite.getImage().getHeight());

                                if (rec1.intersects(rec2)) {
                                    monster.hitPlayer = true;
                                    livesLeft--;
                                    label6.setText("Lives left: " + Integer.toString(livesLeft));
                                }

                                if (livesLeft == 0) {
                                    currentState = GameState.LOSESCREEN;
                                    stage.setScene(lose);
                                    clip.play();
                                    label9.setText("Final Score: " + Integer.toString(score));;
                                }
                            }
                        }
                        break;
                    case CONTINUE:
                        break;
                    case LOSESCREEN:
                        break;
                    case WINSCREEN:
                        break;
                }
            }
        };


        lvl.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            pressedKey = key.getCode();
            if (pressedKey == KeyCode.LEFT) {
                isLeft = true;
                ImageView fireImage = new ImageView(img4);
                FireBall fireBall = new FireBall(525,500, curLevel,true, fireImage, lvlPane);
                fireList.add(fireBall);
                fireBall.draw();
            } else if (pressedKey == KeyCode.RIGHT) {
                isLeft = false;
                ImageView fireImage = new ImageView(img4);
                FireBall fireBall = new FireBall(708,500, curLevel,false, fireImage, lvlPane);
                fireList.add(fireBall);
                fireBall.draw();
            }
            clip2.play();


        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            pressedKey = key.getCode();


            if (pressedKey == KeyCode.Q) {
                System.exit(0);
            } else if (pressedKey == KeyCode.ENTER) {
                stage.setScene(lvl);
                currentState = GameState.lvl;
                timer.start();
            } else if (pressedKey == KeyCode.DIGIT1) {
                stage.setScene(lvl);
                currentState = GameState.lvl;
                timer.start();
            } else if (pressedKey == KeyCode.DIGIT2) {
                stage.setScene(lvl);
                curLevel = 2;
                label14.setText("Current Level: " + Integer.toString(curLevel));
                currentState = GameState.lvl;
                timer.start();
            } else if (pressedKey == KeyCode.DIGIT3) {
                stage.setScene(lvl);
                currentState = GameState.lvl;
                curLevel = 3;
                label14.setText("Current Level: " + Integer.toString(curLevel));
                timer.start();
            }


        });

        lose.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            pressedKey = key.getCode();

            if (pressedKey == KeyCode.Q) {
                System.exit(0);
            } else if (pressedKey == KeyCode.ENTER) {
                currentState = GameState.STARTSCREEN;
                curLevel = 1;
                isLeft = true;
                spawnTimer = 1000;
                enemiesLeft = 20;
                score = 0;
                livesLeft = 3;
                lvlPane.getChildren().clear();
                monsterList.removeAll(monsterList);
                fireList.removeAll(fireList);
                stage.setScene(scene);
                player.draw();
                lvlPane.getChildren().add(label5);
                lvlPane.getChildren().add(label6);
                lvlPane.getChildren().add(label7);
                lvlPane.getChildren().add(label14);
                label5.setText("Current Score: " + Integer.toString(score));
                label6.setText("Lives left: " + Integer.toString(livesLeft));
                label7.setText("Enemies left: " + Integer.toString(enemiesLeft));
                label14.setText("Current Level: " + Integer.toString(curLevel));
            }


        });

        cont.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            pressedKey = key.getCode();

            if (pressedKey == KeyCode.Q) {
                System.exit(0);
            } else if (pressedKey == KeyCode.ENTER) {
                currentState = GameState.lvl;
                curLevel++;
                isLeft = true;
                spawnTimer = 1000;
                enemiesLeft = 20;
                lvlPane.getChildren().clear();
                monsterList.removeAll(monsterList);
                fireList.removeAll(fireList);
                stage.setScene(lvl);
                player.draw();
                lvlPane.getChildren().add(label5);
                lvlPane.getChildren().add(label6);
                lvlPane.getChildren().add(label7);
                lvlPane.getChildren().add(label14);
                label5.setText("Current Score: " + Integer.toString(score));
                label6.setText("Lives left: " + Integer.toString(livesLeft));
                label7.setText("Enemies left: " + Integer.toString(enemiesLeft));
                label14.setText("Current Level: " + Integer.toString(curLevel));
            }


        });

        win.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            pressedKey = key.getCode();

            if (pressedKey == KeyCode.Q) {
                System.exit(0);
            } else if (pressedKey == KeyCode.ENTER) {
                currentState = GameState.STARTSCREEN;
                curLevel = 1;
                isLeft = true;
                spawnTimer = 1000;
                enemiesLeft = 20;
                score = 0;
                livesLeft = 3;
                lvlPane.getChildren().clear();
                monsterList.removeAll(monsterList);
                fireList.removeAll(fireList);
                stage.setScene(scene);
                player.draw();
                lvlPane.getChildren().add(label5);
                lvlPane.getChildren().add(label6);
                lvlPane.getChildren().add(label7);
                lvlPane.getChildren().add(label14);
                label5.setText("Current Score: " + Integer.toString(score));
                label6.setText("Lives left: " + Integer.toString(livesLeft));
                label7.setText("Enemies left: " + Integer.toString(enemiesLeft));
                label14.setText("Current Level: " + Integer.toString(curLevel));
            }


        });






        stage.show();




        //while (true) {
        //   Thread.sleep(100);

        //    stage.show();
       // }

    }

    enum GameState {
        STARTSCREEN,
        lvl,
        CONTINUE,
        LOSESCREEN,
        WINSCREEN
    }
//
//    @Override
//    public void stop() throws Exception {
//        // executed when app is shutdown
//        super.stop();
//        System.out.println("Stop");
//    }
}


