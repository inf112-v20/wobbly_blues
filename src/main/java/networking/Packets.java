package networking;

import classes.Card;

public class Packets {
    public static class MessagePacket{
        public String message;
    }
    public static class CardPacket{
        public Card[] cards;
        public int playerId;
    }
    public static class PlayerNumPacket{
        public int playerNum;
    }
    public static class StartSignalPacket{
        public boolean start;
    }
    public static class NamePacket{
        public String[] name;
        public int playerId;
    }
}
