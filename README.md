
# ToDo CLI Engine

[![Java Version](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Framework](https://img.shields.io/badge/Spring_Shell-4.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-shell)
[![Build Tool](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Testing](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)

A lightweight, enterprise-patterned CLI application designed around a strictly decoupled, layered architecture. This project serves as a foundational blueprint for Spring Boot service management, dependency injection lifecycle hooks, and safe in-memory data mutation.

---

## Architectural Layout

The codebase deliberately avoids monolithic layout patterns, opting instead for a highly maintainable, three-tier enterprise service architecture.


```

com.orion.oblivious.ToDo
├── commands      <- Presentation Layer (Spring Shell Controllers)
├── models        <- Domain Layer (Encapsulated Entities & Enums)
├── services      <- Core Business Logic Layer (State Management)
└── ToDoApplication.java

```

### Design Implementations & Key Patterns

* **Strict Separation of Concerns:** The Controller layer (`TaskCommands`) remains completely blind to business logic rules. It strictly manages input capturing, option parsing, and terminal layout processing.
* **Coding to Interfaces:** Public API contracts leverage abstract collections (`java.util.List`) rather than concrete variants, decoupling internal data mutations from the consuming endpoints.
* **Defensive Enum Parsing:** Incorporated a low-level static factory parsing method inside the `Status` domain enum utilizing case-insensitive string evaluation loops to accept irregular CLI arguments gracefully.
* **Bytecode Parameter Retention Bypass:** Implemented explicit `@Option` mapping hooks across the controller boundaries to prevent runtime reflection failures caused by standard JVM bytecode variable name erasure during production compilation loops.
* **Optimized Buffer Allocation:** Multi-row terminal grid drawing logic avoids performance degradation caused by string immutability mutations within iterative loops, using sequential `StringBuilder` memory allocations and formatted padding tokens (`String.format()`).

---

## Feature Matrix

* Complete CRUD engine operating entirely on an in-memory runtime array context.
* Thread-safe, non-blocking atomic transaction counter (`AtomicInteger`) isolated from array index shifting traps.
* Global edge-case interception handling with granular error routing to insulate standard users from technical stack-traces.
* Structured layout tabular output featuring custom vertical column-width padding rules.

---

## Core Command References

### Add Task
Registers a new task into memory. Business validation automatically sets the base status constraint initialization parameters to `NOT_STARTED`.
```bash
add-task --name "Task Title" --description "Detailed task execution boundaries"

```

### View All Tasks

Renders the complete list array inside a structured vertical table layout.

```bash
get-all-tasks

```

### Inspect Individual Item

Fetches raw data vectors mapped to a specific internal ID and outputs an isolated property summary.

```bash
get-task --id 1

```

### Update Status

Transforms the data state. Automatically validates input text strings before shifting the state property across boundaries.

```bash
update-status --id 1 --status in-progress

```

### Erase Task

Safely purges an element from the array list by evaluating item identities directly rather than trusting changing position indices.

```bash
delete-task --id 1

```

---

## Test Automation Suite

The business logic tier undergoes isolated programmatic quality assurance loops written with JUnit 5. Execution operates entirely outside of the heavy Spring Framework ApplicationContext loop, enabling critical sub-millisecond test run optimizations.

```bash
# Execute the full automated verification harness via the wrapper tool
./mvnw test

```
