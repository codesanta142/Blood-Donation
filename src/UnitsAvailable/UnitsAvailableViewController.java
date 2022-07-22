package UnitsAvailable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UnitsAvailableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label abn;

    @FXML
    private Label abp;

    @FXML
    private Label an;

    @FXML
    private Label ap;

    @FXML
    private Label bn;

    @FXML
    private Label bp;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    @FXML
    void initialize() {
        assert abn != null : "fx:id=\"abn\" was not injected: check your FXML file 'UnitsAvailableView.fxml'.";
        assert abp != null : "fx:id=\"abp\" was not injected: check your FXML file 'UnitsAvailableView.fxml'.";
        assert an != null : "fx:id=\"an\" was not injected: check your FXML file 'UnitsAvailableView.fxml'.";
        assert ap != null : "fx:id=\"ap\" was not injected: check your FXML file 'UnitsAvailableView.fxml'.";
        assert bn != null : "fx:id=\"bn\" was not injected: check your FXML file 'UnitsAvailableView.fxml'.";
        assert bp != null : "fx:id=\"bp\" was not injected: check your FXML file 'UnitsAvailableView.fxml'.";
        con=	databaseConnection.doConnect();
        try {
			pst=con.prepareStatement("select * from total_record");
			 table=pst.executeQuery();
		        while(table.next())
		        {
		        ap.setText(table.getString("Ap"));
		        an.setText(table.getString("An"));
		        abp.setText(table.getString("ABp"));
		        abn.setText(table.getString("ABn"));
		        bp.setText(table.getString("Bp"));
		        bn.setText(table.getString("Bn"));
		        
		} 
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        
    

}
