# Reto DevOps - Grupo #1 
Este proyecto contiene la automatización de pruebas para APIs REST y SOAP.


## **Requisitos**
Para este proyecto, se necesitará:

> - Java 8 o superior.
> - Gradle para compilar el proyecto.
> - Configuración de SonarQube. 
> - Configuración de Jenkins.

## **¿Cómo correr el proyecto?**
Para ejecutar el proyecto se deben seguir los siguientes pasos: 

1. Abrir una consola de comandos o terminal en tu equipo.
2. Ejecutar el siguiente comando en la terminal:

`.\gradlew clean test aggregate`

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


## **Configuración de Jenkins**

