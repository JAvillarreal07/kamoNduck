CREATE DATABASE kamonduck;

USE kamonduck;

CREATE TABLE Patos
(
    IDPato INT AUTO_INCREMENT,
    Nombre_Pato VARCHAR(50) NOT NULL,
    Raza VARCHAR(50) NOT NULL,
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
    Cargo VARCHAR(50) NOT NULL,
    Horario_Trabajo VARCHAR(20) NOT NULL,
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
    IDProducto INT AUTO_INCREMENT,
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
INSERT INTO lagos VALUES (3, 'El Coloso', 'Grande', 40, 0, 25);
INSERT INTO lagos VALUES (4, 'La Charca', 'Pequeño', 5, 0, 50);

INSERT INTO empleados VALUES (NULL, '77823713Q', 'Jesus', 'Cruces Soto', '456126548', 'JCViceAdmin@KamoNDuck.com', 'ViceAdmin', 'L-M-X-J-V-S-D', 'Diurno', 1);
INSERT INTO empleados VALUES (NULL, 'DNIEjemp', 'Jose Antonio', 'Ejemplo', '124516987', 'JAAdmin@KamoNDuck.com', 'Admin', 'L-X-V', 'Nocturno', 1);
INSERT INTO empleados VALUES (NULL, 'DNIEjemp', 'Freya', 'Ejemplo', '78123659', 'FrViceAdmin@KamoNDuck.com', 'ViceAdmin', 'M-J-S', 'Diurno', 1);
INSERT INTO empleados VALUES (NULL, '77836512L', 'Benito', 'Ejemplo', '142032012', 'BenBecario@KamoNDuck.com', 'Becario', 'L-M-X-J-V-S-D', 'Nocturno', 2);
INSERT INTO empleados VALUES (NULL, '78912451W', 'Álvaro', 'Ortiz Castillo', '695647125', 'ÁlvVet@KamoNDuck.com', 'Veterinario', 'L-M-X-J', 'Partido', 1);
INSERT INTO empleados VALUES (NULL, '69514723G', 'Ángela', 'Ortiz Castillo', '695647126', 'ÁngVet@KamoNDuck.com', 'Veterinario', 'V-S-D', 'Partido', 1);
INSERT INTO empleados VALUES (NULL, '59845125P', 'Miguel', 'Castaño Laredo', '548126589', 'MigMante@KamoNDuck.com', 'Mantenimiento', 'L-X-V-D', 'Diurno', 1);
INSERT INTO empleados VALUES (NULL, '59845125P', 'Carlos', 'Gómez Pérez', '954214785', 'CarMante@KamoNDuck.com', 'Mantenimiento', 'M-J-S', 'Diurno', 1);

INSERT INTO patos VALUES (NULL, 'Gilito', 'Pekín americano', 11, 220001, 'Está algo mayor');
INSERT INTO patos VALUES (NULL, 'Jaimito', 'Pekín americano', 2, 220002, 'ALERGIA a las nueces');
INSERT INTO patos VALUES (NULL, 'Jorgito', 'Pekín americano', 2, 220003, 'Muy sano');
INSERT INTO patos VALUES (NULL, 'Juanito', 'Pekín americano', 2, 220004, 'ALERGIA al salmón');
INSERT INTO patos VALUES (NULL, 'Psyduck', 'Pekín blanco', 1, 220006, 'Es una cría, PROBLEMA tiene dolores de cabeza');
INSERT INTO patos VALUES (NULL, 'Lucas', 'Ánade sombrío', 5, 220008, 'Muy exigente');
INSERT INTO patos VALUES (NULL, 'Funky', 'Tadorne coreano', 3, 220009, 'Tratar con cuidado, casi extinto');
INSERT INTO patos VALUES (NULL, 'Iris', 'Mandarín', 4, 220010, 'PROBLEMA está bajo de peso, necesita alimento extra');


INSERT INTO clientes VALUES (NULL, '12345678L', 'Walter', 'Elias Disney', '954623154', '954872102','WDisney@Dinero.com', 'Cheque');
INSERT INTO Clientes VALUES (NULL, '54823621M', 'Misty', 'Ciudad Celeste', '955632154', '965215487', 'MistyEntrenadoradel91@PCDEBill.com', 'Efectivo');
INSERT INTO Clientes VALUES (NULL, '84752136W', 'Harry', 'Warner', '945125478', '666231547', 'HarryElMayor@HermanosWarner.com', 'Paypal');
INSERT INTO Clientes VALUES (NULL, '84752137W', 'Albert', 'Warner', '945125479', '666231548', 'AlbertoElNoMayor@HermanosWarner.com', 'Efectivo');
INSERT INTO Clientes VALUES (NULL, '84752138W', 'Sam', 'Warner', '945125479', '666231549', 'SamuelElNoMenor@HermanosWarner.com', 'Cheque');
INSERT INTO Clientes VALUES (NULL, '84752139W', 'Jack', 'Warner', '945125480', '666231550', 'JacoboElMenor@HermanosWarner.com', 'Transferencia');


INSERT INTO Proveedor VALUES (1, 'Versele-laga', 'Kapellestraat 70 / 9800 Deinze','+32 093813200','Bélgica');
INSERT INTO Proveedor VALUES (2, 'Corporación ACME', 'Calle ficticia', '123123123', '?');
INSERT INTO Proveedor VALUES (3, 'Anecoop S.c.', 'CALLE MONFORTE , 1 - LOCAL 1', '963938500', 'Valencia');
INSERT INTO Proveedor VALUES (4, 'Groupe Limagrain', 'Biopôle Clermont-Limagne Rue Henri Mondor', '473634000', 'Saint Beauzire Francia');


INSERT INTO Productos VALUES (NULL, 'Pienso patos adultos salmón', 'Alimentación', 20, 15, 20, 'Pienso para patos adultos',1);
INSERT INTO Productos VALUES (NULL, 'Preparado para papilla', 'Alimentacion',60,20,5,'Preparado para papilla, para patos recien nacidos',1);
INSERT INTO Productos VALUES (NULL, 'Pala para excrementos','Limpieza', 2, 1, 25, 'Indispensable para recoger excrementos imaginarios',2);
INSERT INTO Productos VALUES (NULL, 'Saco 10 Kg Zanahoria','Alimentacion', 5, 3, 10, '-',3);
INSERT INTO Productos VALUES (NULL, 'Saco 15 Kg Pepino','Alimentacion', 5, 3, 10, '-',3);
INSERT INTO Productos VALUES (NULL, 'Saco 10 Kg Manzana','Alimentacion', 5, 3, 10, '-',3);
INSERT INTO Productos VALUES (NULL, 'Saco 20 Kg semillas','Alimentación', 5, 2, 55, 'Elección de granos y semillas especiales para patos',4);

INSERT INTO Estancia VALUES (NULL, '2021-12-11', '2021-12-24', 5,2,2);
INSERT INTO Estancia VALUES (NULL, '2021-12-13', '2021-12-15', 6,5,2);

