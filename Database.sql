CREATE DATABASE kamonduck;

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
    Nombre VARCHAR(50) NOT NULL,
    Apellidos VARCHAR(50) NOT NULL,
    Telefono1 CHAR(9) NOT NULL,
    Telefono2 CHAR(9),
    Email VARCHAR(50) NOT NULL,
    TipoPago VARCHAR(20) NOT NULL,
    PRIMARY KEY (IDCliente)
);

CREATE TABLE Lagos
(
    IDLago INT NOT NULL,
    TarifaPato INT NOT NULL,
    Tamanho VARCHAR(50) NOT NULL,
    Cap_Patos INT NOT NULL,
    Nombre_Lago VARCHAR(50) NOT NULL,
    PRIMARY KEY (IDLago)
);

CREATE TABLE Empleados
(
    IDEmpleado INT AUTO_INCREMENT,
    Nombre_Empleado VARCHAR(50) NOT NULL,
    Apellidos_Empleado VARCHAR(50) NOT NULL,
    Cargo VARCHAR(10) NOT NULL,
    Horario_Trabajo VARCHAR(10) NOT NULL,
    Turno VARCHAR(20) NOT NULL,
    Telefono_Empleado CHAR(9) NOT NULL,
    IDLago INT NOT NULL,
    PRIMARY KEY (IDEmp),
    FOREIGN KEY (IDLago) REFERENCES Lagos(IDLago)
);

CREATE TABLE Proveedor
(
    IDProveedor INT AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    Dirección VARCHAR(100) NOT NULL,
    Telefono_Proveedor CHAR NOT NULL,
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
    Observaciones VARCHAR(250),
    IDProveedor INT NOT NULL,
    PRIMARY KEY (IDProducto),
    FOREIGN KEY (IDProveedor) REFERENCES Proveedor(IDProveedor)
);
