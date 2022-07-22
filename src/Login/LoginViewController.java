package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void onLogin(ActionEvent event) {
    	if(name.getText().equals("kirti")&&password.getText().equals("123"))
    	{
    		try{
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Panel/PanelView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        		
        		
    			//to hide the opened window
    			 
    			  /* Scene scene1=(Scene)btnComboApp.getScene();
    			   scene1.getWindow().hide();
    			 */

    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		clear();
    	}
    	else
    	{
    		showMsg("Invalid userid or password");
    	}
    }
    void clear()
    {
    	
        name.setText(null);
        password.setText(null);
    	
    }

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'LoginView.fxml'.";

    }

}
