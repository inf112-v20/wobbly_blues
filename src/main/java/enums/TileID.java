package enums;

public enum TileID {
    //Starting positions
    START1(121),
    START2(122),
    START3(123),
    START4(124),
    START5(129),
    START6(130),
    START7(131),
    START8(133),

    //Single walls
    EAST_WALL(23),
    SOUTH_WALL(29),
    WEST_WALL(30),
    NORTH_WALL(31),

    //Walls with lasers
    EAST_LASER_WALL(46),
    SOUTH_LASER_WALL(37),
    WEST_LASER_WALL(38),
    NORTH_LASER_WALL(45),

    //double walls
    SOUTH_EAST_WALL(8),
    NORTH_EAST_WALL(16),
    NORTH_WEST_WALL(24),
    SOUTH_WEST_WALL(32),

    //Laser Lines
    VERTICAL_LASER(47),
    HORIZONTAL_LASER(39),
    CROSSED_LASER(40),

    NORMAL_HOLE(6),
    NORMAL_HOLE2(91),
    NORTH_WEST_HOLE(105),
    NORTH_HOLE(106),
    NORTH_EAST_HOLE(107),

    EAST_HOLE(108),
    NORTH_EAST_SOUTH_HOLE(109),
    WEST_EAST_SOUTH_HOLE(110),
    SOUTH_WEST_HOLE(113),
    SOUTH_HOLE(114),
    SOUTH_EAST_HOLE(115),
    WEST_HOLE(116),

    NORTH_WEST_SOUTH_HOLE(117),
    NORTH_WEST_EAST_HOLE(118),

    WRENCH(14),
    DOUBLE_WRENCH(7),

    PLAYER(137),

    FLAG1(55),
    FLAG2(63),
    FLAG3(71),
    FLAG4(79);

    private final int id;

    TileID(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
