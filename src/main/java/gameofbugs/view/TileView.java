package gameofbugs.view;

import gameofbugs.model.tiles.TileModel;
import gameofbugs.model.tiles.TilePlaceholder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TileView {
    private TileModel tile;

    private double side;
    private double height;
    private double centerX;
    private double centerY;
    private int stackSize;
    static private final String path = "src/main/resources/";

    public TileView(TileModel tile, double centerX, double centerY, double side, int stackSize) {
        this.tile = tile;
        this.centerX = centerX;
        this.centerY = centerY;
        this.side = side;
        this.stackSize = stackSize;
    }

    public ImageView getHex() {
        String hexType = path;
        hexType += tile.getClass().getSimpleName();

        if(!(tile instanceof TilePlaceholder)) {
            hexType += tile.getColor().toString();
            hexType += stackSize;
        }
        hexType += ".png";

        // loading image
        Image image = null;
        try {
            image = new Image(new FileInputStream(hexType));
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        //creating imageview
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(2*side);
        imageView.setPreserveRatio(true);
        imageView.setX(centerX-side);
        imageView.setY(centerY-side);

        return imageView;
    }
}