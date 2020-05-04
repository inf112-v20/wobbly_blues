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
    private SpriteBatch batch2;
    private BitmapFont font2;
    private Button doTurnButton;
    private ArrayList<Button> cards;
    private TextField[] selectedNumbers;
    private TurnHandler turnHandler;

    private Stage stage;

    private int inputCooldown;

    public BoardScreen(StartGame game){
        this.game = game;
        stage = new Stage();
        map = new Map();
        map.getBoard(this);

        TurnHandler.setPlayers(1);
        turnHandler = new TurnHandler();
        turnHandler.setMap(map);

        map.placePlayers(2);

        setPlayer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);

        initCards();

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        doTurnButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/startbtn.png"))));
        doTurnButton.setSize(100,50);
        doTurnButton.setPosition((float)(Options.screenWidth/2),(float)(Options.screenHeight/6),0);
        stage.addActor(doTurnButton);

        camera.position.set((w-(float)1.8)/2,(h-(float)9.5)/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 1/350);
        TMRenderer.setView(camera);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        selectedNumbers = new TextField[5];

        batch2 = new SpriteBatch();
        font2 = new BitmapFont();
        font2.getData().setScale((float) 1.2);
        font2.setColor(Color.BLACK);

        InputProcessor inputProcessorOne = stage;
        InputProcessor inputProcessorTwo = createController();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(inputProcessorOne);
        inputMultiplexer.addProcessor(inputProcessorTwo);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void initCards(){
        cards = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Button button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("card/card.png"))));
            button.setPosition((float)(Options.screenWidth * i / 9) + getCardPadding(),0);
            button.setName(""+i);

            button.setColor(Color.CYAN);
            cards.add(button);
            stage.addActor(button);
        }
    }

    public int getCardPadding(){
        return ((Options.screenWidth/robot.getHand().size())-72)/2;
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

        InfoText();

        stage.act();
        stage.draw();

        cardPress();
        startPressed();

        batch.begin();
        for (int i = 0; i < robot.getHand().size(); i++) {
            robot.getHand().get(i).render(batch,font,Options.screenWidth*i/9+getCardPadding(),0,86,120);
        }
        batch.end();
        inputCooldown = inputCooldownDone() ? 0 : inputCooldown-1;
    }

    private void InfoText() {

        String playerInfoText = robot.getName() + "\n" +
                "Lives: " + robot.getHp() + "\n" +
                "Health: " + robot.getDamageToken() + "\n" +
                "Flags taken: " + robot.numbFlags() + "\n" +
                "Direction: " + robot.getDirection() + "\n" +
                "Position: " + robot.getPos().toString();

        batch2.begin();
        // Player Info
        font2.draw(batch2, playerInfoText, 30, 50 + 200);
        batch2.end();

    }

    private void startPressed(){
        if (inputCooldownDone()) {
            if (doTurnButton.isPressed() && !doTurnButton.isDisabled()) {
                resetInputCooldown();
                System.out.println("START");
                if (robot.setReady()) {
                    System.out.println("yes");
                    turnHandler.setReady();
                    newTurnCleanup();
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
                        int xPos = (Options.screenWidth*i/9)+getCardPadding();
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

    private void newTurnCleanup(){
        for (Button cardButton: cards) {
            cardButton.setColor(Color.CYAN);
            cardButton.setDisabled(false);
        }
        for (int i = 0; i < selectedNumbers.length; i++) {
            selectedNumbers[i].remove();
            selectedNumbers[i]=null;
        }
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

    private InputProcessor createController() {
        /*Input controller*/
       InputProcessor input = new InputAdapter() {
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
                        GameLogic.fireLaser(new Vector2(robot.getPosX(), robot.getPosY()), robot.getDirection());
                        break;
                    case Input.Keys.C:
                        GameLogic.clearLasers();
                        break;
                    case Input.Keys.L:
                        GameLogic.fireAllLasers();
                        break;
                    case Input.Keys.ESCAPE:
                        Gdx.app.exit();
                        break;
                }
                return false;
            }
        };
       return input;
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
