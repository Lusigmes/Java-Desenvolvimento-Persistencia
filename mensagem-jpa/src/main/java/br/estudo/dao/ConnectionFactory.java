package br.estudo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.estudo.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionFactory {
    public static Properties prop;
    public static DataSource dataSource;

    static{
        prop = Config.getConfig();
        log.info("database.url: {}", prop.getProperty("database.url"));
        log.info("database.username: {}", prop.getProperty("database.username"));
        log.info("database.password: {}", prop.getProperty("database.password"));
        log.info("persistence.unit: {}", prop.getProperty("persistence.unit"));
    }

    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }

    public static DataSource getDataSource() throws SQLException{
        if(dataSource == null){
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("database.url"));
            config.setUsername( prop.getProperty("database.username"));
            config.setPassword(prop.getProperty("database.password"));

            String propertie = prop.getProperty("database.max_poll_size");
            if(propertie != null){
                config.setMaximumPoolSize(Integer.parseInt(propertie));
            }
            propertie = prop.getProperty("database.min_idle");
            if(propertie != null){
                config.setMinimumIdle(Integer.parseInt(prop.getProperty("database.min_idle")));
            }
            dataSource = new HikariDataSource(config);
        
        }
        
        

        return dataSource;
    }


}
