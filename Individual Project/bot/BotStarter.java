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
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import field.ShapeType;

import moves.MoveType;

/**
 * BotStarter class
 * 
 * This class is where the main logic should be. Implement getMoves() to
 * return something better than random moves.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */


// implementation of breadth first search for research purposes.

public class BotStarter {

	public BotStarter() {}
	
// rule based approach to the tetris game to drop specific shapes in correct places, will implement searches as we go.
	@SuppressWarnings("unchecked")
	public ArrayList<MoveType> getMoves(BotState state, long timeout) {
		
		ArrayList<MoveType> moves = new ArrayList<MoveType>();
		
		if(state.getCurrentShape() == ShapeType.O) {
	        moves.add(MoveType.RIGHT);
	        moves.add(MoveType.RIGHT);
	        moves.add(MoveType.RIGHT);
	        moves.add(MoveType.DROP);
	    }
	    if(state.getCurrentShape() == ShapeType.I) {
	        moves.add(MoveType.LEFT);
	        moves.add(MoveType.LEFT);
	        moves.add(MoveType.LEFT);
	        moves.add(MoveType.DROP);
	    }
	    else 
	        moves.add(MoveType.DROP);

	    return moves;

		
	}
	
	public static void main(String[] args)
	{
		BotParser parser = new BotParser(new BotStarter());
		parser.run();
	}
}
