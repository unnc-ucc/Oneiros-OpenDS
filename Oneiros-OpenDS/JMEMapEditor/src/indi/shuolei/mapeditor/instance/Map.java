package indi.shuolei.mapeditor.instance;


public class Map {

	private String mapName;
    private int columnNum, rawNum;
    private int tileWidth, tileHeight;
    private int mapWidth, mapHeight;
    private static Map mapProperty;
    
    
    public static Map getInstance(){
    	if(mapProperty == null){
    		mapProperty = new Map();
    	}
    	return mapProperty;
    }
    
    public void setMapProperty(int tileWidth,int tileHeight,int columnNum,int rawNum){
    	setTileWidth(tileWidth);
    	setTileHeight(tileHeight);
    	setColumnNum(columnNum);
    	setRawNum(rawNum);
    }
    
    public int getColumnNum() {
		return columnNum;
	}
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}
	public int getRawNum() {
		return rawNum;
	}
	public void setRawNum(int RawNum) {
		this.rawNum = RawNum;
	}
	public int getTileWidth() {
		return tileWidth;
	}
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	public int getTileHeight() {
		return tileHeight;
	}
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	//
	public int getMapWidth() {
		mapWidth=tileWidth*columnNum;
		return mapWidth;
	}
	public int getMapHeight() {
		mapHeight=tileHeight*rawNum;
		return mapHeight;
	}
	
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	
}
