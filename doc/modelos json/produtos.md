---
noteId: "f2385d50eb2c11eabf2891bfc5a1df88"
tags: []

---

## Content-Type: application/json
## Host: 45.80.152.3:8080

### GET Host/produtos

#### [code]

#### [response]
``` Markdown
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
````
### GET Host/produtos/1

#### [code]

#### [response]
``` Markdown
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
````
### POST Host/segmentos

#### [code]
``` Markdown
    {
        "nome": "teste2",
        "descricao": "teste3",
        "ncm": "teste3",
        "gtin": {
            "id": "1"
        }
    }
````
#### [response]
``` Markdown
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
````
### PUT Host/segmentos/1

#### [code]
``` Markdown
    {
        "nome": "teste4",
        "descricao": "teste4",
        "ncm": "teste4",
        "gtin": {
            "id": "1"
        }
    }
````
#### [response] 
``` Markdown
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
