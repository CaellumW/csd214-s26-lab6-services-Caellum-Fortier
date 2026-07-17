***

## 🛑 CSD214 S26 Lecture 7: The Repository Pattern & Abstraction Layers
This repository serves as the official base for the [**CSD214 Lecture 7 In-Class Exercise and Lab 5 Preparation**](https://docs.google.com/document/d/1naWgzt2X1AGIbOvj9BiYaqkcTuNx5Z33HAvuHc7JQRA/edit?usp=sharing). Building upon the JPA and Hibernate mappings established in Lecture 6, we are now abstracting our data-access logic behind a clean **Repository Layer** to decouple our database queries from our core CLI business logic [6, 7].

### **Repository Implementation Overview**
The `bookstore.repositories` package contains the boundary layers designed to isolate our database plumbing (the `EntityManager` and transactions) from the user interface [6, 7]:

*   **Decoupled Architecture (Outcome 4.2):** We define an `IRepository<T>` interface acting as a generic contract [6]. The CLI controller (`App.java`) is completely isolated from database transactions and Hibernate sessions [6].
*   **The "Upsert" Pattern:** `ProductRepository.java` handles both creation (`persist`) and updating (`merge`) dynamically inside its `save()` method, hiding persistence complexities [7].
*   **Optimized Aggregate Operations:** Includes an optimized `count()` query to verify stock size [7] and a bulk `deleteAll()` operation to drop database records in a single transactional query, avoiding the performance penalties of the N+1 Query Problem.
*   **Polymorphic Persistence:** Safely persists and retrieves books, magazines, tickets, tires, and batteries polymorphically inside a Single-Table inheritance structure.

***
***

# Bookstore CLI Application
- for the csd214 course summer 26 delivery: [course outline](https://welearn.saultcollege.ca/shared/CourseOutlines/csd214_Course_Outline.pdf)
> - [git repository](https://github.com/fcarella/lab1-exercise-fred-carella-csd214-s26)
> - [based on bookstore (git repository): ](https://github.com/fcarella/bookstore-2026-01-30)
> - [this exercise is covered in this lecture...](https://docs.google.com/document/d/1naWgzt2X1AGIbOvj9BiYaqkcTuNx5Z33HAvuHc7JQRA/edit?usp=sharing)
- A console-based Java application for managing a bookstore inventory, performing sales, and tracking cash flow. This project demonstrates object-oriented programming concepts including inheritance, polymorphism, and interface implementation in Java 25.

## Features

*   **Inventory Management (Polymorphic DTO Layer):**
    *   **Books:** Manage items with Title, Author, Price, and Copies.
    *   **Magazines:** Manage periodicals with Order Quantity and Issue Date.
    *   **Disc Magazines:** Specialized magazines that include a disc.
    *   **Tickets:** Simple saleable items with a description and price.
    *   **Vehicle Parts (Lab 1 Exercise):** Expanded inventory to include **Tires** (Diameter) and **Batteries** (Cold Cranking Amps) via a new Automotive hierarchy.
*   **Data Access Abstraction (Repository Pattern):**
    *   Clean separation of presentation logic (DTOs) from database schemas (Entities) [6].
    *   A generic repository contract (`IRepository`) to standardise CRUD lifecycle operations [6].
    *   Encapsulated Hibernate transactions and "Upsert" logic within the data layer [6].
*   **CRUD Operations:** Create, Read, Update, and Delete items.
*   **Sales System:** Sell items to decrement inventory count and increase the Cash Till total.
*   **Data Generation:** Uses `JavaFaker` to populate the database with realistic dummy data for both Bookstore and Auto-Shop departments.
*   **Menu System:** Interactive console menu for navigation.

## Class Hierarchy

![Class Diagram](documentation/bookstore-2026-01-30-142617.png)

The hierarchy implements the following structure:
*   **SaleableItem (Interface):** Defines `sellItem()` and `getPrice()`.
*   **Editable (Abstract):** Handles console input/output and parsing.
*   **Publication:** Base class for Books and Magazines (Title, Price, Copies).
*   **VehiclePart (Lab 1 Exercise):** Base abstract class for the automotive department (Manufacturer, Price).
    *   `Tire` and `Battery` concrete classes implement specific automotive attributes.

## Prerequisites

*   **Java JDK:** Version 25
*   **Maven:** 3.6+
*   **Docker Desktop:** To host containerized MySQL

## Dependencies

*   [JavaFaker](https://github.com/DiUS/java-faker) (1.0.2): For generating random test data.
*   [JUnit 5](https://junit.org/junit5/) (5.10.0): For unit testing.
*   [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (8.2.0): To establish connection driver channels.
*   [Hibernate Core](https://hibernate.org/) (6.6.1.Final): JPA Implementation engine.

## How to Run

1.  **Start your database container:**
    ```bash
    docker-compose up -d
    ```

2.  **Compile the project:**
    ```bash
    mvn clean compile
    ```

3.  **Run the application:**
    ```bash
    mvn exec:java -Dexec.mainClass="bookstore.Main"
    ```

## Usage

Upon starting, the application will automatically populate the database if it detects an empty schema. You will see the following menu:

```text
***********************
 1. Add Items (Repository Save)
 2. Edit Items (Repository Save/Update)
 3. Delete Items (Repository Delete)
 4. Sell item(s) (Logic & Repo Sync)
 5. List items (Polymorphic Filtering)
99. Quit
***********************


The Power of the Interface: When you swapped the InMemoryListRepository for the InMemoryMapRepository (or the database repositories), did you have to modify a single line of user-interface code inside App.java? Discuss how polymorphism and interfaces enable multi-developer productivity.

No, I did not have to modify a sinlge line of user-interface code. With polymorphism, every usable object gets funneled in a way that the recieving code does not care what happens behind the scenes. This decoupling ensures ease of updates, and that only the code that has to rely directly on something, does.

Algorithmic Complexity (O(n) vs O(1)): Discuss your implementation experience of the HashMap vs the ArrayList [6]. Explain why constant-time lookups (O(1)) are crucial for enterprise systems even if both strategies feel identical to the terminal user on a small local dataset [6].

The experience with the hashmap and arraylists were extrememly similar, barring construction and methods. The constant time lookups are essential for growing programs and softwares to ensure maximum efficiency from the very beginning. The difference is hardly noticable to the user and the programmer, but has a lasting impact on future code.

Volatility vs Persistence: Contrast your RAM-based repositories with your SQL-based repositories. Under what scenarios during the development lifecycle is an in-memory repository superior to a database (and vice-versa)?

An in-memory repository is vastly advantageous for testing. Using RAM to test ensures the code runs as quickly as possible, and doesn't have any unnecessary information, allowing a blank slate every time it runs. However, later in the development cycle, especially when pushing to consumers, it is crucial to be able to save the information from past usages

The Manual IoC Experience: Reflect on moving your instantiation logic (new keywords) out of the classes and consolidating them inside Main.java [6]. Does this design make testing simpler? Explain how this manual step mirrors what Spring Boot does automatically.

Moving instantiation logic vastly simplifies testing, narrowing down code interaction scope to just one file. Similarly, spring boot uses code reflection to remove the need to write this kind of code manually, but writing it manually gives insight on how that software works, which is always helpful.
