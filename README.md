# 🔖 PDV Firebase Open Source (Android Nativo)

![Android CI](https://github.com/joaodematejr/pdv-app-pos/actions/workflows/android-build.yml/badge.svg)
![License](https://img.shields.io/github/license/joaodematejr/pdv-app-pos)
![Language](https://img.shields.io/github/languages/top/joaodematejr/pdv-app-pos)

Um sistema de Ponto de Venda (PDV) open source moderno, desenvolvido em **Android nativo com Kotlin**, totalmente integrado ao **Firebase**. Cada comerciante pode usar seu próprio projeto Firebase para controlar seus dados, sem depender de servidores de terceiros.

---

## ✨ Funcionalidades

- Registro de vendas (produtos, quantidades, valores)
- Cadastro de produtos e categorias
- Histórico de vendas
- Controle simples de estoque
- Login e autenticação com e-mail/senha (Firebase Authentication)
- Armazenamento de dados na nuvem com Firestore
- Funciona offline (Firestore com cache local)
- Privacidade e controle total: cada cliente usa seu próprio projeto Firebase

---

## 🚀 Tecnologias

- **Linguagem**: Kotlin
- **Framework**: Android SDK (Jetpack, MVVM)
- **Banco de dados**: Firebase Firestore
- **Autenticação**: Firebase Authentication
- **Persistência offline**: Firestore (com cache local ativado)
- **Gerenciamento de dependências**: Gradle

---

## 🛠️ Como começar

### 1. Clone o projeto

```bash
git clone https://github.com/joaodematejr/pdv-app-pos.git
cd pdv-firebase-open-source

### 2. Configure o Firebase

- Crie um projeto no [Firebase Console](https://console.firebase.google.com/)
- Baixe o arquivo `google-services.json` e coloque dentro de `app/`
- Verifique as regras de Firestore e Authentication no console
