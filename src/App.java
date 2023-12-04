import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class App
{
	
	public static void main(String[] args) 
	{
    	int opc;
    	
        Scanner sc = new Scanner(System.in);
        try 
        {
        	Driver driver = new com.mysql.cj.jdbc.Driver();
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendasjogos", "root", "Bananinha2000!");


            Statement statement = connection.createStatement();
            
           
            
			
			do{	
				 System.out.println("****** Ranking de vendas de jogos ******");
            
            System.out.println("Insira a opção com os relatórios desejados: ");
            System.out.println("1 - Exibir todos os jogos");
            System.out.println("2 - Os jogos vendidos na Europa");
            System.out.println("3 - Os jogos vendidos no Japão e seus numeros");
            System.out.println("4 - Os jogos vendidos nos Estados unidos  e seus numeros");
            System.out.println("5 - Os jogos em outras regiões :");
            System.out.println("6 - Publisher com maiores números de jogos");
            System.out.println("SAIR");
			opc = sc.nextInt();
            switch(opc)
            {
	            case 1:
	            {
	            	ResultSet resultSet = statement.executeQuery("SELECT nome FROM vendasjogos.jogo");
	                while (resultSet.next()) 
	                 {
		               // Mostrar todas as colunas
		               for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) 
		               {
		            	   System.out.println(resultSet.getString(i));
		               }
	                 }
	                break;
	            }
	            case 2:
	            {
	            	ResultSet resultSet = statement.executeQuery("SELECT *\r\n"
	            			+ "     , FROM vendasjogos.jogo \r\n"
	            			+ "     , INNER JOIN vendasjogos.eu_sales\r\n"
	            			+ "     ,  ON jogos.idJogo = eu_sales.ID_jogo;\r\n"
                            +  "order by TotalVendas desc;");
	            	
	            	while (resultSet.next()) 
	            	{
	                    // Print the results
	                    System.out.println( resultSet.getString("jogos") + " | " + resultSet.getDouble("vendasEU"));
	                }
	            	break;
	            }
	            case 3:
	            {
	            	ResultSet resultSet = statement.executeQuery("SELECT\r\n"
	            			+ "  J.nome AS NomeJogo, \r\n"
	            			+ " JP.totalVendas AS TotalVendasJP\r\n"
	            			+ " FROM\r\n"
	            			+ "vendasjogos.jogo AS J\r\n"
	            			+ "JOIN\r\n"
	            			+ " vendasjogos.eu_sales AS EU ON J.idJogo = EU.ID_jogo;\r\n"
	            			+ "order by totalVendas desc;");
	            	
	            	break;
	            }
	            case 4:
	            {
	            	ResultSet resultSet = statement.executeQuery("SELECT\r\n"
	            			+ " J.nome AS NomeJogo,\r\n"
	            			+ "NA_sales.totalVendas AS TotalVendasEstadosUnidos,\r\n"
	            			+ "FROM\r\n"
	            			+ "   ,vendasjogos.jogo AS J \r\n"
	            			+ "JOIN\r\n"
	            			+ "	vendasjogos.NA_Sales AS NA ON J.idJogo = NA.ID_jogo\r\n"
	            			+ "	ORDER BY\r\n"
	            			+ " NA.totalVendas DESC;\r\n"
	            			);
	            	
	            	while(resultSet.next())
	            	{
	            		System.out.println(resultSet.getString("Estados Unidos") + " | " + resultSet.getInt("total Vendas"));
	            	}
	            	break;
	            }
				case 5:{
					ResultSet resultSet = statement.executeQuery("SELECT\r\n"
							+ "     , J.nome AS NomeJogo\r\n"
							+ "     ,  OtherRegions.totalVendas AS TotalVendasOtherRegions\r\n"
							+ "     , FROM\r\n"
							+ "vendasjogos.jogo AS J\r\n"
							+ " JOIN\r\n"
							+ "vendasjogos.eu_sales AS EU ON J.idJogo = EU.ID_jogo;\r\n");


					while(resultSet.next())
	            	{
	            		System.out.println(resultSet.getString("Jogo") + "/" + resultSet.getDouble("TotalVendas")+ " /");
	            	}

				}
				break;


				case 6:{
					ResultSet resultSet = statement.executeQuery("SELECT\r\n"
							+ "	 , P.Nome AS NomePublisher\r\n"
							+ "     , COUNT(J.idJogo) AS NumeroDeJogos\r\n"
							+ "     , FROM\r\n"
							+ " vendasjogos.publisher AS P\r\n"
							+ "   JOIN\r\n"
							+ "vendasjogos.jogo AS J ON P.idPublisher = J.id_publisher\r\n"
							+ "GROUP BY\r\n"
							+ "	   ,  P.Nome\r\n"
							+ "ORDER BY\r\n"
							+ "	   ,  NumeroDeJogos DESC;");
					while(resultSet.next())
	            	{
	            		System.out.println(resultSet.getString("Publisher"));
	            	}
				}
				break;

				case 7:{
					System.out.println("BYE BYE!!!!.");
				}
				default:{
            		System.out.println("Opção invalida!");
				}
			
            }
			}while(opc != 7);
            
            
            // Close the connection
            connection.close();

            
        } 
        catch (Exception e) 
        {
            System.out.println("Error connecting to MySQL: " + e.getMessage());
            
        }
        sc.close();
    }
}
    

