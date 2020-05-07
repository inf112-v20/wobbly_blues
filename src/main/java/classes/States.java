package classes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class States {

    //The different states the robot can have it will fill the cell with the related texture
    private TiledMapTileLayer.Cell normal;
    private TiledMapTileLayer.Cell dead;
    private TiledMapTileLayer.Cell won;

    public States(String filename){
        createState(filename);
    }

    /**
     * loads the different textures and states to the robot
     */
    private void createState(String filename){
        normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal(filename)),900,300).split(300,300)[0][0]));
        dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal(filename)),900,300).split(300,300)[0][1]));
        won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal(filename)),900,300).split(300,300)[0][2]));
        System.out.println(normal);
        System.out.println(dead);
    }

    public TiledMapTileLayer.Cell getNormal(){
        return normal;
    }
    public TiledMapTileLayer.Cell getDead(){
        return dead;
    }
    public TiledMapTileLayer.Cell getWon(){
        return won;
    }

}
