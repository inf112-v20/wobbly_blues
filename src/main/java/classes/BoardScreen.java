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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import enums.*;
import interfaces.IRobot;
import run.StartGame;

import java.util.*;

public class BoardScreen implements Screen {

    //Creates the map, and the layers with different map pieces
    public Map map;

    //Creates a robot that reacts to input
    private IRobot robot;

    private final StartGame game;

    //The camera and the viewpoint
    private OrthogonalTiledMapRenderer TMRenderer;
    private OrthographicCamera camera;

    private final int WIDTH = 12;
    private final int HEIGHT = 15;

    private SpriteBatch batch;
    private BitmapFont font;

    private Stage stage;
    private Button goButton;

    private Button[] cards;

    private boolean moving=false;

    public BoardScreen(StartGame game){
        this.game = game;
        stage = new Stage();
        map = new Map();

        map.placePlayers(3);

        setPlayer();

        //creates an input controller
//        createController();
        Gdx.input.setInputProcessor(stage);
        goButton =
                new Button(new TextureRegionDrawable(new TextureRegion(new Texture("gobutton.png"))));
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        goButton.setPosition(400-goButton.getWidth()/2f,
                200-goButton.getHeight());
        stage.addActor(goButton);

        camera.position.set(w/2,(h-6)/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 0.00333);
        TMRenderer.setView(camera);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        TurnHandler.setPlayers(1);
        cards = new Button[9];
        for (int i = 0; i < cards.length; i++) {
            Image cardBack =
                    new Image(new TextureRegionDrawable(new TextureRegion(new Texture("card/card.png"))));
            cardBack.setPosition(5+i*88,0);
            stage.addActor(cardBack);
            cards[i] =
                    new Button(new TextureRegionDrawable(new TextureRegion(new Texture("card/"+robot.getHand().get(i).getCardType().name()+".png"))));
            cards[i].setPosition(5+i*88,0);
            stage.addActor(cards[i]);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
        TMRenderer.render();
        if (goButton.isPressed()) {
            System.out.println("yes");
            go();
        }
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].isPressed()){
                if (!cards[i].isChecked()) {
                    System.out.println("clicked " + robot.getHand().get(i).getName());
                    robot.chooseCard(i);
                    cards[i].setChecked(true);
                }
            }
        }
        moveAll();
        batch.begin();
//        for (int i = 0; i < robot.getHand().size(); i++) {
//            robot.getHand().get(i).render(batch,font,5+i*88,0,86,120);
//        }
        batch.end();
    }

    public void moveAll(){
        if (moving) {
            System.out.println("moving");
            for (int i = 0; i < 5; i++) {
                TurnHandler.movePhase(i, map);
            }
            robot.newHand();
            moving = false;
        }
    }

    public boolean go(){
        if (robot.setReady()) {
            moving = true;
            return true;
        }
        return false;
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
    }

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
                                            }
                                            return false;
                                        }

                                        @Override
                                        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                                            for (int i = 0; i < robot.getHand().size(); i++) {
                                                int minX = (5 + i * 88);
                                                int maxX = minX + 86;
                                                int minY = 1000;
                                                int maxY = minY - 120;
                                                if (screenX >= minX && screenX <= maxX && screenY <= minY && screenY >= maxY) {
                                                    robot.chooseCard(i);
                                                    return true;
                                                }
                                            }
                                            return false;
                                        }
                                    }
        );
    }

    public void setPlayer(){
        if(robot == null) {
            robot = map.getPlayerList().get(map.switchPlayer(null));
        }
        else {
            robot = map.getPlayerList().get(map.switchPlayer(robot));
        }
    }
}
