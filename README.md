# Reto DevOps - Grupo #1
Este proyecto contiene la automatización de pruebas para APIs REST y SOAP.

## **Requisitos**
Para este proyecto, se necesitará:

**Windows**
> - Java 8 o superior.
> - Gradle para compilar el proyecto.
> - Configuración de SonarQube.
> - Configuración de Jenkins.

**Ubuntu**
> - JDK instalado.
> - Gradle para compilar el proyecto. (https://gradle.org/install/)

## **¿Cómo correr el proyecto?**


Para ejecutar el proyecto se deben seguir los siguientes pasos:

**Windows**
1. Abrir una consola de comandos o terminal en tu equipo dentro de la carpeta del proyecto.
2. Ejecutar el siguiente comando en la terminal:

`.\gradlew clean test aggregate`

**Ubuntu**
1. Abrir una consola de comandos o terminal en tu equipo dentro de la carpeta del proyecto.
2. Ejecutar el siguiente comando en la terminal:

`./gradlew clean test aggregate`

## **Estructura de las carpetas - Patrón ScreenPlay**

**main/java/com/sofkau**

> - _interactions:_ Contiene las clases que modelan las interacciones entre los actores y los elementos de la interfaz de usuario.
> - _models:_ Contiene clases con las definiciones de objetos para automatizar interacciones en aplicaciones.
> - _questions:_ Contiene las clases que incluyen preguntas claves para cada test case.
> - _tasks:_ Contiene las clases con métodos que realizan tareas específicas dentro del sistema, lo cual nos permitiría reutilizar la lógica sí es necesitarlo.
> - _utils:_ Contiene las clases con métodos que nos realicen procesos que son comunes pero utiles.

**test/java/com/sofkau**
> - _runners:_ Clases para correr los test cases.
> - _setup:_ Clases para configuraciones.
> - _stepdefinitions:_ Clases con la ejecución de los pasos definidos en los feature .

**test/resources/java/com/sofkau**
> - _features:_ Contiene los gherkin con la definición de los casos de prueba en un lenguaje natural.


## **Configuración de Sonar**
**Paso 1:**

- Inicialmente nos dirigimos al panel de control de Jenkins y seleccionamos la opción "Administrar Jenkins", allí seleccionamos la opción "Administrar plugins".

![Paso 1](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/Sonar/AdministrarPlugins.png)


**Paso 2:**

- Allí buscamos el plugin con el nombre: "SonarQube Scanner for Jenkins"

![Paso 2](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/Sonar/SonarPlugin.png)


**Paso 3:**

- Una vez instalado el plugin, al momento de configurar una tarea, dentro de los build steps tendremos la opción de seleccionar el plugin de SonarQube para su ejecución.

![Paso 3](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/Sonar/SonarqubeBuildStep.png)


**Paso 4:**

- Una vez seleccionado el nuevo step podremos configurar el escaneo utilizando la siguiente configuración:

![Paso 4](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionSonarPlugin.png)

## **Configuración de Jenkins**
**Paso 1**

- Inicialmente procedemos a crear una nueva tarea.

![Paso 1](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/NuevaTarea.png)

**Paso 2**

- Después procedemos a nombrar y seleccionar el tipo de proyecto que vamos a utilizar.

![Paso 2](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/CreacionNuevaTarea.png)

**Paso 3**

- Una vez se ha creado la nueva tarea se procede a configurar esta tarea ingresando a la siguiente opción.

![Paso 3](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/ConfigurarTarea.png)

**Paso 4**

- Dentro de las opciones de configuración, inicialmente completaremos la configuración del origen del código fuente.

![Paso 4](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/OrigenCodigoFuente.png)

**Paso 5**

- Activamos la siguiente opción para que Jenkins utilice el webhook creado previamente en GitHub.

![Paso 5](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/GithubHookTrigger.png)

**Paso 6**

- Dentro de la configuración del build step procedemos a configurar la línea de código que enviará Jenkins vía CMD, en esta caso usaremos un comado para Windows.

![Paso 6](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/BuildStepComandoWindows.png)

**Paso 7**

- Ahora procederemos a configurar el envío de reportes y mensajes vía email, inicialmente ingresaremos a la configuración de Jenkins usando la siguiente opción:

![Paso 7](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/ConfiguracionEmail01.png)

**Paso 8**

- Posteriormente ingresamos a la opción de configuración de sistema.

![Paso 8](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/ConfiguracionEmail02.png)

**Paso 9**

- Dentro de esta opción deberemos configurar las siguientes opciones:

![Paso 9](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/ConfiguracionEmail03.png)

**Paso 10**

- Dentro de estas opciones podremos configurar las opciones de Subject y cuerpo del email.

![Paso 10](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/ConfiguracionEmail04.png)

**Paso 11**

- Finalmente debemos configurar la dirección de correo desde la cual se enviará el email.

![Paso 11](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/ConfiguracionEmail05.png)

**Paso 12**

- Dentro del build step que ya tenemos creado debemos seleccionar la siguiente opción dentro de las acciones del Build.

![Paso 12](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/EditableEmailNotification.png)

**Paso 13**

- Una vez seleccionada la acción debemos configurar las direcciones de correo donde se enviará el email.

![Paso 13](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/EditableEmailNotification01.png)

**Paso 14**

Posteriormente tendremos la opción de configurar el subject y el cuerpo del email pero esta configuracion aplicará solamente para esta tarea.

![Paso 14](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/EditableEmailNotification02.png)

**Paso 15**

- Aquí seleccionaremos el Trigger para el envío del email, en este caso seleccionaremos que siempre se envie un email pero tenemos muchas más opciones.

![Paso 15](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/EditableEmailNotification03.png)

**Paso 16**

- Una vez seleccionado el Trigger podremos seleccionar la lista de personas a las cuales será enviado el email.

![Paso 16](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/EditableEmailNotification04.png)

**Paso 17**

- Finalizada la configuración crearemos una nueva acción en la cual se enviarán emails cada vez que falle el build, para esto seleccionaremos la siguiente opción:

![Paso 17](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/NotificacionPorCorreo.png)

**Paso 18**

- Aquí ingresaremos unicamente las direcciones de correo electrónico ya que esta acción siempre se disparará cuando falle el build.

![Paso 18](https://github.com/JessiLopezObando/retoQaDevOps2023C1G1/tree/trunk/imagenes/ConfiguracionJenkins/NotificacionPorCorreo01.png)