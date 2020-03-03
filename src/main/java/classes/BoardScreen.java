package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import run.*;

public class BoardScreen implements Screen {

    private final StartGame game;
    private final Robot robot;


    //The camera and the viewpoint
    private OrthogonalTiledMapRenderer TMRenderer;
    private OrthographicCamera camera;
    private Map map;


    private final int WIDTH = 12;
    private final int HEIGHT = 12;

    public BoardScreen(StartGame game){
        this.game = game;
        this.robot = game.getRobot();
        this.map = game.map;



        camera = new OrthographicCamera();


        camera.setToOrtho(false,WIDTH,HEIGHT);

        game.map.setPlayer(robot);

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

        if(map.isHole(robot.getPosX(),robot.getPosY(),robot)) robot.setState(game.dead);
        else if(map.isFlag(robot.getPosX(),robot.getPosY(),robot)) robot.setState(game.won);
        else if(map.isOut(robot.getPosX(),robot.getPosY(),robot)){
            System.out.println("You are dead!");
            Gdx.app.exit();
        }
        else robot.setState(game.normal);

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

    }
}
