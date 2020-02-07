package inf112.skeleton.app;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import Class.*;

public class Board extends InputAdapter implements ApplicationListener {

    /*
    Creates the map, and the layers with different mappieces.
     */
    private TiledMap map;
    private TiledMapTileLayer board;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer playerLayer;

    /*
    The camra and the viewpoint
     */
    private OrthogonalTiledMapRenderer tMrenderer;
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

    /*
    Creates a robot that reacts to input
     */
    private Robot robot;

    @Override
    public void create() {

        createState();
        /*Input controller*/
        Gdx.input.setInputProcessor(this);

        /*
        Loads the map and the layers
         */
        map = new TmxMapLoader().load("fullboard.tmx");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        camera = new OrthographicCamera();
        tMrenderer = new OrthogonalTiledMapRenderer(map, (float) 0.00333);


        robot = new Robot(normal);
        state = robot.getState();

        camera.setToOrtho(false,12,12);

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        camera.position.set(h/2,w/2,0);
        camera.update();

        tMrenderer.setView(camera);

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
        tMrenderer.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        /*
        Checks if the robot has hit a hole or a flag
         */
        if(holeLayer.getCell((int)robot.getPosX(),(int)robot.getPosY()) != null) robot.setState(dead);
        else if(flagLayer.getCell((int)robot.getPosX(), (int)robot.getPosY()) != null) robot.setState(won);
        else robot.setState(normal);

        playerLayer.setCell((int)robot.getPosX(),(int)robot.getPosY(),robot.getState());
        tMrenderer.render();
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
        if(robot.getState()== dead){System.out.println("You are dead!");System.exit(-1);}
        else if(robot.getState() == won){ System.out.println("You won!"); System.exit(-1);}
        else{
            if (keycode == Input.Keys.LEFT && robot.getPosX() > 0) {
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX() - 1, robot.getPosY());
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }

            if (keycode == Input.Keys.RIGHT && robot.getPosX() < camera.viewportWidth-1) {
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX() + 1, robot.getPosY());
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }

            if (keycode == Input.Keys.UP && robot.getPosY() < camera.viewportHeight-1) {
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX(), robot.getPosY() + 1);
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }

            if (keycode == Input.Keys.DOWN && robot.getPosY() > 0) {
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX() , robot.getPosY() - 1);
                playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }
        }
        return false;
    }
}