# Spring Azure Storage

Este repositório contém uma aplicação API construída com Spring Boot, que utiliza o Azure Active Directory (Azure AD) para autenticação e autorização. A aplicação inclui a configuração de um CRUD para gerenciar containers de blob no Azure Storage, com segurança garantida por User Delegation SAS (Shared Access Signature).

## Funcionalidades
- **Autenticação e Autorização**: Integração com Azure AD para autenticação e autorização de usuários.
- **Gerenciamento de Containers Blob**: CRUD completo (Create, Read, Update, Delete) para containers de blob no Azure Storage.
- **Segurança**: Utilização de User Delegation SAS para garantir acesso seguro aos recursos de armazenamento.

## Tecnologias Utilizadas
- **Spring Boot**: Framework principal para construção da API.
- **Azure Active Directory**: Serviço de identidade e gerenciamento de acesso.
- **Azure Storage**: Serviço de armazenamento de blobs.
- **User Delegation SAS**: Mecanismo de segurança para acesso delegado aos recursos de armazenamento.
