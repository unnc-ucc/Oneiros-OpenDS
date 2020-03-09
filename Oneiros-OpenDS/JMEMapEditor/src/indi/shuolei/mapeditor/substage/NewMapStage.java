package indi.shuolei.mapeditor.substage;

import indi.shuolei.mapeditor.fileeditor.codeGeneration;
import indi.shuolei.mapeditor.fileeditor.fileEditor;
import indi.shuolei.mapeditor.instance.Map;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewMapStage extends AnchorPane implements Initializable {
	
	private Stage newAlertDialog;
	private OnNewMapDialogActionListener onNewMapDialogActionListener;
	

	public interface OnNewMapDialogActionListener {
		public void onNewMapOkAction();

		public void onNewMapCancelAction();
	}
	
	@FXML
	private TextField tileWidthTf, tileHeightTf, mapWidth, mapHeight;

	public NewMapStage() {
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("NewMapStage.fxml"));
		fXMLLoader.setRoot(NewMapStage.this);
		fXMLLoader.setController(NewMapStage.this);
		try {
			fXMLLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void showAlertDialog() {
		if (newAlertDialog == null) {
			newAlertDialog = new Stage(StageStyle.TRANSPARENT);
			newAlertDialog.setResizable(false);
			newAlertDialog.setScene(new Scene(this));
			newAlertDialog.show();
		} else {
			newAlertDialog.show();
		}
	}
	
	@FXML
	private void onNewMapAction(ActionEvent event) {
		//String tileWidthStr = tileWidthTf.getText();
		//String tileHeightStr = tileHeightTf.getText();
		String mapWidthStr = mapWidth.getText();
		String mapHeightStr = mapHeight.getText();
		//if (!tileWidthStr.trim().equals("") && !tileHeightStr.trim().equals("") && !mapWidthStr.trim().equals("")
		//		&& !mapHeightStr.trim().equals("")) {
			try {
				//int tileWidth = Integer.parseInt(tileWidthStr);
				//int tileHeight = Integer.parseInt(tileHeightStr);
				int mapWidth = Integer.parseInt(mapWidthStr);
				int mapHeight = Integer.parseInt(mapHeightStr);
				String grassCode = codeGeneration.grassCode(mapWidth*30,mapHeight*30);
				fileEditor.writeIntoJava("./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTemp.java","./Scenes-OpenDS-master/src/net/jmecn/physics3d/The_First_SceneTarget.java","//user input start",grassCode);
				Map.getInstance().setMapProperty(20, 20, mapWidth, mapHeight); // set the basic properties of Map instance
				if (onNewMapDialogActionListener != null) {
					onNewMapDialogActionListener.onNewMapOkAction();
				}
				//System.out.println(Map.getInstance().getMapWidth());
				hideAlertDialog();
			} catch (NumberFormatException e) {
                //e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void hideAlertDialog() {
		if (newAlertDialog != null) {
			newAlertDialog.hide();
		}
	}

	@FXML
	private void onNewMapCancelAction(ActionEvent event) {
		if (onNewMapDialogActionListener != null) {
			onNewMapDialogActionListener.onNewMapCancelAction();
		}
		hideAlertDialog();
	}

	public OnNewMapDialogActionListener getOnNewMapDialogActionListener() {
		return onNewMapDialogActionListener;
	}

	public void setOnNewMapDialogActionListener(OnNewMapDialogActionListener onNewMapDialogActionListener) {
		this.onNewMapDialogActionListener = onNewMapDialogActionListener;
	}
	
}
