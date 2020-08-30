##Content-Type: application/json
##Host: localhost:8080

``` Markdown
___________________________________________________________________

GET Host/familias

[code]

[response]

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
___________________________________________________________________

GET Host/familias/1

[code]

[response]

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
___________________________________________________________________

POST Host/familias

[code]

    {
        "nome": "familiateste",
        "descricao": "familiateste",
        "segmento": {
            "id": "1"
        }
    }

[response]

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

___________________________________________________________________

PUT Host/familias/1

[code]
    {
        "nome": "familiateste1",
        "descricao": "familiateste",
        "segmento": {
            "id": "1"
        }
    }

[response] 
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
