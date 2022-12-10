public class Tetris {
	private Grid grid;
	private Location heartLoc, bombLoc1, bombLoc2, bombLoc3;
	private boolean hitTop, isReady, go;
	private boolean isGameStart = true;
	private int chooseShape, nextShape, shapeLength, shapeWidth;
	private int msElapsed, speed;
	private int lives, score;
	private int heartCount = 0;
	private int bomb1Count = 0;
	private int bomb2Count = 0;
	private int bomb3Count = 0;
	private String direction, currentShapeName;
	private String dropShape = "";
	private String line = "line";
	private String j = "j";
	private String l = "l";
	private String square = "square";
	private String s = "s";
	private String z = "z";
	private String t = "t";
	private String[] currentShapeArr;
	private String[] lineIMG = {"lineBlock.png", "lineBlock4.png"};
	private String[] jIMG = {"jBlock.png", "jBlock4.png"};
	private String[] lIMG = {"lBlock.png", "lBlock4.png"};
	private String[] squareIMG = {"squareBlock.png", "squareBlock4.png"};
	private String[] sIMG = {"sBlock.png", "sBlock4.png"};
	private String[] zIMG = {"zBlock.png", "zBlock4.png"};
	private String[] tIMG = {"tBlock.png", "tBlock4.png"};
	private String[] clearBlock = {"redBlock.png", "orangeBlock.png", "yellowBlock.png", "greenBlock.png", "lBlueBlock.png", "blueBlock.png", "purpleBlock.png", "redBlock.png", "orangeBlock.png", "yellowBlock.png"};
	private String[] miscBlocks = {"finalBlock.png", "headerBlock.png", "holderBlock.png", "shadeBlock.png"};
	
	Location down, leftDown, left2Down, left3Down;
	Location left, left2, left3, leftUp;
	Location right, right2, rightUp;
	Location up, up2, up3;
	String downImage, leftDownImage, left2DownImage, left3DownImage;
	String leftImage, leftUpImage, left2Image, left3Image;
	String rightImage, rightUpImage, right2Image, rightDownImage, right2DownImage;
	String upImage, up2Image, up3Image;
	boolean downValid;
	boolean leftValid, left2Valid, leftUpValid, leftDownValid, left2DownValid, left3DownValid;
	boolean rightValid, rightUpValid, right2Valid, rightDownValid, right2DownValid;
	boolean upValid;

	public Tetris()
	{
		grid = new Grid(21, 10);
		msElapsed = 0;
		lives = 1;
	}

	public void play()
	{
		//running = true;
		
		while (!isGameOver())
		{
			titleScreen();
			Grid.pause(100);
			populateHeader();
			handleKeyPress(currentShapeName, currentShapeArr);

			increaseSpeed();
			if (msElapsed % speed == 0) {
				if(!isGameStart) {
					moveDown(currentShapeName, currentShapeArr);
				}

			}

			if(msElapsed % 300 == 0) {
				spawnhearts();
				spawnBomb1();
				spawnBomb2();
				spawnBomb3();
			}

			if(isReady) {
				populateTopEdge();
				isReady = false;
			}

			updateTitle();
			msElapsed += 100;
		}
		grid.showMessageDialog("Game Over!" + "\n" + "Your Score Is: " + score + "\n" + "Thanks for playing!");
	}

	public void populateHeader() {
		String nextImage;
		if(nextShape == 1) {
			nextImage = "lineBlock.png";
		} else if(nextShape == 2) {
			nextImage = "lBlock.png";
		} else if(nextShape == 3) {
			nextImage = "jBlock.png";
		} else if(nextShape == 7) {
			nextImage = "squareBlock.png";
		} else if(nextShape == 5) {
			nextImage = "sBlock.png";
		} else if(nextShape == 4) {
			nextImage = "zBlock.png";
		} else {
			nextImage = "tBlock.png";
		}

		grid.setImage(new Location(0, 0), nextImage);

		for(int col = 1; col < grid.getNumCols() - 3; col ++) {
			grid.setImage(new Location(0, col), miscBlocks[1]);
		}

		if(lives >= 3) {
			grid.setImage(new Location(0, grid.getNumCols() - 3), "heart.png");
			grid.setImage(new Location(0, grid.getNumCols() - 2), "heart.png");
			grid.setImage(new Location(0, grid.getNumCols() - 1), "heart.png");
		}
		if(lives == 2) {
			grid.setImage(new Location(0, grid.getNumCols() - 3), miscBlocks[1]);
			grid.setImage(new Location(0, grid.getNumCols() - 2), "heart.png");
			grid.setImage(new Location(0, grid.getNumCols() - 1), "heart.png");
		}
		if(lives == 1) {
			grid.setImage(new Location(0, grid.getNumCols() - 3), miscBlocks[1]);
			grid.setImage(new Location(0, grid.getNumCols() - 2), miscBlocks[1]);
			grid.setImage(new Location(0, grid.getNumCols() - 1), "heart.png");
		}
	} 

	public boolean handleMouseClick() {
		go = false;
		Location clicked = grid.checkLastLocationClicked();
		if(clicked != null) {
			go = true;
		} else {
			go = false;
		}
		return go;
	}

	public void handleKeyPress(String shapeName, String[] shape)
	{

		int key = grid.checkLastKeyPressed();
		if(key == 37) {
			if(isColEmpty(0) && isOkayToMoveLeft()) {
				moveLeft();
			}
		}

		if(key == 40) {
			moveDown(shapeName, shape);
		}

		if(key == 38) {
			flipShape(shapeName, shape);
			checkheart(heartLoc);
			checkBomb1(bombLoc1);
			checkBomb2(bombLoc2);
			checkBomb3(bombLoc3);
		}

		if(key == 39) {
			if(isColEmpty(grid.getNumCols() - 1) && isOkayToMoveRight()) {
				moveRight();

			}
		}
	}

	public void titleScreen() {
		while(isGameStart) {
			//letter r
			int row = grid.getNumRows() - 5;
			int col = 1;
			grid.setImage(new Location(row, col), miscBlocks[0]);
			grid.setImage(new Location(row, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col - 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col - 1), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col - 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col - 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 2), miscBlocks[0]);

			//letter i
			col = 4;
			grid.setImage(new Location(row, col), miscBlocks[0]);
			grid.setImage(new Location(row, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 2), miscBlocks[0]);

			//letter s
			col  = 7;
			grid.setImage(new Location(row, col), miscBlocks[0]);
			grid.setImage(new Location(row, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col), miscBlocks[0]);

			//letter t
			row = grid.getNumRows() - 10;
			col = 0;
			grid.setImage(new Location(row, col), miscBlocks[0]);
			grid.setImage(new Location(row, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 1), miscBlocks[0]);

			//letter e
			row = grid.getNumRows() - 10;
			col = 3;
			grid.setImage(new Location(row, col), miscBlocks[0]);
			grid.setImage(new Location(row, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row, col + 3), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 2), miscBlocks[0]);		
			grid.setImage(new Location(row + 4, col + 3), miscBlocks[0]);	

			//letter t
			row = grid.getNumRows() - 10;
			col = 7;
			grid.setImage(new Location(row, col), miscBlocks[0]);
			grid.setImage(new Location(row, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row, col + 2), miscBlocks[0]);
			grid.setImage(new Location(row + 1, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 2, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 3, col + 1), miscBlocks[0]);
			grid.setImage(new Location(row + 4, col + 1), miscBlocks[0]);

			for(row = grid.getNumRows() - 10; row < grid.getNumRows() - 5; row ++) {
				for(col = 0; col < grid.getNumCols(); col ++) {
					String image = grid.getImage(new Location(row, col));
					String image2 = grid.getImage(new Location(row + 5, col));
					grid.setImage(new Location(row, col), clearBlock[col]);
					grid.setImage(new Location(row + 5, col), clearBlock[col]);
					Grid.pause(100);
					if(image != null && image.equals(miscBlocks[0])) {
						if(col < 3) {
							grid.setImage(new Location(row, col), clearBlock[1]);
						} else if(col > 6) {
							grid.setImage(new Location(row, col), clearBlock[3]);
						} else {
							grid.setImage(new Location(row, col), clearBlock[2]);
						}

					} else {
						grid.setImage(new Location(row, col), null);
					}
					if(image2 != null && image2.equals(miscBlocks[0])) {
						if(col < 4) {
							grid.setImage(new Location(row + 5, col), clearBlock[5]);
						} else if(col > 6) {
							grid.setImage(new Location(row + 5, col), clearBlock[6]);
						} else {
							grid.setImage(new Location(row + 5, col), clearBlock[4]);
						}
					} else {
						grid.setImage(new Location(row + 5, col), null);
					}
				}
			}

			int i = 100;
			while(i > 0) {
				row = 2;
				if(i % 2 == 0) {
					col = 3;
				} else {
					col = 4;
				}

				grid.setImage(new Location(row, col), clearBlock[0]);
				grid.setImage(new Location(row, col + 1), clearBlock[0]);
				grid.setImage(new Location(row + 1, col), clearBlock[0]);
				grid.setImage(new Location(row + 1, col + 1), clearBlock[0]);
				grid.setImage(new Location(row + 1, col + 2), clearBlock[0]);
				grid.setImage(new Location(row + 2, col), clearBlock[0]);
				grid.setImage(new Location(row + 2, col + 1), clearBlock[0]);
				grid.setImage(new Location(row + 2, col + 2), clearBlock[0]);
				grid.setImage(new Location(row + 2, col + 3), clearBlock[0]);
				grid.setImage(new Location(row + 3, col), clearBlock[0]);
				grid.setImage(new Location(row + 3, col + 1), clearBlock[0]);
				grid.setImage(new Location(row + 3, col + 2), clearBlock[0]);
				grid.setImage(new Location(row + 3, col + 3), miscBlocks[3]);
				grid.setImage(new Location(row + 4, col), clearBlock[0]);
				grid.setImage(new Location(row + 4, col + 1), clearBlock[0]);
				grid.setImage(new Location(row + 4, col + 2), miscBlocks[3]);
				grid.setImage(new Location(row + 5, col), miscBlocks[3]);
				grid.setImage(new Location(row + 5, col + 1), miscBlocks[3]);	
				Grid.pause(300);
				grid.setImage(new Location(row, col), null);
				grid.setImage(new Location(row, col + 1), null);
				grid.setImage(new Location(row + 1, col), null);
				grid.setImage(new Location(row + 1, col + 1), null);
				grid.setImage(new Location(row + 1, col + 2), null);
				grid.setImage(new Location(row + 2, col), null);
				grid.setImage(new Location(row + 2, col + 1), null);
				grid.setImage(new Location(row + 2, col + 2), null);
				grid.setImage(new Location(row + 2, col + 3), null);
				grid.setImage(new Location(row + 3, col), null);
				grid.setImage(new Location(row + 3, col + 1), null);
				grid.setImage(new Location(row + 3, col + 2), null);
				grid.setImage(new Location(row + 3, col + 3), null);
				grid.setImage(new Location(row + 4, col), null);
				grid.setImage(new Location(row + 4, col + 1), null);
				grid.setImage(new Location(row + 4, col + 2), null);
				grid.setImage(new Location(row + 5, col), null);
				grid.setImage(new Location(row + 5, col + 1), null);
				i --;		
				handleMouseClick();
				if(go) {
					for(int a = 1; a < grid.getNumRows(); a ++) {
						for(int b = 0; b < grid.getNumCols(); b ++) {
							grid.setImage(new Location(a, b), null);
						}
					}
					i = 0;
					isGameStart = false;
					isReady = true;
					populateTopEdge();
					isReady = false;
				}
			}
		}
	}
		
	public void increaseSpeed() {
		if(score < 5000) {
			speed = 500;
		} else if(score < 10000) {
			speed = 400;
		} else if(score < 15000) {
			speed = 300;
		} else if(score < 20000) {
			speed = 200;
		} else {
			speed = 100;
		}
	}

	public void populateTopEdge()
	{
		if(score == 0) {
			chooseShape = (int)(Math.random() * 7) + 1;
		} else {
			chooseShape = nextShape;
		}
		
		if(chooseShape == 1) {
			dropShape = line;
			currentShapeArr = lineIMG;
			shapeLength = 4;
			shapeWidth = 1;
		}
		if(chooseShape == 2) {
			dropShape = l;
			currentShapeArr = lIMG;
			shapeLength = 3;
			shapeWidth = 2;
		}
		if(chooseShape == 3) {
			dropShape = j;
			currentShapeArr = jIMG;
			shapeLength = 3;
			shapeWidth = 2;
		}
		if(chooseShape == 7) {
			dropShape = square;
			currentShapeArr = squareIMG;
			shapeLength = 2;
			shapeWidth = 2;
		}
		if(chooseShape == 5) {
			dropShape = s;
			currentShapeArr = sIMG;
			shapeLength = 3;
			shapeWidth = 2;
		}
		if(chooseShape == 4) {
			dropShape = z;
			currentShapeArr = zIMG;
			shapeLength = 3;
			shapeWidth = 2;
		}
		if(chooseShape == 6) {
			dropShape = t;
			currentShapeArr = tIMG;
			shapeLength = 3;
			shapeWidth = 2;
		}

		currentShapeName = dropShape;
		nextShape = (int)(Math.random() * 7) + 1;

		int row = 1;	
		int col = 4;
		int orientation = (int)(Math.random() * 2);
		direction = "";

		decideOrientation(orientation, row, col, currentShapeArr, shapeLength, shapeWidth);
	}

	public void decideOrientation(int orientation, int row, int col, String[] shape, int shapeLength, int shapeWidth) {
		if(orientation == 0) {
			if(col > grid.getNumCols() - shapeLength) {
				direction = "left";
			} else if(col < shapeLength - 1) {
				direction = "right";
			} else {
				int choose = (int)(Math.random() * 2);
				if(choose == 0) {
					direction = "left";
				} else {
					direction = "right";
				}
			}
		} else if(orientation == 1){
			if(col < shapeWidth - 1) {
				direction = "up";
			} else if(col > grid.getNumCols() - shapeWidth) {
				direction = "down";
			} else {
				int choose = (int)(Math.random() * 2);
				if(choose == 0) {
					direction = "up";
				} else {
					direction = "down";
				}
			}
		} 
		startingOrientation(row, col, shape);
	}
	
	public void startingOrientation(int row, int col, String[] shape) {
		if(shape == lineIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 2, col), shape[0]);
				grid.setImage(new Location(row + 3, col), shape[1]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row, col + 2), shape[0]);
				grid.setImage(new Location(row, col + 3), shape[1]);
			} 

		} else if(shape == lIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 2, col), shape[0]);
				grid.setImage(new Location(row + 2, col + 1), shape[1]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[0]);
				grid.setImage(new Location(row + 2, col + 1), shape[1]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row, col + 2), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[1]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[1]);
				grid.setImage(new Location(row + 1, col - 1), shape[0]);
				grid.setImage(new Location(row + 1, col - 2), shape[0]);
			}
		} else if(shape == jIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 2, col), shape[1]);
				grid.setImage(new Location(row + 2, col - 1), shape[0]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col - 1), shape[0]);
				grid.setImage(new Location(row + 1, col - 1), shape[0]);
				grid.setImage(new Location(row + 2, col - 1), shape[1]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[0]);
				grid.setImage(new Location(row + 1, col + 2), shape[1]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row, col + 2), shape[0]);
				grid.setImage(new Location(row + 1, col + 2), shape[1]);
			}
		} else if(shape == sIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col - 1), shape[0]);
				grid.setImage(new Location(row + 1, col - 1), shape[1]);
				grid.setImage(new Location(row + 1, col - 2), shape[0]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[0]);
				grid.setImage(new Location(row + 2, col + 1), shape[1]);
			}
		} else if(shape == zIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[0]);
				grid.setImage(new Location(row + 1, col + 2), shape[1]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 1, col - 1), shape[0]);
				grid.setImage(new Location(row + 2, col - 1), shape[1]);
			} 
		} else if(shape == tIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col - 1), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[1]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col - 1), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[1]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[0]);
				grid.setImage(new Location(row + 2, col), shape[1]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 2, col), shape[1]);
				grid.setImage(new Location(row + 1, col - 1), shape[0]);
			}
		} else if(shape == squareIMG) {
			if(direction.equals("up") || direction.equals("right") || direction.equals("left") || direction.equals("down")) {
				grid.setImage(new Location(row, col), shape[0]);
				grid.setImage(new Location(row, col + 1), shape[0]);
				grid.setImage(new Location(row + 1, col), shape[0]);
				grid.setImage(new Location(row + 1, col + 1), shape[1]);	
			}
		}
	}
	
	public void locationsAndImages(int row, int col) {
		if(col > 0) {
			leftValid = true;	
			Location left = new Location(row, col - 1);
			leftImage = grid.getImage(left);
		}

		if(col > 1) {
			left2Valid = true;	
			Location left2 = new Location(row, col - 2);
			left2Image = grid.getImage(left2);
		}

		if(col > 0 && row > 1) {
			leftUpValid = true;	
			Location leftUp = new Location(row - 1, col - 1);
			leftUpImage = grid.getImage(leftUp);
		}

		if(col < grid.getNumCols() - 1) {
			rightValid = true;	
			Location right = new Location(row, col + 1);
			rightImage = grid.getImage(right);
		}

		if(col < grid.getNumCols() - 1 && row > 1) {
			rightUpValid = true;	
			Location rightUp = new Location(row - 1, col + 1);
			rightUpImage = grid.getImage(rightUp);
		}

		if(col < grid.getNumCols() - 2) {
			right2Valid = true;	
			Location right2 = new Location(row, col + 2);
			right2Image = grid.getImage(right2);
		}

		if(row < grid.getNumRows() - 1) {
			downValid = true;			
			Location down = new Location(row + 1, col);
			downImage = grid.getImage(down);
		}

		if(row > 1) {
			upValid = true;
			Location up = new Location(row - 1, col);
			upImage = grid.getImage(up);
		}

		if(row < grid.getNumRows() - 1 && col > 0) {
			leftDownValid = true;
			Location leftDown = new Location(row +1, col - 1);
			leftDownImage = grid.getImage(leftDown);
		}

		if(row < grid.getNumRows() - 1 && col > 1) {
			left2DownValid = true;
			Location left2Down = new Location(row + 1, col - 2);
			left2DownImage = grid.getImage(left2Down);
		}

		if(row < grid.getNumRows() - 1 && col > 2) {
			left3DownValid = true;
			Location left3Down = new Location(row + 1, col - 3);
			left3DownImage = grid.getImage(left3Down);
		}

		if(row < grid.getNumRows() - 1 && col < grid.getNumCols() - 1) {
			rightDownValid = true;
			Location rightDown = new Location(row + 1, col + 1);
			rightDownImage = grid.getImage(rightDown);
		}

		if(row < grid.getNumRows() - 1 && col < grid.getNumCols() - 2) {
			right2DownValid = true;
			Location right2Down = new Location(row + 1, col + 2);
			right2DownImage = grid.getImage(right2Down);
		}
	}
	
	public void moveDown(String shapeName, String[] shape) {
		for(int row = grid.getNumRows() - 1; row >= 1; row --) {
			for(int col = grid.getNumCols() - 1; col >= 0; col --) {
				String image = grid.getImage(new Location(row, col));
				locationsAndImages(row, col);

				if(image != null && !image.equals(miscBlocks[0])) {
					if(direction.equals("right") || direction.equals("left")) {
						if(shapeName.equals(line) && image.equals("lineBlock4.png")) {
							landHorizontalUpdate(row, col, line, lineIMG);
						} else if(shapeName.equals(l) && image.equals("lBlock4.png")) {
							landHorizontalUpdate(row, col, l, lIMG);
						} else if(shapeName.equals(j) && image.equals("jBlock4.png")) {
							landHorizontalUpdate(row, col, j, jIMG);
						} else if(shapeName.equals(s) && image.equals("sBlock4.png")) {
							landHorizontalUpdate(row, col, s, sIMG);
						} else if(shapeName.equals(t) && image.equals("tBlock4.png")) {
							landHorizontalUpdate(row, col, t, tIMG);
						} else if(shapeName.equals(z) && image.equals("zBlock4.png")) {
							landHorizontalUpdate(row, col, z, zIMG);
						} else if(shapeName.equals(square) && image.equals("squareBlock4.png")) {
							landHorizontalUpdate(row, col, square, squareIMG);
						}
					} else if(direction.equals("up") || direction.equals("down")) {
						if(shapeName.equals(line) && image.equals("lineBlock4.png")) {
							landVerticalUpdate(row, col, line, lineIMG);
						} else if(shapeName.equals(l) && image.equals("lBlock4.png")) {
							landVerticalUpdate(row, col, l, lIMG);
						} else if(shapeName.equals(j) && image.equals("jBlock4.png")) {
							landVerticalUpdate(row, col, j, jIMG);
						} else if(shapeName.equals(s) && image.equals("sBlock4.png")) {
							landVerticalUpdate(row, col, s, sIMG);
						} else if(shapeName.equals(t) && image.equals("tBlock4.png")) {
							landVerticalUpdate(row, col, t, tIMG);
						} else if(shapeName.equals(z) && image.equals("zBlock4.png")) {
							landVerticalUpdate(row, col, z, zIMG);
						} else if(shapeName.equals(square) && image.equals("squareBlock4.png")) {
							landVerticalUpdate(row, col, square, squareIMG);
						}
					}
				}
			}
		}
	}

	public boolean isColEmpty(int col) {
		int blockCounter = 0;
		for(int row = 1; row < grid.getNumRows(); row ++) {
			String image = grid.getImage(new Location(row, col));
			if(image != null && !image.equals(miscBlocks[0]) && !image.equals("heart.gif") && !image.equals("bomb.gif")) {
				blockCounter ++;
			}
		}
		if(blockCounter == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOkayToMoveRight() {
		for(int row = 1; row < grid.getNumRows(); row ++) {
			for(int col = 0; col < grid.getNumCols(); col ++) {
				String image = grid.getImage(new Location(row, col));
				String nextImage = "";
				boolean nextImageIsValid = false;
				if(col < grid.getNumCols() - 1) {
					nextImage = grid.getImage(new Location(row, col + 1));
					if(nextImage != null) {
						nextImageIsValid = true;
					}
				} if(col < grid.getNumCols() - 1 && image != null && !image.equals(miscBlocks[0]) && !image.equals("heart.gif") &&!image.equals("bomb.gif")) {
					if(nextImageIsValid && nextImage.equals(miscBlocks[0])) {
						return false;
					} 
				} 
			}
		}
		return true;
	}

	public void moveRight() {
		for(int row = grid.getNumRows() - 1; row >= 1; row --) {
			for(int col = grid.getNumCols() - 1; col >= 0; col --) {
				String image = grid.getImage(new Location(row, col));
				String nextImage = "";
				boolean nextImageIsValid = false;
				if(col < grid.getNumCols() - 1) {
					nextImage = grid.getImage(new Location(row, col + 1));
					nextImageIsValid = true;
				}
				if(col < grid.getNumCols() - 1 && image != null && !image.equals(miscBlocks[0]) && !image.equals("heart.gif") &&!image.equals("bomb.gif")) {
					if(nextImageIsValid && (nextImage == null || nextImage.equals("heart.gif") || nextImage.equals("bomb.gif"))) {
						grid.setImage(new Location(row, col + 1), image);
						grid.setImage(new Location(row, col), null);
					}
				}
			}
		}
		checkheart(heartLoc);
		checkBomb1(bombLoc1);
		checkBomb2(bombLoc2);
		checkBomb3(bombLoc3);
	}
	
	public boolean isOkayToMoveLeft() {
		for(int row = 1; row < grid.getNumRows(); row ++) {
			for(int col = 0; col < grid.getNumCols(); col ++) {
				String image = grid.getImage(new Location(row, col));
				String prevImage = "";
				boolean prevImageIsValid = false;
				if(col > 0) {
					prevImage = grid.getImage(new Location(row, col - 1));
					if(prevImage != null) {
						prevImageIsValid = true;
					}
				}
				if(col > 0 && image != null && !image.equals(miscBlocks[0]) && !image.equals("heart.gif") && !image.equals("bomb.gif")) {
					if(prevImageIsValid && prevImage.equals(miscBlocks[0])) {
						return false;
					} 
				} 
			}
		}
		return true;
	}

	public void moveLeft() {
		for(int row = 1; row < grid.getNumRows(); row ++) {
			for(int col = 0; col < grid.getNumCols(); col ++) {
				String image = grid.getImage(new Location(row, col));
				String prevImage = "";
				boolean prevImageIsValid = false;
				if(col > 0) {
					prevImage = grid.getImage(new Location(row, col - 1));
					prevImageIsValid = true;
				}
				if(col > 0 && image != null && !image.equals(miscBlocks[0]) && !image.equals("heart.gif") && !image.equals("bomb.gif")) {
					if(prevImageIsValid && (prevImage == null || prevImage.equals("heart.gif") || prevImage.equals("bomb.gif"))) {
						grid.setImage(new Location(row, col), null);
						grid.setImage(new Location(row, col - 1), image);
					}
				}
			}
		}
		checkheart(heartLoc);
		checkBomb1(bombLoc1);
		checkBomb2(bombLoc2);
		checkBomb3(bombLoc3);
	}

	public void flipShape(String shapeName, String[] shape) {
		String changeDirection = "";
		for(int row = 1; row < grid.getNumRows(); row ++) {
			for(int col = 0; col < grid.getNumCols(); col ++) {
				String image = grid.getImage(new Location(row, col));
				if(image != null && image.equals(shape[1])) {
					holderBlock(row, col, shape);
					if(direction.equals("right")) {
						changeDirection = "down";
					} else if(direction.equals("left")) {
						changeDirection = "up";
					} else if(direction.equals("down")){
						changeDirection = "left";
					} else if(direction.equals("up")) {
						changeDirection = "right";
					}
					direction = changeDirection;
					flipOrientation(row, col, shape);
					for(int r = 1; r < grid.getNumRows(); r++) {
						for(int c = 0; c < grid.getNumCols(); c ++) {
							if(grid.getImage(new Location(r, c)) != null && grid.getImage(new Location(r, c)).equals(miscBlocks[2])) {
								grid.setImage(new Location(r, c), null);
							}
						}
					}
					col = grid.getNumCols() - 1;
					row = grid.getNumRows() - 1;						
				}
			}
		}
	}

	public void holderBlock(int row, int col, String[] shape) {
		if(shape == lineIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row - 3, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row, col), miscBlocks[2]);
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row, col - 2), miscBlocks[2]);
				grid.setImage(new Location(row, col - 3), miscBlocks[2]);
			} 

		} else if(shape == lIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col - 1), miscBlocks[2]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col - 1), miscBlocks[2]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col + 2), miscBlocks[2]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row, col - 2), miscBlocks[2]);
			}
		} else if(shape == jIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col), miscBlocks[2]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col + 1), miscBlocks[2]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row, col - 2), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 2), miscBlocks[2]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 2), miscBlocks[2]);
			}
		} else if(shape == sIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[2]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col - 1), miscBlocks[2]);
			}
		} else if(shape == zIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 2), miscBlocks[2]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col + 1), miscBlocks[2]);
			} 
		} else if(shape == tIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row, col - 2), miscBlocks[2]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[2]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 2, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);
			}
		} else if(shape == squareIMG) {
			if(direction.equals("up") || direction.equals("right") || direction.equals("left") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[2]);
				grid.setImage(new Location(row, col - 1), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col), miscBlocks[2]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[2]);	
			}
		}
	}

	public void updateOrientation(int row, int col, String[] shape) {
		if(shape == lineIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), "lineBlock4.png");
				grid.setImage(new Location(row - 1, col), "lineBlock.png");
				grid.setImage(new Location(row - 2, col), "lineBlock.png");
				grid.setImage(new Location(row - 3, col), "lineBlock.png");	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), "lineBlock4.png");
				grid.setImage(new Location(row, col - 1), "lineBlock.png");
				grid.setImage(new Location(row, col - 2), "lineBlock.png");
				grid.setImage(new Location(row, col - 3), "lineBlock.png");
			} 

		} else if(shape == lIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row, col - 1), "lBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "lBlock.png");
				grid.setImage(new Location(row - 2, col - 1), "lBlock.png");	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row - 1, col), "lBlock.png");
				grid.setImage(new Location(row - 2, col), "lBlock.png");
				grid.setImage(new Location(row - 2, col - 1), "lBlock.png");
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row - 1, col), "lBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "lBlock.png");
				grid.setImage(new Location(row - 1, col + 2), "lBlock.png");
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row - 1, col), "lBlock.png");
				grid.setImage(new Location(row, col - 1), "lBlock.png");
				grid.setImage(new Location(row, col - 2), "lBlock.png");
			}
		} else if(shape == jIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row, col - 1), "jBlock.png");
				grid.setImage(new Location(row - 1, col), "jBlock.png");
				grid.setImage(new Location(row - 2, col), "jBlock.png");	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row - 1, col), "jBlock.png");
				grid.setImage(new Location(row - 2, col), "jBlock.png");
				grid.setImage(new Location(row - 2, col + 1), "jBlock.png");
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row, col - 1), "jBlock.png");
				grid.setImage(new Location(row, col - 2), "jBlock.png");
				grid.setImage(new Location(row - 1, col - 2), "jBlock.png");
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row - 1, col), "jBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "jBlock.png");
				grid.setImage(new Location(row - 1, col - 2), "jBlock.png");
			}
		} else if(shape == sIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), "sBlock4.png");
				grid.setImage(new Location(row, col - 1), "sBlock.png");
				grid.setImage(new Location(row - 1, col), "sBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "sBlock.png");	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), "sBlock4.png");
				grid.setImage(new Location(row - 1, col), "sBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "sBlock.png");
				grid.setImage(new Location(row - 2, col - 1), "sBlock.png");
			}
		} else if(shape == zIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), "zBlock4.png");
				grid.setImage(new Location(row, col - 1), "zBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "zBlock.png");
				grid.setImage(new Location(row - 1, col - 2), "zBlock.png");	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), "zBlock4.png");
				grid.setImage(new Location(row - 1, col), "zBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "zBlock.png");
				grid.setImage(new Location(row - 2, col + 1), "zBlock.png");
			} 
		} else if(shape == tIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col - 1), "tBlock.png");
				grid.setImage(new Location(row, col - 1), "tBlock.png");
				grid.setImage(new Location(row, col - 2), "tBlock.png");	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col), "tBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "tBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "tBlock.png");
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col), "tBlock.png");
				grid.setImage(new Location(row - 2, col), "tBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "tBlock.png");
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col), "tBlock.png");
				grid.setImage(new Location(row - 2, col), "tBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "tBlock.png");
			}
		} else if(shape == squareIMG) {
			if(direction.equals("up") || direction.equals("right") || direction.equals("left") || direction.equals("down")) {
				grid.setImage(new Location(row, col), "squareBlock4.png");
				grid.setImage(new Location(row, col - 1), "squareBlock.png");
				grid.setImage(new Location(row - 1, col), "squareBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "squareBlock.png");	
			}
		}
	}

	public void flipOrientation(int row, int col, String[] shape) {
		if(shape == lineIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				int rowMin = 4;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 0;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "lineBlock4.png");
				grid.setImage(new Location(row - 1, col), "lineBlock.png");
				grid.setImage(new Location(row - 2, col), "lineBlock.png");
				grid.setImage(new Location(row - 3, col), "lineBlock.png");	
			} else if(direction.equals("right") || direction.equals("left")) {
				int rowMin = 1;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 3;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "lineBlock4.png");
				grid.setImage(new Location(row, col - 1), "lineBlock.png");
				grid.setImage(new Location(row, col - 2), "lineBlock.png");
				grid.setImage(new Location(row, col - 3), "lineBlock.png");
			} 

		} else if(shape == lIMG) {
			if(direction.equals("up")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row, col - 1), "lBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "lBlock.png");
				grid.setImage(new Location(row - 2, col - 1), "lBlock.png");	
			} else if(direction.equals("down")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row - 1, col), "lBlock.png");
				grid.setImage(new Location(row - 2, col), "lBlock.png");
				grid.setImage(new Location(row - 2, col - 1), "lBlock.png");
			} else if(direction.equals("right")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 0;
				int colMax = grid.getNumCols() - 3;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row - 1, col), "lBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "lBlock.png");
				grid.setImage(new Location(row - 1, col + 2), "lBlock.png");
			} else if(direction.equals("left")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 2;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "lBlock4.png");
				grid.setImage(new Location(row - 1, col), "lBlock.png");
				grid.setImage(new Location(row, col - 1), "lBlock.png");
				grid.setImage(new Location(row, col - 2), "lBlock.png");
			}
		} else if(shape == jIMG) {
			if(direction.equals("up")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row, col - 1), "jBlock.png");
				grid.setImage(new Location(row - 1, col), "jBlock.png");
				grid.setImage(new Location(row - 2, col), "jBlock.png");	
			} else if(direction.equals("down")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 0;
				int colMax = grid.getNumCols() - 2;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row - 1, col), "jBlock.png");
				grid.setImage(new Location(row - 2, col), "jBlock.png");
				grid.setImage(new Location(row - 2, col + 1), "jBlock.png");
			} else if(direction.equals("right")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 2;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row, col - 1), "jBlock.png");
				grid.setImage(new Location(row, col - 2), "jBlock.png");
				grid.setImage(new Location(row - 1, col - 2), "jBlock.png");
			} else if(direction.equals("left")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 2;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "jBlock4.png");
				grid.setImage(new Location(row - 1, col), "jBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "jBlock.png");
				grid.setImage(new Location(row - 1, col - 2), "jBlock.png");
			}
		} else if(shape == sIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 2;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "sBlock4.png");
				grid.setImage(new Location(row, col - 1), "sBlock.png");
				grid.setImage(new Location(row - 1, col), "sBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "sBlock.png");	
			} else if(direction.equals("right") || direction.equals("left")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "sBlock4.png");
				grid.setImage(new Location(row - 1, col), "sBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "sBlock.png");
				grid.setImage(new Location(row - 2, col - 1), "sBlock.png");
			}
		} else if(shape == zIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 2;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "zBlock4.png");
				grid.setImage(new Location(row, col - 1), "zBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "zBlock.png");
				grid.setImage(new Location(row - 1, col - 2), "zBlock.png");	
			} else if(direction.equals("right") || direction.equals("left")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 0;
				int colMax = grid.getNumCols() - 2;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "zBlock4.png");
				grid.setImage(new Location(row - 1, col), "zBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "zBlock.png");
				grid.setImage(new Location(row - 2, col + 1), "zBlock.png");
			} 
		} else if(shape == tIMG) {
			if(direction.equals("up")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 2;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col - 1), "tBlock.png");
				grid.setImage(new Location(row, col - 1), "tBlock.png");
				grid.setImage(new Location(row, col - 2), "tBlock.png");	
			} else if(direction.equals("down")) {
				int rowMin = 2;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 2;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col), "tBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "tBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "tBlock.png");
			} else if(direction.equals("right")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 0;
				int colMax = grid.getNumCols() - 2;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col), "tBlock.png");
				grid.setImage(new Location(row - 2, col), "tBlock.png");
				grid.setImage(new Location(row - 1, col + 1), "tBlock.png");
			} else if(direction.equals("left")) {
				int rowMin = 3;
				int rowMax = grid.getNumRows() - 1;
				int colMin = 1;
				int colMax = grid.getNumCols() - 1;
				if(row < rowMin) {
					row = rowMin;
				} else if(row > rowMax) {
					row = rowMax;
				}
				if(col < colMin) {
					col = colMin;
				} else if(col > colMax) {
					col = colMax;
				}
				grid.setImage(new Location(row, col), "tBlock4.png");
				grid.setImage(new Location(row - 1, col), "tBlock.png");
				grid.setImage(new Location(row - 2, col), "tBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "tBlock.png");
			}
		} else if(shape == squareIMG) {
			if(direction.equals("up") || direction.equals("right") || direction.equals("left") || direction.equals("down")) {
				grid.setImage(new Location(row, col), "squareBlock4.png");
				grid.setImage(new Location(row, col - 1), "squareBlock.png");
				grid.setImage(new Location(row - 1, col), "squareBlock.png");
				grid.setImage(new Location(row - 1, col - 1), "squareBlock.png");	
			}
		}
	}

	public boolean hitBottomHorizontally(int row, String shapeName, String checkBlock) {
		if(shapeName.equals(line)) {
			if(row == grid.getNumRows() - 1 || (downValid && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock)) || (left2DownImage != null && left2DownImage.equals(checkBlock)) || (left3DownImage != null &&left3DownImage.equals(checkBlock))))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(l)) {
			if(direction.equals("left") && (row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock)) || (left2DownImage != null && left2DownImage.equals(checkBlock)))))) {
				return true; 
			} else if(direction.equals("right") && (row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (rightImage != null && rightImage.equals(checkBlock)) || (right2Image != null && right2Image.equals(checkBlock)))))) {
				return true;
			} else { 
				return false;
			}
		} else if(shapeName.equals(j)) {
			if(direction.equals("left") && (row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftImage != null && leftImage.equals(checkBlock)) || (left2Image != null && left2Image.equals(checkBlock)))))) {
				return true;
			} else if(direction.equals("right") && (row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock)) || (left2DownImage != null && left2DownImage.equals(checkBlock)))))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(s)) {
			if(row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftImage != null && leftImage.equals(checkBlock))))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(z)) {
			if(row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (rightImage != null && rightImage.equals(checkBlock))))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(t)) {
			if(direction.equals("left") && (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftImage != null && leftImage.equals(checkBlock))))) {
				return true;
			} else if(direction.equals("right") && (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (rightImage != null && rightImage.equals(checkBlock))))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(square)) {
			if(row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && ((downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock))))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		} 
	}

	public boolean hitBottomVertically(int row, String shapeName, String checkBlock) {
		if(shapeName.equals(line)) {
			if(row == grid.getNumRows() - 1 || (downValid  && row < grid.getNumRows() - 1 && downImage != null && downImage.equals(checkBlock))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(l)) {
			if(direction.equals("down") && (row == grid.getNumRows() - 1 || row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (leftUpImage != null && leftUpImage.equals(checkBlock) )))) {
				return true;
			} else if(direction.equals("up") && (row == grid.getNumRows() - 1 || (row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock)))))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(j)) {
			if(direction.equals("down") && (row == grid.getNumRows() - 1 || row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (rightUpImage != null && rightUpImage.equals(checkBlock) )))) {
				return true;
			} else if(direction.equals("up") && (row == grid.getNumRows() - 1 || (row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock)))))) {
				return true;
			} else {
				return false;
			}
		} if(shapeName.equals(s)) {
			if(row == grid.getNumRows() - 1 || row < grid.getNumRows() - 1 && ((downValid  && row < grid.getNumRows() - 1 && downImage != null && downImage.equals(checkBlock)) || (row < grid.getNumRows() - 1 && leftDownImage != null && leftDownImage.equals(checkBlock)) || (row < grid.getNumRows() - 1 && rightImage != null && rightImage.equals(checkBlock)))) {
				return true;
			} else {
				return false;
			}
		} if(shapeName.equals(z)) {
			if(row == grid.getNumRows() - 1 || row < grid.getNumRows() - 1 && ((downValid  && row < grid.getNumRows() - 1 && downImage != null && downImage.equals(checkBlock)) || (row < grid.getNumRows() - 1 && leftDownImage != null && leftDownImage.equals(checkBlock)) || (row < grid.getNumRows() - 1 && left2Image != null && left2Image.equals(checkBlock)))) {
				return true;
			} else {
				return false;
			}
		} else if(shapeName.equals(t)) {
			if(direction.equals("down") && (row == grid.getNumRows() - 1 || row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (leftImage != null && leftImage.equals(checkBlock)) || (rightImage != null && rightImage.equals(checkBlock))))) {
				return true;
			} else if(direction.equals("up") && (row == grid.getNumRows() - 1 || (row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock)) || (left2DownImage != null && left2DownImage.equals(checkBlock)))))) {
				return true;
			} else {
				return false;
			}
		} if(shapeName.equals(square)) {
			if(row == grid.getNumRows() - 1 || (row < grid.getNumRows() - 1 && ((downValid  && downImage != null && downImage.equals(checkBlock)) || (leftDownImage != null && leftDownImage.equals(checkBlock))))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void landHorizontalUpdate(int row, int col, String shapeName, String[] shape) {
		if(hitBottomHorizontally(row, shapeName, miscBlocks[0]) ) {
			makeShapeFinal(row, col, shape);
			rowCrusher();
			for(int c = 0; c < grid.getNumCols(); c ++) {
				String image = grid.getImage(new Location(1, c));
				if(image != null && image.equals(miscBlocks[0])) {
					hitTop = true;
					isGameOver();
				}
			}
		} else {
			if(row < grid.getNumRows() - 1) {
				holderBlock(row, col, shape);
				updateOrientation(row + 1, col, shape);
				checkheart(heartLoc);
				checkBomb1(bombLoc1);
				checkBomb2(bombLoc2);
				checkBomb3(bombLoc3);
			} else if(row == grid.getNumRows() - 1 ) {
				makeShapeFinal(row, col, shape);
				rowCrusher();
				for(int c = 0; c < grid.getNumCols(); c ++) {
					String image = grid.getImage(new Location(0, c));
					if(image != null && image.equals(miscBlocks[0])) {
						hitTop = true;
						isGameOver();
					}
				}
			}
		}
		for(int r = 1; r < grid.getNumRows(); r++) {
			for(int c = 0; c < grid.getNumCols(); c ++) {
				if(grid.getImage(new Location(r, c)) != null && grid.getImage(new Location(r, c)).equals(miscBlocks[2])) {
					grid.setImage(new Location(r, c), null);
				}
			}
		}
	}

	public void landVerticalUpdate(int row, int col, String shapeName, String[] shape) {
		if(hitBottomVertically(row, shapeName, miscBlocks[0]) ) {
			makeShapeFinal(row, col, shape);
			rowCrusher();
			for(int c = 0; c < grid.getNumCols(); c ++) {
				String image = grid.getImage(new Location(0, c));
				if(image != null && image.equals(miscBlocks[0])) {
					hitTop = true;
					isGameOver();
				}
			}
		} else {
			if(row < grid.getNumRows() - 1) {
				holderBlock(row, col, shape);
				updateOrientation(row + 1, col, shape);
				checkheart(heartLoc);
				checkBomb1(bombLoc1);
				checkBomb2(bombLoc2);
				checkBomb3(bombLoc3);
			} else if(row == grid.getNumRows() - 1) {
				makeShapeFinal(row, col, shape);
				rowCrusher();
				for(int c = 0; c < grid.getNumCols(); c ++) {
					String image = grid.getImage(new Location(0, c));
					if(image != null && image.equals(miscBlocks[0])) {
						hitTop = true;
						isGameOver();
					}
				}
			}
		}
		for(int r = 1; r < grid.getNumRows(); r++) {
			for(int c = 0; c < grid.getNumCols(); c ++) {
				if(grid.getImage(new Location(r, c)) != null && grid.getImage(new Location(r, c)).equals(miscBlocks[2])) {
					grid.setImage(new Location(r, c), null);
				}
			}
		}
	}

	public void makeShapeFinal(int row, int col, String[] shape) {
		if(shape == lineIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col), miscBlocks[0]);
				grid.setImage(new Location(row - 3, col), miscBlocks[0]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row, col - 2), miscBlocks[0]);
				grid.setImage(new Location(row, col - 3), miscBlocks[0]);
			} 
		} else if(shape == lIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col - 1), miscBlocks[0]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col - 1), miscBlocks[0]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col + 2), miscBlocks[0]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row, col - 2), miscBlocks[0]);
			}
		} else if(shape == jIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col), miscBlocks[0]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col + 1), miscBlocks[0]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row, col - 2), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 2), miscBlocks[0]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 2), miscBlocks[0]);
			}
		} else if(shape == sIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[0]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col - 1), miscBlocks[0]);
			}
		} else if(shape == zIMG) {
			if(direction.equals("up") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 2), miscBlocks[0]);	
			} else if(direction.equals("right") || direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col + 1), miscBlocks[0]);
			} 
		} else if(shape == tIMG) {
			if(direction.equals("up")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row, col - 2), miscBlocks[0]);	
			} else if(direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
			} else if(direction.equals("right")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col + 1), miscBlocks[0]);
			} else if(direction.equals("left")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 2, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);
			}
		} else if(shape == squareIMG) {
			if(direction.equals("up") || direction.equals("right") || direction.equals("left") || direction.equals("down")) {
				grid.setImage(new Location(row, col), miscBlocks[0]);
				grid.setImage(new Location(row, col - 1), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col), miscBlocks[0]);
				grid.setImage(new Location(row - 1, col - 1), miscBlocks[0]);	
			}
		}
		score += 40;
		getScore();
		isReady = true;
	}
	
	public void rowCrusher() {

		for(int row = 1; row < grid.getNumRows(); row ++) {
			int rowCounter = 0;
			int blockCounter = 0;
			for(int col = 0; col < grid.getNumCols(); col ++) {
				String image = grid.getImage(new Location(row, col));
				if(image != null && image.equals(miscBlocks[0])) {
					blockCounter ++;
					if(blockCounter == grid.getNumCols()) {
						rowCounter ++;
					}
				}
			}
			if(blockCounter == grid.getNumCols()) {
				int destroyRow = row;
				//audio.play(clearRowSound, 0);
				for(int rowCount = 0; rowCount < rowCounter; rowCount ++) {
					for(int col = 0; col < grid.getNumCols(); col ++) {
						grid.setImage(new Location(destroyRow, col), clearBlock[col]);
						if(rowCounter >= 4) {
							score += 500;
						} else {
							score += 100;
						}
						getScore();
						Grid.pause(100);
						grid.setImage(new Location(destroyRow, col), null);
					}
				}
				for(int r = destroyRow; r >= 1; r --) {
					for(int c = 0; c < grid.getNumCols(); c ++) {
						String dropImage = grid.getImage(new Location(r, c));
						if(dropImage != null && !dropImage.equals("heart.gif") && !dropImage.equals("bomb.gif")) {
							grid.setImage(new Location(r, c), miscBlocks[2]);
							grid.setImage(new Location(r + 1, c), dropImage);
						}
					}
				}
			}
		}
	}
	
	public void spawnhearts() {
		int row = (int)(Math.random() * 15) + 5;
		int col = (int)(Math.random() * 10);
		int spawnRate = (int)(Math.random() * 100) + 1;
		String image = grid.getImage(new Location(row, col));

		if(image == null && heartCount == 0 && lives < 3 &&  spawnRate > 95) {
			grid.setImage(new Location(row, col), "heart.gif");
			heartLoc = new Location(row, col);
			heartCount ++;
		}
	}

	public void checkheart(Location heartLoc) {
		if(heartCount > 0) {
			if(grid.getImage(heartLoc) != null) {
				String image = grid.getImage(heartLoc);
				if(!image.equals("heart.gif")) {
					lives ++;
					heartCount = 0;
				}
			}
		}
	}
	
	public void spawnBomb1() {
		int row = (int)(Math.random() * 15) + 5;
		int col = (int)(Math.random() * 10);
		int spawnRate = (int)(Math.random() * 100) + 1;
		String image = grid.getImage(new Location(row, col));

		if(image == null && bomb1Count == 0 && spawnRate > 95) {
			bombLoc1 = new Location(row, col);
			grid.setImage(bombLoc1, "bomb.gif");
			bomb1Count ++;
		}
	}
	public void spawnBomb2() {
		int row = (int)(Math.random() * 15) + 5;
		int col = (int)(Math.random() * 10);
		int spawnRate = (int)(Math.random() * 100) + 1;
		String image = grid.getImage(new Location(row, col));

		if(image == null && bomb2Count == 0 && spawnRate > 97) {
			bombLoc2 = new Location(row, col);
			grid.setImage(bombLoc2, "bomb.gif");
			bomb2Count ++;
		}
	}
	public void spawnBomb3() {
		int row = (int)(Math.random() * 15) + 5;
		int col = (int)(Math.random() * 10);
		int spawnRate = (int)(Math.random() * 100) + 1;
		String image = grid.getImage(new Location(row, col));

		if(image == null && bomb3Count == 0 && spawnRate > 99) {
			bombLoc3 = new Location(row, col);
			grid.setImage(bombLoc3, "bomb.gif");
			bomb3Count ++;
		}
	}

	public void checkBomb1(Location bombLoc1) {
		if(bomb1Count > 0) {
			if(grid.getImage(bombLoc1) != null) {
				String image = grid.getImage(bombLoc1);
				if(!image.equals("bomb.gif")) {
					bomb1Count --;
					lives --;
					isGameOver();
				}
			}
		}
	}
	public void checkBomb2(Location bombLoc2) {
		if(bomb2Count > 0) {
			if(grid.getImage(bombLoc2) != null) {
				String image = grid.getImage(bombLoc2);
				if(!image.equals("bomb.gif")) {
					bomb2Count --;
					lives --;
					isGameOver();
				}
			}
		}
	}
	public void checkBomb3(Location bombLoc3) {
		if(bomb3Count > 0) {
			if(grid.getImage(bombLoc3) != null) {
				String image = grid.getImage(bombLoc3);
				if(!image.equals("bomb.gif")) {
					bomb3Count --;
					lives --;
					isGameOver();
				}
			}
		}
	}

	public int getScore()
	{
		return score;
	}

	public void updateTitle()
	{
		grid.setTitle("Score:  " + getScore());
	}

	public boolean isGameOver()
	{
		if(hitTop || lives == 0) {
			grid.setImage(new Location(0, grid.getNumCols() - 3), miscBlocks[1]);
			grid.setImage(new Location(0, grid.getNumCols() - 2), miscBlocks[1]);
			grid.setImage(new Location(0, grid.getNumCols() - 1), miscBlocks[1]);
			return true;

		}
		else {
			return false;
		}
	}

	public Grid getGrid() {
		return grid;
	}

	public static void test()
	{
		Tetris game = new Tetris();
		game.play();
	}

	public static void main(String[] args)
	{
		test();
	}
}