package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import enums.*;

import java.util.ArrayList;

public class BoardScreen implements Screen {

    //Creates the map, and the layers with different map pieces
    public Map map;

    //Creates a robot that reacts to input
    private Robot robot;

    private final StartGame game;

    //The camera and the viewpoint
    private OrthogonalTiledMapRenderer TMRenderer;
    private OrthographicCamera camera;

    private final int WIDTH = 12;
    private final int HEIGHT = 15;

    private SpriteBatch batch;
    private BitmapFont font;
    private Button doTurnButton;
    private ArrayList<Button> cards;
    private TextField[] selectedNumbers;

    private Stage stage;

    private int inputCooldown;

    public BoardScreen(StartGame game){
        this.game = game;
        stage = new Stage();
        map = new Map();
        map.getBoard(this);

        map.placePlayers(2);

        setPlayer();

        //creates an input controller
        createController();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);

        initCards();

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        doTurnButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/startbtn.png"))));
        System.out.println(w+", "+h);
        doTurnButton.setSize(100,50);
        doTurnButton.setPosition(Options.screenWidth/2,Options.screenHeight/6,0);
        stage.addActor(doTurnButton);

        camera.position.set(w/2,(h-6)/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 0.00333);
        TMRenderer.setView(camera);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        selectedNumbers = new TextField[5];

        Gdx.input.setInputProcessor(stage);
        TurnHandler.setPlayers(1);
    }

    private void initCards(){
        cards = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Button button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("card/card.png"))));
            button.setPosition(Options.screenWidth*i/9,0);
            button.setName(""+i);
//            button.setBounds(Options.screenWidth*i/9,0,86,120);
//            button.setSize(86,120);
            button.setColor(Color.CYAN);
            cards.add(button);
            stage.addActor(button);
        }
    }

    @Override
    public void show() {
    }

    public void resetInputCooldown(){
        inputCooldown = 10;
    }

    public boolean inputCooldownDone(){
        return inputCooldown <= 0;
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        TMRenderer.render();
        stage.act();
        stage.draw();

        cardPress();

        startPressed();

        batch.begin();
        for (int i = 0; i < robot.getHand().size(); i++) {
            robot.getHand().get(i).render(batch,font,Options.screenWidth*i/9,0,86,120);
        }
        batch.end();
        inputCooldown = inputCooldownDone() ? 0 : inputCooldown-1;
    }

    private void startPressed(){
        if (inputCooldownDone()) {
            if (doTurnButton.isPressed() && !doTurnButton.isDisabled()) {
                resetInputCooldown();
                System.out.println("START");
                if (robot.setReady()) {
                    System.out.println("yes");
                    TurnHandler.doTurn(0, map);
                    TurnHandler.doTurn(1, map);
                    TurnHandler.doTurn(2, map);
                    TurnHandler.doTurn(3, map);
                    TurnHandler.doTurn(4, map);

                }
            }
        }
    }

    private void cardPress(){
        if (inputCooldownDone()) {
            for (int i = 0; i < 9; i++) {
                Button card = cards.get(i);
                if (card.isPressed()) {
                    resetInputCooldown();
                    if (card.isDisabled()) {
                        removeSelectedText(i);
                    } else {
                        int xPos = (Options.screenWidth*i/9);
                        addSelectedText(i,xPos);
                    }

                }
            }
        }
    }

    private void addSelectedText(int cardNr, int xPos){
        boolean failed = true;
        for (int i = 0; i < 5; i++) {
            if (selectedNumbers[i]==null){
                TextField textField = new TextField(""+i,new TextField.TextFieldStyle(new BitmapFont(),Color.BLACK,null,null,null));
                textField.setName(""+cardNr);
//                textField.setBounds(xPos,125,50,50);
                textField.setPosition(xPos,120);
                System.out.println(xPos);
                selectedNumbers[i]=textField;
                stage.addActor(textField);
                failed=false;
                break;
            }
        }
        if (failed){
            removeLastSelectedText();
            addSelectedText(cardNr,xPos);
        }
        else {
            cards.get(cardNr).setDisabled(true);
            cards.get(cardNr).setColor(Color.MAGENTA);
            robot.selectCard(cardNr);
            System.out.println("selected card " + cardNr);
        }
    }

    private void removeLastSelectedText(){
        int lastIdx = Character.getNumericValue(selectedNumbers[4].getName().charAt(0));
        removeSelectedText(lastIdx);
    }

    private void removeSelectedText(int cardNr){
        System.out.println("unselected card " + cardNr);
        cards.get(cardNr).setColor(Color.CYAN);
        robot.unselectCard(cardNr);
        cards.get(cardNr).setDisabled(false);
        for (int i = 0; i < 5; i++) {
            if (selectedNumbers[i]!=null) {
                if (selectedNumbers[i].getName().equals(cards.get(cardNr).getName())) {
                    selectedNumbers[i].remove();
                    selectedNumbers[i] = null;
                    break;
                }
            }
        }
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
       TMRenderer.dispose();
       batch.dispose();
       stage.dispose();
    }

    /**
     * Add keyboard and mouse interactions
     */
    private void createController() {
        /*Input controller*/
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                /*input controller*/
                switch (keycode) {
                    case Input.Keys.LEFT:
                        map.moveRobot(robot, Direction.LEFT);
                        break;
                    case Input.Keys.RIGHT:
                        map.moveRobot(robot, Direction.RIGHT);
                        break;
                    case Input.Keys.UP:
                        map.moveRobot(robot, Direction.UP);
                        break;
                    case Input.Keys.DOWN:
                        map.moveRobot(robot, Direction.DOWN);
                        break;
                    case Input.Keys.S:
                        setPlayer();
                        break;
                    case Input.Keys.F:
                        map.logic.fireLaser(new Vector2(robot.getPosX(), robot.getPosY()), robot.getDirection());
                        break;
                    case Input.Keys.C:
                        map.logic.clearLasers();
                        break;
                    case Input.Keys.L:
                        map.logic.fireAllLasers();
                        break;
                    case Input.Keys.ESCAPE:
                        Gdx.app.exit();
                        break;
                }
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                for (int i = 0; i < robot.getHand().size(); i++) {
//                    int minX = (5 + i * 88);
//                    int maxX = minX + 86;
//                    int minY = 1000;
//                    int maxY = minY - 120;
//                    if (screenX >= minX && screenX <= maxX && screenY <= minY && screenY >= maxY) {
//                        robot.getHand().get(i).doAction(map);
//                        return true;
//                    }
//                }
                return false;
            }
        }
        );
    }

    /**
     * Set player to interact with
     */
    public void setPlayer(){
        if(robot == null) {
            robot = map.getPlayerList().get(map.switchPlayer(null));
        }
        else {
            robot = map.getPlayerList().get(map.switchPlayer(robot));
        }
    }

    public Map getMap() {
        return map;
    }

}
