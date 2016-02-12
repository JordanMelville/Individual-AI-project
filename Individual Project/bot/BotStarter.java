// Copyright 2015 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//	
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot;

import java.util.ArrayList;
import java.util.HashMap;

import field.Cell;
import field.Field;
import field.Shape;
import field.ShapeType;
import moves.Move;
import moves.MoveType;

/**
 * BotStarter class
 * 
 * This class is where the main logic should be. Implement getMoves() to return
 * something better than random moves.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

// implementation of breadth first search for research purposes.

public class BotStarter {

	public BotStarter() {
	}

	// rule based approach to the tetris game to drop specific shapes in correct
	// places, will implement searches as we go.
	@SuppressWarnings("unchecked")
	public ArrayList<MoveType> getMoves(BotState state, long timeout) {

		// initialization of the variables.
		ArrayList<MoveType> bestMoves = new ArrayList<MoveType>();
		ArrayList<Move> potentialMoves = new ArrayList<Move>();
		HashMap<ShapeType, Integer> shapeRotations;
		int leftMoveCount = 0;
		int comboCount = 0;
		int rotation = 0;
		int bestRotation = 0;
		int bestLeftMove = 0;
		double bestScore = 0.0;
		double currentScore;
		int leftMove = 0;

		// Grabbing the correct information required to make the best move
		// possible.
		Field field = state.getMyField();
		ShapeType currentPiece = state.getCurrentShape();

		// using the state to get the next shape coming up for potential
		// lookahead.
		ShapeType nextPiece = state.getNextShape();

		// use the current shape information to create said shapes.
		Shape shape = new Shape(currentPiece, field, state.getShapeLocation());
		Shape nextShape = new Shape(nextPiece, field, state.getShapeLocation());

		// trying to work out the best move for left at the current moment, will
		// do right shortly.
		bestMoves.add(MoveType.LEFT);
		bestMoves.add(MoveType.LEFT);
		bestMoves.add(MoveType.LEFT);
		bestMoves.add(MoveType.LEFT);
		bestMoves.add(MoveType.LEFT);
		
        // dealing with rotations
		if(state.getCurrentShape() == ShapeType.I) {
			rotation = 0;
		}
		else if(state.getCurrentShape() == ShapeType.J) {
			
			rotation = 3;
		}
		if(state.getCurrentShape() == ShapeType.L) {
			rotation = 3;
		}
		if(state.getCurrentShape() == ShapeType.O) {
			rotation = 0;
		}
		if(state.getCurrentShape() == ShapeType.S) {
			rotation = 1;
		}
		if(state.getCurrentShape() == ShapeType.T) {
			rotation = 3;
		}
		if(state.getCurrentShape() == ShapeType.Z) {
			rotation = 1;
		}
		
			for(; rotation > 0; rotation--) {
				bestMoves.add(MoveType.TURNRIGHT);
			}
		
		
		
		
		// calculate a score for the current move
		currentScore = field.evaluationFunction(shape, comboCount);
		
		
		

		// finally drop the piece in the desired location
		bestMoves.add(MoveType.DROP);

		return bestMoves;

	}

	public static void main(String[] args) {
		BotParser parser = new BotParser(new BotStarter());
		parser.run();
	}
}
