
# FAQ - Perguntas Frequentes

## 1. Como configurar o Firebase no app?

- Crie um projeto no [Firebase Console](https://console.firebase.google.com/).
- Adicione um app Android e baixe o arquivo `google-services.json`.
- Coloque o arquivo `google-services.json` dentro da pasta `app/` do projeto.
- Configure as credenciais de Firebase Authentication e Firestore conforme documentação oficial.

## 2. Quais versões do Android são suportadas?

- O app suporta Android 7.1 (API 25) até Android 13 (API 33).

## 3. Como gerar o APK manualmente?

- No terminal, dentro do projeto, rode:

  ```bash
  ./gradlew assembleDebug
  ```

- O APK estará disponível em `app/build/outputs/apk/debug/app-debug.apk`.

## 4. Como rodar os testes?

- Rode no terminal:

  ```bash
  ./gradlew test
  ```

## 5. Como contribuir com código ou reportar bugs?

- Faça um fork do repositório.
- Crie uma branch para sua feature ou correção (`git checkout -b minha-feature`).
- Faça commits claros e envie um pull request.
- Para bugs, abra uma issue descrevendo o problema e, se possível, como reproduzir.

## 6. Quais permissões o app necessita?

- Internet
- Acesso à rede (para sincronizar dados com Firebase)
- Permissões padrão de armazenamento (para cache local do Firestore)

## 7. Como ativar/desativar o cache offline do Firestore?

- O cache offline está ativado por padrão para melhorar a experiência sem conexão.
- Para desativar, altere a configuração no código onde o Firestore é inicializado (veja o README ou código fonte).

## 8. Posso usar meu próprio projeto Firebase?

- Sim! Cada comerciante pode configurar e usar seu próprio projeto Firebase para ter controle total dos seus dados.

---

Se sua dúvida não estiver aqui, abra uma issue ou entre em contato!