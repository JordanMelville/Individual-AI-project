package moves;

import java.util.ArrayList;
import java.awt.Point;



// class created to deal with single moves, a single move is counted as the actual placing of the block rather than rotations.


public class Move {
	
	public ArrayList<MoveType> moves;
	public Point finalCoordinate;
	public double moveScore;

}
