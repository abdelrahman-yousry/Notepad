

// * @author Abdelrahman Yousry


package notepad ;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Abdelrahman Yousry
 */
public class Day_2_advanced extends Application {
    BorderPane pane ;
    MenuItem save ;
    TextArea area;  
    MenuItem open ;
    MenuItem compiler ;
    MenuItem run ;
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Day_2_advanced.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void init() throws Exception {
    MenuBar bar = new MenuBar();
    Menu file = new Menu("File");
    Menu edit = new Menu("Edit");
    Menu help = new Menu("Help");
    
    MenuItem newfile = new MenuItem("New");
    open = new MenuItem("Open");
    save = new MenuItem("Save");
    MenuItem exit = new MenuItem("Exit");
    
    MenuItem undo = new MenuItem("Undo");
    MenuItem cut  = new MenuItem("Cut");
    MenuItem copy = new MenuItem("Copy");
    MenuItem paste = new MenuItem("Paste");
    MenuItem delete = new MenuItem("Delete");
    MenuItem select = new MenuItem("SelectAll");
    
    MenuItem about = new MenuItem("About");
    compiler = new MenuItem("Compiler");   
    run = new MenuItem("Run");   
    SeparatorMenuItem splt1 = new SeparatorMenuItem();
    SeparatorMenuItem splt2 = new SeparatorMenuItem();
    SeparatorMenuItem splt3 = new SeparatorMenuItem();

    
    file.getItems().addAll(newfile,open,save,splt1,exit);
    //file.getItems().add(3,splt1);
    edit.getItems().addAll(undo,splt2,cut,copy,paste,delete,splt3,select);
    help.getItems().addAll(about,compiler,run);
    bar.getMenus().addAll(file,edit,help);
    area=new TextArea();    
    area.setPrefColumnCount(100);
    area.setPrefRowCount(100);

   
              
    undo.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.undo();
        }
    });
    cut.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.cut();
        }
    });
    copy.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.copy();
        }
    });    
    paste.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.paste();
        }
    });  
    delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.deleteText(area.getSelection());
        }
    });    
    select.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.selectAll();
        }
    });
    exit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Platform.exit();
        }
    });    

     newfile.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            area.clear();
        }
    }); 
     
     
    pane = new BorderPane();
    pane.setTop(bar);
    pane.setCenter(area);
    }
    
    @Override
   
public void start(Stage primaryStage)
{

    //Creating a File chooser
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
    //Adding action on the menu item
    save.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event) {
    //Opening a dialog box
    File file = fileChooser.showSaveDialog(primaryStage);
    saveTextToFile(area.getText(),file);
    }    
    });
        //Creating a File chooser
    FileChooser fileChooser2 = new FileChooser();
    fileChooser2.setTitle("Open");
    fileChooser2.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
    //Adding action on the menu item
    open.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event) {
    //Opening a dialog box
    File file = fileChooser2.showOpenDialog(primaryStage);
        try {
            if (file != null) {
                byte[] c;
                try (FileInputStream fis = new FileInputStream(file)) {
                    c = new byte[fis.available()];
                    fis.read(c);
                }
        String str = new String(c);    
        area.setText(str);
            }
        } catch (IOException ex) {
            Logger.getLogger(Day_2_advanced.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }    
    });
        //Creating a File chooser
    FileChooser fileChooser3 = new FileChooser();
    fileChooser3.setTitle("Open");
    fileChooser3.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
    //Adding action on the menu item
    compiler.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event) {
    //Opening a dialog box
            File file2 = fileChooser3.showOpenDialog(primaryStage);
            try {
                    Runtime.getRuntime().exec("javac " + file2.getAbsolutePath());
                }
             catch (IOException ex) {
                Logger.getLogger(Day_2_advanced.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    });    
        //Creating a File chooser
    FileChooser fileChooser4 = new FileChooser();
    fileChooser4.setTitle("Open");
    fileChooser4.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
    //Adding action on the menu item
    run.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event) {
    //Opening a dialog box
            File file3 = fileChooser3.showOpenDialog(primaryStage);
            String S=file3.getName().split("\\.")[0];
            try {
                    Runtime.getRuntime().exec("java -cp "+file3.getParent()+" "+S);
                }
             catch (IOException ex) {
                Logger.getLogger(Day_2_advanced.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    });  
    
    Scene scene = new Scene(pane,300,400);
    primaryStage.setTitle("NotePad");
    primaryStage.setScene(scene);
    primaryStage.show();

}
        public static void main(String[] args) {
        launch(args);
    }
    
}


