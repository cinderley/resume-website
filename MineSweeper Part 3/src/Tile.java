import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {
	int state;
	int value;
	int tileSize;
	String image = "cover";
	boolean isZero = false;

	public Tile(int tileVals, int tileSize) {
		this.tileSize = tileSize;
		setMinWidth(tileSize);
		setMaxWidth(tileSize);
		setMinHeight(tileSize);
		setMaxHeight(tileSize);
		setPadding(Insets.EMPTY);

		ImageView[] allTileImages = {coverTile, flagTile, greyBombTile, redBombTile, misflaggedTile, questionTile};

		for(ImageView tileImage : allTileImages) {
			tileImage.setFitWidth(tileSize);
			tileImage.setFitHeight(tileSize);
		}	

	}

	ImageView coverTile = new ImageView(new Image("file:res/cover.png"));
	ImageView flagTile = new ImageView(new Image("file:res/flag.png"));
	ImageView greyBombTile = new ImageView(new Image("file:res/mine-grey.png"));
	ImageView redBombTile = new ImageView(new Image("file:res/mine-red.png"));
	ImageView misflaggedTile = new ImageView(new Image("file:res/mine-misflagged.png"));
	ImageView questionTile = new ImageView(new Image("file:res/question.png"));
}