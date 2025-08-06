# Star Protect Vehicle Insurance Management System

A comprehensive web application for managing vehicle insurance with separate portals for administrators and underwriters.

## 📋 Project Overview

This system consists of:
- **Frontend**: Angular 17 with ng-bootstrap for UI components
- **Backend**: Spring Boot 3.2.0 with MVC architecture
- **Database**: Apache Derby (in-memory for development)

## 🏗️ Architecture

### Backend Structure (Spring Boot MVC)
```
backend/
├── src/main/java/com/starprotect/vehicleinsurance/
│   ├── bean/           # Entity classes
│   ├── controller/     # REST controllers
│   ├── dao/           # Data Access Objects
│   ├── service/       # Business logic
│   └── util/          # Utility classes
└── src/main/resources/
    ├── application.properties
    ├── schema.sql     # Database schema
    └── data.sql       # Sample data
```

### Frontend Structure (Angular)
```
frontend/
├── src/app/
│   ├── components/    # UI components
│   ├── models/        # TypeScript interfaces
│   ├── services/      # HTTP services
│   └── app.module.ts  # Main module
├── angular.json       # Angular configuration
└── package.json       # Dependencies
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- Maven 3.6+
- Angular CLI 17

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
   The backend will start on `http://localhost:8080`

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the Angular development server:
   ```bash
   ng serve
   ```
   The frontend will start on `http://localhost:4200`

## 🔑 Default Credentials

### Administrator Login
- **User ID**: admin
- **Password**: admin123

### Sample UnderWriter Login
- **UnderWriter ID**: 1001
- **Password**: john123

## 📊 Database Schema

### UnderWriter Table
```sql
CREATE TABLE UnderWriter (
    underwriterId INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    dob DATE NOT NULL,
    joiningDate DATE NOT NULL,
    password VARCHAR(30) NOT NULL
);
```

### Vehicle Table
```sql
CREATE TABLE Vehicle (
    policyId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
    vehicleNo VARCHAR(10) NOT NULL,
    vehicleType VARCHAR(20) NOT NULL,
    customerName VARCHAR(50) NOT NULL,
    engineNo INTEGER NOT NULL,
    chasisNo INTEGER NOT NULL,
    phoneNo BIGINT NOT NULL,
    insuranceType VARCHAR(20) NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    premiumAmount DECIMAL(10,2) NOT NULL,
    underwriterId INTEGER NOT NULL,
    vehicleValue DECIMAL(10,2),
    claimStatus VARCHAR(10) DEFAULT 'No',
    FOREIGN KEY (underwriterId) REFERENCES UnderWriter(underwriterId) ON DELETE CASCADE
);
```

## 🌟 Features

### Administrator Features
- ✅ Admin login with default credentials
- ✅ UnderWriter registration with auto-generated ID
- ✅ Search UnderWriter by ID
- ✅ Update UnderWriter password
- ✅ Delete UnderWriter
- ✅ View all UnderWriters
- ✅ View vehicles by UnderWriter ID

### UnderWriter Features
- 🔲 UnderWriter login with credentials
- 🔲 Create new vehicle insurance policies
- 🔲 Renew existing policies
- 🔲 Update policy types (Full Insurance ↔ Third Party)
- 🔲 View policy details

### Premium Calculation
- **New Vehicles**: 3.5% of vehicle value
- **Renewal (Old Vehicles)**: 5% of vehicle value minus 5% discount

## 🎨 UI Components

The application uses ng-bootstrap for consistent UI styling:
- Responsive design with Bootstrap 5
- Bootstrap Icons for visual elements
- Form validation and error handling
- Loading states and success/error messages

## 🔄 API Endpoints

### Admin Endpoints
- `POST /api/admin/login` - Admin authentication

### UnderWriter Endpoints
- `POST /api/underwriter/register` - Register new UnderWriter
- `GET /api/underwriter/search/{id}` - Search UnderWriter by ID
- `GET /api/underwriter/all` - Get all UnderWriters
- `PUT /api/underwriter/update-password` - Update password
- `DELETE /api/underwriter/delete/{id}` - Delete UnderWriter
- `POST /api/underwriter/login` - UnderWriter authentication
- `GET /api/underwriter/vehicles/{id}` - Get vehicles by UnderWriter ID

### Vehicle Endpoints
- `POST /api/vehicle/create` - Create vehicle insurance
- `GET /api/vehicle/policy/{id}` - Get vehicle by policy ID
- `GET /api/vehicle/vehicleNo/{vehicleNo}` - Get vehicle by vehicle number
- `PUT /api/vehicle/update-insurance-type` - Update insurance type
- `POST /api/vehicle/renew` - Calculate renewal premium
- `POST /api/vehicle/confirm-renewal` - Confirm policy renewal
- `POST /api/vehicle/calculate-premium` - Calculate premium

## 🔧 Configuration

### Backend Configuration (application.properties)
```properties
server.port=8080
server.servlet.context-path=/api

# Derby Database Configuration
spring.datasource.url=jdbc:derby:memory:starprotectdb;create=true
spring.datasource.driver-class-name=org.apache.derby.jdbc.EmbeddedDriver

# CORS Configuration
spring.web.cors.allowed-origins=http://localhost:4200
```

### Frontend Configuration
- Angular 17 with standalone components disabled
- ng-bootstrap for UI components
- Reactive forms for form handling
- HttpClient for API communication

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing
```bash
cd frontend
ng test
```

## 📦 Building for Production

### Backend
```bash
cd backend
mvn clean package
java -jar target/vehicle-insurance-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd frontend
ng build --prod
```

## 🐛 Troubleshooting

### Common Issues

1. **CORS Issues**: Ensure the backend is configured to allow requests from `http://localhost:4200`
2. **Database Connection**: Derby runs in-memory, so data is lost on restart
3. **Port Conflicts**: Ensure ports 8080 (backend) and 4200 (frontend) are available

### Error Handling
- All API responses include success/error status
- Frontend displays appropriate error messages
- Form validation prevents invalid data submission

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is developed for educational purposes as part of the Star Protect Vehicle Insurance System requirements.

## 👥 Author Information

**Star Protect Vehicle Insurance System**
- Developed by: Software Development Team
- Version: 1.0.0
- Contact: support@starprotect.com
- Phone: +1-800-PROTECT

---

For any issues or questions, please contact the development team.