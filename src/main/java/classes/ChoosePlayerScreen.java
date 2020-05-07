package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

import java.util.*;

public class ChoosePlayerScreen implements Screen{

    //the main game
    private final StartGame game;
    //the stage where we put the buttons
    private Stage stage;
    //the buttons in the menu
    private final Button startBtn,logo;
    private int cooldown, cooldownTimer;
    private float r=.66f, g=.66f, b=1f;
    public Skin defaultSkin;
    public SelectBox<String> selectNumbPlayers;
    public SelectBox<String> selectNumbAI;
    public SelectBox<String> selectMap;

    private SpriteBatch batch;
    private BitmapFont font;
    private SpriteBatch batch2;
    private BitmapFont font2;
    private SpriteBatch batch3;
    private BitmapFont font3;

    public final float BUTTON_WIDTH;
    public final float BUTTON_HEIGHT;


    public ChoosePlayerScreen(StartGame game) {
        this.game = game;
        stage = new Stage();
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        this.defaultSkin = new Skin(Gdx.files.internal("uiskin.json"));

        /**
         * create the dropdown menu for choosing number of players and AI.
         */
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale((float) 1.2);
        font.setColor(Color.BLACK);

        batch2 = new SpriteBatch();
        font2 = new BitmapFont();
        font2.getData().setScale((float) 1.2);
        font2.setColor(Color.BLACK);

        batch3 = new SpriteBatch();
        font3 = new BitmapFont();
        font3.getData().setScale((float) 1.2);
        font3.setColor(Color.BLACK);

        BUTTON_WIDTH = (float) (Gdx.graphics.getWidth() * 0.25);
        BUTTON_HEIGHT = (float) (Gdx.graphics.getHeight() * 0.25);

        //creating buttons, and setting positons
        logo = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/logo.png"))));
        logo.setPosition(width /2f - logo.getWidth()/2, height*4 /6f);

        startBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/startbtn.png"))));
        startBtn.setSize(150,75);
        startBtn.setPosition(width /2f - startBtn.getWidth()/2, height/6f);

        /**
         * sets the contents of the dropdown menu
         */
        selectNumbPlayers = new SelectBox<>(defaultSkin);
        selectNumbPlayers.setItems("1","2","3","4","5","6","7","8");
        selectNumbPlayers.setSelected("1");
        selectNumbPlayers.setWidth(BUTTON_WIDTH * .87f);
        selectNumbPlayers.setPosition(width /2f - selectNumbPlayers.getWidth()/2 , stage.getHeight()/2);

        selectNumbAI = new SelectBox<>(defaultSkin);
        selectNumbAI.setItems("0","1","2","3","4","5","6","7");
        selectNumbAI.setSelected("0");
        selectNumbAI.setWidth(BUTTON_WIDTH * .87f);
        selectNumbAI.setPosition(width /2f - selectNumbAI.getWidth()/2, (stage.getHeight()/2)-100);

        selectMap = new SelectBox<>(defaultSkin);
        selectMap.setItems("Random","Risky_Exchange","Dizzy_Dash","Bloodbath_Chess","Whirlwind_Tour");
        selectMap.setSelected("Random");
        selectMap.setWidth(BUTTON_WIDTH * .87f);
        selectMap.setPosition(width /2f - selectNumbAI.getWidth()/2, (stage.getHeight()/2)-200);

        stage.addActor(selectNumbPlayers);
        stage.addActor(selectNumbAI);
        stage.addActor(selectMap);
        stage.addActor(startBtn);
        stage.addActor(logo);

        Gdx.input.setInputProcessor(stage);
        cooldown = 10;
        cooldownTimer = 0;


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Number of AI",(stage.getWidth()/2)-(BUTTON_WIDTH/2)+50, (stage.getHeight()/2)-50);
        batch.end();

        batch2.begin();
        font2.draw(batch2, "Number of Players",(stage.getWidth()/2)-(BUTTON_WIDTH/2)+30, (stage.getHeight()/2)+55);
        batch2.end();

        batch3.begin();
        font3.draw(batch3, "Select map",(stage.getWidth()/2)-(BUTTON_WIDTH/2)+55, (stage.getHeight()/2)-145);
        batch3.end();

        stage.act(v);
        stage.draw();

        //actions for the buttons
        if(startBtn.isPressed()){
            //if pressed sends you to the board.
            String boardName;
            if(selectNumbAI.getSelectedIndex() + selectNumbPlayers.getSelectedIndex()+1 <=8){
                if(selectMap.getSelected().equals("Random")){
                    Random rand = new Random();
                    int i = rand.nextInt(5);
                    if(i == 0){
                        i++;
                    }
                    selectMap.setSelectedIndex(i);
                    boardName = selectMap.getSelected();
                }
                else{
                    boardName = selectMap.getSelected();
                }
                System.out.println(boardName);
                game.setScreen(new BoardScreen(game,selectNumbPlayers.getSelectedIndex()+1,selectNumbAI.getSelectedIndex(),boardName));
            }
            else{
                System.out.println("you can't have more than 8 players.");
            }
        }

        if (cooldownTimer==0) {
            if (logo.isPressed()) {

                float temp = r;
                r = g;
                g = b;
                b = temp;
                cooldownTimer = cooldown;
            }
        }
        else
            cooldownTimer--;
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}

