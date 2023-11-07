package br.estudo.mensagemspring.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    private String nome;

    @Column(unique = true)
    private String codigo;
    
    private String descricao;
    
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name = "data_entrada")
    private Date dataEntrada;
    
    public Mensagem(int id){
        this.id = id;
    }

    /* public void setDataEntrada(Date dataEntrada) {
        Instant instant = dataEntrada.toInstant();  
        ZoneId zoneId = ZoneId.systemDefault(); // ou qualquer outra zona de fuso horário desejada
        this.dataEntrada = instant.atZone(zoneId).toLocalDateTime();
    } */
}
