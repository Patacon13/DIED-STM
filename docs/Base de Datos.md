---
title: Base de Datos
parent: Funcionamiento
has_children: false
---
# Conexión a Base de Datos

Se utilizó el motor de base de datos 'MySql' y por medio del driver jdbc propio, se estableció la conexión a la BD. Por otro lado, por medio del DriverManager se consigue la conexión pasándole el URL, Usuario y Contraseña de la base de datos.

```java
public Connection crearConexion() throws SQLException, ClassNotFoundException {
	Connection conn = null;
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(URL, USER, PASS);
	return conn;
}
```

**Aclaración: en el binario compilado se puso a 'usuario' y 'contrasena' como usuario y contraseña predeterminado a una base de datos en localhost. Eso se debe configurar manualmente con los scripts insertados en el repositorio**
