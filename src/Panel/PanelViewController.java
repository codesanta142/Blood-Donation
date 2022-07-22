package Panel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void onAvailable(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UnitsAvailable/UnitsAvailableView.fxml"));
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

    }

    @FXML
    void onDonation(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bloodUnitCollection/bloodUnitCollectionView.fxml"));
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

    }


    @FXML
    void onHistory(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/allhistory/allhistoryview.fxml"));
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
    }

    @FXML
    void onIssue(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/issueBlood/issueBloodview.fxml"));
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

     
    }
    
    @FXML
    void onMeet(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MeetDeveloper/MeetDeveloperView.fxml"));
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
    }

    
    @FXML
    void onRegister(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Donor/DonorMasterView.fxml"));
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

    }

    @FXML
    void initialize() {

    }

}
