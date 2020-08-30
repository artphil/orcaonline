## Content-Type: application/json
## Host: localhost:8080

``` Markdown
___________________________________________________________________

GET Host/gtins

[code]

[response]

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
___________________________________________________________________

GET Host/gtins/1

[code]

[response]

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
___________________________________________________________________

POST Host/gtins

[code]

    {
        "numero": "1",
        "brick": {
            "id": "1"
        }
    }
    
[response]

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
___________________________________________________________________

PUT Host/gtins/1

[code]
    {
        "numero": "2",
        "bricks": {
            "id": "1"
        }
    }

[response] 

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
