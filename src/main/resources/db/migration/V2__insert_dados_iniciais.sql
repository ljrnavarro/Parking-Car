-- ============================
-- TIPOS DE VEÍCULO
-- ============================
INSERT INTO tipo_veiculo (id, nome, classificacao) VALUES
(1, 'MOTO', 'LEVE'),
(2, 'PASSEIO', 'MEDIO'),
(3, 'CAMINHAO', 'PESADO');

-- ============================
-- CENTRO DE CUSTO
-- ============================
INSERT INTO centro_custo (
    nome,
    tipo_veiculo,
    valor_hora,
    ativo,
    tolerancia_minutos
) VALUES
('Moto', 'LEVE', 5.00, true,20),
('Carro', 'MEDIO', 10.00, true,10),
('Caminhão', 'PESADO', 20.00, true,10);