/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Javier
 * Created: 08-may-2018
 */
DROP TABLE EMPLEADO;
CREATE TABLE Empleado(
    IdEmpleado INT,
    Nombre VARCHAR(20) NOT NULL,
    INC CHAR,
    Apellidos VARCHAR(40) NOT NULL,
    NSS CHAR(9) NOT NULL,
    FechaNac DATE,
    Direccion VARCHAR(30),
    Salario DECIMAL(10, 2 ),
    PRIMARY KEY (IdEmpleado)
);

DROP TABLE DEPARTAMENTO;
CREATE TABLE Departamento(
    IdDepartamento INT,
    Nombre VARCHAR(20) NOT NULL,
    NumDepart INT NOT NULL,
    FechaJefe DATE,
    PRIMARY KEY (IdDepartamento)
);