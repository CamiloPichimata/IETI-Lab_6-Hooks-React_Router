# Laboratorio 6: Hooks / React Router
## Inovación y Emprendimiento con Tecnologías de Información
#### Camilo Andrés Pichimata Cárdenas
##### Agosto del 2022


## Desarrollo
El presente laboratorio tiene como fin conectar el login realizado en el [laboratorio 5](https://github.com/CamiloPichimata/IETI-Lab_5-Intro_to_React_JS) con el API Restful `Tasks` desarrollada en la segunda parte del [laboratorio 2](https://github.com/CamiloPichimata/IETI-Lab_2-Spring_Boot_REST_API-Tasks_Microservice-Gradle); Para esto se utilizaron diversos hooks de [React](es.reactjs.org), [React Router Web](https://v5.reactrouter.com/web/guides/quick-start) y se realizaron peticiónes GET y POST usando la librería [Axios](https://axios-http.com/docs/intro)

Al API Tasks se le tuvo que agregar la conexión con una base de datos MongoDB debido a que no contaba con ella, a esta misma base de datos se conectó una copia de la API Restful `Users` desarrollada durante los [laboratorios 2, 3 y 4](https://github.com/CamiloPichimata/IETI-Lab_4-Spring_Security_JWT-Users_Microservice-Gradle) con el fin de almacenar los usuarios de la aplicación y restringir el ingreso a este mediante el uso de un token que cuenta con un tiempo determinado de vigencia, en este caso 60 minutos.

A continuación, se presenta la interfaz de la aplicación y su funcionamiento:

### Instalación
Para la ejecución de la aplicación después de clonar el respositorio se deben ejecutar por separado la aplicación `Tasks` y la aplicación `Users`, debido a que en este caso ambas aplicaciones se implementaron usando Spring Boot, por defecto estas buscan correr en el mismo puerto **8080**, para evitar conflictos en el archivo **aplication.properties** de `users` se incluyó la siguiente línea

```
server.port=8081
```

Esto se realiza con el fin de configurar el puerto **8081** para la ejecución de la aplicación.

Después de ejecutar las dos aplicaciones se debe ejecutar la aplicación de React, en este caso ejecutando el comando `npm start`.


### Ejecución
Se abre la aplicación web en la dirección `http://localhost:3000/`, en este caso se muestra la página de inicio (Home)

![](img/home.png)

Si damos click sobre el texto ***Let us help you!*** la aplicación redirigirá al usuario a la página Tasks de la aplicación en caso de estar logueado, de lo contrario se redirige a la página de login:

![](img/login.png)

Si damos click en el botón *Login* sin especificar credenciales obtenemos un error y se muestra una alerta que indica que se deben llenar todos los campos.

![](img/login-2.png)

Del mismo modo al dar click en aceptar se resaltan los campos vacíos y se mustra un mensaje por campo solicitando rellenarlo:

![](img/login-3.png)

Rellenamos los campos y damos click en el botón *Login*:

![](img/login-4.png)

Al dar click se realiza una petición **POST** usando Axios a la dirección web `http://localhost:8081/api/v2/auth` a la que se envían las credenciales ingresadas en el formulario de Login; esta petición es recibida por la aplicación *Users* la cuál verifica si las credenciales pertenecen a un usuario registrado con anterioridad en la base de datos, si esto no es cierto, la aplicación reponde con un error, por otro lado, si los datos coinciden con un registro, la aplicación responde con un token y su fecha de expiración, estos datos son recibidas por la aplicación web y son almacenadas. Si todo sale bien, se redirige a la página *Tasks*:

![](img/tasks.png)

En esta página se visualizan las tareas almacenadas en la base de datos, para obtenerlas se realiza una petición **GET** a la dirección web `http://localhost:8080/api/v2/tasks/`, esta petición es recibida por la aplicación *Tasks* y esta nos responde con una lista de tareas que son procesadas y mapeadas por la aplicación web, para poder hacer esto es necesario tener almacenado el token enviado por la aplicación de *Users*, esto se verifica al momento de ingresar a la página de tareas justo antes de solicitar el listado, en caso de que el token haya caducado la sesión se cerrará y se redirigirá a la página de *Login* para ingresar las credenciales y obtener un nuevo token.

Como se puede ver en la imagen anterior, en la lista de opciones del menú superior ya no se visualiza la opción para ir a la página de *Login*, pero a su vez, se encuentra un botón con el que se puede cerrar la sesión en la esquina superior derecha.

A las páginas *Home* y *About* se puede acceder sin necesidad de loguearse en la aplicación, estas páginas fueron diseñadas con el dín de probar el correcto funcionamiento de React Ruoter

Damos click en la opción *About* para visualizar dicha página:

![](img/about.png)

Finalmente si damos click en el botón *Logout* cerraremos la cesión, pero en este caso, permaneceremos en la página *About*. Como se puede ver el botón de **Logout** ha desaparecido y nuevamente se visualiza en la parte superior la opción para realizar el login. En caso de cerrar sesión en la página *Tasks* seremos redirigidos automáticamente a la página *Login*:

![](img/about-2.png)