CREATE DATABASE tpdied;

CREATE TABLE estacion (
id integer primary key auto_increment,
nombre varchar(64),
horario_apertura time,
horario_cierre time,
estado varchar(64)
);

CREATE TABLE lineadetransporte(
id integer primary key auto_increment,
nombre varchar(256),
color varchar(40),
estado varchar(20)
);

CREATE TABLE ruta(
id integer primary key auto_increment,
origen integer,
destino integer,
duracion numeric,
pasajerosmax integer,
estado varchar(20),
costo numeric,
kilometros numeric,
linea integer,
constraint fk_1 FOREIGN KEY (origen) REFERENCES estacion(id) ON DELETE CASCADE,
constraint fk_2 FOREIGN KEY (destino) REFERENCES estacion(id) ON DELETE CASCADE,
constraint fk_3 FOREIGN KEY (linea) REFERENCES lineadetransporte(id) ON DELETE CASCADE
);

CREATE TABLE mantenimiento(
id integer primary key auto_increment,
estacion integer,
fechainicio date,
fechafin date,
observaciones varchar(512),
constraint fk_4 FOREIGN KEY (estacion) REFERENCES estacion(id) ON DELETE CASCADE
);

CREATE TABLE venta(
nro_boleto integer primary key auto_increment,
correo_electronico_cli varchar(256),
nombre_cliente varchar(256),
fecha_venta date,
origen integer,
destino integer,
costo_boleto numeric,
caminoaseguir varchar(500),
constraint fk_5 FOREIGN KEY (origen) REFERENCES estacion(id) ON DELETE CASCADE,
constraint fk_6 FOREIGN KEY (destino) REFERENCES estacion(id) ON DELETE CASCADE
);

