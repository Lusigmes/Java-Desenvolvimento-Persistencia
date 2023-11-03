package br.estudo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Config {

    private static Properties props = new Properties();
    
    public static Properties getConfig() {
        try {
            log.info("Carregando propriedades da conexao");
            props.load(Config.class.getResourceAsStream("/config.properties"));
            log.info("Configurações carregadas com sucesso!");
            log.info("{}",props.stringPropertyNames());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
