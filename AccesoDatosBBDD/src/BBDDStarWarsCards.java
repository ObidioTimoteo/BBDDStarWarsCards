import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BBDDStarWarsCards {

	public static void main(String[] args) {
		
		// Se crea un objeto BBDDConector con connection = null
		BBDDConector conn = new BBDDConector();
		
		// Se establece la conexión si connection es null o está cerrada
		conn.connectToDB();
		
		// Obtienes la conexión establecida
		Connection connection = conn.getConnection();
		
		menu(connection);
		
		// Cerrar la conexión al salir
        conn.closeConnection();

	}
	
	/**
	 * Método para mostrar el menú de opciones
	 * @method menuSwitch	 * 
	 */
	
	public static void menu(Connection conn) {		
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {
			System.out.println("\nEscoja una de las opciones:");
			System.out.println("[1]: Listar BBDD");
			System.out.println("[2]: Añadir elemento");
			System.out.println("[3]: Eliminar elemento");
			System.out.println("[0]: Salir");
			
			try {
				Integer num = sc.nextInt();
				if (num == 0 || num == 1 || num == 2 || num == 3) {
					exit = menuSwitch(num, conn);
				} else {
					System.err.println("Debe introducir un número del 0 al 3");
				}
			} catch (Exception e){
				System.err.println("Debe introducir un número del 0 al 3");
				sc.nextLine();
			}
		}		
		sc.close();
	}
	
	/**
	 * Método que utiliza el método menu
	 * para ejecutar la opción seleccionada
	 * @method menuSwitch	 * 
	 */	
	public static boolean menuSwitch(int num, Connection conn) {		
		switch (num) {
			case 0:
				// salir();
				System.err.println("\nGracias por utilizar BBDDToys");
				return true;				
			case 1:
				listarElementos(conn);
				return false;				
			case 2:
				anadirElemento(conn);
				return false;				
			case 3:
				eliminarElemento(conn);
				return false;				
			default:
				return false;				
		}
	}
	
	// Método para listar elementos
	public static void listarElementos(Connection conn) {
		String sql = "SELECT * FROM starwarscards ORDER BY numberCard ASC, typeCard ASC";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
						
			// Formato de salida
			System.out.println();
			System.out.printf("%-10s %-20s %-15s%n", "Número", "Nombre", "Tipo");
		    System.out.println("------------------------------------------------------------");
			
			while (rs.next()) {
				String nameCard = rs.getString("nameCard");
				int numberCard = rs.getInt("numberCard");
				String typeCard = rs.getString("typeCard");
				
				// Imprimir cada campo con ancho fijo
		        System.out.printf("%-10d %-20s %-15s%n", numberCard, nameCard, typeCard);
			}
			System.out.println("------------------------------------------------------------");
			rs.close();
			stmt.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	// Método para añadir elemento
	public static void anadirElemento(Connection conn) {		
		Scanner sc = new Scanner(System.in);
		String nameCard;
		Integer numberCard;
		String typeCard;
		System.out.println("\nIntroduzca el nombre de la carta:");
		nameCard = sc.nextLine();
		System.out.println("Introduzca el número de la carta:");
		try {
			numberCard = sc.nextInt();
			sc.nextLine();
			System.out.println("Introduzca el tipo de la carta:");
			typeCard = sc.nextLine();
			StarWarsCard newCard = new StarWarsCard (nameCard, numberCard, typeCard);
			String sql = "INSERT INTO starwarscards (nameCard, numberCard, typeCard) VALUES ('" 
						+ newCard.getNameCard() + "', " + newCard.getNumberCard() + ", '" + newCard.getTypeCard() + "')";			
			Statement stm;
			try {
				stm = conn.createStatement();
				stm.executeUpdate(sql);
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("Debe introducir un número");			
		}
	}
	
	public static void eliminarElemento(Connection conn) {		
		Scanner sc = new Scanner(System.in);
		Integer numberCard;
		
		System.out.println("¿Qué número de carta desea eliminar?");
		try {
			numberCard = sc.nextInt();
			sc.nextLine();
			String sql = "DELETE FROM starwarscards WHERE numberCard = " + numberCard;
			Statement stm;
			try {
				stm = conn.createStatement();
				stm.executeUpdate(sql);
				stm.close();
				System.out.println("Carta número " + numberCard + " eliminada");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("Debe introducir un número");
		}
	}
	
}
