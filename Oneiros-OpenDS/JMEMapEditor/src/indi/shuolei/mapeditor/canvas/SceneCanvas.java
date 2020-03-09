package indi.shuolei.mapeditor.canvas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import indi.shuolei.mapeditor.instance.Layer;

import indi.shuolei.mapeditor.fileeditor.codeGeneration;
import indi.shuolei.mapeditor.fileeditor.fileEditor;
import indi.shuolei.mapeditor.instance.Cell;
import indi.shuolei.mapeditor.instance.Layer;
import indi.shuolei.mapeditor.instance.Map;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SceneCanvas extends Canvas {
	private GraphicsContext gContext2D;
	private double tileWidth, tileHeight;
	private double columnNum, rawNum;
	private double mouseX, mouseY;
	private SimpleDoubleProperty scale = new SimpleDoubleProperty(1.0);
	private SimpleIntegerProperty nowSelectLayerProperty = new SimpleIntegerProperty(-1);
	private List<Layer> layerList = new ArrayList<Layer>();
	private List<Position> clickedPosition = new ArrayList<Position>();
	
	private boolean isShowGrid = true;
	private boolean isDrawAltasList = true;
	
	private SimpleIntegerProperty clickTree = new SimpleIntegerProperty(0);
	public int index = 0;
	
	public SceneCanvas(double width, double height) {
		super(width, height);
		init();
	}

	public SceneCanvas() {
		this(0, 0);
	}

	private void init() {
		setWidth(Map.getInstance().getMapWidth());
		setHeight(Map.getInstance().getMapHeight());
		
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem propertyItem = new MenuItem("属性");
		
		
		setOnMouseMoved(e -> {
//			mouseCols = (int) (e.getX() / (tileWidth * getScale()));
//			mouseRows = (int) (e.getY() / (tileHeight * getScale()));
			mouseX = e.getX();
			mouseY = e.getY();
		});
		
		setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				try {
					fillTheMap();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setOnMouseReleased(e -> {
			isDrawAltasList = true;
		});
		setOnMouseEntered(e -> {
			isDrawAltasList = true;
		});
		setOnMouseDragged(e -> {
			//fillTheMap();
			isDrawAltasList = true;
		});
		setOnMouseExited(e -> {
			isDrawAltasList = false;
		});
	}
	
	public void draw1(int col, int row) throws IOException {	//
		//System.out.println(clickTree.get());
		//horizontal road 
		if(clickTree.get() == 11) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.RED);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale(),20* getScale());
			System.out.println("Row: "+row+"  rawNum: "+rawNum+ "  col:"+col + "  colnum: "+ columnNum);
			String road = codeGeneration.straightRoadCode((float)((p.row-rawNum/2)*18), (float)((p.column-columnNum/2)*18), true);
			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			gContext2D.restore();			
		}
		//vertical road
		if(clickTree.get() == 13) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.RED);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale(),20* getScale());
			System.out.println("Row: "+row+"  rawNum: "+rawNum+ "  col:"+col + "  colnum: "+ columnNum);
			String road = codeGeneration.straightRoadCode((float)((p.row-rawNum/2)*18), (float)((p.column-columnNum/2)*18), false);
			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			gContext2D.restore();			
		}
		if(clickTree.get() == 12) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);
			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.RED);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale()*2,20* getScale());
//			String road = codeGeneration.straightRoadCode((float)((p.row-rawNum/2)*100), (float)((p.column-columnNum/2)*100), true);
//			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			//gContext2D.restore();			
		}
		if(clickTree.get() == 22) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);
			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.RED);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale()*4,20* getScale());
//			String road = codeGeneration.straightRoadCode((float)((p.row-rawNum/2)*100), (float)((p.column-columnNum/2)*100), true);
//			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			//gContext2D.restore();			
		}
		if(clickTree.get() == 24) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);
			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.RED);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale()*8,20* getScale());
//			String road = codeGeneration.straightRoadCode((float)((p.row-rawNum/2)*100), (float)((p.column-columnNum/2)*100), true);
//			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			//gContext2D.restore();			
		}
		if(clickTree.get() == 26) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);
			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.RED);
			gContext2D.fillRect(col*20*getScale(), row*20*getScale(), 20* getScale()*8, 20* getScale());
