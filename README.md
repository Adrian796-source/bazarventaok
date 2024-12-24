Es un proyecto backend en java, spring boot, en intellij idea community con open jdk 17 con base de datos postgreSql, los test hechos con jacoco y cubierto con sonarqube para análisis estático del código. He ejecutado los test de integracion con junit 5 y mockito.Asi mismo he hecho las imagenes y containers con archivos de ejemplo.O sea el proyecto esta dockerizado. He agregado archivos de configuracion de ejemplos para asi resguardar los datos sensibles. Tienen que cambiar lo que indica los archivos de ejemplo por sus configuraciones al crear su proyecto para que todo funcione bien. Por ejemplo application.properties se muestra en application-example.properties dado que application.properties ,con sus datos sensibles estan en .gitignore en el proyecto que les muestro. Todas las configuraciones por lo tanto estan con los archivos de ejemplo. Asi mismo  Dockerfile-sonarqube se muestra en: Dockerfile-sonarqube-example, Dockerfile en : Dockerfile-example, Dockerfile-postgres se muestra en Dockerfile-postgres-example y el docker-compose.yml en:docker-compose-example.yml. Tambien descargar todas las herramientas que sean necesarias para el proyecto por ejemplo sonarqube, docker, postgres o mysql o el gestor de base de datos que usen,intellij idea o el ide con el que editan codigo, etc, etc, etc.
Pasos para Configuración

1. Clonar el Repositorio:
sh git clone https://github.com/tu-usuario/tu-proyecto.git
cd tu-proyecto

Configurar Archivos de Ejemplo:

2. Copia y renombra los archivos de configuración de ejemplo:
cp src/main/resources/application-example.properties src/main/resources/application.properties,etc demas archivos de configuracion.
application-docker-example.properties
application-local-example.properties
application-test-example.properties
application-test-local-example.properties
application-docker-example.properties

cp Dockerfile-example Dockerfile
cp Dockerfile-postgres-example Dockerfile-postgres
cp Dockerfile-sonarqube-example Dockerfile-sonarqube
cp docker-compose-example.yml docker-compose.yml

3.Actualizar Configuraciones:

Reemplaza los valores en los archivos de configuración recién copiados con tus propias configuraciones específicas.

4.Construir y Ejecutar los Contenedores Docker:
docker-compose up --build

5.Ejecutar Pruebas e Integración con SonarQube:
mvn clean install
mvn test jacoco:report
mvn sonar:sonar y agregar el token del proyecto de sonarqube

Nota

Es importante descargar e instalar todas las herramientas necesarias mencionadas anteriormente (Java, Docker, PostgreSQL, SonarQube, etc.) para que el proyecto funcione correctamente.




