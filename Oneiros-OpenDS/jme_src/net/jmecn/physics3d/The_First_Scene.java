package net.jmecn.physics3d;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.math.*;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import java.io.File;
import java.io.IOException;
import sun.applet.Main;


public class The_First_Scene extends SimpleApplication {
    
    float[] scaleTextureCoordinates  = {1f,1f};
    float[] init_rotation = {0f,0f,0f};
    float[] first_cross_position = {0.0f,-0.01f,207f};

    public static void main(String[] args) {
        The_First_Scene app = new The_First_Scene();
        app.start();
    }
    private BulletAppState bulletAppState;

    @Override
    public void simpleInitApp() {
        PhysicsSpace physicsSpace = Space_init();
        cam_setting(100, 0f,8f,0f);
        add_Grand(physicsSpace);
//        add_cross();
//        add_mainroad(4);
//        T_cross_init();
//        Speed_limit("Scenes/Task/speedLimit50/speedLimit50.scene");
//        Guidep();
//        add_park("Scenes/Task/Parking_building/Parking.scene");
//        fence_init(5);
//        tree_init(5);
//        DirectionalLight sun1 = new DirectionalLight();                         // light1
//        sun1.setDirection(new Vector3f(0.1f, 0.5f, 1.0f).normalizeLocal());
//        rootNode.addLight(sun1);
//        DirectionalLight sun2 = new DirectionalLight();                         // light2
//        sun2.setDirection(new Vector3f(1f, 0.1f, 1f).normalizeLocal());
//        rootNode.addLight(sun2);
        Export("Result/scene2.j3o");
    }
    
    public void add_park(String path){
        float[] scale = new float[] {1f, 1f, 1f};
        Spatial park_building = Load_model(path, scale[0],scale[1],scale[2]);
        float[] position = new float[] {768f,8f,1302f };
        float[] rotation = new float[] {0f, 3.1415926f, 0};
        add_sp(park_building,position, rotation);
    }
    
    public void add_cross(){
        float[] cross_size = {9f,0.01f,9f};
        float[] cross_raod_size = {9f,0.01f,12.0f};
        add_cross(cross_size,cross_raod_size,first_cross_position);
    }
    
   public void T_cross_init(){
        Node T_Node1 = new Node("T_Node1");
        rootNode.attachChild(T_Node1);
        float[] N1_position1 = {768f,-0.01f,975f};
        T_cross_node(T_Node1, 0,N1_position1);
        
        Node T_Node2 = new Node("T_Node2");
        rootNode.attachChild(T_Node2);
        float[] N2_position1 = {768f,-0.01f,207f};
        T_cross_node(T_Node2, 1,N2_position1);
        
        Node T_Node3 = new Node("T_Node3");
        rootNode.attachChild(T_Node3);
        float[] N3_position = {0f,-0.01f,975f};
        T_cross_node(T_Node3, 2,N3_position);
    }

    
    public void T_cross_node(Node N, int r, float[] N_position){
        float[] Tcross_size = {9f,0.01f,9f};
        float[] Tcross_raod_size = {9f,0.01f,12.0f};
        N.rotate(0f,r*1.57059f,0f);
        N.setLocalTranslation(N_position[0], N_position[1], N_position[2]);
        add_T_cross(Tcross_size,Tcross_raod_size,N);
    }
            
    public void fence_init(int num){
        Node[] fn = new Node[num];
        
        for(int i = 0; i<num; i++){
            fn[i] = new Node("Node");
            rootNode.attachChild(fn[i]);
        }
        fn[0].setLocalTranslation(0f, -0.1f, 392f);
        add_Fence(fn[0]);
        fn[1].setLocalTranslation(0f, -0.1f, -325f);
        add_Fence(fn[1]);
        fn[2].setLocalTranslation(768f, -0.1f, 392f);
        add_Fence(fn[2]);
        fn[3].setLocalTranslation(185f, -0.1f, 207f);
        fn[3].rotate(0f, 1.57059f, 0f);
        add_Fence(fn[3]);
        fn[4].setLocalTranslation(185f, -0.1f, 975f);
        fn[4].rotate(0f, 1.57059f, 0f);
        add_Fence(fn[4]);
    }
    
