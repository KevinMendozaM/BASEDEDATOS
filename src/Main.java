import java.sql.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/estudiantes2024A";
        String user = "root";
        String password = "123456";

        /*DE ESTA FORMA OBTENEMOS CONECCION CON LA BASE DE DATOS*/

        try (Connection connection= DriverManager.getConnection(url,user,password))
        {
            System.out.println("Conectando a la base de datos");
            String query = "select * from estudiante";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {

                /*DE ESTA FORMA SE IMPRIME E INVOCA LO QUE TENEMOS ALMACENADO EN LA BASE DE DATOS*/
                /*
                System.out.println("Estudiante: " + resultSet.getString ("nombre") );
                System.out.println("Cedula: " + resultSet.getString("cedula"));
                System.out.println("Nota B1: " + resultSet.getString("bimestreUno"));
                System.out.println("Nota B2: " + resultSet.getString("bimestreDos"));*/

                /*DE ESTA FORMA PEDIMOS POR TECLADO*/

                Scanner sc = new Scanner(System.in);
                System.out.println("Ingrese el numero de cedula: ");
                String cedula = sc.nextLine();
                if (cedula.equals(resultSet.getString("cedula"))) {
                    System.out.println("Estudiante: " + resultSet.getString("nombre"));
                    System.out.println("Cedula: " + resultSet.getString("cedula"));
                    System.out.println("Nota B1: " + resultSet.getString("bimestreUno"));
                    System.out.println("Nota B2: " + resultSet.getString("bimestreDos"));
                }
                if (resultSet.next())
                {
                    String Estudiante = resultSet.getString("nombre");
                    String Cedula = resultSet.getString("cedula");
                    double nota = resultSet.getDouble("bimestreUno");
                    double nota2 = resultSet.getDouble("bimestreDos");
                    double promedio = nota + nota2;
                    System.out.println("Estudiante: " + Estudiante);
                    System.out.println("Cedula: " + cedula);
                    System.out.println("Nota Bimestre 1: " + nota);
                    System.out.println("Nota Bimestre 2: " + nota2);
                    System.out.println("Promedio: " + promedio);
                    double pasa = 28.0;
                    if (promedio >= pasa) {
                        System.out.println("Estudiante pasa: ");
                    } else {
                        double faltas = promedio - pasa;
                        System.out.println("Estudiante faltas: " + faltas + "pasa");
                        System.out.println("REPRUEBA");
                    }
                }
            }

        }

        /*DE ESTA FORMA SE IMPRIME*/

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}