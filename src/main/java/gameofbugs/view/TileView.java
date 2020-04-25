package gameofbugs.view;

import gameofbugs.model.Color;
import gameofbugs.model.tiles.TileModel;
import gameofbugs.model.tiles.TilePlaceholder;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TileView {
    private TileModel tile;

    private double side;
    private double height;
    private double centerX;
    private double centerY;

    public TileView(TileModel tile, double centerX, double centerY, double side) {
        this.tile = tile;
        this.centerX = centerX;
        this.centerY = centerY;
        this.side = side;
        this.height = Math.sqrt(side * side * 0.75);
    }

    public Polygon getHex() {
        Polygon hex = new Polygon();

        // creates the polygon using the corner coordinates
        hex.getPoints().addAll(
                centerX, centerY - side,
                centerX - height, centerY - side / 2,
                centerX - height, centerY + side / 2,
                centerX, centerY + side,
                centerX + height, centerY + side / 2,
                centerX + height, centerY - side / 2
        );

        if(tile instanceof TilePlaceholder)
            hex.setFill(javafx.scene.paint.Color.GOLD);
        else if(tile.getColor() == Color.WHITE)
            hex.setFill(javafx.scene.paint.Color.WHITE);
        else
            hex.setFill(javafx.scene.paint.Color.BLACK);

        hex.setStrokeWidth(3);
        hex.setStroke(javafx.scene.paint.Color.BLACK);

        return hex;
    }

    public Text getDescription() {
        Text t = new Text(centerX - height, centerY, tile.getClass().getSimpleName());

        if(tile.getColor() == Color.WHITE)
            t.setFill(javafx.scene.paint.Color.BLACK);
        else
            t.setFill(javafx.scene.paint.Color.WHITE);

        t.setTextAlignment(TextAlignment.CENTER);
        return t;
    }
}