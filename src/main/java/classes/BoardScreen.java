package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import enums.*;
import run.*;

public class BoardScreen implements Screen {

    //Creates the map, and the layers with different map pieces
    public Map map;

    //Creates a robot that reacts to input
    private Robot robot;

    private States states;

    private final StartGame game;

    //The camera and the viewpoint
    private OrthogonalTiledMapRenderer TMRenderer;
    private OrthographicCamera camera;

    private final int WIDTH = 12;
    private final int HEIGHT = 12;

    public BoardScreen(StartGame game){

        this.game = game;

        states = new States();
        map = new Map();
        robot = new Robot(states.getNormal());

        //creates an input controller
        createController();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);

        map.setPlayer(robot);

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        camera.position.set(w/2,h/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 0.00333);
        TMRenderer.setView(camera);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        map.isHole(robot.getPosX(),robot.getPosY(),robot);
        map.isFlag(robot.getPosX(),robot.getPosY(),robot);
        if(map.isOut(robot.getPosX(),robot.getPosY(), robot)){
            Gdx.app.exit();
        }
        else robot.setState(states.getNormal());

        TMRenderer.render();

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
    }

    private void createController(){
        /*Input controller*/
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean keyUp(int keycode) {
                /*input controller*/
                    switch (keycode){
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
                }
                return false;
            }
        }
        );
    }
}
