package persistance;


import game.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class MesaMySql {
    private Connection connection;
    private static MesaMySql instance;
    // Singleton method
    public static MesaMySql getInstance(){
        if(MesaMySql.instance == null){
            MesaMySql.instance = new MesaMySql();
        }
        return MesaMySql.instance;
    }

    private MesaMySql(){
        try {
            // Connect to MySql
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
            // The table is created if not exists
            Statement st = connection.createStatement();
            st.execute("CREATE DATABASE /*!32312 IF NOT EXISTS*/`blackjack` /*!40100 DEFAULT CHARACTER SET latin1 */;");
            st.execute(""
                    + "CREATE TABLE IF NOT EXISTS `blackjack`.`matches` ("
                    + "  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,"
                    + "  `winner_name` varchar(50) DEFAULT NULL,"
                    + "  `winner_money` int DEFAULT 0,"
                    + "  `earn_by_the_table` int DEFAULT 0,"
                    + "  `lost_by_the_table` int DEFAULT 0,"
                    + "  PRIMARY KEY (`id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
            // Connection closed
            connection.close();
            // Connection re-open, directly to the table
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMatch(Mesa m)  {
        try {
            String sql = "insert into matches (winner_name,winner_money,earn_by_the_table,lost_by_the_table) values (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, m.getWinnerName());
            st.setInt(2, m.getWinnerMoney());
            st.setInt(3, m.getMoneyIn());
            st.setInt(4, m.getMoneyOut());
            st.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return a Map with the data of the last match
     */
    public Map<String,String> getLastMatch(){
        Map<String,String> dict = new HashMap<>();
        try {
            String sql = "SELECT * FROM matches ORDER BY id DESC LIMIT 1";
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dict.put("id",rs.getString("id"));
                dict.put("winner_name",rs.getString("winner_name"));
                dict.put("winner_money",rs.getString("winner_money"));
                dict.put("earn_by_the_table",rs.getString("earn_by_the_table"));
                dict.put("lost_by_the_table",rs.getString("lost_by_the_table"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dict;
    }

}
