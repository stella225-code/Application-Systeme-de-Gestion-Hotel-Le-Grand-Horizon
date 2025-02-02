package application;

    import java.io.IOException;
import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	
	
	
public class SingleConnection {

	//private Connection conx; // Objet de connexion à la base de données
    private PreparedStatement statement; // Objet pour exécuter les requêtes SQL préparées
    private ResultSet res; // Objet pour stocker les résultats des requêtes SQL
    private int ok; // Indicateur de succès pour les opérations d'update
    
    // URL de la base de données, utilisateur, mot de passe
     
     
	String db="hoteloranais";
	static String url = "jdbc:mysql://localhost:3307/hoteloranais"; // URL de la base de données
    static String user =  "root"; // Nom d'utilisateur de la base de données
    static String pwd = ""; // Mot de passe de la base de données
    
	private static Connection connection=null; // laconnexion doit etre partarger""static"" avec user , client et autre

	public SingleConnection() {
		 try {
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("instance crerr");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} // Établissement de la connexion
	}
	

	
	public static Connection getConnection() {
		/*if (connection ==null)  
			new SingleConnection();
		return connection;*/
		
		
		    if (connection == null) {
		        try {
		            connection = DriverManager.getConnection(url, user, pwd);
		            System.out.println("Connection établie.");
		            
		        } 
		        catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return connection;
		

	}



	
    
	
}
