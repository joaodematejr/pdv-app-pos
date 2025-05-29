name: Reportar um bug
description: Algo inesperado aconteceu? Relate aqui.
title: "[BUG] "
labels: bug
assignees: ''

body:
- type: textarea
  id: descricao
  attributes:
  label: Descreva o problema
  description: Explique claramente o que está errado, com o máximo de detalhes possível.
  placeholder: Ex. Ao clicar em "Salvar", o app fecha sozinho.
  validations:
  required: true

- type: textarea
  id: passos
  attributes:
  label: Passos para reproduzir
  description: Liste os passos para reproduzir o erro.
  placeholder: |
  1. Acesse a tela de vendas
  2. Clique em "Adicionar produto"
  3. Toque em "Salvar"
  4. O app fecha
  validations:
  required: true

- type: input
  id: dispositivo
  attributes:
  label: Dispositivo e versão Android
  placeholder: Ex. Xiaomi Redmi Note 10, Android 11
  validations:
  required: true

- type: textarea
  id: logs
  attributes:
  label: Logs ou mensagens de erro
  description: Cole aqui a stacktrace ou qualquer mensagem útil.
  placeholder: Ex. java.lang.NullPointerException...
