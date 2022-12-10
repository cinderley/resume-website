
import java.io.File;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MineSweep3 extends Application {

	int totalBombs = 10; int numBombs = totalBombs;
	int rowCount = 8; int colCount = 8;
	int tileSize = 20;
	boolean gameStart = true;
	boolean levelChanged = false;
	double divisor = 12; double addition = tileSize * 0;
	double facePadding;
	Button[][] mineField;
	int[][] tileVals;
	int seconds = 0;
	static Timeline timer = new Timeline();
	static Alert highScoreBox = new Alert(AlertType.INFORMATION);
	Player[] scores = new Player[3];
	File hScores;
	String level = "beginner"; String name;
	int time;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) throws Exception {

		numBombs = totalBombs;
		gameStart = true;

		for(int i = 0; i < scores.length; i ++)
			scores[i] = new Player("n/a", "n/a", -1);

		hScores = new File("res/scores");

		try (Scanner fileInput = new Scanner(hScores)) {
			int i = 0;
			while(fileInput.hasNextLine()) {
				String l = fileInput.next();
				int t = fileInput.nextInt();
				fileInput.next();
				String n = fileInput.next();
				fileInput.nextLine();
				scores[i] = new Player(l, n, t);
				i ++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		MenuBar mBar = new MenuBar();
		Menu gameMenu = new Menu("Menu");
		MenuItem diffLevel = new MenuItem("Difficulty");
		MenuItem beg = new MenuItem("   Beginner");
		MenuItem med = new MenuItem("   Intermediate");
		MenuItem exp = new MenuItem("   Expert");
		MenuItem highScores = new MenuItem("High Scores");
		gameMenu.getItems().addAll(diffLevel, beg, med, exp, highScores);
		mBar.getMenus().add(gameMenu);

		GridPane gPane = new GridPane();
		BorderPane bPane = new BorderPane();
		HBox headerBombs = new HBox(); 
		HBox headerFace = new HBox(); 
		HBox headerTime = new HBox(); 

		FlowPane fPane = new FlowPane();
		fPane.setPrefWidth(colCount * tileSize - (headerBombs.getWidth() + headerFace.getWidth() + headerTime.getWidth() + 20));
		fPane.setAlignment(Pos.CENTER);

		headerBombs.setAlignment(Pos.BOTTOM_LEFT);
		headerBombs.setPadding(new Insets(0, 0, 1, 0));


		headerFace.setAlignment(Pos.CENTER);
		facePadding = ((double) (fPane.getPrefWidth() - (headerBombs.getWidth() + headerFace.getWidth() + headerTime.getWidth())) / divisor) + addition;
		headerFace.setPadding(new Insets(0, facePadding, 0, facePadding));


		headerTime.setAlignment(Pos.BOTTOM_RIGHT);
		headerTime.setPadding(new Insets(0, 0, 1, 0));

		mBar.setStyle("-fx-border-color: #BDBDBD;"
				+ "-fx-border-width: 3 3 3 3;"
				+ "-fx-border-radius: 0.01;");
		fPane.setStyle("-fx-border-width:2px;"
				+ "-fx-border-color: #7b7b7b white white #7b7b7b;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-radius: 0.01;");
		gPane.setStyle("-fx-border-width:3px;"
				+ "-fx-border-insets: 5 0 0 0;"
				+ "-fx-border-color: #7b7b7b white white #7b7b7b;"
				+ "-fx-border-radius: 0.01;"
				);
		bPane.setStyle("-fx-background-color: #BDBDBD;"
				+ "-fx-border-width: 3 3 3 3;"
				+ "-fx-border-color: white #7b7b7b #7b7b7b white;"
				+ "-fx-border-radius: 0.01;");

		mineField = new Tile[rowCount][colCount];
		tileVals = new int[rowCount][colCount];

		ImageView smile = new ImageView(new Image("file:res/face-smile.png"));
		ImageView o = new ImageView(new Image("file:res/faceooh.png"));
		ImageView dead = new ImageView(new Image("file:res/face-dead.png"));
		ImageView win = new ImageView(new Image("file:res/face-win.png"));

		ImageView[] faceImages = {smile, o, dead, win};

		for(ImageView tileImage : faceImages) {
			tileImage.setFitWidth(36);
			tileImage.setFitHeight(36);
		}

		Button face = new Button();
		face.setGraphic(smile);
		face.setPadding(Insets.EMPTY);

		populateHeader(fPane, headerBombs, headerFace, headerTime, face, numBombs);

		for(int row = 0; row < tileVals.length; row ++) {
			for(int col = 0; col < tileVals[row].length; col ++) {	
				mineField[row][col] = new Tile(tileVals[row][col], tileSize);
				Tile tile = (Tile)mineField[row][col];
				tile.value = tileVals[row][col];
				tile.setGraphic(tile.coverTile);
				int r = row; int c = col;

				tile.setOnMousePressed(e -> {					
					MouseButton button = e.getButton();
					face.setGraphic(faceImages[1]);

					if(gameStart) {
						if(button.name().equals("PRIMARY")) {
							populateBombs(tileVals, totalBombs, rowCount, colCount, r, c);
							populateValues(tileVals, totalBombs);

							leftClick(tile, face, faceImages[2], mineField, tileVals, gPane, r, c, rowCount, colCount, timer,  totalBombs, faceImages[3], seconds, scores, hScores, level, time, name);
							seconds = 0;
							timer = new Timeline(new KeyFrame(Duration.seconds(1), e2 -> {
								seconds ++;
								timer(fPane, headerBombs, headerFace, headerTime, seconds);
							}));
							timer.setCycleCount(Timeline.INDEFINITE);
							timer.play();

							gameStart = false;
							levelChanged = false;
						} else {
							numBombs -= rightClick(tile, tileVals, r, c);
							updateHeader(fPane, headerBombs, headerFace, headerTime, face, faceImages[3], numBombs);
						}

					} else if(button.name().equals("PRIMARY")) {
						if(tile.image.equals("cover") || tile.image.equals("question")) {
							leftClick(tile, face, faceImages[2], mineField, tileVals, gPane, r, c, rowCount, colCount, timer, totalBombs, faceImages[3], seconds, scores, hScores, level, time, name);
						} else if(tile.state == 1) {
							fastPlay(tile, face, faceImages[2], faceImages[3], mineField, tileVals, gPane, totalBombs, r, c, rowCount, colCount, seconds, scores, hScores, level, time, name);
						} 
					} else if(button.name().equals("SECONDARY") && (tile.image.equals("cover") || tile.image.contains("Flag") || tile.image.equals("question"))) {
						numBombs -= rightClick(tile, tileVals, r, c);
						updateHeader(fPane, headerBombs, headerFace, headerTime, face, faceImages[3], numBombs);
					}
				});

				tile.setOnMouseReleased(e ->{
					if(!face.getGraphic().equals(faceImages[3]) && !face.getGraphic().equals(faceImages[2]))
						face.setGraphic(faceImages[0]);
					else
						updateHeader(fPane, headerBombs, headerFace, headerTime, face, faceImages[3], numBombs);
				});

				face.setOnMouseClicked(e ->{
					face.setGraphic(faceImages[0]);
					numBombs = totalBombs;
					clearHeader(fPane, headerBombs, headerFace, headerTime);
					populateHeader(fPane, headerBombs, headerFace, headerTime, face, numBombs);
					resetGame(mineField, tileVals, gameStart);
					seconds = 0;
					gameStart = true;
					levelChanged = true;
					timer.stop();
				});

				gPane.add(tile, col, row);
				bPane.setTop(mBar);
				bPane.setBottom(gPane);
				bPane.setCenter(fPane);
			}
		}

		Scene scene = new Scene(bPane);
		theStage.setTitle("Minesweeper");
		theStage.setScene(scene);
		theStage.show();

		beg.setOnAction(e -> {
			totalBombs = 10; rowCount = 8; colCount = 8;
			divisor = 12; addition = tileSize * 0;
			level = "beginner";
			resetGame(mineField, tileVals, gameStart);
			levelChanged = true;
			try {
				start(theStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			timer.stop();
			seconds = 0;
		});

		med.setOnAction(e -> {
			totalBombs = 40; rowCount = 16; colCount = 16;
			divisor = 4; addition = tileSize * 0.9;
			level = "intermediate";
			resetGame(mineField, tileVals, gameStart);
			levelChanged = true;
			try {
				start(theStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			timer.stop();
			seconds = 0;
		});

		exp.setOnAction(e -> {
			totalBombs = 99; rowCount = 16; colCount = 32;
			divisor = 4; addition = tileSize * 4.8;
			level = "expert";
			resetGame(mineField, tileVals, gameStart);
			levelChanged = true;
			try {
				start(theStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			timer.stop();
			seconds = 0;
		});

		highScores.setOnAction(e -> {
			highScores(highScoreBox, scores, hScores, level, seconds, true);
		});
	}

	static void timer(FlowPane fPane, HBox headerBombs, HBox headerFace, HBox headerTime, int seconds) {
		int hunSec = seconds / 100; int tenSec = (seconds % 100) / 10; int oneSec = seconds % 10;

		ImageView time1 = new ImageView(new Image("file:res/time/" + hunSec + ".png"));
		ImageView time2 = new ImageView(new Image("file:res/time/" + tenSec + ".png"));
		ImageView time3 = new ImageView(new Image("file:res/time/" + oneSec + ".png"));

		ImageView[] headerImages = {time1, time2, time3};

		for(ImageView image : headerImages) {
			image.setFitHeight(32);
			image.setFitWidth(16);
		}

		headerTime.getChildren().setAll(time1, time2, time3);
		fPane.getChildren().setAll(headerBombs, headerFace, headerTime);
	}

	static void populateHeader(FlowPane fPane, HBox headerBombs, HBox headerFace, HBox headerTime, Button face, int numBombs) {	
		ImageView hundreds = new ImageView(new Image("file:res/time/" + numBombs / 100 + ".png"));
		ImageView tens = new ImageView(new Image("file:res/time/" + ((numBombs / 10) % 10) + ".png"));
		ImageView ones = new ImageView(new Image("file:res/time/" + numBombs % 10 + ".png"));

		ImageView time1 = new ImageView(new Image("file:res/time/0.png"));
		ImageView time2 = new ImageView(new Image("file:res/time/0.png"));
		ImageView time3 = new ImageView(new Image("file:res/time/0.png"));

		ImageView[] headerImages = {hundreds, tens, ones, time1, time2, time3};

		for(ImageView image : headerImages) {
			image.setFitHeight(32);
			image.setFitWidth(16);
		}

		headerBombs.getChildren().addAll(hundreds, tens, ones);
		headerFace.getChildren().add(face);
		headerTime.getChildren().addAll(time1, time2, time3);
		fPane.getChildren().addAll(headerBombs, headerFace, headerTime);
	}

	static void clearHeader(FlowPane fPane, HBox headerBombs, HBox headerFace, HBox headerTime) {
		headerBombs.getChildren().clear();
		headerFace.getChildren().clear();
		headerTime.getChildren().clear();
		fPane.getChildren().clear();
	}

	static void updateHeader(FlowPane fPane, HBox headerBombs, HBox headerFace, HBox headerTime, Button face, ImageView win, int numBombs) {
		int hun = 0; int ten = 0; int one = 0;
		if(numBombs > 0) {
			hun = 0; ten = ((numBombs / 10) % 10); one = numBombs % 10;
		} else if(numBombs < 0) {
			hun = 222; 	ten = Math.abs(((numBombs / 10) % 10)); one = Math.abs(numBombs % 10);
		} else if(numBombs == 0) {
			hun = 999; ten = 999; one = 999;
		}

		if(face.getGraphic().equals(win))  {
			hun = 0; ten = 0; one = 0;
		}

		ImageView hundreds = new ImageView(new Image("file:res/time/" + hun + ".png"));
		ImageView tens = new ImageView(new Image("file:res/time/" + ten + ".png"));
		ImageView ones = new ImageView(new Image("file:res/time/" + one + ".png"));

		ImageView[] headerImages = {hundreds, tens, ones};

		for(ImageView image : headerImages) {
			image.setFitHeight(32);
			image.setFitWidth(16);
		}

		headerBombs.getChildren().setAll(hundreds, tens, ones);
		fPane.getChildren().setAll(headerBombs, headerFace, headerTime);
	}

	static void populateBombs(int[][] tileVals, int numBombs, int rowCount, int colCount, int r, int c) {
		for(int row = 0; row < tileVals.length; row ++) {
			for(int col = 0; col < tileVals[row].length; col ++) {
				tileVals[row][col] = 0;
			}
		}

		for(int row = r - 1; row <= r + 1; row ++) {
			for(int col = c - 1; col <= c + 1; col ++) {
				if(isValid(row, col, tileVals.length, tileVals[r].length)) {
					tileVals[row][col]= -5;
				}
			}
		}

		int count = 0;
		while(count < numBombs) {
			int x = (int)(Math.random() * rowCount);
			int y = (int)(Math.random() * colCount);
			if(tileVals[x][y] == 0) {
				tileVals[x][y] = 9;
				count ++;
			}
		}
	}

	static void populateValues(int[][] tileVals, int numBombs) {
		for(int row = 0; row < tileVals.length; row ++) {
			for(int col = 0; col < tileVals[row].length; col ++) {
				int bombCount = 0;
				for(int r = row - 1; r <= row + 1; r ++) {
					for(int c = col - 1; c <= col + 1; c ++) {
						if(isValid(r, c, tileVals.length, tileVals[row].length) && tileVals[r][c] == 9) {
							bombCount ++;
						}
					}
				}
				if(tileVals[row][col] < 9)
					tileVals[row][col] = bombCount;
			}
		}
	}

	static void leftClick(Tile tile, Button face, ImageView dead, Button[][] mineField, int[][] tileVals, GridPane gPane, int row, int col, int rowCount, int colCount, Timeline timer, int totalBombs, ImageView win, int seconds, Player[] scores, File hScores, String level, int time, String name) {
		tile.value = tileVals[row][col];
		if(tile.value == 9) {
			tile.image = "redBomb";
			timer.stop();
			face.setGraphic(dead);

			revealBombs(mineField, tileVals, tile, gPane);
			disableField(mineField, tile);
		} else {
			if(tile.value == 0) {
				clearZeros(mineField, tileVals, row, col, rowCount, colCount);
			} else {
				tile.image = "number";
				ImageView tileVal = new ImageView(new Image("file:res/" + tile.value + ".png"));
				tileVal.setFitWidth(tile.tileSize);
				tileVal.setFitHeight(tile.tileSize);
				tile.setGraphic(tileVal);
				tile.state = 1;
			}

		}

		checkForWin(mineField, tileVals, tile, totalBombs, face, win, seconds, scores, hScores, level, time, name);

	}

	public static void checkForWin(Button[][] mineField, int[][] tileVals, Tile tile, int totalBombs, Button face, ImageView win, int seconds, Player[] scores, File hScores, String level, int time, String name) {
		if(checkTileState(mineField, tile) == totalBombs) {
			face.setGraphic(win);
			for(int i = 0; i < mineField.length; i ++) {
				for(int j = 0; j < mineField[i].length; j ++) {
					tile = (Tile)mineField[i][j];
					tile.value = tileVals[i][j];
					if(tile.value == 9 && tile.image.equals("question")) {
						tile.setGraphic(tile.flagTile);
					}
				}
			}
			timer.stop();
			disableField(mineField, tile);
			highScores(highScoreBox, scores, hScores, level, seconds, false);
		}
	}

	private static void fastPlay(Tile tile, Button face, ImageView dead, ImageView win, Button[][] mineField, int[][] tileVals, GridPane gPane, int totalBombs, int row, int col, int rowCount, int colCount, int seconds, Player[] scores, File hScores, String level, int time, String name) {
		int tileNum = tile.value;
		int countFlags = 0;

		for(int r = row - 1; r <= row + 1; r ++) {
			for(int c = col - 1; c <= col + 1; c ++) {
				if(isValid(r, c, tileVals.length, tileVals[row].length)) {
					tile = (Tile)mineField[r][c];
					tile.value = tileVals[r][c];
					if(tile.image.equals("rightFlag") || tile.image.equals("wrongFlag")) {
						countFlags ++;
					} 
				}
			}
		}

		for(int r = row - 1; r <= row + 1; r ++) {
			for(int c = col - 1; c <= col + 1; c ++) {
				if(isValid(r, c, tileVals.length, tileVals[row].length)) {
					tile = (Tile)mineField[r][c];
					tile.value = tileVals[r][c];
					if(countFlags == tileNum && !tile.image.contains("Flag") && !face.getGraphic().equals(win)) {
						leftClick(tile, face, dead, mineField, tileVals, gPane, r, c, rowCount, colCount, timer, totalBombs, win, seconds, scores, hScores, level, time, name);
					}
				}
			}
		}

	}

	static int rightClick(Tile tile, int[][] tileVals, int row, int col) {;
	tile.value = tileVals[row][col];
	int adjustBombs = 0;
	if(tile.image.contains("Flag")) {
		tile.setGraphic(tile.questionTile);
		tile.image = "question";
	} else if(tile.image.equals("question")){
		tile.setGraphic(tile.coverTile);
		tile.image = "cover";
		adjustBombs --;
	} else if(tile.image.equals("cover")){
		tile.setGraphic(tile.flagTile);
		if(tile.value != 9) {
			tile.image = "wrongFlag";
		} else {
			tile.image = "rightFlag";
		}
		adjustBombs ++;
	}
	return adjustBombs;
	}

	private static void clearZeros(Button[][] mineField, int[][] tileVals, int row, int col, int rowCount, int colCount) {
		if(isValid(row, col, rowCount, colCount)) {
			Tile tile = (Tile)mineField[row][col];
			tile.value = tileVals[row][col];

			if(tile.state == 1) {
				return;
			}

			if(!tile.image.contains("Flag")) {
				tile.image = "number";
				ImageView tileVal = new ImageView(new Image("file:res/" + tile.value + ".png"));
				tileVal.setFitWidth(tile.tileSize);
				tileVal.setFitHeight(tile.tileSize);
				tile.setGraphic(tileVal);
				tile.state = 1;
			}

			if(tile.value == 0) {
				clearZeros(mineField, tileVals, row - 1, col - 1, rowCount, colCount);
				clearZeros(mineField, tileVals, row - 1, col, rowCount, colCount);
				clearZeros(mineField, tileVals, row - 1, col + 1, rowCount, colCount);
				clearZeros(mineField, tileVals, row, col - 1, rowCount, colCount);
				clearZeros(mineField, tileVals, row, col + 1, rowCount, colCount);
				clearZeros(mineField, tileVals, row + 1, col - 1, rowCount, colCount);
				clearZeros(mineField, tileVals, row + 1, col, rowCount, colCount); 
				clearZeros(mineField, tileVals, row + 1, col + 1, rowCount, colCount); 
			}
		}
	}

	static void revealBombs(Button[][] mineField, int[][] tileVals, Tile tile, GridPane gPane) {
		for(int row = 0; row < mineField.length; row ++) {
			for(int col = 0; col < mineField[row].length; col ++) {
				tile = (Tile)mineField[row][col];
				tile.value = tileVals[row][col];
				if(tile.value == 9) {
					if(tile.image.equals("rightFlag")) {
						tile.setGraphic(tile.flagTile);
					} else {
						tile.setGraphic(tile.greyBombTile);
					}
				}
				if(tile.image.equals("redBomb")) {
					tile.setGraphic(tile.redBombTile);
				} 
				if(tile.image.equals("wrongFlag")) {
					tile.setGraphic(tile.misflaggedTile);
				}
			}
		}
	}

	static int checkTileState(Button[][] mineField, Tile tile) {
		int coveredTiles = 0;
		for(int row = 0; row < mineField.length; row ++) {
			for(int col = 0; col < mineField[row].length; col ++) {
				tile = (Tile)mineField[row][col];
				if(tile.state == 0) {
					coveredTiles ++;
				}
			}
		}
		return coveredTiles;
	}

	static void disableField(Button[][] mineField, Tile tile) {
		for(int row = 0; row < mineField.length; row ++) {
			for(int col = 0; col < mineField[row].length; col ++) {
				tile = (Tile)mineField[row][col];
				tile.setDisable(true);
				tile.setStyle("-fx-opacity: 1");
			}
		}
	}

	static void highScores(Alert highScoreBox, Player[] scores, File hScores, String level, int seconds, boolean b) {
		int easyTime = scores[0].getTime(); int medTime = scores[1].getTime(); int hardTime = scores[2].getTime();
		boolean isBestEasy = level.equals("beginner") && (easyTime < 0 || seconds < easyTime);
		boolean isBestMed = level.equals("intermediate") && (medTime < 0 || seconds < medTime);
		boolean isBestHard = level.equals("expert") && (hardTime < 0 || seconds < hardTime);

		//credit to Andrea Batchelor for teaching me about TextInputDialogs and Alerts
		if(!b && (isBestEasy || isBestMed || isBestHard)) {
			TextInputDialog winnerName = new TextInputDialog();
			winnerName.setTitle("New Highscore");
			winnerName.setHeaderText("You got a highscore!");
			winnerName.setGraphic(null);
			winnerName.setContentText("Please enter your name: ");
			Optional<String> winName = winnerName.showAndWait();
			String name = winName.get();

			if(isBestEasy)
				scores[0] = new Player("Beginner:", name, seconds);

			if(isBestMed)
				scores[1] = new Player("Intermediate:", name, seconds);

			if(isBestHard)
				scores[2] = new Player("Expert:", name, seconds);

		}

		String s = "";
		String s2 = "No highscores yet!";
		try (PrintWriter output = new PrintWriter(hScores)) {
			for(Player p: scores) {
				output.println(p.toString());
				if(!(p.getTime() < 0)) {
					s += "\n" + p.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		highScoreBox.setTitle("High Scores");
		highScoreBox.setHeaderText(null);
		highScoreBox.setGraphic(null);
		if(scores[0].getTime() < 0 && scores[1].getTime() < 0 && scores[2].getTime() < 0) 
			highScoreBox.setContentText(s2);
		else 
			highScoreBox.setContentText(s);
		highScoreBox.showAndWait();
	}

	static void resetGame(Button[][] mineField, int[][] tileVals, boolean gameStart) {
		for(int row = 0; row < mineField.length; row ++) {
			for(int col = 0; col < mineField[row].length; col ++) {
				Tile tile = (Tile)mineField[row][col];
				tile = (Tile)mineField[row][col];
				tile.value = tileVals[row][col];
				tile.setGraphic(tile.coverTile);
				tile.setDisable(false);
				tile.state = 0;
				tile.image = "cover";
			}
		}
	}

	static boolean isValid(int r, int c, int rowCount, int colCount) {
		return r >= 0 && r < rowCount && c >= 0 && c < colCount;
	}
}


