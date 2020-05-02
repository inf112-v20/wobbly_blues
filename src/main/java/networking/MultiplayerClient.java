package networking;

import classes.Card;
import classes.StartGame;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class MultiplayerClient {
    public Client client;
    private ClientNetworkListener clientNetworkListener;
    int tcp;
    int udp;

    public MultiplayerClient(InetAddress ipAdress, StartGame game, int tcp, int upd) {
        client = new Client();
        clientNetworkListener = new ClientNetworkListener();
        this.tcp = tcp;
        this.udp = upd;

        clientNetworkListener.init(client,game);
        registerPackets();
        client.addListener(clientNetworkListener);

        new Thread(client).start();

        try {
            client.connect(5000,ipAdress,tcp,udp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MultiplayerClient(InetAddress ipAdress, StartGame game) {
        this(ipAdress,game,54555,54777);
    }

    private void registerPackets(){
        Kryo kryo = client.getKryo();
        kryo.register(Packets.MessagePacket.class);
        kryo.register(Packets.CardPacket.class);
        kryo.register(Packets.PlayerNumPacket.class);
        kryo.register(Packets.NamePacket.class);
        kryo.register(String[].class);
        kryo.register(boolean.class);
        kryo.register(int.class);
        kryo.register(int[].class);
        kryo.register(int[][].class);
    }

    public void sendCards(Card[] cards){
        clientNetworkListener.sendCards(cards);
    }

//    public ArrayList<Card[]> getLastCardsReceived(){
//        if (networkListener.getCardsReceived().isEmpty()) return null;
//        return new ArrayList<Card[]>(Arrays.<Card[]>asList(networkListener.getCardsReceived().get(networkListener.getCardsReceived().size()-1).cards));
//    }

    public Card[] getLastCardTransfer(){
        Card[] cards = new Card[clientNetworkListener.getCards().cards.length];
        for (int i = 0; i < clientNetworkListener.getCards().cards.length; i++) {
            cards[i] = clientNetworkListener.getCards().cards[i];
        }
        return cards;
    }

    public int getId() {
        return client.getID();
    }

    public boolean getConnection(){
        return clientNetworkListener.getConnection();
    }

    public void sendStartSignal(){
        clientNetworkListener.sendStartSignal();
    }

    public void sendName(String name){
        Packets.NamePacket namePacket = new Packets.NamePacket();
        namePacket.name = new String[]{name};
        namePacket.playerId = client.getID();
        clientNetworkListener.sendName(namePacket);
    }

    public void dispose(){
        try {
            client.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
