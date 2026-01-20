CREATE TABLE tipo_veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    classificacao VARCHAR(20) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    nome_proprietario VARCHAR(100) NOT NULL,
    tipo_veiculo_id ENUM ('LEVE', 'MEDIO', 'PESADO') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE garagem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    total_vagas INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE estacionamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id BIGINT NOT NULL,
    garagem_id BIGINT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    entrada DATETIME NOT NULL,

    CONSTRAINT fk_estacionamento_veiculo
            FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),

        CONSTRAINT fk_estacionamento_garagem
            FOREIGN KEY (garagem_id) REFERENCES garagem(id)

) ENGINE=InnoDB;

CREATE TABLE centro_custo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,

    tipo_veiculo ENUM ('LEVE', 'MEDIO', 'PESADO') NOT NULL,

    valor_hora DECIMAL(10,2) NOT NULL,
    tolerancia_minutos INT NOT NULL,

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE historico_estacionamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    veiculo_id BIGINT NOT NULL,
    garagem_id BIGINT NOT NULL,

    data_entrada DATETIME NOT NULL,
    data_saida DATETIME NOT NULL,

    valor_total DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_hist_veiculo
        FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),

    CONSTRAINT fk_hist_garagem
        FOREIGN KEY (garagem_id) REFERENCES garagem(id)
)ENGINE=InnoDB;



