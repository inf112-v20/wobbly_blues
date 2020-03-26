package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.*;
import enums.*;
import run.StartGame;

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

    public BoardScreen(StartGame game){
        this.game = game;

        map = new Map();

        map.placePlayers(2);

        setPlayer();

        //creates an input controller
        createController();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);


        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        camera.position.set(w/2,(h-6)/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 0.00333);
        TMRenderer.setView(camera);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        TMRenderer.render();

        batch.begin();
        for (int i = 0; i < robot.getHand().size(); i++) {
            robot.getHand().get(i).render(batch,font,5+i*88,0,86,120);
        }
        batch.end();
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
                                                    robot.getHand().get(i).doAction(map);
                                                    return true;
                                                }
                                            }
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
}
