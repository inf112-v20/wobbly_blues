package classes;

import interfaces.IRobot;

import java.util.ArrayList;
import java.util.Collections;

public class TurnHandler{

    private static int playerAmount;
    private static ArrayList<ArrayList<Card>> registerList;

    public static void setPlayers(int players){
        playerAmount = players;
        registerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            registerList.add(new ArrayList<>());
        }
    }

    public static void addCard(int register, Card card) {
        if (registerList.get(register).size() >= playerAmount){
            throw new IllegalArgumentException("Too many cards played in " +
                    "register "+register);
        }
        else {
            registerList.get(register).add(card);
        }
    }

    public static boolean isReady() {
        for (int i = 0; i < 5; i++) {
            if (registerList.get(i).size() != playerAmount) return false;
        }
        return true;
    }

    public void clearRegisters(){
        for (int i = 0; i < 5; i++) {
            registerList.get(i).clear();
        }
    }

    //TODO: needs testing
    public static ArrayList<ArrayList<Card>> sortByPriority(ArrayList<ArrayList<Card>> cards) {
        for ( ArrayList<Card> register: cards) {
            Collections.sort(register);
        }
        return cards;
    }

    public static void movePhase(int register, Map map) {
        if (!isReady()){
            throw new IllegalArgumentException("cannot start turn, not all " +
                    "robots are ready!");
        }
        sortByPriority(registerList);
        for (Card card: registerList.get(register)) {
            card.doAction(map);
        }
    }

    public static void laserPhase(int register, Map map){
        for (int i = 0; i < registerList.get(register).size(); i++) {
            IRobot robot = registerList.get(register).get(i).getRobot();
            robot.shootLaser();
            if (register==4){
                robot.newHand();
            }
        }
    }
}
