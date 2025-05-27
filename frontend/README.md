# Frontend Project

This is the frontend project for the starter kit, built with Vite, React, TypeScript, and shadcn/ui.

## Prerequisites

- Node.js (v18 or later)
- npm

## Running the Project

1. Navigate to the frontend directory:
   ```
   cd frontend
   ```

2. Install dependencies:
   ```
   npm install
   ```

3. Run the development server:
   ```
   npm run dev
   ```

4. Open your browser and visit:
   ```
   http://localhost:5173
   ```

## Project Structure

- `src/`: Source code for the React application.
- `src/lib/utils.ts`: Utility functions for shadcn/ui.
- `tailwind.config.js`: Tailwind CSS configuration.
- `postcss.config.js`: PostCSS configuration.

## Using shadcn/ui

To add a new component, run:
```
npx shadcn-ui add <component-name>
```

For example, to add a button:
```
npx shadcn-ui add button
```

## Generating TypeScript Clients

To generate TypeScript clients from the OpenAPI spec, run:
```
npm run generate-api
```

This will fetch the OpenAPI spec from your backend (running at http://localhost:8080) and generate strongly typed clients in the `src/api` folder.
