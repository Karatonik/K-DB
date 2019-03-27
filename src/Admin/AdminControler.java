package Admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminControler implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TableView<studentData> studenttable;
    @FXML
    private TableColumn<studentData,String> idcolumn;
    @FXML
    private TableColumn<studentData,String> fnamecolumn;
    @FXML
    private TableColumn<studentData,String> lnamecolumn;
    @FXML
    private TableColumn<studentData,String> emailcolumn;
    @FXML
    private TableColumn<studentData,String> dobcolumn;
    private dbConnection dc;
    private ObservableList<studentData> data;

    private final String sql="SELECT * FROM students";
    public void initialize(URL url, ResourceBundle rb){
this.dc=new dbConnection();
    }
    @FXML
    private void loadstudentData(ActionEvent event){
        try {
            Connection conn=dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery(sql);
            while(rs.next()){
                this.data.add(new studentData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException e){
            System.err.println("ERROR"+e);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<studentData,String>("ID"));
        this.fnamecolumn.setCellValueFactory(new PropertyValueFactory<studentData,String>("firstName"));
        this.lnamecolumn.setCellValueFactory(new PropertyValueFactory<studentData,String>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<studentData,String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<studentData,String>("DOB"));
        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);
    }
    @FXML
    private void addstudent(ActionEvent event){
        String sqlInsert="INSERT INTO students(id,fname,lname,email,DOB) VALUES (?,?,?,?,?) ";
        try{
Connection conn=dbConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sqlInsert);
            ps.setString(1,this.id.getText());
            ps.setString(2,this.fname.getText());
            ps.setString(3,this.lname.getText());
            ps.setString(4,this.email.getText());
            ps.setString(5,this.dob.getEditor().getText());
            ps.execute();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    private  void clearFild(ActionEvent event){
this.id.setText("");
        this.fname.setText("");
        this.lname.setText("");
        this.email.setText("");
        this.dob.setValue(null);

    }

}
