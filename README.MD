# Handle Ticket

## Overview

Handle Ticket is a Java-based Spring Boot application that utilizes Google Cloud Vision API to process and analyze
images of tickets. This project allows users to upload ticket images, which are then processed by Google Cloud Vision to
extract structured data representing the ticket information.

### Key Features

- Image Upload: Users can upload images of tickets
- Image Processing: Google Cloud Vision API processes the uploaded images
- Data Extraction: Structured data is extracted from the processed images
- User Interface: A simple web interface for uploading and viewing ticket information

## Technologies Used

- **Java**: Version 21
- **Spring Boot**: Version 3.3.4
- **Lombok**: For reducing boilerplate code
- **Google Cloud Vision API**: Version 3.1.3

## Architecture
The Handle Ticket project follows a hexagonal architecture pattern combined with Domain-Driven Design principles. Key components:

- Domain Layer: Contains core business entities and domain logic
- Application Layer: Defines ports (interfaces) for input/output operations
- Infrastructure Layer: Implements adapters for external systems and technologies

## Setup and Configuration

### Prerequisites

- Google Cloud Account
- Enabled Google Cloud Vision API
- Created Service Account with necessary permissions

### Steps to Run

1. Clone the repository
2. Create Google Vision service account and create a key with JSON format 
3. Set up environment variables:
   ```
    sudo nano ~/.bashrc
    export GOOGLE_APPLICATION_CREDENTIALS="/path/to/your/service-account-key.json"
    source ~/.bashrc
   ```
3. Build and run the application

## Usage

1. Upload a ticket image through the web interface
2. The application will automatically process the image using Google Cloud Vision API
3. Extracted data will be displayed in a structured format

## Contributing

Contributions are welcome! Please feel free to submit pull requests or issues.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Google Cloud Vision API for image processing capabilities
- Spring Boot framework for rapid development
- Lombok for reducing boilerplate code

---

