# Full Stack Starter Kit

A modern full-stack application template with TypeScript, Spring Boot, and PostgreSQL.

## Implementation Checklist

### Backend

#### Authentication & Authorization
- [ ] Implement Cookie-based authentication
  - Add `spring-boot-starter-security` dependency
  - Configure `HttpOnly` and `Secure` cookies
  - Implement session management
  - Add CSRF protection
  - Configure cookie settings:
    - SameSite=Strict
    - Secure flag
    - HttpOnly flag
    - Domain restrictions
  - Implement remember-me functionality
  - Add session timeout configuration
  - Implement role-based access control (RBAC)
  - Add password encryption with BCrypt
  - Configure session fixation protection
  - OAuth2.0 google login
  - OAuth2.0 facebook login
  - OAuth2.0 github login
  - OAuth2.0 twitter login
  - OAuth2.0 linkedin login
  - OAuth2.0 instagram login

#### Monitoring & Metrics
- [ ] Add Spring Boot Actuator
  - Add `spring-boot-starter-actuator` dependency
  - Configure health checks
  - Add custom health indicators
- [ ] Implement Prometheus metrics
  - Add `micrometer-registry-prometheus` dependency
  - Configure custom metrics
  - Set up Prometheus scraping
- [ ] Add distributed tracing
  - Add `spring-cloud-starter-sleuth` and `spring-cloud-starter-zipkin` dependencies
  - Configure trace sampling
  - Add custom trace spans
- [ ] Implement OpenTelemetry
  - Add OpenTelemetry dependencies:
    - `opentelemetry-api`
    - `opentelemetry-sdk`
    - `opentelemetry-exporter-otlp`
    - `opentelemetry-instrumentation-annotations`
  - Configure OpenTelemetry SDK
  - Set up OTLP exporter
  - Add custom instrumentation
  - Configure sampling and sampling rules
  - Add baggage propagation
  - Configure context propagation
- [ ] Add Query Monitoring
  - Add `p6spy` for SQL logging
  - Configure query performance metrics
  - Add slow query detection
  - Implement query execution time tracking
  - Add query plan analysis
  - Configure query result caching
  - Add N+1 query detection
  - Implement query statistics collection
  - Add query execution plan visualization
  - Configure query timeout handling

#### Testing
- [ ] Add integration tests
  - Configure test containers
  - Add API integration tests
  - Add security integration tests
- [ ] Add performance tests
  - Configure JMeter or Gatling
  - Add load test scenarios

### Frontend

#### State Management & Data Fetching
- [ ] Implement TanStack Query (React Query)
  - Add `@tanstack/react-query` dependency
  - Configure query client
  - Implement query hooks
  - Add mutation hooks
  - Configure error handling
  - Add optimistic updates
- [ ] Add TanStack Router
  - Add `@tanstack/react-router` dependency
  - Configure route definitions
  - Add route guards
  - Implement nested routes
  - Add route-based code splitting

#### Authentication
- [ ] Implement authentication flow
  - Implement login/logout
  - Add protected routes
  - Add session management
  - Implement remember me functionality
  - Add CSRF token handling
  - Configure session timeout handling
  - Add automatic session refresh
  - Implement concurrent session handling

#### Error Handling
- [ ] Add global error boundary
- [ ] Implement error logging service
- [ ] Add error reporting (e.g., Sentry)
  - Add `@sentry/react` dependency
  - Configure error tracking
  - Add performance monitoring

#### Testing
- [ ] Add unit tests
  - Configure Vitest
  - Add component tests
  - Add hook tests
- [ ] Add E2E tests
  - Configure Playwright
  - Add critical path tests
  - Add visual regression tests

#### Performance
- [ ] Implement code splitting
- [ ] Add lazy loading
- [ ] Configure caching strategies
- [ ] Add performance monitoring
  - Configure Web Vitals tracking
  - Add performance budgets

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

### Required Software
- Node.js 18+
- Java 21+
- Docker and Docker Compose
- Maven

### Global NPM Packages
The following packages need to be installed globally:
```bash
# Install OpenAPI TypeScript Code Generator
npm install -g openapi-typescript-codegen
```

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

## Database Migrations

### Migration Files
- `V1__create_foods_table.sql`: Creates the initial foods table

### How Migrations Work
1. Migrations are automatically applied when the application starts
2. Flyway tracks executed migrations in `flyway_schema_history` table
3. Each migration runs only once

### Handling Rollbacks
Flyway doesn't support automatic rollbacks. Instead, follow these practices:

1. **Create a new migration** to revert changes:
   - Create a new file with a higher version number (e.g., `V2__revert_foods_table.sql`)
   - Include the necessary SQL to revert the changes

2. **Manual rollback** (if needed):
```bash
# Drop the table manually
psql -h localhost -U postgres -d mydatabase -c "DROP TABLE IF EXISTS foods;"
# Clean Flyway's history
psql -h localhost -U postgres -d mydatabase -c "DELETE FROM flyway_schema_history WHERE version = '1';"
```

Note: Always backup your database before applying any changes in production. 