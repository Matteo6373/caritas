markdown
# Caritas Management System - Backend API

### 📌 Descrizione del Progetto
Questo repository contiene il cuore pulsante del sistema gestionale Caritas, una soluzione progettata per digitalizzare la gestione delle risorse e dei volontari. Si tratta di un'applicazione **Spring Boot 4** robusta, focalizzata sulla sicurezza dei dati, la gestione granulare degli accessi e l'automazione dell'infrastruttura database.

### 🚀 Funzionalità Chiave (Core Backend)
*   **Sicurezza Stateless:** Autenticazione e autorizzazione tramite **JWT (JSON Web Token)**.
*   **Gestione Ruoli (RBAC):** Protezione degli endpoint basata su ruoli (es. ADMIN, VOLONTARIO) tramite Spring Security.
*   **Database Versioning:** Gestione automatizzata dello schema del database con **Flyway**.
*   **Persistenza Dati:** Utilizzo di Spring Data JPA per un mapping efficiente delle entità e relazioni complesse.
*   **Docker Ready:** Configurazione pronta per l'ambiente di produzione tramite containerizzazione.

### 🛠 Tech Stack
*   **Java 21+**
*   **Spring Boot 4.0** (Spring Security, Data JPA, Web)
*   **Flyway** (Database Migration)
*   **PostgreSQL/MySQL** (Supportati tramite profili Spring)
*   **Docker & Docker Compose**
*   **Git** (Version Control)

### 🏗️ Gestione Database con Flyway
Il progetto utilizza un approccio rigoroso per la gestione del database. Ogni modifica strutturale è tracciata tramite script SQL versionati per garantire che ogni ambiente (Sviluppo, Test, Produzione) sia sempre allineato.
*   Le migrazioni si trovano in: `src/main/resources/db/migration`
*   All'avvio, Flyway verifica e applica automaticamente gli script mancanti.

### 🔐 Sicurezza e JWT
Il sistema implementa un filtro di sicurezza personalizzato che:
1. Intercetta le richieste HTTP.
2. Valida la firma del token JWT presente nell'header.
3. Carica il contesto di sicurezza con i ruoli specifici dell'utente, permettendo o negando l'accesso alle risorse in base alle annotazioni `@PreAuthorize`.

### 🐳 Setup e Installazione (Docker)
Per avviare il backend e il database in un unico comando:

   ```bash
   # 1. Clonare il repository
    git clone https://github.com/Matteo6373/caritas
    cd caritas
    
    # 2. Avviare backend e database con Docker
    docker-compose up -d --build
