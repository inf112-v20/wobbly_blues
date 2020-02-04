package inf112.skeleton.app;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import com.badlogic.gdx.math.*;
import Class.*;

public class HelloWorld extends InputAdapter implements ApplicationListener {
    private TiledMap map;
    private TiledMapTileLayer Board;
    private TiledMapTileLayer FlagLayer;
    private TiledMapTileLayer HoleLayer;
    private TiledMapTileLayer PlayerLayer;
    private OrthogonalTiledMapRenderer TMrenderer;
    private OrthographicCamera Camera;
    private Texture Robot;
    private TiledMapTileLayer.Cell Normal;
    private TiledMapTileLayer.Cell Dead;
    private TiledMapTileLayer.Cell Won;
    private TiledMapTileLayer.Cell state;

    private Robot robot;

    @Override
    public void create() {
        creatState();
        Gdx.input.setInputProcessor(this);

        map = new TmxMapLoader().load("example.xml");
        Board = (TiledMapTileLayer) map.getLayers().get("Board");
        FlagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        HoleLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        PlayerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        Camera = new OrthographicCamera();
        TMrenderer = new OrthogonalTiledMapRenderer(map, (float) 0.00333);
        robot = new Robot(Normal);
        state = robot.getState();

        state = Normal;

        Camera.setToOrtho(false,5,5);

        float h = Camera.viewportHeight;
        float w = Camera.viewportWidth;

        Camera.position.set(h/2,w/2,0);
        Camera.update();

        TMrenderer.setView(Camera);

    }

    private void creatState(){
        Robot = new Texture(Gdx.files.internal("player.png"));
        Normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][0]));
        Dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][1]));
        Won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][2]));
    }

    @Override
    public void dispose(){
        TMrenderer.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(HoleLayer.getCell((int)robot.getPosX(),(int)robot.getPosY()) != null) robot.setState(Dead);
        else if(FlagLayer.getCell((int)robot.getPosX(), (int)robot.getPosY()) != null) robot.setState(Won);
        else robot.setState(Normal);

        PlayerLayer.setCell((int)robot.getPosX(),(int)robot.getPosY(),robot.getState());
        TMrenderer.render();
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
        if(robot.getState()== Dead) System.out.println("You are dead!");
        else if(robot.getState() == Won) System.out.println("You won!");
        else{
            if (keycode == Input.Keys.LEFT && robot.getPosX() > 0) {
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX() - 1, robot.getPosY());
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }

            if (keycode == Input.Keys.RIGHT && robot.getPosX() < 4) {
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX() + 1, robot.getPosY());
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }

            if (keycode == Input.Keys.UP && robot.getPosY() < 4) {
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX(), robot.getPosY() + 1);
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }

            if (keycode == Input.Keys.DOWN && robot.getPosY() > 0) {
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                robot.setPos(robot.getPosX() , robot.getPosY() - 1);
                PlayerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
            }
        }
        return false;
    }
}