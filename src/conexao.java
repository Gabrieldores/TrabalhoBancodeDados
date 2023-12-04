
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao{

    private static final String url = "jdbc:mysql://localhost3306/vendasjogos";
    private static final String user = "root";
    private static final String senha = "Bananinha2000!";

   private static Connection conn;

   public static Connection getConexao(){
   try {
    if(conn == null)
        {
            conn = DriverManager.getConnection(url, user, senha);
            return conn;
        } 
            else
            {
                return conn;
            }
    }
    catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
   }


    }

