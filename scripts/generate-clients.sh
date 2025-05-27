#!/bin/bash

# Exit on error
set -e

echo "🚀 Generating TypeScript clients from backend API..."

# Check if backend is running
if ! curl -s http://localhost:8080/actuator/health > /dev/null; then
    echo "❌ Backend is not running. Please start the backend first."
    exit 1
fi

# Get the absolute path to the project root
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# Create output directory if it doesn't exist
mkdir -p "$PROJECT_ROOT/frontend/src/api"

# Generate TypeScript client using the same configuration as frontend
echo "🔨 Generating TypeScript client..."
cd "$PROJECT_ROOT/frontend" && npx openapi-typescript-codegen \
    --input http://localhost:8080/api-docs \
    --output "$PROJECT_ROOT/frontend/src/api" \
    --client fetch \
    --useOptions \
    --useUnionTypes \
    --exportSchemas true \
    --exportServices true \
    --indent 2

echo "✅ TypeScript client generated successfully!"
echo "📁 Generated files are in: frontend/src/api" 