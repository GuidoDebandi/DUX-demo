# DUX Demo

Este proyecto es una demostración de un entorno de microservicios para el manejo de entidades con Java Springboot, implementando validacion de usuarios a traves JWT.

## Requisitos
- [Java](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Estructura del proyecto

El proyecto está organizado en los siguientes directorios:

- `back-proxy/`: Servicio enrutador del entorno quien disponibiliza los endpoints validando la autoridad del usuario solicitante.
- `security/`: Servicio para validar la seguridad del entorno, con login y registro de usuarios y validacion de JWT.
- `teams/`: Servicio para la logica del negocio, implementa un CRUD basico para entidades de equipos de futbol.

## Construir y ejecutar los servicios

El proyecto se encuentra configurado para levantar los servicios necesario con solo el comando de docker-compose up. Se describe a continuacion un instructivo de como hacerlo:

### Paso 1: Clonar el repositorio
Clonar este repositorio en tu máquina local:
```sh
git clone https://github.com/GuidoDebandi/DUX-demo.git
cd DUX-demo
```

### Paso 2: Empaquetado de Servicios
Es necesario crear una build de maven para que exista el archivo .jar dentro de cada proyecto, para esto se debe navegar dentro de cada servicio para ejecutar el comando mvn clean package :
```sh
cd back-proxy
mvn clean package
cd ../security
mvn clean package
cd ../teams
mvn clean package
```

### Paso 3: Construir los servicios
Navega al directorio raíz del proyecto y ejecuta el siguiente comando para construir las imágenes de Docker para cada servicio y levantar los contenedores:
```sh
docker-compose up --build
```
Este comando levantara los servicios en los siguiente paths:

- `back-proxy/`: http://localhost:8080/.
- `security/`: http://localhost:9001/.
- `teams/`: http://localhost:8082/.

Internamente docker podrá acceder a cada servicio de la siguiente manera:

- `back-proxy/`: http://back-proxy:8080/.
- `security/`: http://security:9001/.
- `teams/`: http://teams:8082/.

Esto porque docker levanta cada imagen de los servicios y crea una ruta mas amigable para ser utilizada internamente. 


Construirá las imágenes de Docker para back-proxy y service2 utilizando los Dockerfiles correspondientes.
Levantará los contenedores y expondrá los puertos especificados en el archivo docker-compose.yml.

### Apagar los servicios
Para apagar los servicios, ejecuta el siguiente comando:
```sh
docker-compose down
```

## Notas adicionales
Asegúrate de que los archivos JAR (service1.jar y service2.jar) estén ubicados en el directorio target de cada servicio antes de construir las imágenes.
Puedes personalizar los puertos y otros parámetros en el archivo docker-compose.yml según tus necesidades específicas.



