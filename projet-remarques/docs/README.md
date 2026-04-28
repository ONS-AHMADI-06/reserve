# 📚 Projet Remarques Étudiants
## Guide complet — Spring Boot + Angular 17 + PostgreSQL 15

---

## 📁 Structure du projet

```
projet-remarques/
│
├── backend/                          ← Projet Spring Boot
│   ├── pom.xml                       ← Dépendances Maven
│   └── src/main/
│       ├── java/com/etudiant/remarques/
│       │   ├── RemarquesApplication.java    ← Point d'entrée
│       │   ├── entity/
│       │   │   └── Remarque.java            ← Entité JPA
│       │   ├── repository/
│       │   │   └── RemarqueRepository.java  ← Accès BDD
│       │   ├── service/
│       │   │   └── RemarqueService.java     ← Logique métier
│       │   └── controller/
│       │       └── RemarqueController.java  ← API REST
│       └── resources/
│           └── application.properties       ← Config BDD
│
├── frontend/                         ← Projet Angular 17
│   ├── package.json
│   ├── angular.json
│   ├── tsconfig.json
│   └── src/
│       ├── main.ts                          ← Bootstrap Angular
│       ├── index.html
│       ├── styles.css
│       └── app/
│           ├── app.component.ts             ← Composant racine
│           ├── app.config.ts                ← Config HttpClient
│           ├── app.routes.ts                ← Routes
│           └── remarque-etudiant/
│               ├── remarque.model.ts        ← Interface TypeScript
│               ├── remarque.service.ts      ← Appels HTTP
│               ├── remarque-etudiant.component.ts
│               ├── remarque-etudiant.component.html
│               └── remarque-etudiant.component.css
│
└── docs/
    ├── script.sql                    ← Script PostgreSQL
    └── README.md                     ← Ce fichier
```

---

## ⚙️ ÉTAPE 1 — Configurer PostgreSQL

### 1.1 Ouvrir pgAdmin ou le terminal PostgreSQL

```sql
-- Créer la base de données
CREATE DATABASE remarques_db;

-- Se connecter à la base
\c remarques_db

-- Créer la table
CREATE TABLE IF NOT EXISTS remarque (
    id BIGSERIAL PRIMARY KEY,
    contenu TEXT NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    etudiant_id BIGINT NOT NULL
);

-- Insérer des données de test
INSERT INTO remarque (contenu, date, etudiant_id) VALUES
    ('Bon travail sur le projet !', NOW(), 1),
    ('Améliorer la présentation', NOW(), 1),
    ('Excellent rapport écrit', NOW(), 2);

-- Vérifier
SELECT * FROM remarque;
```

### 1.2 Vérifier la configuration

Dans `backend/src/main/resources/application.properties` :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/remarques_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

> ⚠️ Si votre mot de passe PostgreSQL est différent, modifiez `password=postgres`

---

## ⚙️ ÉTAPE 2 — Lancer le Backend Spring Boot

### Prérequis
- Java 17+ installé → vérifier : `java -version`
- Maven installé → vérifier : `mvn -version`

### Commandes

```bash
# Aller dans le dossier backend
cd backend

# Compiler et lancer
mvn spring-boot:run
```

### ✅ Succès si vous voyez :
```
Started RemarquesApplication in X.XXX seconds
Tomcat started on port(s): 8080
```

### URLs disponibles :
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | http://localhost:8080/remarques | Toutes les remarques |
| GET | http://localhost:8080/remarques/1 | Remarques étudiant n°1 |
| POST | http://localhost:8080/remarques | Ajouter une remarque |

---

## ⚙️ ÉTAPE 3 — Lancer le Frontend Angular 17

### Prérequis
- Node.js 18+ → vérifier : `node -v`
- npm → vérifier : `npm -v`

### Commandes

```bash
# Aller dans le dossier frontend
cd frontend

# Installer les dépendances (première fois seulement)
npm install

# Lancer l'application
ng serve
# ou
npx ng serve
```

### ✅ Succès si vous voyez :
```
✔ Compiled successfully.
Local:   http://localhost:4200/
```

Ouvrez votre navigateur : **http://localhost:4200**

---

## 🧪 BONUS — Tests avec Postman

### Test 1 : GET toutes les remarques
```
GET http://localhost:8080/remarques
```

### Test 2 : GET remarques d'un étudiant
```
GET http://localhost:8080/remarques/1
```

### Test 3 : POST ajouter une remarque
```
POST http://localhost:8080/remarques
Content-Type: application/json

Body (raw JSON) :
{
    "contenu": "Très bon avancement du projet",
    "etudiantId": 1
}
```

> La date est automatiquement définie par le backend.

---

## 🔄 Architecture de communication

```
Angular (port 4200)
        │
        │  HTTP Request
        ▼
Spring Boot (port 8080)
        │
        │  JPA / SQL
        ▼
PostgreSQL (port 5432)
    remarques_db
```

---

## ❌ Problèmes fréquents

| Problème | Solution |
|----------|----------|
| `Connection refused 8080` | Démarrez le backend Spring Boot |
| `CORS error` | Le @CrossOrigin est déjà dans le controller |
| `password authentication failed` | Modifiez password dans application.properties |
| `ng: command not found` | Utilisez `npx ng serve` |
| `Port 4200 already in use` | Utilisez `ng serve --port 4201` |

---

## 📝 User Story

**En tant qu'étudiant**, je peux déposer des comptes rendus périodiques afin de rendre compte de l'avancement de mon projet.

### Critères d'acceptation :
- [ ] L'étudiant peut consulter la liste de ses remarques en entrant son ID
- [ ] L'étudiant peut ajouter une nouvelle remarque via un formulaire
- [ ] Les remarques s'affichent avec la date et le contenu
- [ ] Le formulaire se réinitialise après soumission
- [ ] Un message de confirmation s'affiche après l'ajout

---

## 🚀 Commandes résumées

```bash
# Terminal 1 — Backend
cd backend
mvn spring-boot:run

# Terminal 2 — Frontend
cd frontend
npm install   # (première fois seulement)
ng serve
```

Puis ouvrez : http://localhost:4200
