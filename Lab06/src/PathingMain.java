import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import processing.core.PApplet;
import processing.core.PImage;

public class PathingMain extends PApplet {
   private List<PImage> imgs;
   private int current_image;
   private long next_time;
   private PImage background;
   private PImage obstacle;
   private PImage goal;
   private List<Point> path;

   private static final int TILE_SIZE = 32;

   private static final int ANIMATION_TIME = 100;

   private GridValues[][] grid;
   private static final int ROWS = 15;
   private static final int COLS = 20;

   private static enum GridValues { BACKGROUND, OBSTACLE, GOAL, SEARCHED };

   private Point wPos;

   private boolean drawPath = false;

	public void settings() {
      size(640,480);
	}
	
	/* runs once to set up world */
   public void setup()    {

      path = new LinkedList<>();
      wPos = new Point(2, 2);
      imgs = new ArrayList<>();
      imgs.add(loadImage("Lab06/images/wyvern1.bmp"));
      imgs.add(loadImage("Lab06/images/wyvern2.bmp"));
      imgs.add(loadImage("Lab06/images/wyvern3.bmp"));

      background = loadImage("Lab06/images/grass.bmp");
      obstacle = loadImage("Lab06/images/vein.bmp");
      goal = loadImage("Lab06/images/water.bmp");

      grid = new GridValues[ROWS][COLS];
      initialize_grid(grid);

      current_image = 0;
      next_time = System.currentTimeMillis() + ANIMATION_TIME;
      noLoop();
      draw();
   }

	/* set up a 2D grid to represent the world */
   private static void initialize_grid(GridValues[][] grid)    {
      for (int row = 0; row < grid.length; row++)
      {
         for (int col = 0; col < grid[row].length; col++)      {
            grid[row][col] = GridValues.BACKGROUND;
         }
      }

		//set up some obstacles
      for (int row = 2; row < 8; row++)      {
         grid[row][row + 5] = GridValues.OBSTACLE;
      }

      for (int row = 8; row < 12; row++)       {
         grid[row][19 - row] = GridValues.OBSTACLE;
      }

      for (int col = 1; col < 8; col++)       {
         grid[11][col] = GridValues.OBSTACLE;
      }

      grid[14][14] = GridValues.GOAL;
   }

   private void next_image()    {
      current_image = (current_image + 1) % imgs.size();
   }

	/* runs over and over */
   public void draw()    {
      // A simplified action scheduling handler
      long time = System.currentTimeMillis();
      if (time >= next_time)       {
         next_image();
         next_time = time + ANIMATION_TIME;
      }

      draw_grid();
      draw_path();

      image(imgs.get(current_image), wPos.x * TILE_SIZE, wPos.y * TILE_SIZE);
   }

   private void draw_grid()    {
      for (int row = 0; row < grid.length; row++)
      {
         for (int col = 0; col < grid[row].length; col++)
         {
            draw_tile(row, col);
         }
      }
   }

   private void draw_path()    {
      if (drawPath)       {
         for (Point p : path)          {
            fill(128, 0, 0);
            rect(p.x * TILE_SIZE + TILE_SIZE * 3 / 8,
               p.y * TILE_SIZE + TILE_SIZE * 3 / 8,
               TILE_SIZE / 4, TILE_SIZE / 4);
         }
      }
   }

   private void draw_tile(int row, int col)    {
      switch (grid[row][col])       {
         case BACKGROUND:
            image(background, col * TILE_SIZE, row * TILE_SIZE);
            break;
         case OBSTACLE:
            image(obstacle, col * TILE_SIZE, row * TILE_SIZE);
            break;
         case SEARCHED:
            fill(0, 128);
            rect(col * TILE_SIZE + TILE_SIZE / 4,
               row * TILE_SIZE + TILE_SIZE / 4,
               TILE_SIZE / 2, TILE_SIZE / 2);
            break;
         case GOAL:
            image(goal, col * TILE_SIZE, row * TILE_SIZE);
            break;
      }
   }

   public static void main(String args[])    {
      PApplet.main("PathingMain");
   }

   public void keyPressed()    {
      if (key == ' ')       {
         System.out.println("space pressed");
			//clear out prior path
         path.clear();
			//example - replace with dfs
         if (depthFirstSearch(wPos, grid, path)){
            drawPath = true;
            redraw();
         }
      }
      if (key == 'p')       {
         System.out.println("p pressed");
         drawPath ^= true;
         redraw();
      }
   }

	/* replace the below with a depth first search 
		this code provided only as an example of moving in
		in one direction for one tile - it mostly is for illustrating
		how you might test the occupancy grid and add nodes to path!
	*/

   private boolean depthFirstSearch(Point current, GridValues[][] grid, List<Point> path){
      try {
         Thread.sleep(2);
      } catch (Exception e) {}

      //check if we reached the goal yet, if so return true, otherwise keep going
      if(grid[current.y][current.x] == GridValues.GOAL){
         return true;
      }

      grid[current.y][current.x] = GridValues.SEARCHED; //mark current point as searched

      //build a list of in bound neighbors in the order right, down, left up
      ArrayList<Point> neighbors = new ArrayList<>();
      if(withinBounds(new Point(current.x + 1, current.y), grid)){
         Point right = new Point(current.x + 1, current.y);
         neighbors.add(right);
      }
      if(withinBounds(new Point(current.x, current.y + 1), grid)){
         Point down = new Point(current.x, current.y + 1);
         neighbors.add(down);
      }
      if(withinBounds(new Point(current.x - 1, current.y), grid)){
         Point left = new  Point(current.x - 1, current.y);
         neighbors.add(left);
      }
      if(withinBounds( new Point(current.x, current.y -1), grid)){
         Point up =  new Point(current.x, current.y -1);
         neighbors.add(up);
      }

      //search the neighbor as long as it has not been searched and is not an obstacle
      for(Point neighbor : neighbors){
         if(grid[neighbor.y][neighbor.x] != GridValues.SEARCHED && grid[neighbor.y][neighbor.x] != GridValues.OBSTACLE){
            //if the goal is found, then backtrack and add path to the list, add to index zero is list is in order
            if(depthFirstSearch(neighbor, grid, path)){
               path.add(0, current);
               return true;
            }
         }
      }
      //return false if no path is found
      return false;
   }

   private boolean moveOnce(Point pos, GridValues[][] grid, List<Point> path)    {
      try {
         Thread.sleep(2);
      } catch (Exception e) {}
      redraw();

      Point rightN = new Point(pos.x +1, pos.y );
     
		//test if this is a valid grid cell 
		if (withinBounds(rightN, grid)  &&
         grid[rightN.y][rightN.x] != GridValues.OBSTACLE &&
         grid[rightN.y][rightN.x] != GridValues.SEARCHED)    {
			//check if my right neighbor is the goal
      	if (grid[rightN.y][rightN.x] == GridValues.GOAL) {
         	path.add(0, rightN);
         	return true;
      	}
			//set this value as searched
      	grid[rightN.y][rightN.x] = GridValues.SEARCHED;
      }
		return false;
   }

   private static boolean withinBounds(Point p, GridValues[][] grid)    {
      return p.y >= 0 && p.y < grid.length &&
         p.x >= 0 && p.x < grid[0].length;
   }
}
