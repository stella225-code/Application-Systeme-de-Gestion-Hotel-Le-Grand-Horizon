
module horizonhotel {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	//requires mysql.connector.j;
	
	opens application to javafx.graphics, javafx.fxml;



requires javafx.base;
requires java.desktop;
requires javafx.graphics;
//requires java.mail;
//requires java.mail;


//opens application to javafx.graphics, javafx.fxml;
//opens application.Produit to javafx.graphics, javafx.fxml;

 exports application;}