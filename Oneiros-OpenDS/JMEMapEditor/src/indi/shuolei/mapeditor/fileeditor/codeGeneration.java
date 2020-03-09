package indi.shuolei.mapeditor.fileeditor;

public class codeGeneration {
	public static String buildingCode(double X,double Z, double height, String name, String Type) {
		String outString = "//Name = "+name+", ("+Double.toString(X)+", "+Double.toString(Z)+"), height = "+height+", Type="+Type;
		return outString;
	}
	public static String straightRoadCode(float X, float Z, Boolean direction) {
		String outString = "        editor_add_mainroad("+X+"f,"+Z+"f,"+direction+");";
		return outString;
	}
	public static String corssroadCode(float X, float Z, float roadX, float roadZ, float directionX, float directionY) {
		String outString = "        editor_add_cross("+ X +"f,"+Z+"f,"+roadX+"f,"+ roadZ+"f," + directionX+"f,"+ directionY + "f" + ");";
		return outString;
	}
	public static String grassCode(float X, float Z) {
		String outString = "        editor_add_Grand(physicsSpace,"+X+"f,"+Z+"f);";
		System.out.println(outString);
		return outString;
	}
	//Traffic Signs
	public static String speedLimit40Code(float X, float Z, Boolean direction) {
		String outString = "        editor_speed_limit("+X+"f,"+Z+"f,"+direction+");";
		return outString;
	}


}
