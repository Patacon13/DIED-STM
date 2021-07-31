CREATE TABLE estacion (
id integer primary key auto_increment,
nombre varchar(64),
horario_apertura time,
horario_cierre time,
estado varchar(64)
);

CREATE TABLE ruta(
id integer primary key auto_increment,
origen integer references estacion(id),
destino integer references estacion(id),
duracion numeric,
pasajerosmax integer,
estado varchar(20),
costo numeric,
kilometros numeric,
linea integer references lineadetransporte(id)
);

CREATE TABLE mantenimiento(
id integer primary key auto_increment,
estacion integer references estacion(id),
fechainicio date,
fechafin date,
observaciones varchar(512)
);

CREATE TABLE lineadetransporte(
id integer primary key auto_increment,
nombre varchar(256),
color varchar(40),
estado varchar(20)
);


CREATE TABLE venta(
nro_boleto integer primary key,
correo_electronico_cli varchar(256),
nombre_cliente varchar(256),
fecha_venta date,
origen integer references estacion(id),
destino integer references estacion(id),
costo_boleto numeric
);

CREATE TABLE caminoaseguir(
nro_boleto integer references venta(nro_boleto),
ruta integer references ruta(id),
primary key(nro_boleto, ruta)
);
