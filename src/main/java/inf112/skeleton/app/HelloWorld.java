package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;


public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap map;
    private TiledMapTileLayer Board;
    private TiledMapTileLayer Flag;
    private TiledMapTileLayer Hole;
    private TiledMapTileLayer Player;
    private OrthogonalTiledMapRenderer TMrenderer;
    private OrthographicCamera Camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        map = new TmxMapLoader().load("example.xml");
        Board = (TiledMapTileLayer) map.getLayers().get("Board");
        Flag = (TiledMapTileLayer) map.getLayers().get("Flag");
        Hole = (TiledMapTileLayer) map.getLayers().get("Hole");
        Player = (TiledMapTileLayer) map.getLayers().get("Player");
        Camera = new OrthographicCamera();
        TMrenderer = new OrthogonalTiledMapRenderer(map, (float) 0.00333);

        Camera.setToOrtho(false,5,5);
        float h = Camera.viewportHeight;
        float w = Camera.viewportWidth;
        Camera.position.set(h/2,w/2,0);
        Camera.update();

        TMrenderer.setView(Camera);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        TMrenderer.render();
        batch.begin();
        font.draw(batch, "Hello World", 200, 200);
        batch.end();
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
}
