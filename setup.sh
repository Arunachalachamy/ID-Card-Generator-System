#!/bin/bash

# Star Protect Vehicle Insurance System Setup Script
echo "=================================================="
echo "Star Protect Vehicle Insurance System Setup"
echo "=================================================="

# Check if Java is installed
if command -v java &> /dev/null; then
    echo "✅ Java is installed: $(java -version 2>&1 | head -n 1)"
else
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check if Maven is installed
if command -v mvn &> /dev/null; then
    echo "✅ Maven is installed: $(mvn -version | head -n 1)"
else
    echo "❌ Maven is not installed. Please install Maven 3.6+."
    exit 1
fi

# Check if Node.js is installed
if command -v node &> /dev/null; then
    echo "✅ Node.js is installed: $(node --version)"
else
    echo "❌ Node.js is not installed. Please install Node.js 18+."
    exit 1
fi

# Check if npm is installed
if command -v npm &> /dev/null; then
    echo "✅ npm is installed: $(npm --version)"
else
    echo "❌ npm is not installed. Please install npm."
    exit 1
fi

echo ""
echo "Setting up backend..."
cd backend

# Install backend dependencies and compile
if mvn clean compile; then
    echo "✅ Backend dependencies installed and compiled successfully"
else
    echo "❌ Backend setup failed"
    exit 1
fi

cd ..

echo ""
echo "Setting up frontend..."
cd frontend

# Install frontend dependencies
if npm install; then
    echo "✅ Frontend dependencies installed successfully"
else
    echo "❌ Frontend setup failed"
    exit 1
fi

cd ..

echo ""
echo "=================================================="
echo "✅ Setup completed successfully!"
echo "=================================================="
echo ""
echo "To start the application:"
echo ""
echo "1. Start the backend:"
echo "   cd backend"
echo "   mvn spring-boot:run"
echo "   (Backend will run on http://localhost:8080)"
echo ""
echo "2. In a new terminal, start the frontend:"
echo "   cd frontend"
echo "   ng serve"
echo "   (Frontend will run on http://localhost:4200)"
echo ""
echo "3. Open your browser and go to http://localhost:4200"
echo ""
echo "Default admin credentials:"
echo "   User ID: admin"
echo "   Password: admin123"
echo ""
echo "Sample UnderWriter credentials:"
echo "   UnderWriter ID: 1001"
echo "   Password: john123"
echo ""
echo "=================================================="