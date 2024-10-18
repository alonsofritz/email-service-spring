# Simplified Email Microservice

This project is an API built using **Java, Java Spring, AWS Simple Email Service (SeS) and Clean Architecture principles**. The API allows you to send e-mails using AWS SeS.

## Index

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Arquitetura da Aplicação](#application-architecture)
- [Classes Principais](#main-classes)

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

## Application Architecture
The application follows a structure that resembles Clean Architecture, with some adaptations:  
1. **Application Layer:**  
   - **Responsibility:** Starts the application and manages the controllers.
   - **Example:** `EmailServiceApplication`, `EmailSenderController`.
2. **Domain Layer:**  
   - **Responsibility:** Contains business rules and entities.
   - **Example:** `EmailSenderUseCase`, `EmailServiceException`.
3. **Infrastructure Layer:**  
   - **Responsibility:** Manages communication with external services and configurations.
   - **Example:** `SesEmailSender`, `SesConfig`.
4. **Adapters Layer:**  
   - **Responsibility:** Defines contracts and interfaces for communication between layers.
   - **Example:** `EmailSenderGateway`.

## Main Classes
1. **EmailServiceApplication**  
   - Description: Main class that starts the Spring Boot application.
2. **SesEmailSender**  
   - **Description:** Implements the `EmailSenderGateway` interface to send emails using Amazon SES.
   - **Attributes:**
     - **emailSender:** `String` (configured via `@Value` from `application.properties`)
     - **amazonSimpleEmailService:** `AmazonSimpleEmailService` (injected via `@Autowired`)
   - **Methods:**
     - `sendEmail(String to, String subject, String body)`: Sends an email using Amazon SES.
3. **SesConfig**  
   - **Description:** Configures the `AmazonSimpleEmailService` client to be used in the application.
   - **Methods:**
     - `amazonSimpleEmailService()`: Creates and configures a bean of type `AmazonSimpleEmailService`.
4. **EmailSenderGateway**  
   - Description: Interface that defines the contract for sending emails.
   - **Methods:**
     - `sendEmail(String to, String subject, String body)`: Abstract method for sending email.
5. **EmailServiceException**  
   - **Description:** Custom exception for errors in the email service.

**RELATIONSHIPS**

- `EmailServiceApplication` has no direct dependencies on other classes.
- `SesEmailSender` implements the `EmailSenderGateway` interface.
- `SesEmailSender` uses `AmazonSimpleEmailService` configured by the `SesConfig` class.
- `SesEmailSender` throws `EmailServiceException` in case of email sending failure.

 ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
 ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
 [![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

