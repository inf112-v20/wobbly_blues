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

    WRENCH(15),
    DOUBLE_WRENCH(7),

    PLAYER(137),

    FLAG1(55),
    FLAG2(63),
    FLAG3(71),
    FLAG4(79),

    EAST_TO_SOUTH_BELT(33),
    NORTH_TO_EAST_BELT(41),
    WEST_TO_NORTH_BELT(42),
    SOUTH_TO_WEST_BELT(34),
    EAST_TO_NORTH_BELT(43),
    NORTH_TO_WEST_BELT(44),
    WEST_TO_SOUTH_BELT(36),
    SOUTH_TO_EAST_BELT(35),
    EAST_TO_WEST_BELT(51),
    NORTH_TO_SOUTH_BELT(50),
    WEST_TO_EAST_BELT(52),
    SOUTH_TO_NORTH_BELT(49),

    WEST_SOUTH_TO_NORTH_BELT(57),
    EAST_SOUTH_TO_NORTH_BELT(65),
    WEST_EAST_TO_NORTH_BELT(69),
    WEST_NORTH_TO_SOUTH_BELT(67),
    EAST_NORTH_TO_SOUTH_BELT(59),
    WEST_EAST_TO_SOUTH_BELT(62),
    WEST_SOUTH_TO_EAST_BELT(66),
    WEST_NORTH_TO_EAST_BELT(58),
    NORTH_SOUTH_TO_EAST_BELT(61),
    EAST_SOUTH_TO_WEST_BELT(60),
    EAST_NORTH_TO_WEST_BELT(68),
    NORTH_SOUTH_TO_WEST_BELT(70),

    EAST_TO_SOUTH_EXPRESS_BELT(17),
    NORTH_TO_EAST_EXPRESS_BELT(25),
    WEST_TO_NORTH_EXPRESS_BELT(26),
    SOUTH_TO_WEST_EXPRESS_BELT(18),
    EAST_TO_NORTH_EXPRESS_BELT(27),
    NORTH_TO_WEST_EXPRESS_BELT(28),
    WEST_TO_SOUTH_EXPRESS_BELT(20),
    SOUTH_TO_EAST_EXPRESS_BELT(19),
    EAST_TO_WEST_EXPRESS_BELT(22),
    NORTH_TO_SOUTH_EXPRESS_BELT(21),
    WEST_TO_EAST_EXPRESS_BELT(14),
    SOUTH_TO_NORTH_EXPRESS_BELT(13),

    WEST_SOUTH_TO_NORTH_EXPRESS_BELT(73),
    EAST_SOUTH_TO_NORTH_EXPRESS_BELT(77),
    WEST_EAST_TO_NORTH_EXPRESS_BELT(84),
    WEST_NORTH_TO_SOUTH_EXPRESS_BELT(86),
    EAST_NORTH_TO_SOUTH_EXPRESS_BELT(75),
    WEST_EAST_TO_SOUTH_EXPRESS_BELT(82),
    WEST_SOUTH_TO_EAST_EXPRESS_BELT(78),
    WEST_NORTH_TO_EAST_EXPRESS_BELT(74),
    NORTH_SOUTH_TO_EAST_EXPRESS_BELT(81),
    EAST_SOUTH_TO_WEST_EXPRESS_BELT(76),
    EAST_NORTH_TO_WEST_EXPRESS_BELT(85),
    NORTH_SOUTH_TO_WEST_EXPRESS_BELT(83),

    ROTATE_PAD_LEFT(53),
    ROTATE_PAD_RIGHT(54);


    private final int id;

    TileID(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
