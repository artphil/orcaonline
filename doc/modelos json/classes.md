## Content-Type: application/json
## Host: localhost:8080


### GET Host/classes

#### [code]

#### [response]

``` Markdown
[
    {
        "id": 1,
        "nome": "familiateste",
        "descricao": "familiateste",
        "familia": {
            "id": 1,
            "nome": "alimento4",
            "descricao": "alimento4"
        }
    },
        {
        "id": 2,
        "nome": "familiateste2",
        "descricao": "familiateste2",
        "familia": {
            "id": 1,
            "nome": "alimento4",
            "descricao": "alimento4"
        }
    },
]
````

### GET Host/classes/1

#### [code]

#### [response]

``` Markdown
[
    {
        "id": 1,
        "nome": "familiateste",
        "descricao": "familiateste",
        "familia": {
            "id": 1,
            "nome": "alimento4",
            "descricao": "alimento4"
        }
    }
]
````

### POST Host/classes

#### [code]
``` Markdown
    {
        "nome": "familiateste",
        "descricao": "familiateste",
        "familia": {
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
    "familia": {
        "id": 1,
        "nome": null,
        "descricao": null,
        "segmento": null
    }
}
````


### PUT Host/classes/1

#### [code]
``` Markdown
    {
        "nome": "familiateste1",
        "descricao": "familiateste",
        "familia": {
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
    "familia": {
        "id": 1,
        "nome": null,
        "descricao": null,
        "segmento": null
    }
}
````

<!-- │ └ ├ ─ -->


