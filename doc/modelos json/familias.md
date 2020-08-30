## Content-Type: application/json
## Host: localhost:8080

### GET Host/familias

#### [code]

#### [response]
``` Markdown
[
    {
        "id": 1,
        "nome": "familiateste",
        "descricao": "familiateste",
        "segmento": {
            "id": 1,
            "nome": "alimento4",
            "descricao": "alimento4"
        }
    },
        {
        "id": 2,
        "nome": "familiateste2",
        "descricao": "familiateste2",
        "segmento": {
            "id": 1,
            "nome": "alimento4",
            "descricao": "alimento4"
        }
    }
]
````
### GET Host/familias/1

#### [code]

#### [response]
``` Markdown
    {
        "id": 1,
        "nome": "familiateste",
        "descricao": "familiateste",
        "segmento": {
            "id": 1,
            "nome": "alimento4",
            "descricao": "alimento4"
        }
    }
````
### POST Host/familias

#### [code]
``` Markdown
    {
        "nome": "familiateste",
        "descricao": "familiateste",
        "segmento": {
            "id": "1"
        }
    }
````
#### [response]
``` Markdown
{
    "id": 2,
    "nome": "familiateste",
    "descricao": "familiateste",
    "segmento": {
        "id": 1,
        "nome": null,
        "descricao": null
    }
}
````
### PUT Host/familias/1

#### [code]
``` Markdown
    {
        "nome": "familiateste1",
        "descricao": "familiateste",
        "segmento": {
            "id": "1"
        }
    }
````
#### [response] 
``` Markdown
{
    "id": 1,
    "nome": "familiateste1",
    "descricao": "familiateste",
    "segmento": {
        "id": 1,
        "nome": null,
        "descricao": null
    }
}
````

<!-- │ └ ├ ─ -->
