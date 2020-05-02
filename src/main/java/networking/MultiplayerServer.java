package networking;

import classes.Card;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class MultiplayerServer implements Runnable{

    int udp;
    int rcp;
    private InetAddress address;
    private Server server;
    private NetworkListener networkListener;

    public MultiplayerServer(int udp, int rcp) {
        this.udp = udp;
        this.rcp = rcp;
    }

    public MultiplayerServer() {
        this.udp = 54555;
        this.rcp = 54777;
    }

    @Override
    public void run() {
        server = new Server();
        networkListener = new NetworkListener(server);
        server.addListener(networkListener);
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        registerPackets();
        server.start();
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void registerPackets(){
        Kryo kryo = server.getKryo();
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

    public InetAddress getAddress() {
        return address;
    }


    public ArrayList<Card[]> getLastCardsReceived(){
        if (networkListener.getCardsReceived().isEmpty()) return null;
        return new ArrayList<Card[]>(Arrays.<Card[]>asList(networkListener.getCardsReceived().get(networkListener.getCardsReceived().size()-1).cards));
    }

    public void dispose(){
        try {
            server.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
