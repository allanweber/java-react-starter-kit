# Entity, Repository, and Controller Creation Guide

## General Patterns

### Entity Creation
1. Always use Lombok `@Data` for clean code
2. Use UUID as primary key with `@GeneratedValue(strategy = GenerationType.UUID)`
3. Include audit fields:
   ```java
   @CreationTimestamp
   @Column(name = "created_at", nullable = false, updatable = false)
   private LocalDateTime createdAt;

   @UpdateTimestamp
   @Column(name = "updated_at", nullable = false)
   private LocalDateTime updatedAt;
   ```
4. Use appropriate column constraints:
   - `@Column(nullable = false)` for required fields
   - `@Column(columnDefinition = "TEXT")` for long text
   - `@Column(precision = 10, scale = 2)` for decimal numbers
5. Use meaningful field names with proper casing (camelCase)
6. Add JPA annotations for relationships:
   - `@OneToMany`, `@ManyToOne`, `@ManyToMany` as needed
   - Always specify `mappedBy` for bidirectional relationships
   - Use `@JoinColumn` for foreign keys

### DTO Creation
1. Create request DTOs in the `dto` package only for request bodies for post and put
2. Use Lombok `@Data` for clean code
3. Add validation annotations:
   ```java
   @NotBlank
   @Size(max = 255)
   private String name;

   @NotNull
   @Positive
   private BigDecimal value;
   ```
4. Keep DTOs focused on specific use cases (create, update, response)
5. Use meaningful names (e.g., `EntityNameRequest`, `EntityNameResponse`)

### Mapper Creation
1. Create mappers in the `dto` package
2. Use `@Component` for Spring dependency injection
3. Implement mapping methods:
   ```java
   public void updateEntityFromRequest(Entity entity, EntityRequest request) {
       entity.setName(request.getName());
       entity.setValue(request.getValue());
   }
   ```
4. Keep mapping logic in mapper classes, not in controllers
5. Use meaningful method names (e.g., `updateEntityFromRequest`, `toResponse`)

### Repository Creation
1. Extend `JpaRepository<Entity, UUID>`
2. Use meaningful method names following Spring Data conventions
3. Add custom queries using `@Query` when needed
4. Include common methods:
   ```java
   Optional<Entity> findById(UUID id);
   List<Entity> findAll();
   Entity save(Entity entity);
   void deleteById(UUID id);
   ```
5. Add pagination support with null constraints:
   ```java
   @NonNull
   Page<Entity> findAll(@NonNull Pageable pageable);
   ```

### Controller Creation
1. Use `@RestController` and `@RequestMapping("/api/v1/[resource]")`
2. Implement standard CRUD endpoints with proper HTTP status codes:
   ```java
   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   List<Entity> findAll();

   @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   Entity findById(@PathVariable UUID id);

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   Entity create(@Valid @RequestBody EntityRequest request);

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   Entity update(@PathVariable UUID id, @Valid @RequestBody EntityRequest request);

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void delete(@PathVariable UUID id);
   ```
3. Use DTOs for request/response handling
4. Return appropriate HTTP status codes:
   - 200 OK for successful GET/PUT
   - 201 Created for successful POST
   - 204 No Content for successful DELETE
   - 404 Not Found for missing resources
   - 400 Bad Request for invalid input
5. Add pagination and sorting support when needed
6. Include basic OpenAPI documentation

## Validation Rules

### Common Validations
1. String fields:
   - `@NotBlank` for required strings
   - `@Size(min = 1, max = 255)` for length constraints
2. Numeric fields:
   - `@NotNull` for required numbers
   - `@Positive` for positive numbers
   - `@DecimalMin` and `@DecimalMax` for ranges
3. Date fields:
   - `@NotNull` for required dates
   - `@Past` or `@Future` as appropriate

## API Response Patterns

### Success Responses
```json
{
    "id": "uuid",
    "name": "value",
    // other entity fields
}
```

### Error Responses
```json
{
    "status": 404,
    "error": "Not Found",
    "message": "Resource not found"
}
```

## Testing Requirements

### Entity Tests
1. Test all validations
2. Test relationships
3. Test audit fields
4. Test business logic methods

### Repository Tests
1. Test CRUD operations
2. Test custom queries
3. Test pagination
4. Test sorting

### Controller Tests
1. Test all endpoints
2. Test validation
3. Test HTTP status codes
4. Test pagination and sorting

## Security Considerations

1. Always validate input
2. Implement proper authorization checks
3. Use proper HTTP methods
4. Implement rate limiting
5. Add proper logging
6. Handle sensitive data appropriately

## Documentation Requirements

1. Add basic OpenAPI annotations
2. Document endpoints
3. Document request/response formats
4. Document HTTP status codes 