# Simplified Email Microservice

This project is an API built using **Java, Java Spring, AWS Simple Email Service (SeS) and Clean Architecture principles**. The API allows you to send e-mails using AWS SeS.

## Index

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Arquitetura da Aplicação](#arquitetura-da-aplicação)
- [Classes Principais](#classes-principais)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/alonsofritz/simplified-authenticator-spring.git
```

2. Install dependencies with Maven


3. Update `application.properties` putting your AWS Credentials

```yaml
aws.access.key=your-access-key
aws.secret.key=your-secret-key
aws.region=us-east-1

email.sender=your-email
```
_**Obs:** The sender email must be verified in AWS SeS._
## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

_**Obs:** You can change the port in `application.properties` file, changing the `server.port` property._

## API Endpoints
The API provides the following endpoints:

**GET EMAIL**
```markdown
POST /api/email/send - Send a e-mail from your sender to the destination
```

**BODY**
```json
{
  "to": "johndoe@email.com",
  "subject": "test",
  "body": "test"
}
```

**RESPONSE**
```json
{
  "message": "E-mail sent successfully"
}
```

## Arquitetura da Aplicação
A aplicação em questão segue uma estrutura que se assemelha à Arquitetura Limpa, com algumas adaptações:  
1. **Camada de Aplicação:**  
   - **Responsabilidade:** Inicia a aplicação e gerencia os controladores.
   - **Exemplo:** EmailServiceApplication, EmailSenderController.
2. **Camada de Domínio:**  
   - **Responsabilidade:** Contém as regras de negócio e entidades.
   - **Exemplo:** EmailSenderUseCase, EmailServiceException.
3. **Camada de Infraestrutura:**  
   - **Responsabilidade:** Gerencia a comunicação com serviços externos e configurações.
   - **Exemplo:** SesEmailSender, SesConfig.
4. **Camada de Adapters:**  
   - **Responsabilidade:** Define contratos e interfaces para comunicação entre camadas.
   - **Exemplo:** EmailSenderGateway.

## Classes Principais
1. **EmailServiceApplication**  
   - Descrição: Classe principal que inicia a aplicação Spring Boot.
2. **SesEmailSender**  
   - **Descrição:** Implementa a interface EmailSenderGateway para enviar emails usando o Amazon SES.
   - **Atributos:**
     - **emailSender:** String (configurado via @Value a partir do application.properties)
     - **amazonSimpleEmailService:** AmazonSimpleEmailService (injeção via @Autowired)
   - **Métodos:**
     - **sendEmail(String to, String subject, String body)**: Envia um email utilizando o serviço Amazon SES.
3. **SesConfig**  
   - **Descrição:** Configura o cliente AmazonSimpleEmailService para ser usado na aplicação.
   - **Métodos:**
     - **amazonSimpleEmailService():** Cria e configura um bean do tipo AmazonSimpleEmailService.
4. **EmailSenderGateway**  
   - Descrição: Interface que define o contrato para envio de emails.
   - **Métodos:**
     - **sendEmail(String to, String subject, String body):** Método abstrato para envio de email.
5. **EmailServiceException**
   - **Descrição:** Exceção personalizada para erros no serviço de email.

**RELACIONAMENTOS**

**EmailServiceApplication** não possui dependências diretas com outras classes.

**SesEmailSender** implementa a interface **EmailSenderGateway**.

**SesEmailSender** utiliza **AmazonSimpleEmailService** configurado pela classe SesConfig.

**SesEmailSender** lança **EmailServiceException** em caso de falha no envio de email.

 ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
 ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
 [![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

