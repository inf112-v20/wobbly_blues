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
        state = robot.getState();
        //setting the screen
        this.setScreen(new StartScreen(this));

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