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

package field;

import field.Cell;

/**
 * Field class
 * 
 * Represents the playing field for one player.
 * Has some basic methods already implemented.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

public class Field {
	private String fieldString;
	private int width;
	private int height;
	private Cell grid[][];

	public Field(int width, int height, String fieldString) {
		this.fieldString = fieldString;
		this.width = width;
		this.height = height;
	
		parse(fieldString);
	}
	
	/**
	 * Parses the input string to get a grid with Cell objects
	 * @param fieldString : input string
	 */
	private void parse(String fieldString) {
		
		this.grid = new Cell[this.width][this.height];
		
		// get the separate rows
		String[] rows = fieldString.split(";");
		for(int y=0; y < this.height; y++) {
			String[] rowCells = rows[y].split(",");
			
			// parse each cell of the row
			for(int x=0; x < this.width; x++) {
				int cellCode = Integer.parseInt(rowCells[x]);
				this.grid[x][y] = new Cell(x, y, CellType.values()[cellCode]);
			}
		}
	}
	
	public Cell getCell(int x, int y) {
		if(x < 0 || x >= this.width || y < 0 || y >= this.height)
			return null;
		return this.grid[x][y];
	}
	
	
	// added to count the total number of holes and as a move is carried out a check is done in the evaluation function to add a weight for every extra hole.
	
	public int getTotalHoles(){
		int holes = 0;
		
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				boolean fullCell = false;
				if (this.grid[i][j].isBlock()) {
					fullCell = true;
				}
				if(fullCell && this.grid[i][j].isEmpty()) {
				      holes += 1;
				}
			}
		}
	
		return holes;
	}
	
	// method to clone the field to lookahead for potential moves.
	public Field clone() {
		return new Field(this.height, this.width,this.fieldString);
		
	}
	
	
	// uses the isLine method to quickly go through each row in the field and check to see whether they are a line.if so incremement the line total.
	public int totalLines() {
		int lineTotal = 0;
		
		
		for(int i = 0; i < this.height; i++)
			if(this.isLine(i)) {
				lineTotal++;
				
			}
		return lineTotal;
		}
		
		
		
		
	
	
	// method added to detect whether a line is either empty or solid, if not then it is not a line.
	private Boolean isLine(int row) {
		
		for(int i = 0; i < this.width; i++) {
			if(this.grid[i][row].isEmpty()) {
				return false;
			}
			else if(this.grid[i][row].isSolid()) {
				return false;
			}
			
		}
		return true;
		
	}
	
	
	
	
	// weight for holes and landing height of shape taken from heuristics used to improve dellacherie's algorithm.
	public double evaluationFunction(Shape currentShape, int combos) {
		
		double landingHeight = 0;
		double heuristicScore = 0;
		int totalLines = this.totalLines();
		
		landingHeight = this.getHeight() - currentShape.getLocation().getY() - currentShape.getSize()/2;
		
		heuristicScore = landingHeight * -4.500158825082766 + this.getTotalHoles() * -7.899265427351652 + totalLines * combos * 4.4181268101392694;
		
		return heuristicScore;
		
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}

	
	
}
