package history;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import issuedHistory.issuedBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class historyViewController {
	Connection con;
    PreparedStatement pst;
    ResultSet table;
    int f=0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> bgroup;
    
    @FXML
    private TableView<donorBean> records;
    ObservableList<donorBean>allRecords;
    
    public void writeExcel( ObservableList<donorBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("Donorsinfo.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,Name,Gender,Address,City,Blood Group,Age,Disease,Date of Registeration\n";
            writer.write(text);
            for (donorBean p : list)
            {
				text = p.getMobile()+ "," + p.getName()+ "," + p.getGender()+ "," + p.getAddress()+ "," + p.getCity()+ "," + p.getBgroup()+ "," + p.getAge()+ "," +  p.getDisease()+"\n";
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
    		
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    ObservableList<donorBean> getAllObjects() 
    {
  	ObservableList<donorBean> ary=FXCollections.observableArrayList();
  	
  	PreparedStatement pst;
  	try {
  		if(f==0)
  		{
  		  	pst=con.prepareStatement("select mobile,name,gender,address,city,bgroup,age,disease from donor_table");

  		}
  		else
  		{
  		  	pst=con.prepareStatement("select mobile,name,gender,address,city,bgroup,age,disease from donor_table where bgroup=?");
  		  	pst.setString(1, bgroup.getValue());
  		}
  	table=pst.executeQuery();
  	while(table.next())
  	{
  		String mobile=table.getString("mobile");
  		String name=table.getString("name");
  		String gender=table.getString("gender");
  		String address=table.getString("address");
  		String city=table.getString("city");
  		String bgroup=table.getString("bgroup");
  		int age=table.getInt("age");
  		String disease=table.getString("disease");
  		donorBean obj=new donorBean(mobile,name,gender,address,city,bgroup,age,disease);
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
    void onFetch(ActionEvent event) {
         f=1;
    	

    	   	
    	
    allRecords=getAllObjects();	
    	

    	records.setItems(allRecords);
    }

    
	@FXML
    void onShow(ActionEvent event) {
    	f=0;
    	    	
    	allRecords=getAllObjects();	
    	

    	records.setItems(allRecords);
    }
	@SuppressWarnings("unchecked")
	void addCols()
	{
		TableColumn<donorBean, String> mobile=new TableColumn<donorBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(100);
    	
    	TableColumn<donorBean, String> name=new TableColumn<donorBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(100);
    	
    	TableColumn<donorBean, String> gender=new TableColumn<donorBean, String>("Gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	gender.setMinWidth(80);
    	
    	TableColumn<donorBean, String> address=new TableColumn<donorBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<donorBean, String> city=new TableColumn<donorBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	city.setMinWidth(80);
    	
    	TableColumn<donorBean, String> bgroup=new TableColumn<donorBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));
    	bgroup.setMinWidth(34);
    	
    	TableColumn<donorBean, Integer> age=new TableColumn<donorBean, Integer>("Age");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));
    	age.setMinWidth(100);
    	
    	TableColumn<donorBean, String> disease=new TableColumn<donorBean, String>("Disease");
    	disease.setCellValueFactory(new PropertyValueFactory<>("disease"));
    	disease.setMinWidth(100);
    	
    	records.getColumns().addAll(mobile,name,gender,address,city,bgroup,age,disease);
    	   	

	}

    @FXML
    void initialize() {
       // assert bgroup != null : "fx:id=\"bgroup\" was not injected: check your FXML file 'historyView.fxml'.";
        //assert records != null : "fx:id=\"records\" was not injected: check your FXML file 'historyView.fxml'.";

    	addCols();
        ArrayList<String> items=new ArrayList<String>(Arrays.asList("A+","O+","B+","AB+","A-","O-","B-","AB-"));
        bgroup.getItems().setAll(items);
        
        con=	databaseConnection.doConnect();
    }

}
