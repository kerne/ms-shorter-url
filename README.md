
# Challenge - Shorter URL

Aplicación que permite transformar una url larga en una url corta. Se utiliza para permitir adjuntar una URL corta a un  mensajes que se envía a través de mensajería SMS o Twitter.

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

