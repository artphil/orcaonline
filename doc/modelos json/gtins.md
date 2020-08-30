## Content-Type: application/json
## Host: localhost:8080

### GET Host/gtins

#### [code]

#### [response]
``` Markdown
[
    {
        "id": 1,
        "numero": 1,
        "brick": {
            "id": 1,
            "nome": "bricksteste2",
            "descricao": "bricksteste2",
            "classe": {
                "id": 1,
                "nome": "familiateste",
                "descricao": "familiateste",
                "familia": {
                    "id": 1,
                    "nome": "familiateste1",
                    "descricao": "familiateste",
                    "segmento": {
                        "id": 1,
                        "nome": "alimento4",
                        "descricao": "alimento4"
                    }
                }
            }
        }
    },
    {
        "id": 2,
        "numero": 2,
        "brick": {
            "id": 1,
            "nome": "bricksteste2",
            "descricao": "bricksteste2",
            "classe": {
                "id": 1,
                "nome": "familiateste",
                "descricao": "familiateste",
                "familia": {
                    "id": 1,
                    "nome": "familiateste1",
                    "descricao": "familiateste",
                    "segmento": {
                        "id": 1,
                        "nome": "alimento4",
                        "descricao": "alimento4"
                    }
                }
            }
        }
    }    
]
````
### GET Host/gtins/1

#### [code]

#### [response]
``` Markdown
    {
        "id": 1,
        "numero": 1,
        "brick": {
            "id": 1,
            "nome": "bricksteste2",
            "descricao": "bricksteste2",
            "classe": {
                "id": 1,
                "nome": "familiateste",
                "descricao": "familiateste",
                "familia": {
                    "id": 1,
                    "nome": "familiateste1",
                    "descricao": "familiateste",
                    "segmento": {
                        "id": 1,
                        "nome": "alimento4",
                        "descricao": "alimento4"
                    }
                }
            }
        }
    }
````
### POST Host/gtins

#### [code]
``` Markdown
    {
        "numero": "1",
        "brick": {
            "id": "1"
        }
    }
````    
#### [response]
``` Markdown
{
    "id": 1,
    "numero": 1,
    "brick": {
        "id": 1,
        "nome": null,
        "descricao": null,
        "classe": null
    }
}
````
### PUT Host/gtins/1

####[code]
``` Markdown
    {
        "numero": "2",
        "bricks": {
            "id": "1"
        }
    }
````
####[response] 
``` Markdown
{
    "id": 1,
    "numero": "2",
    "bricks": {
        "id": 1,
        "nome": null,
        "descricao": null,
        "familia": null
    }
}
````

<!-- │ └ ├ ─ -->
