#!/bin/bash

# Exit on error
set -e

echo "🚀 Generating TypeScript clients from backend API..."

# Check if backend is running
if ! curl -s http://localhost:8080/actuator/health > /dev/null; then
    echo "❌ Backend is not running. Please start the backend first."
    exit 1
fi

# Create output directory if it doesn't exist
mkdir -p frontend/src/api

# Generate TypeScript client using the same configuration as frontend
echo "🔨 Generating TypeScript client..."
cd frontend && npx openapi-typescript-codegen \
    --input http://localhost:8080/api-docs \
    --output src/api \
    --client fetch \
    --useOptions \
    --useUnionTypes \
    --exportSchemas true \
    --exportServices true \
    --indent 2

echo "✅ TypeScript client generated successfully!"
echo "📁 Generated files are in: frontend/src/api" 