name: Sugerir uma funcionalidade
description: Tem uma ideia que poderia melhorar o app? Compartilhe aqui!
title: "[FEATURE] "
labels: enhancement
assignees: ''

body:
- type: textarea
  id: descricao
  attributes:
  label: Descrição da funcionalidade
  description: Explique o que você gostaria que fosse adicionado ou melhorado.
  placeholder: Ex. Adicionar filtro por data no histórico de vendas.
  validations:
  required: true

- type: textarea
  id: justificativa
  attributes:
  label: Por que isso é útil?
  description: Ajude a entender a importância dessa funcionalidade.
  placeholder: Ex. Isso facilita visualizar as vendas por período.
  validations:
  required: false

- type: textarea
  id: implementacao
  attributes:
  label: Possível implementação
  description: Alguma ideia de como implementar? (opcional)
  placeholder: Ex. Pode ser um DatePicker que filtra os dados no Firestore.
