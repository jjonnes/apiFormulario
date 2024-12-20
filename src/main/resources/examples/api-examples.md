# Exemplos de Requisições e Respostas da API

## GrupoCliente

### 1. Criar novo Grupo de Cliente (POST /api/grupocliente)

Request:

```json
{
  "nome": "Grupo A"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "nome": "Grupo A",
  "clientes": []
}
```

### 2. Listar Grupos (GET /api/grupocliente)

Response (200 OK):

```json
[
  {
    "id": 1,
    "nome": "Grupo A",
    "clientes": []
  },
  {
    "id": 2,
    "nome": "Grupo B",
    "clientes": []
  }
]
```

### 3. Buscar Grupo por ID (GET /api/grupocliente/{id})

Response (200 OK):

```json
{
  "id": 1,
  "nome": "Grupo A",
  "clientes": []
}
```

### 4. Atualizar Grupo (PUT /api/grupocliente/{id})

Request (PUT /api/grupocliente/1):

```json
{
  "nome": "Grupo A Atualizado"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "nome": "Grupo A Atualizado",
  "clientes": []
}
```

### 5. Deletar Grupo (DELETE /api/grupocliente/{id})

Response (204 No Content)

## Cliente

### 1. Criar novo Cliente (POST /api/clientes/{grupoId})

Request (POST /api/clientes/1):

```json
{
  "nome": "Cliente A"
}
```

Response (200 OK):

```json
{
  "id": 1.1,
  "nome": "Cliente A",
  "grupoId": 1,
  "sequencia": 1
}
```

### 2. Listar Clientes (GET /api/clientes)

Response (200 OK):

```json
[
  {
    "id": 1.1,
    "nome": "Cliente A",
    "grupoId": 1,
    "sequencia": 1
  },
  {
    "id": 1.2,
    "nome": "Cliente B",
    "grupoId": 1,
    "sequencia": 2
  }
]
```

### 3. Buscar Cliente por ID (GET /api/clientes/{id})

Response (200 OK):

```json
{
  "id": 1.1,
  "nome": "Cliente A",
  "grupoId": 1,
  "sequencia": 1
}
```

### 4. Atualizar Cliente (PUT /api/clientes/{id})

Request (PUT /api/clientes/1.1):

```json
{
  "nome": "Cliente A Atualizado",
  "grupoId": 1
}
```

Response (200 OK):

```json
{
  "id": 1.1,
  "nome": "Cliente A Atualizado",
  "grupoId": 1,
  "sequencia": 1
}
```

### 5. Deletar Cliente (DELETE /api/clientes/{id})

Response (204 No Content)

## Atividade

### 1. Criar nova Atividade (POST /api/atividades)

Request:

```json
{
  "descricao": "Vistoria Estrutural"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "descricao": "Vistoria Estrutural",
  "subAtividades": []
}
```

### 2. Listar Atividades (GET /api/atividades)

Response (200 OK):

```json
[
  {
    "id": 1,
    "descricao": "Vistoria Estrutural",
    "subAtividades": []
  },
  {
    "id": 2,
    "descricao": "Análise de Documentação",
    "subAtividades": []
  }
]
```

### 3. Buscar Atividade por ID (GET /api/atividades/{id})

Response (200 OK):

```json
{
  "id": 1,
  "descricao": "Vistoria Estrutural",
  "subAtividades": []
}
```

### 4. Atualizar Atividade (PUT /api/atividades/{id})

Request (PUT /api/atividades/1):

```json
{
  "descricao": "Vistoria Estrutural Atualizada"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "descricao": "Vistoria Estrutural Atualizada",
  "subAtividades": []
}
```

### 5. Deletar Atividade (DELETE /api/atividades/{id})

Response (204 No Content)

## SubAtividade

### 1. Criar SubAtividade (POST /api/subatividades/{atividadeId})

Request (POST /api/subatividades/1):

```json
{
  "descricao": "Inspeção de Pilares"
}
```

