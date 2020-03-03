package run;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import enums.Direction;

public class StartGame extends Game {

    //Creates the map, and the layers with different map pieces
    public Map map;

    //Where we can paint different things
    public SpriteBatch paint;


    //The different states the robot can have it will fill the cell with the related texture
    public TiledMapTileLayer.Cell normal;
    public TiledMapTileLayer.Cell dead;
    public TiledMapTileLayer.Cell won;
    public TiledMapTileLayer.Cell state;

    //Creates a robot that reacts to input
    private Robot robot;

    @Override
    public void create() {
        createState();
        map = new Map();
        robot = new Robot(normal);
        this.setScreen(new BoardScreen(this));
        state = robot.getState();


        /*Input controller*/
        Gdx.input.setInputProcessor(new InputAdapter(){
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
                        case Input.Keys.L:

                    }
                }
                return false;
            }
        } );
    }

    private void createState(){
        /*loads the different textures and states to the robot */
        normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("player.png")),900,300).split(300,300)[0][0]));
        dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("player.png")),900,300).split(300,300)[0][1]));
        won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("player.png")),900,300).split(300,300)[0][2]));
    }

    @Override
    public void dispose(){
    }

    @Override
    public void render() {
        super.render();
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

    public Robot getRobot(){
        return robot;
    }
}