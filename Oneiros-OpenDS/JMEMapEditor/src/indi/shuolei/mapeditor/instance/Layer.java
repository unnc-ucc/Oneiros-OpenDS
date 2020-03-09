package indi.shuolei.mapeditor.instance;

import javafx.beans.property.SimpleStringProperty;

public class Layer {

	private Cell[][] cells;
	private SimpleStringProperty layerName = new SimpleStringProperty();
    private boolean isVisible = true;
    private boolean isCollider = false;
    private double alpha = 1.0d;
	public Layer(int width, int height) {
		cells = new Cell[height][width];
		for(int i = 0;i < height;i ++){
			for(int j = 0;j < width; j++){
				cells[i][j] = new Cell();
			}
		}
	}

	public Layer() {
		this(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
	}

	public void setCell(int x, int y, Cell cell) {
		cells[y][x] = cell;
	}

	public void setCell(Cell[][] cells) {
		this.cells = cells;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public SimpleStringProperty LayerNameProperty() {
		return layerName;
	}

	public String getLayerName() {
		return layerName.get();
	}

	public void setLayerName(String name) {
		layerName.set(name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[0].length; x++) {
				if (y == cells.length - 1 && x == cells[0].length - 1) {
					sb.append(cells[y][x].toString());
				} else {
					sb.append(cells[y][x].toString() + "T");
				}
			}
		}
		return sb.toString();
	}

	public void ConvertFromString(String str) {
		String[] data = str.split("T");
		System.out.println("Data的长度:" + data.length);
		int mapWidth = Map.getInstance().getMapWidth();
		int mapHeight = Map.getInstance().getMapHeight();
		if (cells == null) {
			cells = new Cell[mapHeight][mapWidth];
		}
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
                cells[y][x] = new Cell();
                cells[y][x].CovertFromString(data[y * mapWidth + x]);
			}
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public boolean isCollider() {
		return isCollider;
	}

	public void setCollider(boolean isCollider) {
		this.isCollider = isCollider;
	}
}

