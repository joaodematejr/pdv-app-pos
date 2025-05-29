# 🧱 Arquitetura do Projeto

Este app de Ponto de Venda (PDV) Android usa uma arquitetura limpa, escalável e de fácil manutenção.

## 🔧 Camadas principais

- **Presentation (UI)**: Composables ou Activities/Fragments que exibem os dados e interagem com o ViewModel.
- **ViewModel**: Responsável pela lógica da interface e comunicação com os casos de uso ou repositórios.
- **Repository**: Camada que abstrai o acesso aos dados (Firebase Firestore/Realtime Database).
- **Model**: Classes de dados (DTOs e Models) que representam os dados do domínio.
- **Firebase Service**: Encapsula chamadas ao Firebase (auth, firestore, etc).

## 📐 Padrões utilizados

- MVVM (Model - View - ViewModel)
- Repository Pattern
- Dependency Injection (manual por enquanto ou com Hilt futuramente)
- Single Source of Truth com LiveData ou StateFlow

## 🔁 Ciclo de dados

1. UI → ViewModel → Repository → Firebase
2. Firebase → Repository → ViewModel → UI
