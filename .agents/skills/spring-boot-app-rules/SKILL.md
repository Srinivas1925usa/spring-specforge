---
name: spring-boot-app-rules
description: Coding standards, architecture patterns, and best practices for Spring Boot Java projects. Use when writing, reviewing, or refactoring Java/Spring Boot code, controllers, services, repositories, DTOs, configurations, tests, or microservices.
---

# Spring Boot Java Project Conventions

## Project Structure
- Follow standard layered architecture:
  - `controller` / `web` – REST controllers only
  - `service` – business logic
  - `repository` – Spring Data JPA / data access
  - `domain` / `entity` – JPA entities
  - `dto` / `payload` – request/response objects
  - `config` – Spring configuration classes
  - `exception` – custom exceptions + global handler
  - `mapper` – MapStruct mappers (preferred) or manual converters
  - `util` / `common` – shared utilities

- Prefer package-by-feature when the service grows large (e.g. `order/`, `payment/`, `inventory/`).

## Naming Conventions
- Classes: PascalCase
- Methods & variables: camelCase
- Constants: UPPER_SNAKE_CASE
- Packages: all lowercase, no underscores
- REST endpoints: plural nouns (`/api/v1/orders`, `/api/v1/customers/{id}`)
- Boolean methods: start with `is`, `has`, `can`, `should`

## Controllers
- Keep controllers thin – only handle HTTP concerns
- Use `@RestController` + `@RequestMapping` at class level
- Prefer `@GetMapping`, `@PostMapping`, etc. over generic `@RequestMapping`
- Always return `ResponseEntity<T>` for full control over status codes
- Validate input with `@Valid` + Bean Validation annotations
- Never put business logic in controllers

## Services
- Annotate with `@Service`
- Prefer constructor injection (no `@Autowired` on fields)
- Make services interface + implementation when there is a clear benefit (multiple implementations or better testability)
- Keep methods focused and under ~30-40 lines when possible
- Use `@Transactional` only on methods that need it (prefer service layer)

## DTOs & Mapping
- Never expose JPA entities directly in API responses
- Use dedicated Request and Response DTOs
- Prefer MapStruct for mapping (entity ↔ DTO)
- Use Java records for immutable DTOs when possible (Java 17+)

## Exception Handling
- Create a `@ControllerAdvice` / `@RestControllerAdvice` global exception handler
- Use custom business exceptions (e.g. `ResourceNotFoundException`, `BusinessException`)
- Return consistent error response structure (timestamp, status, error, message, path)
- Ensure at least one custom exception class is written

## Configuration
- Use `application.yml` exclusively
- Use `@ConfigurationProperties` for type-safe configuration
- Externalize all environment-specific values
- Use Spring Profiles (`local`, `dev`, `qa`, `prod`)

## Testing
- Unit tests: JUnit 5 + Mockito
- Slice tests: `@WebMvcTest`, `@DataJpaTest`
- Integration tests: `@SpringBootTest`
- Prefer AssertJ for fluent assertions
- Test method naming: `should_DoSomething_When_Condition()`

## Code Style & Quality
- Follow Google Java Style or Spotless/Checkstyle configuration used in the project
- Prefer immutable objects where practical
- Avoid nulls – use `Optional` appropriately (don’t overuse)
- Log with SLF4J (`@Slf4j` from Lombok is acceptable)
- Never log sensitive data (passwords, tokens, PII)
- Use meaningful commit messages and keep methods/classes focused

## Dependencies & Best Practices
- Prefer Spring Boot starters
- Keep Spring Boot and dependency versions managed via parent POM or BOM
- Use Lombok judiciously (`@Getter`, `@Builder`, `@Slf4j`, etc.) – avoid `@Data` on entities
- Prefer constructor injection
- Make classes `final` when they are not designed for extension

## API Design
- Follow RESTful principles
- Use proper HTTP status codes
- Version APIs (`/api/v1/...`)
- Support pagination, sorting, and filtering consistently
- Document with SpringDoc OpenAPI (Swagger)

When generating or reviewing code, always follow the above conventions unless the existing codebase clearly uses a different established pattern.
