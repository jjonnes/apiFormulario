[
  {
    "table": "grupos_clientes",
    "mockStartIndex": "auto",
    "mockCount": 3,
    "mockValueReference": "http://mockjs.com/examples.html#DPD",
    "mock": {
      "nome": {
        "type": "string",
        "value": "@word(5, 10) Grupo"
      }
    }
  },
  {
    "table": "clientes",
    "mockStartIndex": "auto",
    "mockCount": 10,
    "mockValueReference": "http://mockjs.com/examples.html#DPD",
    "mock": {
      "nome": {
        "type": "string",
        "value": "@name()"
      },
      "grupoId": {
        "type": "number",
        "value": "@integer(1, 3)"
      }
    }
  },
  {
    "table": "atividades",
    "mockStartIndex": "auto",
    "mockCount": 5,
    "mockValueReference": "http://mockjs.com/examples.html#DPD",
    "mock": {
      "descricao": {
        "type": "string",
        "value": "@sentence(3, 5)"
      }
    }
  },
  {
    "table": "sub_atividades",
    "mockStartIndex": "auto",
    "mockCount": 10,
    "mockValueReference": "http://mockjs.com/examples.html#DPD",
    "mock": {
      "descricao": {
        "type": "string",
        "value": "@sentence(3, 5)"
      },
      "atividadeId": {
        "type": "number",
        "value": "@integer(1, 5)"
      }
    }
  },
  {
    "table": "funcionarios",
    "mockStartIndex": "auto",
    "mockCount": 10,
    "mockValueReference": "http://mockjs.com/examples.html#DPD",
    "mock": {
      "nome": {
        "type": "string",
        "value": "@name()"
      }
    }
  },
  {
    "table": "acompanhantes",
    "mockStartIndex": "auto",
    "mockCount": 10,
    "mockValueReference": "http://mockjs.com/examples.html#DPD",
    "mock": {
      "nome": {
        "type": "string",
        "value": "@name()"
      }
    }
  }
]