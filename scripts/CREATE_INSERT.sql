CREATE TABLE SUCURSAL (
	NUMERO INT IDENTITY NOT NULL,
	NOMBRE VARCHAR(100),		
	CONSTRAINT PK_SUCURSAL PRIMARY KEY (NUMERO)
);
SET IDENTITY_INSERT SUCURSAL ON;

CREATE TABLE CLIENTE (		
	CUIL BIGINT NOT NULL,
	NOMBRE VARCHAR(100),
	APELLIDO VARCHAR(100),
	NUMERO_TELEFONO VARCHAR(50),
	EMAIL VARCHAR(50),		
	FECHA_ALTA DATE,
	DOMICILIO_LEGAL VARCHAR(100),
	SUCURSAL_NUMERO INT NOT NULL,
	CONSTRAINT FK_CLIENTE_SUCURSAL  FOREIGN KEY (SUCURSAL_NUMERO) REFERENCES SUCURSAL (NUMERO),
	CONSTRAINT PK_CLIENTE PRIMARY KEY (CUIL)
);

CREATE TABLE EMPLEADO (
	CUIL BIGINT IDENTITY NOT NULL,
	NOMBRE VARCHAR(100),
	APELLIDO VARCHAR(100),
	NUMERO_TELEFONO VARCHAR(50),
	EMAIL VARCHAR(50),
	LEGAJO VARCHAR(100),
	FECHA_INGRESO DATE,
	SUCURSAL_NUMERO INT NOT NULL,
	CONSTRAINT FK_EMPLEADO_SUCURSAL  FOREIGN KEY (SUCURSAL_NUMERO) REFERENCES SUCURSAL (NUMERO),
	CONSTRAINT PK_EMPLEADO PRIMARY KEY (CUIL)
);

CREATE TABLE CUENTA (
	NUMERO BIGINT IDENTITY NOT NULL,
	CBU VARCHAR(100),
	MONEDA INT NOT NULL,
	TIPO_CUENTA INT NOT NULL,	
	SALDO MONEY NOT NULL,	
	CLIENTE_CUIL BIGINT NOT NULL,	
	CONSTRAINT FK_CAJA_AHORROS_CLIENTE  FOREIGN KEY (CLIENTE_CUIL) REFERENCES CLIENTE (CUIL),	
	CONSTRAINT PK_CAJA_AHORRO PRIMARY KEY (NUMERO)
);

INSERT INTO SUCURSAL (NUMERO, NOMBRE) VALUES 
('1', 'Sucursal Santa Fe'),
('2', 'Sucursal Villa Ocampo'),
('3', 'Sucursal Capital Federal'),
('4', 'Sucursal Mar del Plata');

INSERT INTO CLIENTE (CUIL, NOMBRE, APELLIDO, NUMERO_TELEFONO, EMAIL, FECHA_ALTA, DOMICILIO_LEGAL, SUCURSAL_NUMERO)  VALUES 
('201111111112', 'Gabriel Jesus', 'Rodriguez', '0342-15789969', 'gabriel@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '1'),
('202222222223', 'Gaston', 'Gimenez', '0342-15789969', 'gaston@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '1'),
('203333333334', 'Javier', 'Diaz', '0342-15789969', 'javier@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '1'),
('204444444445', 'Pedro', 'Getar', '0342-15789969', 'pedro@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '1'),
('205555555556', 'Timoteo', 'Martinez', '0342-15789969', 'timoteo@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '2'),
('206666666667', 'Francisco', 'Peralta', '0342-15789969', 'francisco@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '2'),
('207777777778', 'Adrian', 'Masacane', '0342-15789969', 'adrian@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '2'),
('208888888889', 'Martin', 'Schumacher', '0342-15789969', 'martin@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '2'),
('209999999991', 'Nicolas', 'Cano', '0342-15789969', 'nicolas@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '2'),
('200000000009', 'Matias', 'Gonzalez', '0342-15789969', 'matias@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '2'),
('201236541238', 'Enrique', 'Perez', '0342-15789969', 'enrique@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '3'),
('206549898352', 'Jose', 'Alonso', '0342-15789969', 'jose@gmail.com', '05/05/2022', 'Avenida Aguas Vivas Nro 2025', '4');


INSERT INTO CUENTA (CBU, MONEDA, TIPO_CUENTA, SALDO, CLIENTE_CUIL)  VALUES 
('011055616101', '1', '1', '1000,00', '201111111112'),
('011055616102', '1', '3', '1000,00', '202222222223'),
('011055616103', '1', '1', '1000,00', '203333333334'),
('011055616104', '1', '3', '1000,00', '204444444445'),
('011055616101', '1', '1', '1000,00', '205555555556'),
('011055616102', '1', '3', '1000,00', '206666666667'),
('011055616103', '1', '1', '1000,00', '207777777778'),
('011055616104', '1', '3', '1000,00', '208888888889'),
('011055616101', '1', '1', '1000,00', '209999999991'),
('011055616102', '1', '3', '1000,00', '200000000009'),
('011055616101', '1', '1', '1000,00', '201236541238'),
('011055616102', '1', '3', '1000,00', '206549898352');
