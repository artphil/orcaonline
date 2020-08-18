# OrçaOnline

Sistema para realização de orçamentos online entre empresas.

<!-- ## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

``` bash
pip install foobar
```

## Usage

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
``` -->

## Sprint Planning

#### Atores

* Administrador (A#)
* Comprador (C#)
* Fornecedor (F#)

#### Tarefas Gerais

* Modelagem do Banco de Dados (Catarina)
* Modelagem das Classes 
  + Usuário/Cliente (Catarina)
  + Usuário/Fornecedor (Catarina)
  + Orçamento (Fabiano)
  + Produtos (Fabiano)
* Implementação do Banco de Dados (Fabiano)

#### E1-C1: Estória

Camila precisa fazer uma nova aquisição de matéria prima para sua produção. Ela descobriu a plataforma e deseja se registrar no sistema para fazer um orçamento.

#### E1-C1: Tarefas

* Criar classe de usuário/cliente (Catarina)
* Criar camada de persistência para o usuário. (Catarina)
* Criar camada de recursos (endpoint) para o usuário. (Catarina)
* Criar classe do orçamento. (Fabiano)
* Criar camadas de persistência para o orçamento. (Fabiano)
* Criar camada de recursos (endpoint) para o orçamento. (Fabiano)
* CRUD Comprador (Catarina)
* CRUD Orçamento (Fabiano)

* Tela de Cadastro do Usuário/Comprador (Arthur)
* Tela de edição dos dados de Usuário/Comprador (Arthur)
* Dashboard do Comprador (Arthur)
* Tela de Listagem de Produtos (Yasmim)
* Tela de Cadastro de Orçamento (Yasmim)
* Tela de Orçamentos Solicitados (Yasmim)
* Tela de exibição do Orçamento enviado por um Fornecedor (Yasmim)

#### E2-F1: Estória

Fábio ainda não é usuário do sistema, ele deseja se  cadastrar e informar os produtos que fornece para aumentar sua visibilidade no mercado. Fábio produz dois itens primários e não tem nenhuma especificidade.

#### E2-F1: Tarefas

* Criar classe de usuário/Fornecedor (Catarina)
* Criar camada de persistência para o usuário. (Catarina)
* Criar camada de recursos (endpoint) para o usuário. (Catarina)
* CRUD Fornecedor (Catarina)
* CRUD Produtos (Fabiano)
* Sistema de login e autenticação (Fabiano)

* Tela de Cadastro do Usuário/Fornecedor (Arthur)
* Tela de Edição dos dados do Usuário/Fornecedor (Arthur)
* Tela de Login (Arthur)
* Dashboard do Fornecedor (Arthur)
* Tela de Edição dos dados do Usuário/Fornecedor (Arthur)
* Tela de Cadastro de Produtos (Yasmim)
* Tela de Edição dos Produtos (Yasmim)
* Tela de Exclusão dos Produtos (Yasmim)

#### E3-A1: Estória

Antônio precisa cadastrar as categorias de produtos para que sejam encontrados pelos usuários, tanto pelos fornecedores que vão cadastrar seus produtos, como pelos compradores que vão pesquisar pelos produtos de interesse. 

#### E3-A1: Tarefas

* Criar classe do produto (Fabiano)
* Criar camadas de persistência para o produto. (Fabiano)
* Criar camada de recursos (endpoint) para o produto. (Fabiano)

* Tela de Listagem de Categorias de Produtos (Yasmim)
* Tela de Cadastro de Categorias de Produtos (Arthur)
* Tela de Edição de Categorias de Produtos (Arthur)
* Tela de Exclusão de Categorias de Produtos (Arthur)

#### E4-A1: Estória

Antônio precisa saber se há orçamentos em aberto que não foram preenchidos por nenhum fornecedor.

#### E4-A1: Tarefas

Filtro Orçamentos Pendentes (Fabiano)
Filtro Fornecedores que preencheram o Orçamento (Fabiano)

Tela de Listagem de Orçamentos pendentes (Yasmim)
Tela de Listagem de Fornecedores que preencheram o Orçamento  (Yasmim)
Tela de Cadastro de Orçamentos. (Arthur)
Tela de Exclusão de Produtos (Arthur)

#### E5-A1: Estória

Antônio necessita de uma maneira de coletar feedback dos usuários do sistema a fim de melhorar a prestação do serviço, bem como sugestões de inserção de novos produtos.

#### E5-A1: Tarefas

* CRUD Feedback (Fabiano)

* Tela de Listagem de Feedback (Reclamação, Elogio, Sugestão)  (Yasmim)
* Tela de Cadastro de Feedback (Reclamação, Elogio, Sugestão)  (Arthur)
* Tela de Edição de Feedback (Reclamação, Elogio, Sugestão) (Arthur)
* Tela de Exclusão de Feedback (Reclamação, Elogio, Sugestão) (Arthur)
* Tela de Relatórios de Uso do Sistema (Yasmim)

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
