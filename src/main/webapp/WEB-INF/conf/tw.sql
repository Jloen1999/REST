-- Creacion de la base de datos y usuario
CREATE DATABASE IF NOT EXISTS tw;

CREATE USER IF NOT EXISTS 'tw'@'localhost' IDENTIFIED BY 'tw2324';
GRANT ALL PRIVILEGES ON tw.* TO 'tw'@'localhost';

-- Uso de la base de datos
USE tw;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `usuarios`;

-- Tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios
(
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(50)  NOT NULL,
    apellidos VARCHAR(50)  NOT NULL,
    email     VARCHAR(100) NOT NULL,
    username  VARCHAR(20)  NOT NULL,
    password  VARCHAR(20)  NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Tabla cartadata
CREATE TABLE IF NOT EXISTS cartadata
(
    idCartaData INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario   INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuarios (idUsuario)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- Tabla Productos
CREATE TABLE IF NOT EXISTS productos
(
    idProducto  INT AUTO_INCREMENT PRIMARY KEY,
    nombreProd  VARCHAR(100) NOT NULL,
    pathImagen  VARCHAR(255),
    comentarios TEXT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Tabla ItemData
CREATE TABLE IF NOT EXISTS itemdata
(
    idItemData  INT AUTO_INCREMENT PRIMARY KEY,
    idProducto  INT NOT NULL,
    cantidad    INT NOT NULL,
    idCartaData INT NOT NULL,

    FOREIGN KEY (idProducto) REFERENCES productos (idProducto),
    FOREIGN KEY (idCartaData) REFERENCES cartadata (idCartaData)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO usuarios (nombre, apellidos, email, username, password)
VALUES
    ('Juan', 'Garcia Perez', 'juangarcia@gmail.com', 'juang', 'password123'),
    ('Maria', 'Martinez Lopez', 'mariaml@hotmail.com', 'marial', 'securepass'),
    ('Pedro', 'Fernandez Rodriguez', 'pedrofr@gmail.com', 'pedrof', 'mypassword'),
    ('Laura', 'Sanchez Gomez', 'laurasg@yahoo.com', 'lauras', 'laura123'),
    ('Carlos', 'Gonzalez Martin', 'carlosgm@gmail.com', 'carlosm', 'pass123'),
    ('Ana', 'Jimenez Ruiz', 'anajr@gmail.com', 'anaj', 'anapassword'),
    ('Diego', 'Hernandez Castro', 'diegohc@gmail.com', 'diegoh', 'diego123'),
    ('Elena', 'Diaz Garcia', 'elenadg@gmail.com', 'elenad', 'elenapass'),
    ('Sara', 'Perez Martinez', 'saram@gmail.com', 'saram', 'sarapassword'),
    ('Javier', 'Ruiz Lopez', 'javierrl@gmail.com', 'javierr', 'javierpass');

-- Suponiendo que los usuarios Juan (idUsuario=1) y Maria (idUsuario=2) tienen carritos
INSERT INTO cartadata (idUsuario)
VALUES
    (1),
    (2);


INSERT INTO productos (nombreProd, pathImagen, comentarios)
VALUES
    ('Cien anios de soledad', 'http://ejemplo.com/imagen1.jpg', 'Obra maestra del realismo magico'),
    ('El codigo Da Vinci', 'http://ejemplo.com/imagen2.jpg', 'Thriller que combina arte y religion'),
    ('Clean Code', 'http://ejemplo.com/imagen3.jpg', 'Manual sobre escribir codigo limpio'),
    ('La sombra del viento', 'http://ejemplo.com/imagen4.jpg', 'Novela ambientada en la Barcelona posguerra'),
    ('Percy Jackson y el ladron del rayo', 'http://ejemplo.com/imagen5.jpg', 'Aventuras basadas en mitologia griega'),
    ('Harry Potter y la piedra filosofal', 'http://ejemplo.com/imagen6.jpg', 'Primera entrega de la saga de Harry Potter'),
    ('El principito', 'http://ejemplo.com/imagen7.jpg', 'Fabula sobre la amistad y la perspectiva'),
    ('El alquimista', 'http://ejemplo.com/imagen8.jpg', 'Exploracion del viaje personal hacia la realizacion'),
    ('1984', 'http://ejemplo.com/imagen9.jpg', 'Distopia sobre el poder y el control totalitario'),
    ('Orgullo y prejuicio', 'http://ejemplo.com/imagen10.jpg', 'Historia de amor en la Inglaterra del siglo XIX');

-- Suponiendo que el carrito de Juan (idCartaData=1) tiene productos
INSERT INTO itemdata (idProducto, cantidad, idCartaData)
VALUES
    (1, 2, 1),  -- 2 unidades del Producto con idProducto=1 en el carrito de Juan
    (2, 1, 1);  -- 1 unidad del Producto con idProducto=2 en el carrito de Juan


