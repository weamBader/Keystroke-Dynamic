/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Weam Bader
 */
@Path("weam")
public class GenericResource {

    public GenericResource() {
    }

    @GET
    @Path("/{cal}")
    public String fetch(@PathParam("cal") String sth,String charid) {
        JSONObject array = new JSONObject();
        String time = null;
        String[] tokens = sth.split(",");
        String username = tokens[0];
        String password = tokens[1];
        charid = tokens[2];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject" + unicode, "root", "");
            Statement statement = connection.createStatement();

            String x = "SELECT * from finalproject where username= '" + username + "' and password = '" + password + "'";

            ResultSet select = statement.executeQuery(x);
            while (select.next()) {
                //array.put("Test",select.getString("A"));
                time = select.getString(charid);
            }
            array.put("time", time);

        } catch (ClassNotFoundException | SQLException exs) {

        }
        return array + "";
    }

    @GET
    @Path("login/{cal}")
    public String login(@PathParam("cal") String sth) {
        JSONObject array = new JSONObject();
        String time = null;
        String time2 = null;
        String[] tokens = sth.split(",");
        String username = tokens[0];
        String password = tokens[1];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject" + unicode, "root", "");
            Statement statement = connection.createStatement();

            String x = "SELECT username,password from finalproject where username= '" + username + "' and password = '" + password + "'";

            ResultSet select = statement.executeQuery(x);
            while (select.next()) {
                array.put("status","success");
                
            }

        } catch (ClassNotFoundException | SQLException exs) {

        }
        return array + "";
    }
}
