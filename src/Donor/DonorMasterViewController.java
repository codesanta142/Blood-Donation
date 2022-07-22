package Donor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField city;

    @FXML
    private TextField disease;

    @FXML
    private Button find;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private ComboBox<String> group;

    @FXML
    private ImageView image1;

    @FXML
    private TextField mobile;

    @FXML
    private TextField name;
    Connection con;
    PreparedStatement pst;
    String f1=null;
    ResultSet table;
    @FXML
    void onBloodGroup(ActionEvent event) {

    }

    @FXML
    void onBrowse(ActionEvent event) {
    	FileChooser file=new FileChooser();
        file.getExtensionFilters().add(new ExtensionFilter("JPG FILES","*.jpg") );
        File f=file.showOpenDialog(null);
        if(f!=null)
        {
      	  try {
  			Image img=new Image(new FileInputStream(f.getAbsoluteFile()));
  			image1.setImage(img);
  			f1=f.getAbsolutePath();
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      	  
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from donor_table where mobile=?");
			pst.setString(1, mobile.getText());
			int count=pst.executeUpdate();
			if(count==0)
				showMsg("Invalid Mobile Number");
			else
			showMsg("Deleted successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void onFind(ActionEvent event) {
    	String str=mobile.getText();
    	if(findRecord(str)==false)
    			showMsg("Invalid mobile no....");
    }

    @FXML
    void onGender(ActionEvent event) {

    }
    boolean chkmobileno(String mobileno)
    {
    	boolean jasoos=false;
    	try {
        	pst=con.prepareStatement("select * from donor_table where mobile=?");
        	pst.setString(1,mobile.getText());
        	
        	table=pst.executeQuery();
        	      	
        	
        	while(table.next())
        	{
        		jasoos=true;
        	}
        	}
        	catch(Exception exp)
        	{ 		
        	}
    	return jasoos;
    }

    @FXML
    void onNew(ActionEvent event) {
    	
    	mobile.setText("");
    	name.setText("");
        address.setText("");
        city.setText("");
    	disease.setText("");
    	age.setText("");
    	gender.setValue("");
    	group.setValue("");
    	image1.setImage(null);

    }
    void clear()
    {
    	mobile.setText("");
    	name.setText("");
        address.setText("");
        city.setText("");
    	disease.setText("");
    	age.setText("");
    	gender.setValue("");
    	group.setValue("");
    	image1.setImage(null);
    }
    boolean findRecord(String mobileno)
    {
    	boolean jasoos=false;
    	try {
        	pst=con.prepareStatement("select * from donor_table where mobile=?");
        	pst.setString(1, mobileno);
        	
        	table=pst.executeQuery();
        	      	
        	
        	while(table.next())
        	{
        		jasoos=true;
        		name.setText(table.getString("name"));
        		gender.setValue(table.getString("gender"));
        		address.setText(table.getString("address"));
        		city.setText(table.getString("city"));
        		group.setValue(table.getString("bgroup"));
        		age.setText(String.valueOf(table.getInt("age")));
        		disease.setText(table.getString("disease"));
        		Image image = new Image(new FileInputStream(table.getString("image")));
        		image1.setImage(image);
        		
        	}
        	}
        	catch(Exception exp)
        	{ 		
        	}
    	return jasoos;
    }
    @FXML
    void onRegister(ActionEvent event) {
    	String mobile1=mobile.getText();
    	if(chkmobileno(mobile1)==true)
    		{
    		showMsg("Mobile no already Exists...");
    		return;
    		}
    	try {
			pst=con.prepareStatement("insert into donor_table values(?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, mobile.getText());
			pst.setString(2, f1);
			pst.setString(3, name.getText());
			pst.setString(4, gender.getSelectionModel().getSelectedItem());
			pst.setString(5,address.getText());
			pst.setString(6,city.getText());
			pst.setString(7, group.getSelectionModel().getSelectedItem());
			pst.setString(8, age.getText());
			pst.setString(9, disease.getText());
			pst.executeUpdate();
			showMsg("Record Inserted Successfullyyyy");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void onUpdate(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("update donor_table set image=?,name=?,gender=?, address=?, city=?, bgroup=?,age=?,disease=? where mobile=?");
    		pst.setString(9, mobile.getText());
			pst.setString(1, f1);
			pst.setString(2, name.getText());
			pst.setString(3, gender.getSelectionModel().getSelectedItem());
			pst.setString(4,address.getText());
			pst.setString(5,city.getText());
			pst.setString(6, group.getSelectionModel().getSelectedItem());
			pst.setString(7, age.getText());
			pst.setString(8, disease.getText());
			pst.executeUpdate();
			int count=pst.executeUpdate();
			if(count==0)
				showMsg("Invalid Mobile Number");
			else
				
			showMsg("Record Updated Successfullyyyy");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert age != null : "fx:id=\"age\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert city != null : "fx:id=\"city\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert disease != null : "fx:id=\"disease\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert find != null : "fx:id=\"find\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert group != null : "fx:id=\"group\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert image1 != null : "fx:id=\"image\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert mobile != null : "fx:id=\"mobile\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        con=	databaseConnection.doConnect();
        ArrayList<String> items=new ArrayList<String>(Arrays.asList("Female","Male"));
        gender.getItems().setAll(items);
        
        ArrayList<String> items1=new ArrayList<String>(Arrays.asList("A-","A+","B-","B+","AB-","AB+"));
        group.getItems().setAll(items1);
       
    }

}
