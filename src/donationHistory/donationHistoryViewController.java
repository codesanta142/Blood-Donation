package donationHistory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import history.databaseConnection;
import history.donorBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class donationHistoryViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField mobile;

    @FXML
    private TableView<collectionBean> records;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    int f=0;

    ObservableList<collectionBean>allRecords1;
    ObservableList<collectionBean> getAllObjects() 
    {
  	ObservableList<collectionBean> ary=FXCollections.observableArrayList();
  	
  	PreparedStatement pst;
  	try {
  		if(f==0)
  		{
  		  	pst=con.prepareStatement("select mobile,bgroup,dateofdonation from donations");

  		}
  		else
  		{
  		  	pst=con.prepareStatement("select mobile,bgroup,dateofdonation from donations where mobile=?");
  		  	pst.setString(1, mobile.getText());
  		}
  	table=pst.executeQuery();
  	while(table.next())
  	{
  		String mobile=table.getString("mobile");
  		
  		String bgroup=table.getString("bgroup");
  		
  		String dateofdonation=table.getString("dateofdonation");
  		collectionBean obj=new collectionBean(mobile,bgroup,dateofdonation);
  		ary.add(obj);
  	}
  	}
  	catch(Exception exp)
  	{ 	
  		System.out.println(exp);
  	}
  	return ary;
  }

    @FXML
    void AllShow(ActionEvent event) {
    	f=0;
      	allRecords1=getAllObjects();		
    	

    	records.setItems(allRecords1);
    }

    @FXML
    void onFetch(ActionEvent event) {
    	 f=1;
     	

 	   	
     	
     	allRecords1=getAllObjects();	
     	

     	records.setItems(allRecords1);

    }
    @SuppressWarnings("unchecked")
	void addCols()
	{
		TableColumn<collectionBean, String> mobile=new TableColumn<collectionBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(200);
    	
    	
    	TableColumn<collectionBean, String> bgroup=new TableColumn<collectionBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));
    	bgroup.setMinWidth(100);
    	
    	
    	TableColumn<collectionBean, String> dob=new TableColumn<collectionBean, String>("Date of donation");
    	dob.setCellValueFactory(new PropertyValueFactory<>("dateofdonation"));
    	dob.setMinWidth(100);
    	
    	records.getColumns().addAll(mobile,bgroup,dob);
    	   	

	}
    
    public void writeExcel( ObservableList<collectionBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("Bloodcollectioninfo.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,Blood Group,Date of Donation\n";
            writer.write(text);
            for (collectionBean p : list)
            {
				text = p.getMobile() + "," + p.getBgroup()+ "," +  p.getDateofdonation()+"\n";
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
    void onConvert(ActionEvent event) {
    	try {
			writeExcel(allRecords1);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  
    
    @FXML
    void initialize() {
        assert mobile != null : "fx:id=\"mobile\" was not injected: check your FXML file 'donationHistoryView.fxml'.";
        assert records != null : "fx:id=\"records\" was not injected: check your FXML file 'donationHistoryView.fxml'.";
        con=	databaseConnection.doConnect();

    	addCols();
    }

}
