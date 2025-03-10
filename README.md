# Library Management System

## Project Overview

Develop a Library Management System using Java Spring Boot that manages user information, book borrowing details, and addresses. 
The system should allow users to register, borrow books, and return books within a specified timeframe.

## Entities & Relationships

### User
* A user has a unique ID, name, phone number, and email.
* Each user is associated with a single address.
* A user can borrow multiple books.

### Address
* An address includes details like house number, area, city, state, country, and pincode.
* Each user has exactly one address (One-to-One relationship).

### Book
* A book has a unique ID, title, and author.
* A book can be borrowed by a user and has borrowed time and return time.
* A book can only be borrowed by one user at a time (Many-to-One relationship).

## Functional Requirements

1. User Management
* Register a new user with name, phone number, email, and address.
* Retrieve user details.
* Update or delete user information.

2. Book Management
* Add new books to the system.
* Retrieve book details.
* Update or remove books.

3. Borrow & Return Books
* Allow users to borrow books, recording the borrowedTime.
* Set a returnTime when a book is borrowed.
* Mark a book as returned when a user returns it.

## Non-Functional Requirements
* Use Spring Boot for backend development.
* Implement Spring Data JPA for database interactions.
* Use REST APIs for communication.
* Ensure proper validation and error handling.
* Use H2/PostgreSQL/MySQL as the database.
* Implement logging and exception handling.
* Secure endpoints using Spring Security/JWT Authentication.