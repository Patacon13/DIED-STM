CREATE TABLE usuario(
nombre varchar(64),
apellido varchar(64),
email varchar(256)
);

CREATE TABLE estacion (
nombre varchar(64) primary key,
horario_apertura varchar(12),
horario_cierre varchar(12)
);

CREATE TABLE ruta(
id integer primary key,
origen varchar(64) references estacion(nombre),
destino varchar(64) references estacion(nombre),
duracion numeric,
pasajerosmax integer,
estado varchar(20),
costo numeric
);

CREATE TABLE mantenimiento(
id integer primary key,
estacion references estacion(nombre),
fechainicio date,
fechafin date,
observaciones varchar(512)
);

CREATE TABLE lineadetransporte(
id integer primary key,
nombre varchar(256),
color varchar(40),
estado varchar(20),
listaestaciones varchar(512)
);

CREATE TABLE venta(
nro_boleto integer primary key,
correo_electronico_cli varchar(256),
nombre_cliente varchar(256),
fecha_venta date,
origen references estacion(nombre),
destino references estacion(nombre),
costo_boleto numeric,
camino_a_seguir varchar(512)
);