//			String road = codeGeneration.straightRoadCode((float)((p.row-rawNum/2)*100), (float)((p.column-columnNum/2)*100), true);
//			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			//gContext2D.restore();		
					
		}
		//Crossroad 01
		if(clickTree.get() == 30) { 
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.GREEN);
			gContext2D.fillRect(col*20*getScale()-40, row*20*getScale(), 20* getScale()*5, 20* getScale());
			gContext2D.fillRect(col*20*getScale(), row*20*getScale()-40, 20* getScale(), 20* getScale()*5);
			System.out.println("Row: "+col*20*getScale()+"  rawNum: "+row*20*getScale()+ "  col:"+20* getScale()
			+ "  colnum: "+ 20* getScale());
			System.out.println("Row: "+row+"  rawNum: "+rawNum+ "  col:"+col + "  colnum: "+ columnNum);
			String road = codeGeneration.corssroadCode(9, 9, 9, 9, (float)((p.row-rawNum/2)*18), (float)((p.column-columnNum/2)*18));
			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			gContext2D.restore();
			
		}
		
		//Traffic Signs
		//Horizontal Speed Limit 40, 
		if(clickTree.get() == 51) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.BLUE);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale(),20* getScale());
			System.out.println("Row: "+row+"  rawNum: "+rawNum+ "  col:"+col + "  colnum: "+ columnNum);
			String road = codeGeneration.speedLimit40Code((float)((p.row-rawNum/2)*18), (float)((p.column-columnNum/2)*18), true);
			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			gContext2D.restore();			
		}
		//Vertical Speed Limit 40, 
		if(clickTree.get() == 52) {
			Position p = new Position();
			p.setRow(row);
			p.setColumn(col);
			clickedPosition.add(p);			
			gContext2D = getGraphicsContext2D();		
			gContext2D.save();
			gContext2D.setFill(Color.BLUE);
			gContext2D.fillRect(col*20*getScale(),row*20*getScale(),20* getScale(),20* getScale());
			System.out.println("Row: "+row+"  rawNum: "+rawNum+ "  col:"+col + "  colnum: "+ columnNum);
			String road = codeGeneration.speedLimit40Code((float)((p.row-rawNum/2)*18), (float)((p.column-columnNum/2)*18), false);
			fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",road);
			gContext2D.restore();			
		}
		
		
	}
	
	public void draw() {
		gContext2D = getGraphicsContext2D();		
		gContext2D.save();
		gContext2D.clearRect(0, 0, getWidth(), getHeight());	//clean the whole canvas
		gContext2D.setStroke(Color.BLACK);		
		gContext2D.setFill(Color.RED);
		gContext2D.setGlobalAlpha(0.5f);
		//System.out.println(index);
//		if(clickTree.get() == 11) {
//			//fill the clicked cell to red
//			for(int i=0; i< clickedPosition.size(); i++) {	
//				gContext2D.fillRect(clickedPosition.get(i).getColumn()*20,clickedPosition.get(i).getRow()*20,20,20);
//			}
//		}
		
		
		Map tiledMap = Map.getInstance();
		tileWidth = tiledMap.getTileWidth();
		tileHeight = tiledMap.getTileHeight();
		columnNum = tiledMap.getColumnNum();
		rawNum = tiledMap.getRawNum();
		int cols = (int) (mouseX / (tileWidth * getScale()));
		int rows = (int) (mouseY / (tileHeight * getScale()));
//		int cellX = (int) (image.getWidth() / tileWidth);
//		int col = index % cellX;
//		int row = index / cellX;
//		int startCol = nowChooseProperty.get(0) % cellX;
//		int startRow = nowChooseProperty.get(0) / cellX;
		gContext2D.fillRect((cols) * tileWidth * getScale(),
				(rows) * tileHeight * getScale(), tileWidth * getScale(),
				tileHeight * getScale());
		//System.out.println(tileHeight+ "tiledMap.getMapWidth(): "+ tiledMap.getMapWidth());
		
//		Layer layer = new Layer();
//		layerList.add(layer);
//		Cell[][] cells = layer.getCells();
//		gContext2D.setGlobalAlpha(layer.getAlpha());
//		if (cells != null) {
//			for (int y = 0; y < cells.length; y++) {
//				for (int x = 0; x < cells[0].length; x++) {
//					if (cells[y][x] != null) {
//						gContext2D.save();
//						gContext2D.setGlobalAlpha(0.6f);
//						gContext2D.setFill(Color.RED);
//						gContext2D.fillRect(x * tileWidth,
//								y * tileHeight, tileWidth, tileHeight);
//						//gContext2D.restore();
//						
//						
//					}
//				}
//			}
//		}
		
		if (isShowGrid) {                                  //isShowGrid is a method in the past
			// 缁樺埗缃戞牸
			gContext2D.setGlobalAlpha(1.0f);
			gContext2D.setLineWidth(0.5f);
			for (int i = 0; i < columnNum; i++) {
				for (int j = 0; j < rawNum; j++) {
					gContext2D.strokeRect(i * tileWidth* getScale(), j * tileHeight* getScale(), tileWidth* getScale()      //Have deleted the "getScale()" method
							, tileHeight* getScale());
				}
			}
		}
		
//		if (isDrawAltasList && clickTree.get() == 1) {
//			int col = (int) (mouseX / tileWidth);
//			int row = (int) (mouseY / tileHeight);
//			
//			gContext2D.save();
//			gContext2D.setFill(Color.RED);
//			gContext2D.fillRect(col*20,row*20,20,20);			
//			gContext2D.restore();
//		}
		
		
	}
	
	private void fillTheMap() throws IOException {
		int col = (int) (mouseX / (tileWidth* getScale()));
		int row = (int) (mouseY / (tileHeight* getScale()));
		draw1(col,row);
		System.out.println("Function draw 1");
		
//		Layer layer = layerList.get(0);
//			
//		Cell cell = new Cell();
//		cell.setAltasIndex(1);
//		cell.setAltasId("");
//		layer.setCell(col, row, cell);
			
		
	}
	
	public void setMapLayerList(List<Layer> mapLayerList) {
		this.layerList = mapLayerList;
	}
	public SimpleIntegerProperty NowSelectLayerProperty() {
		return nowSelectLayerProperty;
	}
	public void setNowSelectLayer(int nowSelect) {
		nowSelectLayerProperty.set(nowSelect);
	}
	public int getNowSelectLayer() {
		return nowSelectLayerProperty.get();
	}
	public SimpleIntegerProperty ClickTree() {
		return clickTree;
	}
	public void setScale(double scaleRank) {
		scale.set(scaleRank);
	}
	public double getScale() {
		return scale.get();
	}
}

class Position{
	int row, column;
	
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setColumn(int column) {
		this.column = column;
	}

	
}
