##Content-Type: application/json
##Host: localhost:8080

``` Markdown
___________________________________________________________________

GET Host/bricks

[code]

[response]

[
    {
        "id": 1,
        "nome": "bricksteste",
        "descricao": "bricksteste",
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
    },
    {
        "id": 2,
        "nome": "bricksteste2",
        "descricao": "bricksteste2",
        "classe": {
            "id": 1,
            "nome": "classeteste",
            "descricao": "classeteste",
            "familia": {
                "id": 1,
                "nome": "familiateste",
                "descricao": "familiateste",
                "segmento": {
                    "id": 1,
                    "nome": "segmentoteste",
                    "descricao": "segmentoteste"
                }
            }
        }
    }    
]
___________________________________________________________________

GET Host/produtos/1

[code]

[response]

    {
        "id": 2,
        "nome": "bricksteste2",
        "descricao": "bricksteste2",
        "classe": {
            "id": 1,
            "nome": "classeteste",
            "descricao": "classeteste",
            "familia": {
                "id": 1,
                "nome": "familiateste",
                "descricao": "familiateste",
                "segmento": {
                    "id": 1,
                    "nome": "segmentoteste",
                    "descricao": "segmentoteste"
                }
            }
        }
    }   
___________________________________________________________________

POST Host/bricks

[code]

    {
        "nome": "bricksteste",
        "descricao": "bricksteste",
        "classe": {
            "id": "1"
        }
    }

[response]

{
    "id": 1,
    "nome": "bricksteste",
    "descricao": "bricksteste",
    "classe": {
        "id": 1,
        "nome": null,
        "descricao": null,
        "familia": null
    }
}

___________________________________________________________________

PUT Host/bricks/1

[code]
    {
        "nome": "bricksteste2",
        "descricao": "bricksteste2",
        "classe": {
            "id": "1"
        }
    }

[response] 

{
    "id": 1,
    "nome": "bricksteste2",
    "ncm": "bricksteste2",
    "classe": {
        "id": 1,
        "nome": null,
        "descricao": null,
        "familia": null
    }
}
````

<!-- │ └ ├ ─ -->