Response (200 OK):

```json
{
  "id": 1.1,
  "descricao": "Inspeção de Pilares",
  "atividadeId": 1,
  "sequencia": 1
}
```

### 2. Listar SubAtividades (GET /api/subatividades)

Response (200 OK):

```json
[
  {
    "id": 1.1,
    "descricao": "Inspeção de Pilares",
    "atividadeId": 1,
    "sequencia": 1
  },
  {
    "id": 1.2,
    "descricao": "Inspeção de Vigas",
    "atividadeId": 1,
    "sequencia": 2
  }
]
```

### 3. Buscar SubAtividade por ID (GET /api/subatividades/{id})

Response (200 OK):

```json
{
  "id": 1.1,
  "descricao": "Inspeção de Pilares",
  "atividadeId": 1,
  "sequencia": 1
}
```

### 4. Atualizar SubAtividade (PUT /api/subatividades/{id})

Request (PUT /api/subatividades/1.1):

```json
{
  "descricao": "Inspeção de Pilares Atualizada",
  "atividadeId": 1
}
```

Response (200 OK):

```json
{
  "id": 1.1,
  "descricao": "Inspeção de Pilares Atualizada",
  "atividadeId": 1,
  "sequencia": 1
}
```

### 5. Deletar SubAtividade (DELETE /api/subatividades/{id})

Response (204 No Content)

## Funcionários

### 1. Criar novo Funcionário (POST /api/funcionarios)

Request:

```json
{
  "nome": "ENG. A"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "nome": "ENG. A"
}
```

### 2. Criar Múltiplos Funcionários (POST /api/funcionarios/batch)

Request:

```json
[
  {
    "nome": "ENG. A"
  },
  {
    "nome": "ENG. B"
  }
]
```

Response (200 OK):

```json
[
  {
    "id": 1,
    "nome": "ENG. A"
  },
  {
    "id": 2,
    "nome": "ENG. B"
  }
]
```

### 3. Listar Funcionários (GET /api/funcionarios)

Response (200 OK):

```json
[
  {
    "id": 1,
    "nome": "ENG. A"
  },
  {
    "id": 2,
    "nome": "ENG. B"
  }
]
```

### 4. Buscar Funcionário por ID (GET /api/funcionarios/{id})

Response (200 OK):

```json
{
  "id": 1,
  "nome": "ENG. A"
}
```

### 5. Atualizar Funcionário (PUT /api/funcionarios/{id})

Request (PUT /api/funcionarios/1):

```json
{
  "nome": "ENG. A Atualizado"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "nome": "ENG. A Atualizado"
}
```

### 6. Deletar Funcionário (DELETE /api/funcionarios/{id})

Response (204 No Content)

## Acompanhantes

### 1. Criar novo Acompanhante (POST /api/acompanhantes)

Request:

```json
{
  "nome": "SÍNDICO(A)"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "nome": "SÍNDICO(A)"
}
```

### 2. Criar Múltiplos Acompanhantes (POST /api/acompanhantes/batch)

Request:

```json
[
  {
    "nome": "SÍNDICO(A)"
  },
  {
    "nome": "ZELADOR"
  }
]
```

Response (200 OK):

```json
[
  {
    "id": 1,
    "nome": "SÍNDICO(A)"
  },
  {
    "id": 2,
    "nome": "ZELADOR"
  }
]
```

### 3. Listar Acompanhantes (GET /api/acompanhantes)

Response (200 OK):

```json
[
  {
    "id": 1,
    "nome": "SÍNDICO(A)"
  },
  {
    "id": 2,
    "nome": "ZELADOR"
  }
]
```

### 4. Buscar Acompanhante por ID (GET /api/acompanhantes/{id})

Response (200 OK):

```json
{
  "id": 1,
  "nome": "SÍNDICO(A)"
}
```

### 5. Atualizar Acompanhante (PUT /api/acompanhantes/{id})

Request (PUT /api/acompanhantes/1):

