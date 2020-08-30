## Content-Type: application/json
## Host: localhost:8080

### GET Host/bricks

#### [code]

#### [response]

``` Markdown
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
````
### GET Host/produtos/1

#### [code]

#### [response]
``` Markdown
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
````
### POST Host/bricks

#### [code]
``` Markdown
    {
        "nome": "bricksteste",
        "descricao": "bricksteste",
        "classe": {
            "id": "1"
        }
    }
````
#### [response]
``` Markdown
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
````
### PUT Host/bricks/1

#### [code]
``` Markdown
    {
        "nome": "bricksteste2",
        "descricao": "bricksteste2",
        "classe": {
            "id": "1"
        }
    }
````
#### [response] 
``` Markdown
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
