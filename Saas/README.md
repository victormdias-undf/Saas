# 🎓 Sistema de Gerenciamento Estudantil



# ⚙️ Como configurar o ambiente

# 1. Pré-requisitos
- Java JDK instalado
- MySQL instalado e rodando

# 2. Criando o banco de dados
```bash
mysql -u root -p < script_criacao_banco.sql
```

### 3. Configurando a senha do banco

**Windows (PowerShell):**
```powershell
setx DB_SENHA "estud@nte"

⚠️ Após rodar o comando, **feche e reabra o VS Code** para ele reconhecer a variável.

## 🗂️ Estrutura do projeto
📦 gerenciamento-estudantil
┣ 📂 src
┃ ┣ 📂 model        → Pessoa, Estudante, Professor, Curso, Matricula
┃ ┣ 📂 dao          → EstudanteDAO, ProfessorDAO, CursoDAO, MatriculaDAO
┃ ┣ 📂 view         → JanelaPrincipal e telas do sistema
┣ 📂 lib            → MySQL Connector/J (.jar)
┣ 📜 script_criacao_banco.sql
┗ 📜 README.md