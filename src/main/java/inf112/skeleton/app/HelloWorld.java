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
    private Vector2 PlayerPos;
    private TiledMapTileLayer.Cell state;

    private Test_robot robot = new Test_robot();

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);

        map = new TmxMapLoader().load("example.xml");
        Board = (TiledMapTileLayer) map.getLayers().get("Board");
        FlagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        HoleLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        PlayerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        Robot = new Texture(Gdx.files.internal("player.png"));
        PlayerPos = new Vector2();

        Normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][0]));
        Dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][1]));
        Won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][2]));

        Camera = new OrthographicCamera();
        TMrenderer = new OrthogonalTiledMapRenderer(map, (float) 0.00333);

        state = robot.getState();

        state = Normal;
        PlayerPos.set(0,0);

        Camera.setToOrtho(false,5,5);
        float h = Camera.viewportHeight;
        float w = Camera.viewportWidth;

        Camera.position.set(h/2,w/2,0);
        Camera.update();

        TMrenderer.setView(Camera);

    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(HoleLayer.getCell((int)PlayerPos.x,(int)PlayerPos.y) != null) state = Dead;
        else if(FlagLayer.getCell((int)PlayerPos.x, (int)PlayerPos.y) != null) state = Won;
        else state = Normal;

        PlayerLayer.setCell((int)PlayerPos.x,(int)PlayerPos.y,state);
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
        if(state == Dead) System.out.println("You are dead!");
        else if(state == Won) {
            System.out.println("You won!");
        }
        else{
            if (keycode == Input.Keys.LEFT && PlayerPos.x > 0) {
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, null);
                PlayerPos.set(PlayerPos.x - 1, PlayerPos.y);
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, state);
            }

            if (keycode == Input.Keys.RIGHT && PlayerPos.x < 4) {
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, null);
                PlayerPos.set(PlayerPos.x + 1, PlayerPos.y);
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, state);
            }

            if (keycode == Input.Keys.UP && PlayerPos.y < 4) {
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, null);
                PlayerPos.set(PlayerPos.x, PlayerPos.y + 1);
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, state);
            }

            if (keycode == Input.Keys.DOWN && PlayerPos.y > 0) {
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, null);
                PlayerPos.set(PlayerPos.x, PlayerPos.y - 1);
                PlayerLayer.setCell((int) PlayerPos.x, (int) PlayerPos.y, state);
            }
        }
        return false;
    }
}