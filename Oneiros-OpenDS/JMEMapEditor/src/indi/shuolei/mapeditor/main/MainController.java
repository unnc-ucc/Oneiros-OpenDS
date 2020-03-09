package indi.shuolei.mapeditor.main;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import indi.shuolei.mapeditor.instance.Layer;

import indi.shuolei.mapeditor.substage.NewMapStage.OnNewMapDialogActionListener;
import indi.shuolei.mapeditor.canvas.SceneCanvas;
import indi.shuolei.mapeditor.canvas.ModelType;
import indi.shuolei.mapeditor.substage.NewMapStage;
import indi.shuolei.mapeditor.instance.Map;


public class MainController implements Initializable {
	private NewMapStage newMapStage;
	private SceneCanvas sceneCanvas;
	public int index = 0;
	private SimpleIntegerProperty clickTree = new SimpleIntegerProperty();
	
	//draw thread in Canvas
	private long threadSleep = 50;	
	private Thread drawThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (isRunning) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						
						if (sceneCanvas != null) {
							sceneCanvas.draw();
//							if (isNewOrOpenMap && layerList.size() > 0)
//								nowMousePositionLabel.setText(mapCanvas.getMouseCols() + " , "
//										+ mapCanvas.getMouseRows());
						}
					}
				});
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	public static boolean isRunning = true;
	@FXML
	private Slider scaleRatio;
	@FXML
	private Label mapSizeLabel, nowMousePositionLabel;
	@FXML
	private ScrollPane mapScrollPane;
	@FXML
	private Label mScaleLabel;
	@FXML
	private ListView<String> layerListView;
	@FXML
	private Slider layerAlphaSlider;
	@FXML
	private CheckBox layerShowCheck, layerColliderCheck;
	
	private ObservableList<String> layerList = FXCollections.observableArrayList();
	private List<Layer> LayerList = new ArrayList<>();
	
	@FXML
	public void onNewMapAction(ActionEvent e) {
		newMapStage.showAlertDialog();		
	}
	
	@FXML
	public void onButtonClick(ActionEvent event) {
		System.out.println("Click Button!");
	}
	
	@FXML
	public void onClickTreeAction11_h(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 1;
		clickTree.set(11);
	}
	public void onClickTreeAction11_v(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 1;
		clickTree.set(13);
	}
	@FXML
	public void onClickTreeAction12(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 2;
		clickTree.set(12);
	}
	@FXML
	public void onClickTreeAction22(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 3;
		clickTree.set(22);
	}
	@FXML
	public void onClickTreeAction24(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 4;
		clickTree.set(24);
	}
	@FXML
	public void onClickTreeAction26(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 5;
		clickTree.set(26);
	}
	@FXML
	public void onClickCrossroadAction01(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 6;
		clickTree.set(30);
	}
	//Traffic Signs	
	//horizontal sign
	@FXML
	public void onClickTS40_h(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 7;
		//the index of traffic signs is from 51 to 60 
		clickTree.set(51);
	}
	//vertical sign
	@FXML
	public void onClickTS40_v(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 7;
		//the index of traffic signs is from 51 to 60 
		clickTree.set(52);
	}
	@FXML
	public void onClickTS50_h(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 8;
		clickTree.set(53);
	}
	@FXML
	public void onClickTS50_v(ActionEvent event) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		sceneCanvas.index = 8;
		clickTree.set(54);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sceneCanvas = new SceneCanvas(Map.getInstance().getMapWidth(), Map.getInstance().getMapHeight());
		//System.out.println(Map.getInstance().getMapWidth());	
		//print at the first time, result is 0
		
		sceneCanvas.NowSelectLayerProperty().bind(layerListView.getSelectionModel().selectedIndexProperty());
		sceneCanvas.ClickTree().bind(clickTree);
		sceneCanvas.setMapLayerList(LayerList);
		
		// 图层列表
		layerListView.setItems(layerList);
		layerListView.setEditable(true);
		layerListView.setCellFactory(TextFieldListCell.forListView());
		
		layerListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(EditEvent<String> event) {
				layerList.set(event.getIndex(), event.getNewValue());
				LayerList.get(event.getIndex()).setLayerName(event.getNewValue());
			}
		});
	
		layerListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			int index = newValue.intValue();
				if (index >= 0 && index < LayerList.size()) {
					Layer mapLayer = LayerList.get(index);
					layerAlphaSlider.setValue(mapLayer.getAlpha());
					layerShowCheck.setSelected(mapLayer.isVisible());	
					layerColliderCheck.setSelected(mapLayer.isCollider());
				}
			}
		});
		
		// 图层alpha值的修改
		layerAlphaSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int index = layerListView.getSelectionModel().selectedIndexProperty().get();
				if (index >= 0 && index < LayerList.size()) {
					Layer mapLayer = LayerList.get(index);
					mapLayer.setAlpha(newValue.doubleValue());
				}
			}
		});
				
		//涂层显示的修改
		layerShowCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				int index = layerListView.getSelectionModel().selectedIndexProperty().get();
				if (index >= 0) {
					Layer mapLayer = LayerList.get(index);
					mapLayer.setVisible(newValue);
				}
			}
		});
				
		//涂层collision修改
		layerColliderCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				int index = layerListView.getSelectionModel().selectedIndexProperty().get();
				if (index >= 0) {
					Layer mapLayer = LayerList.get(index);
					mapLayer.setCollider(newValue);
				}
			}
		});
				
		
		
		scaleRatio.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String value = newValue.toString().subSequence(0, 3).toString();
				mScaleLabel.setText(value);
				sceneCanvas.setScale(newValue.doubleValue());
				double width = Map.getInstance().getMapWidth() * newValue.doubleValue();
				double height = Map.getInstance().getMapHeight() * newValue.doubleValue();
				sceneCanvas.setWidth(width);
				sceneCanvas.setHeight(height);
			}
		});		
		
		mapScrollPane.setContent(sceneCanvas);
		drawThread.start();
		// 对话框
		newMapStage = new NewMapStage();
		newMapStage.setOnNewMapDialogActionListener(new OnNewMapDialogActionListener() {

			@Override
			public void onNewMapOkAction() {
				//clearAll();
				//newOrOpenMap();
				//openMapFile = null;
				// 设置地图画布大小
				sceneCanvas.setWidth(Map.getInstance().getMapWidth());
				sceneCanvas.setHeight(Map.getInstance().getMapHeight());      
				//System.out.println(Map.getInstance().getMapWidth());
				//Have the corresponding result, when click "OK".
				
				
				//mapSizeLabel.setText(TiledMap.getInstance().getMapWidth() + " x "
				//		+ TiledMap.getInstance().getMapHeight());
			}

			@Override
			public void onNewMapCancelAction() {

			}
		});	
	}

	@FXML
	public void onAddNewLayerAction(ActionEvent e) {
		String defaultName = "新建图层";
		layerList.add(defaultName);
		Layer tiledMapLayer = new Layer();
		tiledMapLayer.setLayerName(defaultName);
		LayerList.add(tiledMapLayer);
	}
	@FXML
	public void onDeleteLayerAction(ActionEvent e) {
		int index = layerListView.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			layerList.remove(index);
			LayerList.remove(index);
		}
	}
	@FXML
	public void onLayerUpAction(ActionEvent e) {
		int index = layerListView.getSelectionModel().getSelectedIndex();
		if (index > 0) {
			String layerStr = layerList.remove(index - 1);
			Layer layer = LayerList.remove(index - 1);
			layerList.add(index, layerStr);
			LayerList.add(index, layer);
		}
	}
	@FXML
	public void onLayerDownAction(ActionEvent e) {
		int index = layerListView.getSelectionModel().getSelectedIndex();
		if (index < layerList.size() - 1) {
			String layerStr = layerList.remove(index);
			Layer layer = LayerList.remove(index);
			layerList.add(index + 1, layerStr);
			LayerList.add(index + 1, layer);
		}
	}
	
	
	
	
	
	
	
}
