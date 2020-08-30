##Content-Type: application/json
##Host: localhost:8080
``` Markdown
___________________________________________________________________

GET Host/produtos

[code]

[response]

[
    {
        "id": 1,
        "nome": "teste2",
        "descricao": "teste3",
        "ncm": "teste3",
        "gtin": {
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
    },
    {
        "id": 2,
        "nome": "teste2",
        "descricao": "teste3",
        "ncm": "teste3",
        "gtin": {
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
    }
]
___________________________________________________________________

GET Host/produtos/1

[code]

[response]

    {
        "id": 1,
        "nome": "teste2",
        "descricao": "teste3",
        "ncm": "teste3",
        "gtin": {
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
    }
___________________________________________________________________

POST Host/segmentos

[code]

    {
        "nome": "teste2",
        "descricao": "teste3",
        "ncm": "teste3",
        "gtin": {
            "id": "1"
        }
    }

[response]

{
    "id": 2,
    "nome": "teste2",
    "descricao": "teste3",
    "ncm": "teste3",
    "gtin": {
        "id": 1,
        "numero": null,
        "brick": null
    }
}

___________________________________________________________________

PUT Host/segmentos/1

[code]
    {
        "nome": "teste4",
        "descricao": "teste4",
        "ncm": "teste4",
        "gtin": {
            "id": "1"
        }
    }

[response] 

    {
        "nome": "teste4",
        "descricao": "teste4",
        "ncm": "teste4",
        "gtin": {
            "id": 1,
            "nome": null,
            "descricao": null,
            "classe": null
   	 }
    }
````

<!-- │ └ ├ ─ -->
