import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BBDDConector {

	// Atributos:	Voy a hacer que me devuelva la conexión directamente
	private Connection connection;
	
	// Al no tener un constructor, habrá un constructor por defecto.
	// Al crear un objeto de esta clase, se creará un objeto con el atributo
	// connection con valor null
	
	// Método para realizar la conexión a la BD
	public void connectToDB() {
		try {
            if (connection == null || connection.isClosed()) {
                // Configura tu URL de conexión aquí
                String url = "jdbc:mysql://localhost:3308/bbddtoys";
                String user = "root";
                String password = "";
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión a la base de datos establecida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }		
	
	// Método para obtener la conexión
    public Connection getConnection() {
        return connection;
    }

    // Método para cerrar la conexión
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("\nConexión a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

		
}

