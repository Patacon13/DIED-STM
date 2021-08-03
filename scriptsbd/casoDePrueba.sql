INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A1','08:00','20:00','OPERATIVA');
INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A2','10:00','18:00','OPERATIVA');
INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A3','11:00','12:00','OPERATIVA');
INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A4','07:30','20:30','OPERATIVA');
INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A5','08:30','15:00','OPERATIVA');
INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A6','15:00','00:00','EN_MANTENIMIENTO');
INSERT INTO estacion (nombre,horario_apertura,horario_cierre, estado) VALUES ('A7','10:00','20:00','EN_MANTENIMIENTO');

INSERT INTO lineadetransporte (nombre, color, estado) VALUES ('L1','AMARILLO','ACTIVO');
INSERT INTO lineadetransporte (nombre, color, estado) VALUES ('L2','AZUL','ACTIVO');
INSERT INTO lineadetransporte (nombre, color, estado) VALUES ('L3','CYAN','INACTIVO');
INSERT INTO lineadetransporte (nombre, color, estado) VALUES ('L4','GRIS','ACTIVO');

INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (1,2,15,15,'ACTIVO',10,25,1);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (2,7,5,16,'ACTIVO',12,25,2);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (7,1,10,15,'INACTIVO',13,25,3);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (3,4,13,21,'ACTIVO',6,4,4);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (5,6,22,18,'INACTIVO',3,12,2);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (6,2,20,20,'ACTIVO',30,12,2);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (2,3,12,15,'ACTIVO',4,25,2);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (4,2,14,5,'ACTIVO',22,18,1);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (5,1,11,3,'ACTIVO',19,20,3);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (1,5,13,12,'ACTIVO',13,14,2);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (4,5,12,10,'ACTIVO',15,14,3);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (5,6,16,2,'INACTIVO',13,16,4);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (6,7,13,5,'INACTIVO',18,19,1);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (5,2,5,35,'ACTIVO',12,12,2);
INSERT INTO ruta (origen, destino, duracion, pasajerosmax, estado, costo, kilometros, linea) VALUES (4,6,2,20,'INACTIVO',10,11,4);
