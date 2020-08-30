# Content-Type: application/json
# Host: localhost:8080

``` Markdown
___________________________________________________________________

GET Host/classes

[code]

[response]

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
___________________________________________________________________


GET Host/classes/1

[code]

[response]

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
___________________________________________________________________

POST Host/classes

[code]

    {
        "nome": "familiateste",
        "descricao": "familiateste",
        "familia": {
            "id": "1"
        }
    }

[response]

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

___________________________________________________________________

PUT Host/classes/1

[code]
    {
        "nome": "familiateste1",
        "descricao": "familiateste",
        "familia": {
            "id": "1"
        }
    }

[response] 
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


