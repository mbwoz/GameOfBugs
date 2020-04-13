package gameofbugs;

import gameofbugs.tiles.TileModel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameView {

    public Scene getBoardView(BoardModel boardModel) {
        ScrollPane boardMap = new ScrollPane();
        AnchorPane tileMap = new AnchorPane();
        boardMap.setContent(tileMap);

        Scene content = new Scene(boardMap, 800, 600);

        int tilesPerCol = 32; // how many rows of tiles should be created
        int tilesPerRow = 32; // the amount of tiles that are contained in each row

        int firstRow = -1, firstCol = -1;

        for (int row = 0; row < tilesPerRow; row++) {
            for (int col = 0; col < tilesPerCol; col++) {
                if(!boardModel.isEmpty(new Position(row, col))) {
                    firstRow = row;
                    break;
                }
            }
            if(firstRow != -1)
                break;
        }


        for (int col = 0; col < tilesPerCol; col++) {
            for (int row = 0; row < tilesPerRow; row++) {
                if(!boardModel.isEmpty(new Position(row, col))) {
                    firstCol = col;
                    break;
                }
            }
            if(firstCol != -1)
                break;
        }

        //System.out.println(firstRow + " " + firstCol);

        for (int row = 0; row < tilesPerRow; row++) {
            for (int col = 0; col < tilesPerCol; col++) {
                Position pos = new Position(row, col);
                if(boardModel.isEmpty(pos))
                    continue;

                Tile tile = new Tile(pos, boardModel.getTopTile(pos), firstRow, firstCol);

                Group fullTile = new Group();
                fullTile.getChildren().addAll(tile.getHex(), tile.getDesc());
                tileMap.getChildren().add(fullTile);
            }
        }

        return content;
    }

    private class Tile {
        private Position position;
        private TileModel tile;
        private final double a = 50;
        private final double h = Math.sqrt(a * a * 0.75);
        private final double spacing = 0;
        private final double gap = 2 * h + spacing;
        private double centerC;
        private double centerR;

        Tile(Position position, TileModel tile, int firstRow, int firstCol) {
            this.position = position;
            this.tile = tile;

            this.centerC = h + (position.getY() - firstCol) * gap + (position.getX() - firstRow) * (gap / 2);
            this.centerR = a + (position.getX() - firstRow) * Math.sqrt(gap * gap * 0.75);
        }

        public Polygon getHex() {
            Polygon hex = new Polygon();

            // creates the polygon using the corner coordinates
            hex.getPoints().addAll(
                    centerC, centerR - a,
                    centerC - h, centerR - a / 2,
                    centerC - h, centerR + a / 2,
                    centerC, centerR + a,
                    centerC + h, centerR + a / 2,
                    centerC + h, centerR - a / 2
            );

            if(tile.getColor() == gameofbugs.Color.WHITE)
                hex.setFill(Color.WHITE);
            else
                hex.setFill(Color.BLACK);

            hex.setStrokeWidth(3);
            hex.setStroke(Color.BLACK);
            hex.setOnMouseClicked(e ->
                    System.out.println("Clicked: " + this.position.getX() + " " + this.position.getY()));

            return hex;
        }

        public Text getDesc() {
            Text t = new Text(centerC - h, centerR, tile.getClass().getSimpleName());

            if(tile.getColor() == gameofbugs.Color.WHITE)
                t.setFill(Color.BLACK);
            else
                t.setFill(Color.WHITE);

            t.setTextAlignment(TextAlignment.CENTER);
            return t;
        }
    }
}