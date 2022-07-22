package issueBlood;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class issueBloodviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> bgroup;

    @FXML
    private TextField hospital;

    @FXML
    private TextField mobile;

    @FXML
    private TextField name;

    @FXML
    private DatePicker picker;

    @FXML
    private TextField purpose;

    @FXML
    private TextField units;
    
	Connection con;
    PreparedStatement pst;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    void clear()
    {
    	bgroup.setValue(null);
    	picker.setValue(null);
    	hospital.setText(null);
    	mobile.setText(null);
        name.setText(null);
        purpose.setText(null);
    	units.setText(null);
    }
    @FXML
    void onUpdate(ActionEvent event) throws Exception {
    	String s=bgroup.getValue();
    	String m=null;
    	if(s.equals("A+"))
    	{
    		m="Ap";
    	}
    	else if(s.equals("A-"))
    	{
    		m="An";
    	}
    	else if(s.equals("B+"))
    	{
    		m="Bp";
    	}
    	else if(s.equals("B-"))
    	{
    		m="Bn";
    	}
    	else if(s.equals("AB+"))
    	{
    		m="ABp";
    	}
    	else if(s.equals("AB-"))
    	{
    		m="ABn";
    	}
    	
    	pst=con.prepareStatement("select "+m+" from total_record");
    	ResultSet table1=pst.executeQuery();
    	int q=0;
    	while(table1.next())
    	{
    		q=table1.getInt(m);

    	}
    	if((q-Integer.parseInt(units.getText())<0))
    	{
    		showMsg("This amount of blood is not available.");
    		clear();
    		return ;
    	}
    	pst=con.prepareStatement("insert into issued values(?,?,?,?,?)");
    	pst.setString(1, name.getText());
    	pst.setString(2,mobile.getText());
    	pst.setString(3, hospital.getText());
    	pst.setString(4, purpose.getText());
    	LocalDate local=picker.getValue();
    	java.sql.Date date=java.sql.Date.valueOf(local);
    	pst.setDate(5, date);
    	pst.executeUpdate();
    	
    	
    	pst=con.prepareStatement("update total_record set "+m+"="+m+"-?");
    	pst.setInt(1,Integer.parseInt(units.getText()) );
    	pst.executeUpdate();
    	showMsg("Record Inserted Successfullyyyy");
    	clear();
    }

    @FXML
    void initialize() {
        assert bgroup != null : "fx:id=\"bgroup\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        assert hospital != null : "fx:id=\"hospital\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        assert mobile != null : "fx:id=\"mobile\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        assert picker != null : "fx:id=\"picker\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        assert purpose != null : "fx:id=\"purpose\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        assert units != null : "fx:id=\"units\" was not injected: check your FXML file 'issueBloodview.fxml'.";
        ArrayList<String> items2=new ArrayList<String>(Arrays.asList("A-","A+","B-","B+","AB-","AB+"));
        bgroup.getItems().setAll(items2);
        
        con=	databaseConnection.doConnect();
    }

}
