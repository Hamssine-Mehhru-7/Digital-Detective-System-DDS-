🔍 DIGITAL DETECTIVE

Your Digital Investigation Assistant

📌 Project Overview

Digital Detective is a web-based digital investigation system developed using Java and Spring Boot. It provides a platform for users to manage investigation cases, store digital evidence, analyze submitted content, maintain scan history, and generate investigation reports.

The system aims to make digital investigation activities more organized and accessible through a user-friendly web interface.

✨ Features

1. User Authentication

- User registration and login.
- User-specific investigation records.
- Secure storage of password hashes.

2. Case Management

- Create new investigation cases.
- Add case titles and descriptions.
- View and manage existing cases.
- Track case status, including Pending and Analysed.

3. Evidence Management

- Store digital evidence associated with investigation cases.
- Maintain evidence details for future reference.

4. New Investigation

- Submit content for investigation.
- Analyze submitted content using the available analysis functionality.
- Save investigation results.
- Update the corresponding case status after successful analysis.

5. Scan History

- Maintain records of previous scans.
- View previously saved analysis information and risk-related results.

6. Report Generation

- Generate investigation reports using the available project functionality.

🛠️ Technologies Used

Technology| Purpose
Java| Main programming language
Spring Boot| Backend application framework
Spring Web MVC| Handling web requests
Thymeleaf| Dynamic frontend page rendering
HTML| Web page structure
CSS| Web page styling
JavaScript| Frontend interactions
JDBC| Database connectivity
SQLite| Database management
Maven| Dependency and project management

🗄️ Database Structure

The application uses SQLite as its database and JDBC to connect Java with the database.

The following tables are created during application startup:

Table| Description
"users"| Stores user account information
"cases"| Stores investigation case details
"evidence"| Stores evidence associated with cases
"analysis_results"| Stores investigation analysis results
"user_scans"| Stores users' scan history

The database connection is managed through a dedicated "DatabaseConnection" class.

🏗️ Project Architecture

The application follows a layered architecture:

- Controller Layer: Handles incoming HTTP requests and application navigation.
- DAO Layer: Handles database operations.
- Database Layer: Establishes JDBC connections and initializes SQLite tables.
- Model Layer: Represents application data.
- View Layer: Uses Thymeleaf, HTML, CSS, and JavaScript to display the user interface.

This structure separates the user interface, application logic, and database operations.

⚙️ Installation and Setup

Prerequisites

Make sure the following are installed:

- Java Development Kit (JDK)
- Apache Maven
- Visual Studio Code or IntelliJ IDEA
- Git

Step 1: Clone the Project

git clone <repository-url>

Step 2: Navigate to the Project Directory

cd DIGITAL-DETECTIVE-SYSTEM

Navigate into the directory containing the "pom.xml" file if the project is inside a subfolder.

Step 3: Build the Project

mvn clean install

Step 4: Run the Application

mvn spring-boot:run

Step 5: Open the Application

Open a browser and visit:

http://localhost:8080

The application should now be accessible locally.

🔄 Application Workflow

1. The user opens the Digital Detective website.
2. The user registers or logs in.
3. The user creates an investigation case.
4. The user navigates to the New Investigation section.
5. The user submits content for analysis.
6. The system processes the submitted content.
7. The analysis result is saved in the database.
8. The corresponding case status is updated after successful analysis.
9. The user can view investigation records and scan history.

🎯 Project Objective

The main objective of Digital Detective is to provide a simple and organized platform for digital investigation activities.

By combining case management, evidence storage, content analysis, scan history, and report generation, the system helps users maintain investigation records in one centralized platform.

🌐 Live Application

Live Website:
https://digital-detective-system-dds.onrender.com 

👥 Project Information

- Project Name: Digital Detective
- Project Type: Web-Based Application
- Domain: Digital Investigation
- Backend: Java and Spring Boot
- Frontend: Thymeleaf, HTML, CSS, JavaScript
- Database: SQLite

📄 License

This project was developed for academic purposes.
