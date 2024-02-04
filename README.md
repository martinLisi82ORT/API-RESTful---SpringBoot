# API RESTful - Spring Boot

Arquitectura de Cliente/Servidor mediante el protocolo HTTP, desarrollado con Spring Boot.
Todos los recursos de la API pueden ser consumidos mediante peticiones HTTP (POST, GET, PUT y DELETE). La misma está configurada con una Base de Datos MySQL

Aplicación web que permite el manejo de **Productos**, **Clientes** y **Ventas** de un bazar. La aplicación permite realizar un CRUD completo (GET, POST, DELETE y PUT) de todas sus clases con validación de datos (datos vacíos, nulos, numéricos y fecha) mediante la annotation _@Valid_. 

Al realizar una **Venta**, a la misma se le debe asignar un **Cliente** (valida en la Base de Datos que el Cliente exista) y una _Lista de productos_. Si la **Venta** es exitosa, se actualiza el stock de **Productos** en la respectiva Base de Datos. También valida que el stock en la Base de Datos sea suficiente para realizar la **Venta**.
Entre los requerimientos, además de los **GET** (obtener lista completa), **POST** (agregado), **PUT** (modificación) y **DELETE** (eliminación) de cada clase, también se puede:
1. Obtener **Producto**, **Cliente** o **Venta** individual.
2. Obtener los **Productos** cuya cantidad disponible sea inferior a 5 (cinco).
3. Obtener la lista de **Productos** de una determinada **Venta** por medio del código de venta (_codigo_venta_).
4. Obtener la sumatoria del monto y cantidad total de ventas de un determinado día por medio de la fecha de venta (_fecha_venta_).
5. Obtener el _codigo_venta_, el _total_, la _cantidad de productos_, el _nombre y apellido del Cliente_ de la **Venta** con el mayor monto.




**URL base:** 
`http://localhost:${PORT}/api/bazar`


---

### :page_facing_up: Documentacion:


---
### Herramientas utilizadas:
Java | SpringBoot | MySQL | Netbeans 

<div align="center">
<img width="60" height="30" src="https://elblogdecodigo.files.wordpress.com/2014/12/java_logo.png" />

<img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" />

<img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" />

<img src="https://img.shields.io/badge/apache%20netbeans-1B6AC6?style=for-the-badge&logo=apache%20netbeans%20IDE&logoColor=white" />
</div

