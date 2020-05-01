package classes;

import java.util.ArrayList;
import java.util.Collections;

public class TurnHandler{

    private static int playerAmount;
    private static ArrayList<ArrayList<Card>> registerList = new ArrayList<>();

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
    public static boolean isReady() {
        for (int i = 0; i < 5; i++) {
            if (registerList.get(i).size() != playerAmount) return false;
        }
        return true;
    }

    //TODO: needs testing
    public static ArrayList<ArrayList<Card>> sortByPriority(ArrayList<ArrayList<Card>> cards) {
        for ( ArrayList<Card> register: cards) {
            Collections.sort(register);
        }
        return cards;
    }

    /**
     * Do turn of robot with given register index, all cards in the register do their action
     * @param register index of register
     * @param map map to do action on
     */
    public static void doTurn(int register, Map map) {
        System.out.println("doTurn "+register);
        if (!isReady()){
            throw new IllegalArgumentException("cannot start turn, not all " +
                    "robots are ready!");
        }
        sortByPriority(registerList);
        for (Card card: registerList.get(register)) {
            card.doAction(map);
        }
    }
}
