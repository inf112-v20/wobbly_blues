package inf112.skeleton.app;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import com.badlogic.gdx.math.*;


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
        Camera = new OrthographicCamera();
        TMrenderer = new OrthogonalTiledMapRenderer(map, (float) 0.00333);

        Normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,300,300)));
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
        PlayerLayer.setCell(0,0,Normal);
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
        return super.keyUp(keycode);
    }
}
