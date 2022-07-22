package Testing;

public class TestingConstants
{
    public static int[][][] ALL_COORDS = getAllCoords();
    
    private static int[][][] getAllCoords()
    {
        int[][][] allCoords = new int[100][100][2];
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                allCoords[i][j][0] = i;
                allCoords[i][j][1] = j;
            }
        }
        
        return allCoords;
    }
    
    public static final int[][][] FLEET1_POSITIONS = {
            {{1, 1}, {5, 1}},   // Carrier (B2-B6)
            {{2, 3}, {2, 6}},   // Battleship (D3-G3)
            {{8, 2}, {8, 4}},   // Cruiser 1 (C9-E9)
            {{6, 8}, {8, 8}},   // Cruiser 2 (I7-I9)
            {{0, 9}, {2, 9}},   // Submarine 1 (J1-J3)
            {{4, 5}, {4, 7}},   // Submarine 2 (F5-H5)
            {{5, 4}, {6, 4}},   // Destroyer 1 (E6-E7)
            {{7, 6}, {8, 6}},   // Destroyer 2 (G8-G9)
            {{7, 0}, {7, 1}}    // Destroyer 3 (A8-B8)
    };
    
    public static final int[][][] FLEET1_COORDS = {
            {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}},
            {{2, 3}, {2, 4}, {2, 5}, {2, 6}},
            {{8, 2}, {8, 3}, {8, 4}},
            {{6, 8}, {7, 8}, {8, 8}},
            {{0, 9}, {1, 9}, {2, 9}},
            {{4, 5}, {4, 6}, {4, 7}},
            {{5, 4}, {6, 4}},
            {{7, 6}, {8, 6}},
            {{7, 0}, {7, 1}}
    };
    
    public static final int[][][] FLEET2_POSITIONS = {
            {{1, 1}, {1, 5}},   // Carrier (B2-F2)
            {{3, 2}, {6, 2}},   // Battleship (B4-B7)
            {{0, 8}, {2, 8}},   // Cruiser 1 (I1-I3)
            {{8, 6}, {8, 8}},   // Cruiser 2 (G9-I9)
            {{2, 6}, {4, 6}},   // Submarine 1 (G3-G5)
            {{7, 3}, {7, 5}},   // Submarine 2 (D8-F8)
            {{5, 7}, {5, 8}},   // Destroyer 1 (H6-I6)
            {{8, 1}, {9, 1}},   // Destroyer 2 (B9-B10)
            {{9, 4}, {9, 5}}    // Destroyer 3 (E10-F10)
    };
    
    public static final int[][][] FLEET2_COORDS = {
            {{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}},
            {{3, 2}, {4, 2}, {5, 2}, {6, 2}},
            {{0, 8}, {1, 8}, {2, 8}},
            {{8, 6}, {8, 7}, {8, 8}},
            {{2, 6}, {3, 6}, {4, 6}},
            {{7, 3}, {7, 4}, {7, 5}},
            {{5, 7}, {5, 8}},
            {{8, 1}, {9, 1}},
            {{9, 4}, {9, 5}}
    };
    
    private static int[][][] getCoords(int[][][] positions)
    {
        // {{{x, y}
        // [ship][pos][coord]
        int[][][] allCoords = new int[9][][];
        
        for(int s = 0; s < positions.length; s++)
        {
            int[] start = positions[s][0];
            int[] end = positions[s][1];
            
            int i;
            int j;
            int len;
            // Same row
            if(start[0] == end[0])
            {
                len = (end[1] - start[1]) + 1;
                allCoords[s] = new int[len][2];
                i = start[0];
                for(j = start[1]; j <= end[1]; j++)
                {
                    allCoords[s][(j - start[1])][0] = i;
                    allCoords[s][(j - start[1])][1] = j;
                }
            }
            // Same col
            else
            {
                len = (end[0] - start[0]) + 1;
                allCoords[s] = new int[len][2];
                j = start[1];
                for(i = start[0]; i <= end[0]; i++)
                {
                    allCoords[s][(i - start[0])][0] = i;
                    allCoords[s][(i - start[0])][1] = j;
                }
            }
        }
        
        return allCoords;
    }
    
    
    public static final int[][][] PV_PLACED_SHIP_POSITIONS = {
            {{4, 0}, {4, 4}}, {{4, 5}, {4, 9}},
            {{5, 0}, {5, 4}}, {{5, 5}, {5, 9}},
            {{0, 4}, {4, 4}}, {{5, 4}, {9, 4}},
            {{0, 5}, {4, 5}}, {{5, 5}, {9, 5}},
            {{4, 2}, {4, 6}}, {{2, 4}, {6, 4}} // E3, E4, E5, E6, E7
            
    };
    
    public static final String[][] PV_TEST_SHIP_POSITIONS = {
            {"A1", "E1"}, {"F1", "J1"}, {"A10", "E10"}, {"F10", "J10"}, // Valid Horizontal         (0-3)
            {"A1", "A5"}, {"J1", "J5"}, {"A6", "A10"}, {"J6", "J10"},   // Valid Vertical           (4-7)
            {"E1", "A1"},                                               // Valid wrong order        (8)
            {"A1", "D1"}, {"A1", "A4"},                                 // Invalid Short            (9-10)
            {"A1", "F1"}, {"A1", "A6"},                                 // Invalid long             (11-12)
            {"A1", "E5"}                                                // Invalid diagonal         (13)
    };
    
    
    public static final String EXPECTED_EMPTY_FLEET_VIEW = """
            \t\t\t\t   Remaining Ships\t\t\t\t
            ====================================================
            \t\t My Fleet \t\t\t\t Enemy Fleet\t\t
            ------------------------\t------------------------
            Aircraft Carrier     (0)\tAircraft Carrier     (0)
            Battleship           (0)\tBattleship           (0)
            Cruiser              (0)\tCruiser              (0)
            Cruiser              (0)\tCruiser              (0)
            Submarine            (0)\tSubmarine            (0)
            Submarine            (0)\tSubmarine            (0)
            Destroyer            (0)\tDestroyer            (0)
            Destroyer            (0)\tDestroyer            (0)
            Destroyer            (0)\tDestroyer            (0)
            """;
    
    public static final String EXPECTED_FIRST_HIT_FLEET_VIEW = """
            \t\t\t\t   Remaining Ships\t\t\t\t
            ====================================================
            \t\t My Fleet \t\t\t\t Enemy Fleet\t\t
            ------------------------\t------------------------
            Aircraft Carrier     (1)\tAircraft Carrier     (0)
            Battleship           (0)\tBattleship           (0)
            Cruiser              (0)\tCruiser              (0)
            Cruiser              (0)\tCruiser              (0)
            Submarine            (0)\tSubmarine            (0)
            Submarine            (0)\tSubmarine            (0)
            Destroyer            (0)\tDestroyer            (0)
            Destroyer            (0)\tDestroyer            (0)
            Destroyer            (0)\tDestroyer            (0)
            """;
    
    public static final String EXPECTED_SUNK_SHIP_FLEET_VIEW = """
            \t\t\t\t   Remaining Ships\t\t\t\t
            ====================================================
            \t\t My Fleet \t\t\t\t Enemy Fleet\t\t
            ------------------------\t------------------------
            Aircraft Carrier(S)  (5)\tAircraft Carrier     (0)
            Battleship           (0)\tBattleship           (0)
            Cruiser              (0)\tCruiser              (0)
            Cruiser              (0)\tCruiser              (0)
            Submarine            (0)\tSubmarine            (0)
            Submarine            (0)\tSubmarine            (0)
            Destroyer            (0)\tDestroyer            (0)
            Destroyer            (0)\tDestroyer            (0)
            Destroyer            (0)\tDestroyer            (0)
            """;
    
    public static final String EXPECTED_SUNK_FLEET_VIEW = """
            \t\t\t\t   Remaining Ships\t\t\t\t
            ====================================================
            \t\t My Fleet \t\t\t\t Enemy Fleet\t\t
            ------------------------\t------------------------
            Aircraft Carrier(S)  (5)\tAircraft Carrier     (0)
            Battleship(S)        (4)\tBattleship           (0)
            Cruiser(S)           (3)\tCruiser              (0)
            Cruiser(S)           (3)\tCruiser              (0)
            Submarine(S)         (3)\tSubmarine            (0)
            Submarine(S)         (3)\tSubmarine            (0)
            Destroyer(S)         (2)\tDestroyer            (0)
            Destroyer(S)         (2)\tDestroyer            (0)
            Destroyer(S)         (2)\tDestroyer            (0)
            """;
    
    
    public static final String EXPECTED_EMPTY_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------\t -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - - 	  1  - - - - - - - - - -\s
              2  - - - - - - - - - - 	  2  - - - - - - - - - -\s
              3  - - - - - - - - - - 	  3  - - - - - - - - - -\s
              4  - - - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - - - - - - - - - - 	  5  - - - - - - - - - -\s
              6  - - - - - - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - - - - - - - 	  7  - - - - - - - - - -\s
              8  - - - - - - - - - - 	  8  - - - - - - - - - -\s
              9  - - - - - - - - - - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    
    public static final String EXPECTED_SINGLE_SHIP_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------\t -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - - 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - - 	  2  - - - - - - - - - -\s
              3  - A - - - - - - - - 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - - - - - - 	  5  - - - - - - - - - -\s
              6  - A - - - - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - - - - - - - 	  7  - - - - - - - - - -\s
              8  - - - - - - - - - - 	  8  - - - - - - - - - -\s
              9  - - - - - - - - - - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_ALL_SHIPS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_HIT_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - X - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_OPPONENT_HIT_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_BOTH_HIT_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - X - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_MISS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  O - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_OPPONENT_MISS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  O - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_BOTH_MISS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  O - - - - - - - - S 	  1  O - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_TWO_HITS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - X X - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_OPPONENT_TWO_HITS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - X - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_BOTH_TWO_HITS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - X X - - - - - - -\s
              3  - X - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_TWO_MISSES_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  O - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  O - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_OPPONENT_TWO_MISSES_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  O O - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_BOTH_TWO_MISSES_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  O O - - - - - - - S 	  1  O - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  O - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_HIT_AND_MISS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  O - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - X - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_OPPONENT_HIT_AND_MISS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  O - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_BOTH_SINGLE_HIT_AND_MISS_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  O - - - - - - - - S 	  1  O - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - X - - - - - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SINGLE_SUNK_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - A - - - - - - - S 	  2  - X X X X X - - - -\s
              3  - A - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - A - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - A - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - A - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_OPPONENT_SINGLE_SUNK_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - - - - - - - - - -\s
              3  - X - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - X - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - X - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - X - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_BOTH_SINGLE_SUNK_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - S 	  2  - X X X X X - - - -\s
              3  - X - B B B B - - S 	  3  - - - - - - - - - -\s
              4  - X - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - X - - - S S S - - 	  5  - - - - - - - - - -\s
              6  - X - - D - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - D - - - C - 	  7  - - - - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - - - - - - - -\s
              9  - - C C C - D - C - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    public static final String EXPECTED_SUNK_FLEET_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - S 	  1  - - - - - - - - X -\s
              2  - A - - - - - - - S 	  2  - X X X X X - - X -\s
              3  - A - B B B B - - S 	  3  - - - - - - X - X -\s
              4  - A - - - - - - - - 	  4  - - X - - - X - - -\s
              5  - A - - - S S S - - 	  5  - - X - - - X - - -\s
              6  - A - - D - - - - - 	  6  - - X - - - - X X -\s
              7  - - - - D - - - C - 	  7  - - X - - - - - - -\s
              8  D D - - - - D - C - 	  8  - - - X X X - - - -\s
              9  - - C C C - D - C - 	  9  - X - - - - X X X -\s
             10  - - - - - - - - - - 	 10  - X - - X X - - - -\s
            """;
    
    public static final String EXPECTED_OPPONENT_SUNK_FLEET_BOARD_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t
             -----------------------	 -----------------------
                 A B C D E F G H I J 	     A B C D E F G H I J\s
              1  - - - - - - - - - X 	  1  - - - - - - - - - -\s
              2  - X - - - - - - - X 	  2  - - - - - - - - - -\s
              3  - X - X X X X - - X 	  3  - - - - - - - - - -\s
              4  - X - - - - - - - - 	  4  - - - - - - - - - -\s
              5  - X - - - X X X - - 	  5  - - - - - - - - - -\s
              6  - X - - X - - - - - 	  6  - - - - - - - - - -\s
              7  - - - - X - - - X - 	  7  - - - - - - - - - -\s
              8  X X - - - - X - X - 	  8  - - - - - - - - - -\s
              9  - - X X X - X - X - 	  9  - - - - - - - - - -\s
             10  - - - - - - - - - - 	 10  - - - - - - - - - -\s
            """;
    
    
    public static final String EXPECTED_EMPTY_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_FIRST_MOVE_MISS_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_FIRST_MOVE_HIT_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.  A1-HB
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_FIRST_MOVE_SUNK_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.  A1-SB
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_SECOND_MOVE_MISS_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,   A1-M
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_SECOND_MOVE_HIT_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,  A1-HB
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_SECOND_MOVE_SUNK_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,  A1-SB
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_THIRD_MOVE_MISS_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,   A1-M
              2.   A2-M
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_THIRD_MOVE_HIT_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,   A1-M
              2.  A2-HB
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_THIRD_MOVE_SUNK_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,   A1-M
              2.  A2-SB
              """ + "\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t\n\t";
    
    public static final String EXPECTED_TWENTY_TWO_MOVES_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              1.   A1-M,   B1-M
              2.   C1-M,   D1-M
              3.   E1-M,   F1-M
              4.   G1-M,   H1-M
              5.   I1-M,   J1-M
              6.   A2-M,   B2-M
              7.   C2-M,   D2-M
              8.   E2-M,   F2-M
              9.   G2-M,   H2-M
             10.   I2-M,   J2-M
             11.   A3-M,   B3-M
            """;
    
    public static final String EXPECTED_TWENTY_THREE_MOVES_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              2.   C1-M,   D1-M
              3.   E1-M,   F1-M
              4.   G1-M,   H1-M
              5.   I1-M,   J1-M
              6.   A2-M,   B2-M
              7.   C2-M,   D2-M
              8.   E2-M,   F2-M
              9.   G2-M,   H2-M
             10.   I2-M,   J2-M
             11.   A3-M,   B3-M
             12.   C3-M
            """;
    
    public static final String EXPECTED_TWENTY_SEVEN_MOVES_HISTORY_VIEW = """
            \t\t\t Moves
              ===================
              4.   G1-M,   H1-M
              5.   I1-M,   J1-M
              6.   A2-M,   B2-M
              7.   C2-M,   D2-M
              8.   E2-M,   F2-M
              9.   G2-M,   H2-M
             10.   I2-M,   J2-M
             11.   A3-M,   B3-M
             12.   C3-M,   D3-M
             13.   E3-M,   F3-M
             14.   G3-M
            """;
    
    public static final String EXPECTED_EMPTY_PLAYER_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\t\t\t\t\t   Remaining Ships\t\t\t\t\t\t\t\t Moves
             -----------------------\t -----------------------\t====================================================\t  ===================
                 A B C D E F G H I J \t     A B C D E F G H I J \t\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\t
              1  - - - - - - - - - - \t  1  - - - - - - - - - - \t------------------------\t------------------------\t\t
              2  - - - - - - - - - - \t  2  - - - - - - - - - - \tAircraft Carrier     (0)\tAircraft Carrier     (0)\t\t
              3  - - - - - - - - - - \t  3  - - - - - - - - - - \tBattleship           (0)\tBattleship           (0)\t\t
              4  - - - - - - - - - - \t  4  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              5  - - - - - - - - - - \t  5  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              6  - - - - - - - - - - \t  6  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              7  - - - - - - - - - - \t  7  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              8  - - - - - - - - - - \t  8  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
              9  - - - - - - - - - - \t  9  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             10  - - - - - - - - - - \t 10  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
            """;
    
    public static final String EXPECTED_SINGLE_SHIP_PLAYER_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\t\t\t\t\t   Remaining Ships\t\t\t\t\t\t\t\t Moves
             -----------------------\t -----------------------\t====================================================\t  ===================
                 A B C D E F G H I J \t     A B C D E F G H I J \t\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\t
              1  - - - - - - - - - - \t  1  - - - - - - - - - - \t------------------------\t------------------------\t\t
              2  - A - - - - - - - - \t  2  - - - - - - - - - - \tAircraft Carrier     (0)\tAircraft Carrier     (0)\t\t
              3  - A - - - - - - - - \t  3  - - - - - - - - - - \tBattleship           (0)\tBattleship           (0)\t\t
              4  - A - - - - - - - - \t  4  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              5  - A - - - - - - - - \t  5  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              6  - A - - - - - - - - \t  6  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              7  - - - - - - - - - - \t  7  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              8  - - - - - - - - - - \t  8  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
              9  - - - - - - - - - - \t  9  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             10  - - - - - - - - - - \t 10  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
            """;
    
    public static final String EXPECTED_FULL_FLEET_PLAYER_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\t\t\t\t\t   Remaining Ships\t\t\t\t\t\t\t\t Moves
             -----------------------\t -----------------------\t====================================================\t  ===================
                 A B C D E F G H I J \t     A B C D E F G H I J \t\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\t
              1  - - - - - - - - - S \t  1  - - - - - - - - - - \t------------------------\t------------------------\t\t
              2  - A - - - - - - - S \t  2  - - - - - - - - - - \tAircraft Carrier     (0)\tAircraft Carrier     (0)\t\t
              3  - A - B B B B - - S \t  3  - - - - - - - - - - \tBattleship           (0)\tBattleship           (0)\t\t
              4  - A - - - - - - - - \t  4  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              5  - A - - - S S S - - \t  5  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              6  - A - - D - - - - - \t  6  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              7  - - - - D - - - C - \t  7  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              8  D D - - - - D - C - \t  8  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
              9  - - C C C - D - C - \t  9  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             10  - - - - - - - - - - \t 10  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             """;
    
    public static final String EXPECTED_SINGLE_MISS_PLAYER_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\t\t\t\t\t   Remaining Ships\t\t\t\t\t\t\t\t Moves
             -----------------------\t -----------------------\t====================================================\t  ===================
                 A B C D E F G H I J \t     A B C D E F G H I J \t\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\t   1.   A1-M
              1  - - - - - - - - - S \t  1  O - - - - - - - - - \t------------------------\t------------------------\t\t
              2  - A - - - - - - - S \t  2  - - - - - - - - - - \tAircraft Carrier     (0)\tAircraft Carrier     (0)\t\t
              3  - A - B B B B - - S \t  3  - - - - - - - - - - \tBattleship           (0)\tBattleship           (0)\t\t
              4  - A - - - - - - - - \t  4  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              5  - A - - - S S S - - \t  5  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              6  - A - - D - - - - - \t  6  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              7  - - - - D - - - C - \t  7  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              8  D D - - - - D - C - \t  8  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
              9  - - C C C - D - C - \t  9  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             10  - - - - - - - - - - \t 10  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             """;
    
    public static final String EXPECTED_SINGLE_HIT_PLAYER_VIEW = """
            \t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\t\t\t\t\t   Remaining Ships\t\t\t\t\t\t\t\t Moves
             -----------------------\t -----------------------\t====================================================\t  ===================
                 A B C D E F G H I J \t     A B C D E F G H I J \t\t\t My Fleet \t\t\t\t Enemy Fleet\t\t\t   1.  A1-HB
              1  - - - - - - - - - S \t  1  - - - - - - - - - - \t------------------------\t------------------------\t\t
              2  - A - - - - - - - S \t  2  - - - - - - - - - - \tAircraft Carrier     (0)\tAircraft Carrier     (0)\t\t
              3  - A - B B B B - - S \t  3  - - - - - - - - - - \tBattleship           (0)\tBattleship           (0)\t\t
              4  - A - - - - - - - - \t  4  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              5  - A - - - S S S - - \t  5  - - - - - - - - - - \tCruiser              (0)\tCruiser              (0)\t\t
              6  - A - - D - - - - - \t  6  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              7  - - - - D - - - C - \t  7  - - - - - - - - - - \tSubmarine            (0)\tSubmarine            (0)\t\t
              8  D D - - - - D - C - \t  8  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
              9  - - C C C - D - C - \t  9  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             10  - - - - - - - - - - \t 10  - - - - - - - - - - \tDestroyer            (0)\tDestroyer            (0)\t\t
             """;
}