
# Challenge - Shorter URL

Aplicación que permite transformar una url larga en una url corta. Se utiliza para permitir adjuntar una URL corta a un  mensajes que se envía a través de mensajería SMS o Twitter.

## Tech Stack

**MS:** Spring boot, Java 11, h2-r2dbc, Redis

## API Reference

#### Create an Url short

```http
  POST /
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `url` | `string` | **Required**. URL to Transform |

#### Get Data URL

```http
  GET /?uri=${uri}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `uri`      | `string` | **Required**. Id of url to get details |

#### Delete data URL

```http
  DELETE /?uri=${uri}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `uri`      | `string` | **Required**. Id of url to delete |


## Requisito

Iniciar Redis
```bash
docker run -p 6379:6379 -d redis
```

## Run Locally
Clonar repo

```bash
  git clone https://github.com/kerne/ms-shorter-url
```

acceder a carpeta

```bash
  cd ms-shorter-url
```
iniciar app

```bash
  ./gradlew bootRun
```

