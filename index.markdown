---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: default
---

# DIED-STM

## Descripcion

Trabajo práctico integrador para la materia Diseño e Implementación de Estructuras de Datos - UTN FRSF - 2021.

El trabajo presentado consta de la resolución de los 6 incisos con su integración en una base de datos y la visualización de una interfaz gráfica de usuario diseñada desde la biblioteca gráfica Swing.

## Compilacion

Por ser el IDE recomendado por la cátedra, el proyecto está configurado para ser fácilmente compilado y construido en Eclipse (por las rutas relativas a las librerías).

### Compilación

0. Instalar dependencias e IDE

#### En Ubuntu 20.04

En una terminal:
```sh
sudo apt update
sudo apt install snapd

sudo apt install git
sudo apt install openjdk-11-jdk

sudo snap install eclipse --classic 
```

Aclaración: la ultima version estable otorgada por snap es del 2019. Para instalar una versión posterior estable se debe usar el instalador oficial https://www.eclipse.org/downloads/

#### En Windows
Abrir una terminal (Windows Terminal, CMD, Powershell, etc.) con Chocolatey (https://chocolatey.org/install) previamente instalado.

```sh
choco install openjdk11
choco install git
choco install eclipse
```

1. Clonar proyecto

En una terminal:
```sh
git clone <LINKDELREPO>
```

2. Importar proyecto

En eclipse:

```
File->Open Projects from File System
Directory... *Seleccionar directorio*
Finish
```

3. Ejecutar

En eclipse:

```
Run->Run Configurations
Doble click en Java Application
Search...
Seleccionar gui.ventana
Run
```

**Aclaración importante: Si se ejecuta el binario, se debe tener una base de datos en localhost con puerto 3306 con usuario 'usuario' y contraseña 'contrasena'**
