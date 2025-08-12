# 📚 Sistema de Gerenciamento de Biblioteca


Projeto de um sistema de gerenciamento de biblioteca via console, desenvolvido em Java para aplicar e solidificar conceitos de Programação Orientada a Objetos.

---

### 🌟 Sobre o Projeto

Este projeto foi criado como parte do meu percurso de estudos em Java. O objetivo principal foi colocar em prática os pilares da POO (Encapsulamento, Herança, Polimorfismo) e outros conceitos fundamentais como tratamento de exceções, manipulação de arquivos para persistência de dados e criação de uma interface de usuário interativa no console.

O sistema simula as operações básicas de uma biblioteca, permitindo o cadastro de livros e membros, a realização de empréstimos e devoluções, e a busca por títulos no acervo.

---

### ✨ Funcionalidades

O sistema permite ao usuário realizar as seguintes operações:

* **Gerenciamento de Livros:**
    * Adicionar um novo livro ao acervo.
    * Listar todos os livros cadastrados e seu status (disponível/emprestado).
    * Buscar por um livro específico pelo título (busca case-insensitive).
* **Gerenciamento de Membros:**
    * Cadastrar um novo membro com um ID único (com validação para não permitir IDs duplicados).
    * Listar todos os membros cadastrados.
* **Operações da Biblioteca:**
    * Emprestar um livro para um membro (com validação de disponibilidade e existência de livro/membro).
    * Devolver um livro.
* **Persistência de Dados:**
    * Os dados de livros e membros são salvos em arquivos `.csv` ao sair do programa.
    * Os dados são carregados automaticamente ao iniciar o programa, mantendo o estado do sistema entre sessões.

---

### 🛠️ Tecnologias Utilizadas

* **Java:** Linguagem principal do projeto.
* **Programação Orientada a Objetos (POO):** Base de toda a arquitetura do sistema.

---

### 👨‍💻 Autor

* **Luciano Santos**
* **GitHub:** [lucianosantos-dev](https://github.com/lucianosantos-dev)