    public void tree_init(int num){
        Node[] tr= new Node[num];
        for(int i = 0; i<num; i++){
            tr[i] = new Node("Node");
            rootNode.attachChild(tr[i]);
        }
        tr[0].setLocalTranslation(-11f, -0.1f, 272f);
        add_Tree(tr[0]);
        tr[1].setLocalTranslation(-11f, -0.1f, -495f);
        add_Tree(tr[1]);
        tr[2].setLocalTranslation(757f, -0.1f, 272f);
        add_Tree(tr[2]);
        tr[3].setLocalTranslation(65f, -0.1f, 218f);
        tr[3].rotate(0f, 1.57059f, 0f);
        add_Tree(tr[3]);
        tr[4].setLocalTranslation(65f, -0.1f, 986f);
        tr[4].rotate(0f, 1.57059f, 0f);
        add_Tree(tr[4]);
        
        
    }
    
    public void add_Grand(PhysicsSpace physicsSpace){
        Texture grand_tex = tex_init("Scenes/Task/two/grass.png", true);
        float[] size = {3000f,0.1f,3000f};
        float[] num = {3000,3000};
        Spatial grand = Spatial_init(size,num, grand_tex);
        grand.setLocalTranslation(400, -0.12f, 398);
        rootNode.attachChild(grand);
        physicsSpace.add(grand);
    }
    
    
    
    public void add_mainroad(int num){
        float[] main_size = {9f,0.01f,325f};
        Texture road_tex = tex_init("Scenes/Task/two/ML2.jpg", true);
        float[] rmian_scale = {21f,2f};
        Spatial main_road1 = Spatial_init(main_size, rmian_scale, road_tex);
        Spatial last_road1 = Spatial_init(main_size, rmian_scale, road_tex);
        float[] first_position = {0f,0.01f,-175f};
        add_sp(main_road1, first_position, init_rotation);
        float[] last_postion = {768f,0.01f,1357f};
        add_sp(last_road1,last_postion, init_rotation);
        //Spatial main_road = Spatial_init(main_size, rmian_scale, road_tex);
        for(int i = 0; i<num; i++){
            Spatial main_road = Spatial_init(main_size, rmian_scale, road_tex);
            if( i %2 == 1){
                float[] road_roatation = {0,1.57079f,0};
                float[] road_position = {first_cross_position[1]+384f, first_cross_position[1], first_cross_position[2]+384*(i-1)};
                add_sp(main_road, road_position, road_roatation);
            }
            else{
                float[] road_position = {first_cross_position[1]+384*(i), first_cross_position[1], first_cross_position[2]+384f};
                add_sp(main_road, road_position, init_rotation);
            }
        }  
    }
    
    
    
    
    public PhysicsSpace Space_init(){
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        assetManager.registerLocator("assets", FileLocator.class);
        PhysicsSpace physicsSpace = bulletAppState.getPhysicsSpace();
        return physicsSpace;
    }
    
    
    public void cam_setting(int speed, float x, float y, float z){
        
    flyCam.setMoveSpeed(speed);
    cam.setLocation(new Vector3f(x,y,z));
    cam.setRotation(new Quaternion(-0.046265673f, 0.9518722f, -0.1815604f, -0.2425582f));
    
    }
        

    public Texture tex_init(String path, Boolean mode) {
    
        Texture texture = assetManager.loadTexture(path);
        if(mode)
        {texture.setWrap(WrapMode.Repeat);}
        else
        {texture.setWrap(WrapMode.EdgeClamp);}
        return texture;
    }
    
    public Spatial Spatial_init(float[] size, float[] r_num, Texture tex){
    
        Mesh box = new Box (size[0],size[1],size[2]);
        box.scaleTextureCoordinates(new Vector2f(r_num[0], r_num[1]));
        Material box_brick = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        box_brick.setTexture("ColorMap", tex);
        Geometry geo = new Geometry("floor", box);
        geo.setMaterial(box_brick);
        Spatial sp = geo;
        return sp;
    }

    
    public void add_sp(Spatial sp, float[] position, float[] rotation){
    
        sp.rotate(rotation[0], rotation[1], rotation[2]);
        sp.setLocalTranslation(position[0], position[1], position[2]);
        rootNode.attachChild(sp);
    }   
    
    public void add_sp(Spatial sp, float x, float y, float z, float[] rotation){
    
        sp.rotate(rotation[0], rotation[1], rotation[2]);
        sp.setLocalTranslation(x, y, z);
        rootNode.attachChild(sp);
    }
    
