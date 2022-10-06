# Laboratorio 6: Hooks / React Router

## Inovación y Emprendimiento con Tecnologías de Información

#### Camilo Andrés Pichimata Cárdenas

##### Septiembre del 2022

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

Si damos click sobre el texto **_Let us help you!_** la aplicación redirigirá al usuario a la página Tasks de la aplicación en caso de estar logueado, de lo contrario se redirige a la página de login:

![](img/login.png)

Si damos click en el botón _Login_ sin especificar credenciales obtenemos un error y se muestra una alerta que indica que se deben llenar todos los campos.

![](img/login-2.png)

Del mismo modo al dar click en aceptar se resaltan los campos vacíos y se mustra un mensaje por campo solicitando rellenarlo:

![](img/login-3.png)

Rellenamos los campos y damos click en el botón _Login_:

![](img/login-4.png)

Al dar click se realiza una petición **POST** usando Axios a la dirección web `http://localhost:8081/api/v2/auth` a la que se envían las credenciales ingresadas en el formulario de Login; esta petición es recibida por la aplicación _Users_ la cuál verifica si las credenciales pertenecen a un usuario registrado con anterioridad en la base de datos, si esto no es cierto, la aplicación reponde con un error, por otro lado, si los datos coinciden con un registro, la aplicación responde con un token y su fecha de expiración, estos datos son recibidas por la aplicación web y son almacenadas. Si todo sale bien, se redirige a la página _Tasks_:

![](img/tasks.png)

En esta página se visualizan las tareas almacenadas en la base de datos, para obtenerlas se realiza una petición **GET** a la dirección web `http://localhost:8080/api/v2/tasks/`, esta petición es recibida por la aplicación _Tasks_ y esta nos responde con una lista de tareas que son procesadas y mapeadas por la aplicación web, para poder hacer esto es necesario tener almacenado el token enviado por la aplicación de _Users_, esto se verifica al momento de ingresar a la página de tareas justo antes de solicitar el listado, en caso de que el token haya caducado la sesión se cerrará y se redirigirá a la página de _Login_ para ingresar las credenciales y obtener un nuevo token.

Como se puede ver en la imagen anterior, en la lista de opciones del menú superior ya no se visualiza la opción para ir a la página de _Login_, pero a su vez, se encuentra un botón con el que se puede cerrar la sesión en la esquina superior derecha.

A las páginas _Home_ y _About_ se puede acceder sin necesidad de loguearse en la aplicación, estas páginas fueron diseñadas con el dín de probar el correcto funcionamiento de React Ruoter

Damos click en la opción _About_ para visualizar dicha página:

![](img/about.png)

Finalmente si damos click en el botón _Logout_ cerraremos la cesión, pero en este caso, permaneceremos en la página _About_. Como se puede ver el botón de **Logout** ha desaparecido y nuevamente se visualiza en la parte superior la opción para realizar el login. En caso de cerrar sesión en la página _Tasks_ seremos redirigidos automáticamente a la página _Login_:

![](img/about-2.png)

# Laboratorio 7: Bruenas practicas de Código

## Inovación y Emprendimiento con Tecnologías de Información

#### Camilo Andrés Pichimata Cárdenas

##### Septiembre del 2022

## Desarrollo

Para el desarrollo de este laboratorio se trabajó sobre una nueva rama llamada [`Lab_7`](https://github.com/CamiloPichimata/IETI-Lab_6-Hooks-React_Router/tree/Lab_7) en el laboratorio 6

### Parte 1: VSCode

1. Se realizó la instalación del plugin **_Prettier_** en _VSCode_

![](img/Prettier.png)

2. Se realizó la instalación del plugin **_Eslint_** en _VSCode_

![](img/Eslint.png)

### Parte 2: Eslint

1. Se abrió el archivo **[package.json](https://github.com/CamiloPichimata/IETI-Lab_6-Hooks-React_Router/blob/master/WebApplication/package.json)**, nos dirigimos a `eslintConfig`, en donde encontramos lo siguiente:

```json
  "eslintConfig": {
    "extends": [
      "react-app",
      "react-app/jest"
    ]
  },
```

Modificamos hasta obtener lo siguiente y guardamos

```json
  "eslintConfig": {
    "extends": [
      "eslint:recommended",
      "react-app",
      "react-app/jest",
      "prettier"
    ]
  },
```

2. Ejecutamos el comando `npm i -D eslint` desde el directorio raiz de la aplicación.

![](img/comando_consola_1.png)

3. Agregamos las siguientes líneas al archivo **[package.json](https://github.com/CamiloPichimata/IETI-Lab_6-Hooks-React_Router/blob/master/WebApplication/package.json)** en `scripts`

```json
"lint": "eslint --ext .js,.jsx .",
"lint:fix": "npm run lint -- --fix"
```

Obtenemos lo siguiente:

```json
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject",
    "lint": "eslint --ext .js,.jsx .",
    "lint:fix": "npm run lint -- --fix"
  },
```

4. Se ha completado la instalación y configuración del complemento, si todo sale bien al poner el puntero del mouse sobre algún error o marca de error en el editor, se debe visualizar un mensaje por parte de **_eslint_**, en este caso se agregó una variable que no ha sido usada en ningún lado, se puede observar el siguiente mensaje por parte de **_eslint_**:

![](img/Eslint-prueba.png)

### Parte 3: Prettier

1. Se agregón una nueva llave llamada `"prettier"` abajo de `eslintConfig` como se puede ver a continuación:

```json
  ...
  "eslintConfig": {
    "extends": [
      ...
    ]
  },
  "prettier": {
  },
  "browserslist": {
    ...
```

2. Nos dirigimos a **_file/preferences/settings_** y en la configuración de usuarios (Users) vamos a **_Text Editor / Formatting_**; Allí damos clic en el checkbox **Format On Save** como se puede ver a continuación:

![](img/configuraci%C3%B3n.png)

### Parte 4: Husky

Corremos los siguientes comandos desde la dirección raiz de la aplicación:

```
npm i -D husky
```

![](img/comando_consola_2.png)

```
npm set-script prepare "husky install"
```

![](img/comando_consola_3.png)

```
npm run prepare
```

![](img/comando_consola_4.png)

```
npm i -D prettier
```

![](img/comando_consola_5.png)

```
npm set-script format "prettier --write ."
```

![](img/comando_consola_6.png)

```
npx husky add .husky/pre-commit "npm run lint:fix && npm format"
```

![](img/comando_consola_7.png)

Ahora cada vez que se intente realizar un commit, entes de que este se lleve a cabo, se valida que no hayan errores de **eslint** y se formatearán los archivos del proyecto usando la configuración de **prettier**.
