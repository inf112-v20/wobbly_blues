package inf112.skeleton.app;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import classes.*;
import enums.Direction;

public class Board extends InputAdapter implements ApplicationListener {

    private Card_Left cardLeft;

    /*
    Creates the map, and the layers with different mappieces.
     */

    Map map;
    /*
    The camra and the viewpoint
     */

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
        map = new Map();

        camera = new OrthographicCamera();


        robot = new Robot(normal);
        state = robot.getState();

        cardLeft = new Card_Left(robot);

        camera.setToOrtho(false,5,5);

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;

        camera.position.set(h/2,w/2,0);
        camera.update();

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
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        /*
        Checks if the robot has hit a hole or a flag
         */
        if (map.isHole(robot.ghttps://www.vogella.com/tutorials/Mockito/article.html    etPosX(),robot.getPosY()))robot.setState(dead);
        else if (map.isFlag(robot.getPosX(),robot.getPosY())) robot.setState(won);
        else robot.setState(normal);
        map.render();
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
        if(robot.getState()== dead){
            System.out.println("You are dead!");
            Gdx.app.exit();}
        else if(robot.getState() == won){ System.out.println("You won!"); System.exit(-1);}
        else{
            switch (keycode){
                case Input.Keys.W:
                case Input.Keys.UP:
                    map.moveRobot(robot, Direction.UP);
                    break;
                case Input.Keys.S:
                case Input.Keys.DOWN:
                    map.moveRobot(robot, Direction.DOWN);
                    break;
                case Input.Keys.A:
                case Input.Keys.LEFT:
                    map.moveRobot(robot, Direction.LEFT);
                    break;
                case Input.Keys.D:
                case Input.Keys.RIGHT:
                    map.moveRobot(robot, Direction.RIGHT);
                    break;
                default:
                    break;
            }
        }
        return false;
    }
}