    public void add_cross(float[] cross_size,float[] road_size,float[] position){
        float cha = cross_size[0] + road_size[2];
        Texture tex_cross = tex_init("Scenes/Task/two/cross.jpg", true);
        float[] cross_roatation = {0,0,0};
        Spatial cross_sp = Spatial_init(cross_size, scaleTextureCoordinates, tex_cross);
        add_sp(cross_sp, position, cross_roatation);
        Texture soild_line_tex = tex_init("Scenes/Task/two/solid_line.jpg", true);
        float[] soild_line_size = {9f,0.01f,13f};
        float cha2 = soild_line_size[2] +cha + road_size[2];
        float[] soild_scale = {2,2};
        Texture tex_crossr = tex_init("Scenes/Task/two/crossing.jpg", true);
        //Spatial[] solid_line = Spatial_init(soild_line_size, soild_scale, soild_line_tex,4);
        for(int i = 0;i<4;i++){
            Spatial cross_line = Spatial_init(road_size, scaleTextureCoordinates, tex_crossr);
            Spatial solid_line = Spatial_init(soild_line_size, soild_scale, soild_line_tex);
            
            if(i % 2 == 0 ){
               i+=2;
               float[] crossr_roatation = {0,i*1.57079f,0};
               i-=2;
               float[] road_position = {position[0], position[1], position[2]+(i-1)*cha};
               float[] solid_position = {position[0], position[1], position[2]+(i-1)*cha2};
               add_sp(cross_line, road_position, crossr_roatation);
               add_sp(solid_line, solid_position, crossr_roatation);
            }
            else{
                float[] crossr_roatation = {0,-i*1.57079f,0};
                float[] road_position = {position[1]+(i-2)*cha, position[1], position[2]};
                float[] solid_position = {position[1]+(i-2)*cha2, position[1], position[2]};
                add_sp(cross_line, road_position, crossr_roatation);
                add_sp(solid_line, solid_position, crossr_roatation);
            }
        }
    }
    
    public void Light(Vector3f direction){
       
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(direction.normalizeLocal());
        rootNode.addLight(sun);
    }
    
    
    public void add_T_cross(float[] T_cross_size,float[] T_road_size, Node N){
        
        float cha = T_cross_size[0] + T_road_size[2];
        Texture  Tcross_tex = tex_init("Scenes/Task/two/cross.jpg", true);
        Spatial Tcross_sp = Spatial_init(T_cross_size, scaleTextureCoordinates, Tcross_tex);
        Tcross_sp.rotate(0, 3.1415926f, 0);
        N.attachChild(Tcross_sp);
        Texture soild_line_tex = tex_init("Scenes/Task/two/solid_line.jpg", true);
        float[] soild_line_size = {9f,0.01f,13f};
        float cha2 = soild_line_size[2] +cha + T_road_size[2];
        float[] soild_scale = {2,2};
        Texture Tline_tex1 = tex_init("Scenes/Task/two/s2_new.jpg", true);
        Spatial cross_line1 = Spatial_init(T_road_size, scaleTextureCoordinates, Tline_tex1);
        Texture Tline_tex2 = tex_init("Scenes/Task/two/s3_new.jpg", true);
        Spatial cross_line2 = Spatial_init(T_road_size, scaleTextureCoordinates, Tline_tex2);
        Texture Tline_tex3 = tex_init("Scenes/Task/two/s4_new.jpg", true);
        Spatial cross_line3 = Spatial_init(T_road_size, scaleTextureCoordinates, Tline_tex3);
        
        
        for(int i = 0; i<3; i++){
            Spatial solid_line = Spatial_init(soild_line_size, soild_scale, soild_line_tex);
            if(i%2 == 0){
                solid_line.setLocalTranslation(0f, 0f, (i-1)*cha2);
                N.attachChild(solid_line);
                if(i ==0){
                    cross_line1.setLocalTranslation(0f, 0f, (i-1)*cha);
                    cross_line1.rotate(0, 3.1415926f, 0);
                    N.attachChild(cross_line1);
                }
                else{
                    cross_line2.setLocalTranslation(0f, 0f, (i-1)*cha);
                    //cross_line2.rotate(0f, 3.1415926f, 0f);
                    N.attachChild(cross_line2);
                }
            }
            else{
                solid_line.rotate(0f, 1.57059f, 0f);
                solid_line.setLocalTranslation(-(i)*cha2, 0f, 0f);
                N.attachChild(solid_line);
                cross_line3.setLocalTranslation(-(i)*cha, 0f, 0f);
                cross_line3.rotate(0f, -1.57059f, 0f);
                N.attachChild(cross_line3);
            }
        }
    }
    
    
    