```json
{
  "nome": "SÍNDICO(A) ATUALIZADO"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "nome": "SÍNDICO(A) ATUALIZADO"
}
```

### 6. Deletar Acompanhante (DELETE /api/acompanhantes/{id})

Response (204 No Content)

## Visitas

### 1. Criar nova Visita (POST /api/visitas)

Request:

```json
{
  "grupo": "Grupo A",
  "cliente": "Cliente A",
  "funcionarios": "ENG. A, ENG. B",
  "acompanhantes": "SÍNDICO(A)",
  "subatividades": "Inspeção de Pilares, Inspeção de Vigas",
  "dataHoraInicio": "2024-01-20T09:00:00",
  "dataHoraFim": "2024-01-20T11:00:00",
  "conteudoObs": "Observações da visita",
  "conteudoVisita": "Detalhes da visita realizada"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "grupo": "Grupo A",
  "cliente": "Cliente A",
  "funcionarios": "ENG. A, ENG. B",
  "acompanhantes": "SÍNDICO(A)",
  "subatividades": "Inspeção de Pilares, Inspeção de Vigas",
  "dataHoraInicio": "2024-01-20T09:00:00",
  "dataHoraFim": "2024-01-20T11:00:00",
  "conteudoObs": "Observações da visita",
  "conteudoVisita": "Detalhes da visita realizada"
}
```

### 2. Listar Visitas (GET /api/visitas)

Response (200 OK):

```json
[
  {
    "id": 1,
    "grupo": "Grupo A",
    "cliente": "Cliente A",
    "funcionarios": "ENG. A, ENG. B",
    "acompanhantes": "SÍNDICO(A)",
    "subatividades": "Inspeção de Pilares, Inspeção de Vigas",
    "dataHoraInicio": "2024-01-20T09:00:00",
    "dataHoraFim": "2024-01-20T11:00:00",
    "conteudoObs": "Observações da visita",
    "conteudoVisita": "Detalhes da visita realizada"
  }
]
```

### 3. Buscar Visita por ID (GET /api/visitas/{id})

Response (200 OK):

```json
{
  "id": 1,
  "grupo": "Grupo A",
  "cliente": "Cliente A",
  "funcionarios": "ENG. A, ENG. B",
  "acompanhantes": "SÍNDICO(A)",
  "subatividades": "Inspeção de Pilares, Inspeção de Vigas",
  "dataHoraInicio": "2024-01-20T09:00:00",
  "dataHoraFim": "2024-01-20T11:00:00",
  "conteudoObs": "Observações da visita",
  "conteudoVisita": "Detalhes da visita realizada"
}
```

### 4. Atualizar Visita (PUT /api/visitas/{id})

Request (PUT /api/visitas/1):

```json
{
  "grupo": "Grupo A",
  "cliente": "Cliente A",
  "funcionarios": "ENG. A, ENG. C",
  "acompanhantes": "SÍNDICO(A), ZELADOR",
  "subatividades": "Inspeção de Pilares, Inspeção de Vigas",
  "dataHoraInicio": "2024-01-20T09:00:00",
  "dataHoraFim": "2024-01-20T12:00:00",
  "conteudoObs": "Observações atualizadas da visita",
  "conteudoVisita": "Detalhes atualizados da visita realizada"
}
```

Response (200 OK):

```json
{
  "id": 1,
  "grupo": "Grupo A",
  "cliente": "Cliente A",
  "funcionarios": "ENG. A, ENG. C",
  "acompanhantes": "SÍNDICO(A), ZELADOR",
  "subatividades": "Inspeção de Pilares, Inspeção de Vigas",
  "dataHoraInicio": "2024-01-20T09:00:00",
  "dataHoraFim": "2024-01-20T12:00:00",
  "conteudoObs": "Observações atualizadas da visita",
  "conteudoVisita": "Detalhes atualizados da visita realizada"
}
```

### 5. Deletar Visita (DELETE /api/visitas/{id})

Response (204 No Content)
