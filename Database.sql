CREATE DATABASE kamonduck;

USE kamonduck;

CREATE TABLE Patos
(
    IDPato INT AUTO_INCREMENT,
    Nombre_Pato VARCHAR(50) NOT NULL,
    Raza VARCHAR(10) NOT NULL,
    Edad INT NOT NULL,
    Num_Cartilla INT NOT NULL,
    Descripcion VARCHAR (100),
    PRIMARY KEY (IDPato)
);

CREATE TABLE Clientes
(
    IDCliente INT AUTO_INCREMENT,
    DNI CHAR(9) NOT NULL,
    Nombre_Cliente VARCHAR(50) NOT NULL,
    Apellidos_Cliente VARCHAR(50) NOT NULL,
    Telefono_Cliente1 CHAR(16) NOT NULL,
    Telefono_Cliente2 CHAR(16),
    Email_Cliente VARCHAR(50) NOT NULL,
    TipoPago VARCHAR(20) NOT NULL,
    PRIMARY KEY (IDCliente)
);

CREATE TABLE Lagos
(
    IDLago INT NOT NULL,
    Nombre_Lago VARCHAR(50) NOT NULL,
    Tamanho VARCHAR(50) NOT NULL,
    Cap_Patos INT NOT NULL,
    Patos_Dentro INT NOT NULL,
    Tarifa INT NOT NULL,
    PRIMARY KEY (IDLago)
);

CREATE TABLE Empleados
(
    IDEmpleado INT AUTO_INCREMENT,
    DNI_Empleado CHAR (9) NOT NULL,
    Nombre_Empleado VARCHAR(50) NOT NULL,
    Apellidos_Empleado VARCHAR(50) NOT NULL,
    Telefono_Empleado CHAR(16) NOT NULL,
    Email_Empleado VARCHAR(50) NOT NULL,
    Cargo VARCHAR(10) NOT NULL,
    Horario_Trabajo VARCHAR(10) NOT NULL,
    Turno VARCHAR(20) NOT NULL,
    IDLago INT NOT NULL,
    PRIMARY KEY (IDEmpleado),
    FOREIGN KEY (IDLago) REFERENCES Lagos(IDLago)
);

CREATE TABLE Proveedor
(
    IDProveedor INT AUTO_INCREMENT,
    Nombre_Proveedor VARCHAR(100) NOT NULL,
    Direccion VARCHAR(100) NOT NULL,
    Telefono_Proveedor CHAR(16) NOT NULL,
    Pais VARCHAR(50) NOT NULL,
    PRIMARY KEY (IDProveedor)
);

CREATE TABLE Estancia
(
    IDEstancia INT AUTO_INCREMENT,
    Fecha_Ingreso DATE NOT NULL,
    Fecha_Salida DATE NOT NULL,
    IDPato INT NOT NULL,
    IDCliente INT NOT NULL,
    IDLago INT NOT NULL,
    PRIMARY KEY (IDEstancia),
    FOREIGN KEY (IDPato) REFERENCES Patos(IDPato),
    FOREIGN KEY (IDCliente) REFERENCES Clientes(IDCliente),
    FOREIGN KEY (IDLago) REFERENCES Lagos(IDLago)
);

CREATE TABLE Productos
(
    IDProducto INT NOT NULL,
    Nombre_Producto VARCHAR(100) NOT NULL,
    Tipo_Producto VARCHAR(50) NOT NULL,
    Cantidad INT NOT NULL,
    Minimo INT NOT NULL,
    Precio DOUBLE NOT NULL,
    Observaciones VARCHAR(250),
    IDProveedor INT NOT NULL,
    PRIMARY KEY (IDProducto),
    FOREIGN KEY (IDProveedor) REFERENCES Proveedor(IDProveedor)
);

INSERT INTO lagos VALUES (1, 'El Original', 'Grande', 30, 0, 20);
INSERT INTO lagos VALUES (2, 'El animado', 'Mediano', 15, 0, 10);