    public Spatial Load_model(String path, float x, float y, float z){
        assetManager.registerLocator("assets", FileLocator.class);
        Spatial model = (Spatial) assetManager.loadModel(path);
        model.scale(x, y, z);
        return model;
    }
    
  
    
    
   public void Speed_limit(String path){
        Spatial[] Speed = new Spatial[7];
        for(int i = 0; i<5; i++){
            Speed[i] = Load_model(path, 2f, 2f, 2f);
            Speed[i].rotate(0f, 3.1415926f, 0);
        }
        Speed[1] = Load_model("Scenes/Task/speedLimit40/speedLimit40.scene", 2f, 2f, 2f);
        Speed[1].rotate(0f, 3.1415926f, 0);
        Speed[3] = Load_model("Scenes/Task/speedLimit40/speedLimit40.scene", 2f, 2f, 2f);
        Speed[3].rotate(0f, 3.1415926f, 0);
        add_sp(Speed[0], -10f, -1.1f, -250f, init_rotation);
        add_sp(Speed[1], -10f, -1.1f, 258f, init_rotation);
        add_sp(Speed[2], 758f, -1.1f, 258f, init_rotation);
        float[] rotarion ={0, 1.57059f,0f}; 
        add_sp(Speed[3], 150f, -1.1f, 217f, rotarion);
        add_sp(Speed[4], 150f, -1.1f, 985f, rotarion);
        Speed[5] = Load_model("Scenes/Task/speedLimitEnd/speedLimitEnd.scene", 2f, 2f, 2f);
        Speed[5].rotate(0f, 1.57059f, 0);
        add_sp(Speed[5], -10f, -1.1f, 785f, rotarion);
        Speed[6] = Load_model("Scenes/Task/speedLimitEnd/speedLimitEnd.scene", 2f, 2f, 2f);
        Speed[6].rotate(0f, 3.1415926f, 0);
        add_sp(Speed[6], 650f, -1.1f, 217f, rotarion);
        
   }
    
    
    public void Guidep(){
       float[] position1 = new float[] {-10f,-1.1f,850f};
       float[] rotation1 = new float[] {0f,-1.57059f,0f};
       float[] rotation2 = new float[] {0f,3.1415926f,0f};
       add_sp( Load_model("Scenes/Task/Parking1/Parking.scene", 2f, 2f, 2f), position1, rotation1);
       float[] position2 = new float[] {758f,-2.1f,850f};
       add_sp( Load_model("Scenes/Task/Parking3/Parking.scene", 2f, 2f, 2f), position2, rotation1);   // lack of Texture
       float[] position3 = new float[] {700,-1.1f,217f};
       add_sp( Load_model("Scenes/Task/Parking2/Parking.scene", 2f, 2f, 2f), position3, init_rotation);
       float[] position4 = new float[] {700,-1.1f,985f};
       add_sp( Load_model("Scenes/Task/Parking2/Parking.scene", 2f, 2f, 2f), position4, init_rotation);
       float[] position5 = new float[] {-10f,-1.1f,70f};
       add_sp( Load_model("Scenes/Task/Parking4/speedLimit50.scene", 2f, 2f, 2f), position5, rotation2);
       
    }
    
    public void add_Fence(Node N){
        
        Spatial[] fen = new Spatial[6];
        for(int i = 0; i<5;i++){
            fen[i] = Load_model("Scenes/Task/fence/fence10.obj", 0.00400f, 0.0013f, 0.002f);
            fen[i].rotate(0f, 1.57059f, 0f);
            fen[i].setLocalTranslation(0.0f, 0.14f, i*114.6f);
            N.attachChild(fen[i]);
        }
        fen[5] = Load_model("Scenes/Task/fence/fence2.obj", 0.004f, 0.0013f, 0.002f);
        fen[5].rotate(0f, -1.57059f, 0f);
        fen[5].setLocalTranslation(0.0f, 0.14f, -92.2f);
        N.attachChild(fen[5]);
    }
    
    public void add_Tree(Node T){
        
        Spatial[] tree = new Spatial[22];
        for(int i = 0; i<11;i++){
            tree[i] = Load_model("Scenes/Task/Tree 02/Tree.obj", 2f, 2f, 2f);
            tree[i].rotate(0f, 1.57059f, 0f);
            tree[i].setLocalTranslation(0.0f, 0.2f, i*60f);
            T.attachChild(tree[i]);
        }
        for(int i = 11; i<22;i++){
            tree[i] = Load_model("Scenes/Task/Tree 02/Tree.obj", 2f, 2f, 2f);
            tree[i].rotate(0f, 1.57059f, 0f);
            tree[i].setLocalTranslation(22f, 0.2f, (i-11)*60f);
            T.attachChild(tree[i]);
        }
    }
   
    public void Export(String export_path){
        
        BinaryExporter exporter = BinaryExporter.getInstance(); 
        assetManager.registerLocator("assets", FileLocator.class);
        File file = new File("assets" + export_path.replace("Scene", "j3o"));
        try {
            exporter.save(rootNode, file);
        } catch (IOException ex) {
            ex.printStackTrace();
    }

    }
    
    
}
