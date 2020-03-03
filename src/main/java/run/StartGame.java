package run;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import enums.Direction;

public class StartGame extends Game {

    //Creates the map, and the layers with different map pieces
    public Map map;

    //Where we can paint different things
    public SpriteBatch paint;

    /*
    The camra and the viewpoint
     */
    private OrthogonalTiledMapRenderer TMRenderer;
    private OrthographicCamera camera;

    //The different states the robot can have it will fill the cell with the related texture
    public TiledMapTileLayer.Cell normal;
    public TiledMapTileLayer.Cell dead;
    public TiledMapTileLayer.Cell won;
    public TiledMapTileLayer.Cell state;

    private final int WIDTH = 12;
    private final int HEIGHT = 12;

    /*
    Creates a robot that reacts to input
     */
    private Robot robot;

    @Override
    public void create() {
        createState();
        map = new Map();
        robot = new Robot(normal);
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

        camera = new OrthographicCamera();


        camera.setToOrtho(false,WIDTH,HEIGHT);

        map.setPlayer(robot);

        float h = camera.viewportHeight;
        float w = camera.viewportWidth;
        camera.position.set(w/2,h/2,0);
        camera.update();

        TMRenderer = new OrthogonalTiledMapRenderer(map.getMap(), (float) 0.00333);
        TMRenderer.setView(camera);
    }

    private void createState(){
        /*loads the different textures and states to the robot */
        normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("player.png")),900,300).split(300,300)[0][0]));
        dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("player.png")),900,300).split(300,300)[0][1]));
        won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("player.png")),900,300).split(300,300)[0][2]));
    }

    @Override
    public void dispose(){
        TMRenderer.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(map.isHole(robot.getPosX(),robot.getPosY(),robot)) robot.setState(dead);
        else if(map.isFlag(robot.getPosX(),robot.getPosY(),robot)) robot.setState(won);
        else if(map.isOut(robot.getPosX(),robot.getPosY(),robot)){
            System.out.println("You are dead!");
            Gdx.app.exit();
        }
        else robot.setState(normal);

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
}