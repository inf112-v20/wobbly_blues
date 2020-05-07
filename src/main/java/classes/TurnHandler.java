package classes;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class TurnHandler{

    private static int playerAmount;
    private static ArrayList<ArrayList<Card>> registerList = new ArrayList<>();
    private static boolean isReady=false;

    //CONCURRENCY TEST
    private Semaphore isReadySem;
    private Thread thread;
    private Map map;


    public TurnHandler() {
        isReadySem = new Semaphore(0);
        thread = new Thread(this::doTurnThread);
        thread.start();
    }

    /**
     * Set the amount of players in the game for the turn handler to interact with
     * @param players amount of players
     */
    public static void setPlayers(int players){
        playerAmount = players;
        registerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            registerList.add(new ArrayList<>());
        }
    }

    /**
     * add a card to register by index of register
     * @param register index of register to get the card
     * @param card card to add to register
     */
    public static void addCard(int register, Card card) {
        if (registerList.get(register).size() >= playerAmount){
            throw new IllegalArgumentException("Too many cards played in " +
                    "register "+register);
        }
        else {
            registerList.get(register).add(card);
        }
    }

    public static boolean removeCard(Card card){
        for (ArrayList<Card> register: registerList) {
            if (register.contains(card)){
                register.remove(card);
                return true;
            }
        }
        return false;
    }

    /**
     * check if all registers are full
     * @return True if all registers are full
     */
    public void setReady() {
        isReadySem.release();
    }

    public static ArrayList<ArrayList<Card>> sortByPriority(ArrayList<ArrayList<Card>> cards) {
        for ( ArrayList<Card> register: cards) {
            Collections.sort(register);
        }
        return cards;
    }

    public boolean allPlayersReady(){
        for (int i = 0; i < 5; i++) {
            if (registerList.get(i).size() != playerAmount) return false;
        }
        return true;
    }

    /**
     * Do turn of robot with given register index, all cards in the register do their action
     * @param register index of register
     * @param map map to do action on
     */
    public void doTurn(int register, Map map) {
        System.out.println("doTurn "+register);
        sortByPriority(registerList);
        for (Card card: registerList.get(register)) {
            sleep(200);
            System.out.println(card.getName());
            card.doAction(map);
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * multithreaded gameloop that waits for all robots to be ready, then does all movement and lasers.
     */
    //TODO: tweeking for at det fungere litt bedre
    public void doTurnThread(){
        while(true){
            try {
                isReadySem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Thread.interrupted()) return;
            for (int i = 0; i < 5; i++) {
                doTurn(i,map);
                for (Robot robot: map.getListOfPlayers()) {
                    GameLogic.check(robot.getPosX(),robot.getPosY(),robot);
                    sleep(100);
                    GameLogic.doConveyor(robot);
                    sleep(100);
                    GameLogic.rotorPad(robot);
                    sleep(100);
                    GameLogic.repeair(robot);
                }
                registerList.get(i).clear();
                GameLogic.fireAllLasers();
                for (Robot robot: map.getListOfPlayers()) {
                    GameLogic.fireLaser(robot.getPos(),robot.getDirection());
                }
                sleep(300);
                GameLogic.clearLasers();
                sleep(500);
            }
            for (Robot robot:map.getListOfPlayers()) {
                robot.clearHand();
            }
        }
    }

    public void sleep(int i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
