# OrçaOnline

Sistema para realização de orçamentos online entre empresas.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Sprint Planning

##### F1 ainda não é usuário do sistema, ele deseja se  cadastrar e informar os produtos que fornece para aumentar sua visibilidade no mercado. F1 produz dois itens primários e não tem nenhuma especificidade.
##### C1 precisa fazer uma nova aquisição de matéria prima para sua produção. Ele descobriu a plataforma e deseja se registrar no sistema para testar suas funcionalidades.  

###### Tarefas: H1-F1, H1-C1
* Criar classe de usuário 
* Criar camada de persistência para o usuário.
* Criar camada de recursos (endpoint) para o usuário.
* Tela de Cadastro do Usuário
* Tela de Edição dos dados do Usuário
* Tela de Cadastro do Fornecedor

##### A1 precisa cadastrar produtos para que sejam encontrados pelos usuários. 

###### Tarefas: H1-A1
* Criar classe do produto 
* Criar camadas de persistência para o produto.
* Criar camada de recursos (endpoint) para o produto.
* Tela de Cadastro de Produtos
* Tela de Listagem de Produtos
* Tela de Edição de Produtos
* Tela de Exclusão de Produtos

##### A1 precisa saber se há orçamentos em aberto que não foram preenchidos por nenhum fornecedor. 

###### Tarefas: H2-A1
* Criar classe do orçamento. 
* Criar camadas de persistência para o orçamento.
* Criar camada de recursos (endpoint) para o orçamento.
* Tela de Cadastro de Orçamento
* Tela de Listagem de Orçamentos Pendentes/Solicitação de Orçamentos.
* Lista de Fornecedores que preencheram o Orçamento

##### A1 Necessita de uma maneira de coletar feedback dos usuários do sistema a fim de melhorar a prestação do serviço, bem como sugestões de inserção de novos produtos.

###### Tarefas: H3-A1
* Página de Feedback
* Cadastro de Feedback (Reclamação, Elogio, Sugestão)
* Página de relatórios de uso do Sistema

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
