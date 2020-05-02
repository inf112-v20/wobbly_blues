package networking;

import classes.Card;
import classes.StartGame;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {
    private Client client;
    private StartGame game;
    private Packets.MessagePacket firstMessagePacket;
    private Packets.CardPacket cards;
    private boolean connection = false;

    public void init(Client client, StartGame game){
        this.client = client;
        this.game = game;
        firstMessagePacket = new Packets.MessagePacket();
        cards = new Packets.CardPacket();
    }

    public void connect(Connection c){
        System.out.println("You have connected.");

        firstMessagePacket.message = "Hello there";
        client.sendTCP(firstMessagePacket);
        connection = true;
    }

    public void sendInformation(String message){
        firstMessagePacket.message = message;
        client.sendTCP(firstMessagePacket);
    }

    public void sendCards(Card[] cards){
        Packets.CardPacket cardPacket = new Packets.CardPacket();
        cardPacket.cards = new Card[cards.length];
        for (int i = 0; i < cards.length; i++) {
            cardPacket.cards[i] = null; //TODO
        }
        cardPacket.playerId = client.getID();
        client.sendTCP(cardPacket);
    }

    public void sendStartSignal(){
        Packets.StartSignalPacket startSignalPacket = new Packets.StartSignalPacket();
        startSignalPacket.start = true;
        client.sendTCP(startSignalPacket);
    }

    public void sendName(Packets.NamePacket name){
        client.sendTCP(name);
    }

    public void disconnect(Connection c){
        connection = false;
        System.out.println("You have disconnected.");
    }

    public void received(Connection c, Object packet){
        if (packet instanceof Packets.MessagePacket){
            Packets.MessagePacket messagePacket = (Packets.MessagePacket) packet;
        }
        else if (packet instanceof Packets.CardPacket){
            Packets.CardPacket cardPacket = (Packets.CardPacket) packet;
            cards = cardPacket;
            //TODO game is ready
        }
        else if (packet instanceof Packets.PlayerNumPacket){
            Packets.PlayerNumPacket playerNumPacket = (Packets.PlayerNumPacket) packet;
            //TODO: game set num of players
        }
        else if (packet instanceof Packets.NamePacket){
            Packets.NamePacket namePacket = (Packets.NamePacket) packet;
            //TODO: game receive names
        }
    }

    public Packets.CardPacket getCards() {
        return cards;
    }

    public boolean getConnection(){
        return connection;
    }
}
