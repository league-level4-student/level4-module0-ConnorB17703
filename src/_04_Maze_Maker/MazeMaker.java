package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int ranNum = randGen.nextInt(h);
		int ranNum2 = randGen.nextInt(w);
		Cell ranCell = new Cell(ranNum, ranNum2);
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(ranCell);
		
		// ***FIGURE OUT HOW TO DETERMINE THE START AND FINISH LINES OF THE MAZE***
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors= getUnvisitedNeighbors(currentCell);
		
		//C. if has unvisited neighbors,
		if(unvisitedNeighbors.size() > 0){
			//C1. select one at random.
			Random random = new Random();
			int ranNum = random.nextInt(unvisitedNeighbors.size());
			Cell rc = unvisitedNeighbors.get(ranNum);
			
			
			//C2. push it to the stack
			uncheckedCells.push(rc);
			//C3. remove the wall between the two cells
			removeWalls(rc, currentCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = rc;
			rc.hasBeenVisited();
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		else{
			//D1. if the stack is not empty
			if(uncheckedCells.size() > 0){
				// D1a. pop a cell from the stack
				
				// done in D1b
				
				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				
				selectNextPath(currentCell);
				
			}
		}
	}
		
	

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getY() == c2.getY()){
			if(c1.getX() < c2.getX()){
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			if(c1.getX() > c2.getX()){
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			
		}
		if(c1.getX() == c2.getX()){
			if(c1.getY() < c2.getY()){
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			if(c1.getY() > c2.getY()){
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
		
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
		Cell topCell = null;
		Cell bottomCell = null;
		Cell leftCell = null;
		Cell rightCell = null;
		
		if(c.getY() < height-1){
		topCell = maze.getCell(c.getX(), c.getY()+1);
				if(topCell.hasBeenVisited() == false){
					unvisited.add(topCell);
				}
		}
		
		if(c.getY() > 0){
		bottomCell = maze.getCell(c.getX(), c.getY()-1);
				if(bottomCell.hasBeenVisited() == false){
					unvisited.add(bottomCell);
				}
		}
		
		if(c.getX() > 0){
		leftCell = maze.getCell(c.getX()-1, c.getY());
				if(leftCell.hasBeenVisited() == false){
					unvisited.add(leftCell);
				}
		}
		
		if(c.getX() < width-1){
		rightCell = maze.getCell(c.getX()+1, c.getY());
				if(rightCell.hasBeenVisited() == false){
					unvisited.add(rightCell);
				}
		}
		
		
		return unvisited;
	}
}
