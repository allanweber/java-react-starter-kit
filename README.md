# Full Stack Starter Kit

A modern full-stack application template with TypeScript, Spring Boot, and PostgreSQL.

## Tech Stack

### Frontend
- TypeScript
- Vite
- Tailwind CSS
- ESLint
- Prettier

### Backend
- Java 17+
- Spring Boot
- Maven
- PostgreSQL 16

## Prerequisites

- Node.js 18+
- Java 17+
- Docker and Docker Compose
- Maven

## Getting Started

1. Clone the repository:
```bash
git clone <repository-url>
cd starter-kit
```

2. Start the database:
```bash
docker-compose up -d db
```

3. Start the backend:
```bash
cd backend
./mvnw spring-boot:run
```

4. Start the frontend:
```bash
cd frontend
npm install
npm run dev
```

The application will be available at:
- Frontend: http://localhost:5173
- Backend API: http://localhost:8080
- Database: localhost:5432

## Development Workflow

### Code Generation

The project includes automated scripts for generating TypeScript clients from the backend API:

```bash
# Generate TypeScript clients
./scripts/generate-clients.sh
```

### Git Hooks

The project uses git hooks to ensure code quality. They are automatically installed when you run:

```bash
npm install
```

Hooks include:
- Pre-commit: Format code and run linting
- Pre-push: Run tests

### Available Scripts

#### Root Directory
- `npm run generate-clients`: Generate TypeScript clients from backend API
- `npm run format`: Format all code using Prettier
- `npm run lint`: Run ESLint on all code

#### Frontend
- `npm run dev`: Start development server
- `npm run build`: Build for production
- `npm run preview`: Preview production build
- `npm run test`: Run tests
- `npm run lint`: Run ESLint
- `npm run format`: Format code

#### Backend
- `./mvnw spring-boot:run`: Start development server
- `./mvnw test`: Run tests
- `./mvnw clean install`: Build the project

## Project Structure

```
.
├── frontend/           # TypeScript + Vite frontend
├── backend/           # Spring Boot backend
├── docker-compose.yml # Docker services configuration
└── scripts/          # Development and automation scripts
```

## Contributing

1. Create a new branch for your feature
2. Make your changes
3. Run tests and ensure code quality
4. Submit a pull request

### Code Style

- Frontend: ESLint + Prettier
- Backend: Google Java Style Guide

## License

MIT 