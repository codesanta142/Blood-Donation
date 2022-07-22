module BloodDoner {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;

	opens Donor to javafx.graphics, javafx.fxml;
	opens bloodUnitCollection to javafx.graphics, javafx.fxml;
	opens Login to javafx.graphics, javafx.fxml;
	opens Panel to javafx.graphics, javafx.fxml;
	opens MeetDeveloper to javafx.graphics, javafx.fxml;
	opens UnitsAvailable to javafx.graphics, javafx.fxml;
	opens issueBlood to javafx.graphics, javafx.fxml;
	opens history to javafx.graphics, javafx.fxml,javafx.base;
	opens donationHistory to javafx.graphics, javafx.fxml,javafx.base;
	opens issuedHistory to javafx.graphics, javafx.fxml,javafx.base;
	opens allhistory to javafx.graphics, javafx.fxml,javafx.base;
}
