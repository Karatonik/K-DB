package Loginapp;

import Admin.AdminControler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsControler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControler implements Initializable {
    LoginModel loginModel=new LoginModel();
@FXML
private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button  loginbutton;
    @FXML
    private Label loginstatus;
    public void initialize(URL url, ResourceBundle rb){
if(this.loginModel.isDatabaseConnected()){
    this.dbstatus.setText("Connected to Database");
}else this.dbstatus.setText(" Not Connected to Database");
this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }
    @FXML
    public void login(ActionEvent event){
        try{
if(this.loginModel.islogin(this.username.getText(),this.password.getText(),((option)this.combobox.getValue()).toString())){
    Stage stage=(Stage)this.loginbutton.getScene().getWindow();
    stage.close();
    switch (((option)this.combobox.getValue()).toString()){
        case "Admin":
            adminLogin();
            break;
        case "Student":
            studentLogin();
            break;

        }
    }
else{
    this.loginstatus.setText("błąd przy logowaniu");
}


        }catch (Exception localException){
            localException.getStackTrace();
        }

    }
    @FXML
    public void studentLogin(){
try{
    Stage userstage=new Stage();
    FXMLLoader loader=new FXMLLoader();
    Pane root=(Pane) loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());
    StudentsControler studentsControler=(StudentsControler) loader.getController();
    Scene scene=new Scene(root);
    userstage.setScene(scene);
    userstage.setTitle("Uczniowie");
    userstage.setResizable(false);
    userstage.show();
}catch (IOException ex){
    ex.printStackTrace();
}
    }
    @FXML
    public  void adminLogin(){
try{
    Stage adminstage= new Stage();
    FXMLLoader loader=new FXMLLoader();
    Pane root=(Pane) loader.load(getClass().getResource("/Admin/AdminFXML.fxml").openStream());
    AdminControler adminControler= (AdminControler)loader.getController();
    Scene scene=new Scene(root);
    adminstage.setScene(scene);
    adminstage.setTitle("Administracja");
    adminstage.setResizable(false);
    adminstage.show();
}catch (IOException ex){
    ex.printStackTrace();
}
    }
}
