package run;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import enums.Direction;

public class Game extends InputAdapter implements ApplicationListener {

    /*
    Creates the map, and the layers with different map pieces.
     */
    Map map;

    /*
    The camra and the viewpoint
     */
    private OrthogonalTiledMapRenderer TMRenderer;
    private OrthographicCamera camera;

    /*
    contains the textures of the robot
     */
    private Texture sprite;

    /*
    The different states the robot can have it will fill the cell with the related texture
     */
    private TiledMapTileLayer.Cell normal;
    private TiledMapTileLayer.Cell dead;
    private TiledMapTileLayer.Cell won;
    private TiledMapTileLayer.Cell state;

    final int WIDTH = 12;
    final int HEIGHT = 12;

    /*
    Creates a robot that reacts to input
     */
    private Robot robot;

    @Override
    public void create() {

        createState();
        /*Input controller*/
        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera();

        robot = new Robot(normal);
        state = robot.getState();
        map = new Map();

        camera.setToOrtho(false,WIDTH,HEIGHT);

        map.setPlayer(robot);

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;
        camera.position.set(h/2,w/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 0.00333);
        TMRenderer.setView(camera);
        TMRenderer.setView(camera);
    }

    private void createState(){
        /*loads the different textures and states to the robot */
        sprite = new Texture(Gdx.files.internal("player.png"));
        normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(sprite,900,300).split(300,300)[0][0]));
        dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(sprite,900,300).split(300,300)[0][1]));
        won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(sprite,900,300).split(300,300)[0][2]));
    }

    @Override
    public void dispose(){
        TMRenderer.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(map.isHole(robot.getPosX(),robot.getPosY())) robot.setState(dead);
        else if(map.isFlag(robot.getPosX(),robot.getPosY())) robot.setState(won);
        else robot.setState(normal);

        map.moveRobot(robot);
        TMRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean keyUp(int keycode) {
        /*input controller*/
        if(robot.getState() == dead){
            System.out.println("You are dead!");
            Gdx.app.exit();}
        else if(robot.getState() == won){ System.out.println("You won!"); System.exit(-1);}
        else{
            switch (keycode){
                case Input.Keys.LEFT:
                    map.moveRobot(robot,Direction.LEFT);
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
        }
        return false;
    }
}