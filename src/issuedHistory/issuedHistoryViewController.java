package issuedHistory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class issuedHistoryViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> bgroup;

    @FXML
    private DatePicker date;

    @FXML
    private TableView<issuedBean> records;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    ObservableList<issuedBean> allRecords;
    
    ObservableList<issuedBean> getAllObjects() 
    {
  	ObservableList<issuedBean> ary=FXCollections.observableArrayList();
  	
  	PreparedStatement pst;
  	try {
  		  	pst=con.prepareStatement("select * from issued where bgroup=? and issuedate=?");
  		  	pst.setString(1,bgroup.getValue());
  		    LocalDate local=date.getValue();
      	    java.sql.Date date=java.sql.Date.valueOf(local);
      	    pst.setDate(2, date);
  		
  	table=pst.executeQuery();
  	while(table.next())
  	{
  		String nname=table.getString("name");
  		String mobile=table.getString("mobile");
  		String hospital=table.getString("hospital");
  		String reason=table.getString("reason");
  		String doi=table.getString("issuedate");
  		String bgroup=table.getString("bgroup");
  		issuedBean obj=new issuedBean(nname,mobile,hospital,reason,doi,bgroup);
  		ary.add(obj);
  	}
  	}
  	catch(Exception exp)
  	{ 	
  		System.out.println(exp);
  	}
  	return ary;
  }

    @SuppressWarnings("unchecked")
	@FXML
    void onList(ActionEvent event) {
    	TableColumn<issuedBean, String> nname=new TableColumn<issuedBean, String>("Needy Name");
    	nname.setCellValueFactory(new PropertyValueFactory<>("nname"));
    	nname.setMinWidth(100);
    	
    	TableColumn<issuedBean, String> mobile=new TableColumn<issuedBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<issuedBean, String> hospital=new TableColumn<issuedBean, String>("Hospital Name");
    	hospital.setCellValueFactory(new PropertyValueFactory<>("hospital"));
    	hospital.setMinWidth(100);
    	
    	TableColumn<issuedBean, String> reason=new TableColumn<issuedBean, String>("Reason");
    	reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
    	reason.setMinWidth(100);
    	
    	TableColumn<issuedBean, String> doi=new TableColumn<issuedBean, String>("Date of Issue");
    	doi.setCellValueFactory(new PropertyValueFactory<>("doi"));
    	doi.setMinWidth(100);
    	
    	TableColumn<issuedBean, String> bgroup=new TableColumn<issuedBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));
    	bgroup.setMinWidth(100);
    	
    	records.getColumns().clear();
    	records.getColumns().addAll(nname,mobile,hospital,reason,doi,bgroup);
    	   	
    	
    	allRecords=getAllObjects();	
    	

    	records.setItems(allRecords);
    }
    public void writeExcel( ObservableList<issuedBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BloodIssuedinfo.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Needy Name,Mobile No,Hospital Name,Reason,Date of Issue,Blood Group\n";
            writer.write(text);
            for (issuedBean p : list)
            {
				text = p.getNname() + "," + p.getMobile()+ "," +  p.getHospital()+"," + p.getReason()+ "," +  p.getDoi()+","+p.getBgroup()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    @FXML
    void toConvert(ActionEvent event) {
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void initialize() {
        assert bgroup != null : "fx:id=\"bgroup\" was not injected: check your FXML file 'issuedHistoryView.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'issuedHistoryView.fxml'.";
        assert records != null : "fx:id=\"records\" was not injected: check your FXML file 'issuedHistoryView.fxml'.";
        ArrayList<String> items=new ArrayList<String>(Arrays.asList("A+","B+","AB+","A-","B-","AB-"));
        bgroup.getItems().setAll(items);
        con=	databaseConnection.doConnect();
    }

}
