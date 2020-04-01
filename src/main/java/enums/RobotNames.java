package enums;

import java.util.*;

public enum RobotNames {

    //robot names
    TWONKEY (0),
    SQUASH (1),
    TWITCH (2),
    ZOOM (3),
    HAMMER (4),
    SPIN (5),
    HULK (6),
    TRUNDLE (7);

    private final int id;

    RobotNames(int id){this.id = id;}

    public int getId() {
        return this.id;
    }
    public static RobotNames getById(int id) {
        for(RobotNames e : values()) {
            if(Objects.equals(e.id, id)) return e;
        }
        return TWONKEY;
    }

}