INSERT INTO empleados VALUES (NULL, '77823713Q', 'Jesus', 'Cruces Soto', '456126548', 'JCViceAdmin@KamoNDuck.com', 'ViceAdmin', 'L-D', 'Mañana', 1);
INSERT INTO empleados VALUES (NULL, 'DNIEjemp', 'Jose Antonio', 'Ejemplo', '124516987', 'JAAdmin@KamoNDuck.com', 'Admin', 'L-D', 'Mañana', 1);
INSERT INTO empleados VALUES (NULL, 'DNIEjemp', 'Freya', 'Ejemplo', '78123659', 'FrViceAdmin@KamoNDuck.com', 'ViceAdmin', 'L-D', 'Mañana', 1);
INSERT INTO empleados VALUES (NULL, '77836512L', 'Benito', 'Ejemplo', '142032012', 'BenBecario@KamoNDuck.com', 'Becario', 'L-D', 'Partido', 2);

INSERT INTO patos VALUES (NULL, 'Gilito', 'Animado', 65, 220001, 'No existe, pero tiene ALEGRÍA al dinero');
INSERT INTO patos VALUES (NULL, 'Jaimito', 'Animado', 12, 220002, 'No existe, pero tiene ALERGIA a las nueces');
INSERT INTO patos VALUES (NULL, 'Jorgito', 'Animado', 12, 220003, 'No existe, pero está sanisimo');
INSERT INTO patos VALUES (NULL, 'Juanito', 'Animado', 12, 220004, 'No existe, nada relevante');
INSERT INTO patos VALUES (NULL, 'Pyduck', 'Pokemon', 2, 220006, 'No existe, PROBLEMA tiene dolores de cabeza');
INSERT INTO patos VALUES (NULL, 'Lucas', 'Animado', 30, 220008, 'No existe, todo le molesta');


INSERT INTO clientes VALUES (NULL, '12345678L', 'Walter', 'Elias Disney', '954623154', '954872102','WDisney@Dinero.com', 'Cheque');
INSERT INTO Clientes VALUES (NULL, '54823621M', 'Mysty', 'Ciudad Celeste', '955632154', '965215487', 'MistyEntrenadoradel91@PCDEBill.com', 'Efectivo');
INSERT INTO Clientes VALUES (NULL, '84752136W', 'Harry', 'Warner', '945125478', '666231547', 'HarryElMayor@HermanosWarner.com', 'Paypal');
INSERT INTO Clientes VALUES (NULL, '84752136W', 'Albert', 'Warner', '945125479', '666231548', 'AlbertoElNoMayor@HermanosWarner.com', 'Efectivo');
INSERT INTO Clientes VALUES (NULL, '84752136W', 'Sam', 'Warner', '945125479', '666231548', 'SamuelElNoMenor@HermanosWarner.com', 'Cheque');
INSERT INTO Clientes VALUES (NULL, '84752136W', 'Jack', 'Warner', '945125480', '666231548', 'JacoboElMenor@HermanosWarner.com', 'Transferencia');


INSERT INTO Proveedor VALUES (1,'Versele-laga', 'Kapellestraat 70 / 9800 Deinze','+32 093813200','Bélgica');
INSERT INTO Proveedor VALUES (2, 'Corporación ACME', 'Calle ficticia', '123123123', '?');


INSERT INTO Productos VALUES (001, 'Pienso para patos adultos sabor salmón', 'Alimentación', 20, 15, 20, 'Pienso para patos reales adultos, en concreto los que aman el pescado',1);
INSERT INTO Productos VALUES (002,'Pienso para patos imaginarios', 'Alimentacion',60,20,5,'Pienso especial para patos que no existen',2);
INSERT INTO Productos VALUES (008,'Comida pokemon especial monotipo', 'Alimentación', 6, 5, 10,'Especialmente diseñado para el tipo agua',2);
INSERT INTO Productos VALUES (015, 'Pala para recoger excrementos imaginarios','Limpieza', 2, 1, 25, 'Indispensable para recoger excrementos imaginarios',2);

INSERT INTO Estancia VALUES (NULL, '2021-12-11', '2021-12-24', 5,2,2);
INSERT INTO Estancia VALUES (NULL, '2021-12-13', '2021-12-15', 6,5,2);

