package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

    private Connection connection;
    private static Conexao databaseSingleton;

    private Conexao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cachacariaantoniocarlos", "root", "SENHA_USUÁRIO");
            //System.out.println("Conexão com banco de dados realizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexao getInstance() {
        if (databaseSingleton == null) {
            databaseSingleton = new Conexao();
        }
        return databaseSingleton;
    }

    public Connection getConnection() {
        return connection;
 }

}


/* CRIAÇÃO DO BANCO

CREATE DATABASE cachacariaantoniocarlos;
USE cachacariaantoniocarlos;

CREATE TABLE sabor (
    id INT AUTO_INCREMENT,
    tipoSabor VARCHAR(50),
    validade BOOLEAN,
    codigoBarra VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT,
    sabor_id INT,
    lote VARCHAR(20),
    fabricacao DATE,
    quantidade INT,
    restante INT,
    PRIMARY KEY (id),
    FOREIGN KEY (sabor_id) REFERENCES sabor(id)
);

CREATE TABLE venda (
    id INT AUTO_INCREMENT,
    dataVenda DATE,
    cliente VARCHAR(100),
    produto_id INT,
    quantidade INT,
    valorTotal DECIMAL(10, 2),
    notaFiscal VARCHAR(100), 
    PRIMARY KEY (id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE ingrediente (
    id INT AUTO_INCREMENT,
    nome VARCHAR(200),
    dataCompra DATE,
    lote VARCHAR(50),
    kg_l VARCHAR(20),
    fabricacao DATE,
    validade DATE,
    fornecedor VARCHAR(100),
    valorTotal DECIMAL(10, 2),
    notaFiscal VARCHAR(100), 
    PRIMARY KEY (id)
);

CREATE TABLE utensilio (
    id INT AUTO_INCREMENT,
    nome VARCHAR(200),
    dataCompra DATE,
    lote VARCHAR(50),
    quantidade VARCHAR(50),
    fornecedor VARCHAR(100),
    valorTotal DECIMAL(10, 2),
    notaFiscal VARCHAR(100), 
    PRIMARY KEY (id)
);

DELIMITER //

CREATE TRIGGER atualizar_quantidade_produto AFTER INSERT ON venda
FOR EACH ROW
BEGIN
    UPDATE produto
    SET restante = restante - NEW.quantidade
    WHERE id = NEW.produto_id;
END//

DELIMITER ;

DELIMITER //

CREATE TRIGGER restaurar_quantidade_produto AFTER DELETE ON venda
FOR EACH ROW
BEGIN
    UPDATE produto
    SET restante = restante + OLD.quantidade
    WHERE id = OLD.produto_id;
END//

DELIMITER ;

DELIMITER //

CREATE TRIGGER atualizar_quantidade_produto_editada AFTER UPDATE ON venda
FOR EACH ROW
BEGIN
    DECLARE diferenca INT;
    
    SET diferenca = NEW.quantidade - OLD.quantidade;
    
    UPDATE produto
    SET restante = restante - diferenca
    WHERE id = NEW.produto_id;
END//

DELIMITER ;

*/