package bloodUnitCollection;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Donor.databaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bloodUnitCollectionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bgroup;
     
    @FXML
    private ComboBox<String> mobile;

    @FXML
    private ImageView picDisplay;

    @FXML
    private DatePicker picker;
     
    Connection con;
    PreparedStatement pst;
    String f1=null;
    ResultSet table;
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void onMobile(ActionEvent event) {
       
    }

    @FXML
    void onClear(ActionEvent event) {
     
    	mobile.setValue("");
    	bgroup.setText("");
    	picDisplay.setImage(null);
    	picker.setValue(null);
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
        		
        		bgroup.setText(table.getString("bgroup"));
        		
        		Image image = new Image(new FileInputStream(table.getString("image")));
        		picDisplay.setImage(image);
        		
        	}
        	}
        	catch(Exception exp)
        	{ 		
        	}
    	return jasoos;
    }
    @FXML
    void onSearch(ActionEvent event) {

    	String str= mobile.getSelectionModel().getSelectedItem();
    	if(findRecord(str)==false)
    			showMsg("Invalid mobile no....");
    	
    }
    boolean chkmobileno(String mobileno)
    {
    	boolean jasoos=false;
    	try {
        	pst=con.prepareStatement("select * from donor_table where mobile=?");
        	pst.setString(1, mobile.getSelectionModel().getSelectedItem());
        	
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
    void onUpload(ActionEvent event) {
    	LocalDate local=picker.getValue();
    	
    	String str=mobile.getSelectionModel().getSelectedItem();
    	if(chkmobileno(str)==false)
    			showMsg("Invalid mobile no....");
    	try {
			pst=con.prepareStatement("insert into donations values(?,?,?)");
			pst.setString(1, mobile.getSelectionModel().getSelectedItem());
			pst.setString(2, bgroup.getText());
			java.sql.Date date=java.sql.Date.valueOf(local);

			pst.setDate(3, date);
			pst.executeUpdate();
			
			String bg=bgroup.getText();
			String bg1=null;
			if(bg.equals("A+"))
				bg1="Ap";
			if(bg.equals("A-"))
				bg1="An";
			if(bg.equals("B+"))
				bg1="Bp";
			if(bg.equals("B-"))
				bg1="Bn";
			if(bg.equals("AB+"))
				bg1="ABp";
			if(bg.equals("AB-"))
				bg1="ABn";
			
			String s="update total_record set "+bg1+"="+bg1+" +1";

			pst=con.prepareStatement(s);

			pst.executeUpdate();
			showMsg("Record Inserted Successfullyyyy");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      
    }

    @FXML
    void initialize() {
        assert bgroup != null : "fx:id=\"bgroup\" was not injected: check your FXML file 'bloodUnitCollectionView.fxml'.";
        assert mobile != null : "fx:id=\"mobile\" was not injected: check your FXML file 'bloodUnitCollectionView.fxml'.";
        assert picDisplay != null : "fx:id=\"picDisplay\" was not injected: check your FXML file 'bloodUnitCollectionView.fxml'.";
        assert picker != null : "fx:id=\"picker\" was not injected: check your FXML file 'bloodUnitCollectionView.fxml'.";
        con=	databaseConnection.doConnect();
        try {
			pst=con.prepareStatement("select distinct mobile from donor_table");
			ArrayList<String> item=new ArrayList<String>();
	        table=pst.executeQuery();
	        while(table.next())
	        {
	        	item.add(table.getString("mobile"));
	        }
	        mobile.getItems().setAll(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }

}
