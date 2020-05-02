package networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;

public class NetworkListener extends Listener {

    private Server server;
    private int[] players;
    private int playerNum=0;
    private String[] playerNames;
    private final int maxPlayerNum = 5;
    private ArrayList<Packets.CardPacket> cardsReceived;
    private ArrayList<Packets.CardPacket> cardsReceivedCopy;

    public NetworkListener(Server server) {
        this.server = server;
        players = new int[maxPlayerNum];
        playerNames = new String[maxPlayerNum];

    }

    public void connect(Connection c){
        players[playerNum] = c.getID();
        playerNum++;
        System.out.println("Player "+(playerNum) +" connected!");
        Packets.PlayerNumPacket numOfPlayers = new Packets.PlayerNumPacket();
        numOfPlayers.playerNum = playerNum;
        server.sendToAllTCP(numOfPlayers);
    }

    public void disconnected(Connection c){
        System.out.println("Player "+playerNum+" disconnected!");
        playerNum--;
        playerNames[c.getID()] = null;
        Packets.PlayerNumPacket numOfPlayers = new Packets.PlayerNumPacket();
        numOfPlayers.playerNum = playerNum;
        server.sendToAllTCP(numOfPlayers);
        Packets.NamePacket name = new Packets.NamePacket();
        name.name = playerNames;
        server.sendToAllTCP(name);
    }

    public void received(Connection c, Object packet){
        if (packet instanceof Packets.MessagePacket){
            Packets.MessagePacket messagePacket = (Packets.MessagePacket) packet;
            server.sendToAllExceptTCP(c.getID(), messagePacket);
        }
        else if (packet instanceof Packets.CardPacket){
            Packets.CardPacket cardPacket = (Packets.CardPacket) packet;
            cardsReceived.add(cardPacket);
            cardsReceivedCopy.add(cardPacket);
            server.sendToAllExceptTCP(c.getID(), cardPacket);
            if (cardsReceived.size() == playerNum){
                for (Packets.CardPacket pack: cardsReceived) {
                    server.sendToAllTCP(pack);
                }
                cardsReceived.clear();
            }
        }
        else if (packet instanceof Packets.StartSignalPacket){
            Packets.StartSignalPacket startSignalPacket = (Packets.StartSignalPacket) packet;
            server.sendToAllTCP(startSignalPacket);
        }
        else if (packet instanceof Packets.NamePacket){
            Packets.NamePacket namePacket = (Packets.NamePacket) packet;
            playerNames[c.getID()] = namePacket.name[0];
            namePacket.name = playerNames;
            server.sendToAllTCP(namePacket);
        }
    }

    public  ArrayList<Packets.CardPacket> getCardsReceived(){
        if (cardsReceivedCopy.size() > 4){
            cardsReceivedCopy.clear();
        }
        return cardsReceivedCopy;
    }

}
