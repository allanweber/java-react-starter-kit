# AI Diet Planner Implementation Plan

## Overview
An AI-powered diet planning application that provides personalized meal suggestions and tracking capabilities for both anonymous and authenticated users.

## Core Features

### 1. User Management
- [ ] Anonymous Access
  - Basic meal suggestions
  - Calorie calculator
  - Macro calculator
  - No data persistence

- [ ] Authenticated Users
  - Personal profile
  - Dietary preferences
  - Goals tracking
  - Meal history
  - Favorite meals
  - Shopping lists

### 2. Meal Planning
- [ ] Daily Meal Suggestions
  - Breakfast
  - Lunch
  - Dinner
  - Snacks
  - Custom meal times

- [ ] Nutritional Information
  - Calories
  - Macronutrients (protein, carbs, fats)
  - Micronutrients
  - Allergens
  - Ingredients list

- [ ] Dietary Restrictions
  - Vegetarian
  - Vegan
  - Gluten-free
  - Dairy-free
  - Halal
  - Kosher
  - Custom restrictions

### 3. AI Integration
- [ ] Meal Suggestion Engine
  - User preference learning
  - Dietary restriction compliance
  - Nutritional balance
  - Seasonal ingredient consideration
  - Previous meal history analysis

- [ ] Smart Features
  - Calorie optimization
  - Macro balancing
  - Meal variety
  - Budget consideration
  - Cooking time optimization

### 4. Progress Tracking
- [ ] User Goals
  - Weight goals
  - Fitness goals
  - Dietary goals
  - Progress visualization

- [ ] Analytics
  - Daily/weekly/monthly summaries
  - Goal progress
  - Nutritional insights
  - Trend analysis

## Technical Implementation

### Database Schema

```sql
-- Users
CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255),  -- Optional for OAuth users
    name VARCHAR(100),
    email_verified TIMESTAMP,    -- Track email verification
    image_url VARCHAR(255),      -- Profile picture
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- OAuth Accounts
CREATE TABLE oauth_accounts (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    provider VARCHAR(50),        -- e.g., 'google', 'github', 'twitter'
    provider_account_id VARCHAR(255),
    refresh_token TEXT,
    access_token TEXT,
    expires_at TIMESTAMP,
    token_type VARCHAR(50),
    scope VARCHAR(255),
    id_token TEXT,
    session_state VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    UNIQUE(provider, provider_account_id)
);

-- Sessions
CREATE TABLE sessions (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    session_token VARCHAR(255) UNIQUE,
    expires_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- User Preferences
CREATE TABLE user_preferences (
    user_id UUID PRIMARY KEY REFERENCES users(id),
    dietary_restrictions TEXT[],
    allergies TEXT[],
    preferred_cuisines TEXT[],
    daily_calorie_goal INTEGER,
    meal_count INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Foods (Base nutritional information)
CREATE TABLE foods (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    calories_per_100g DECIMAL,
    protein_per_100g DECIMAL,
    carbs_per_100g DECIMAL,
    fats_per_100g DECIMAL,
    fiber_per_100g DECIMAL,
    sugar_per_100g DECIMAL,
    sodium_per_100g DECIMAL,
    serving_size_g DECIMAL,
    serving_size_unit VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- User Foods (Custom foods created by users)
CREATE TABLE user_foods (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    food_id UUID REFERENCES foods(id),
    name VARCHAR(255),
    description TEXT,
    calories_per_100g DECIMAL,
    protein_per_100g DECIMAL,
    carbs_per_100g DECIMAL,
    fats_per_100g DECIMAL,
    fiber_per_100g DECIMAL,
    sugar_per_100g DECIMAL,
    sodium_per_100g DECIMAL,
    serving_size_g DECIMAL,
    serving_size_unit VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Meals (User's meal templates)
CREATE TABLE meals (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    name VARCHAR(255),
    description TEXT,
    instructions TEXT,
    cooking_time INTEGER,
    difficulty VARCHAR(20),
    image_url VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Meal Foods (Foods in a meal with quantities)
CREATE TABLE meal_foods (
    id UUID PRIMARY KEY,
    meal_id UUID REFERENCES meals(id),
    food_id UUID REFERENCES foods(id),
    user_food_id UUID REFERENCES user_foods(id),
    quantity_g DECIMAL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Meal Categories
CREATE TABLE meal_categories (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    name VARCHAR(100),
    description TEXT,
    created_at TIMESTAMP
);

-- Meal Tags
CREATE TABLE meal_tags (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    name VARCHAR(50),
    created_at TIMESTAMP
);

-- Meal-Tag Relationships
CREATE TABLE meal_tags_relation (
    meal_id UUID REFERENCES meals(id),
    tag_id UUID REFERENCES meal_tags(id),
    PRIMARY KEY (meal_id, tag_id)
);

-- User Meal History
CREATE TABLE user_meal_history (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    meal_id UUID REFERENCES meals(id),
    consumed_at TIMESTAMP,
    portion_size DECIMAL,
    created_at TIMESTAMP
);

-- User Goals
CREATE TABLE user_goals (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    goal_type VARCHAR(50),
    target_value DECIMAL,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20),
    created_at TIMESTAMP
);

-- Shopping Lists
CREATE TABLE shopping_lists (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    name VARCHAR(255),
    created_at TIMESTAMP,
    completed_at TIMESTAMP
);

-- Shopping List Items
CREATE TABLE shopping_list_items (
    id UUID PRIMARY KEY,
    shopping_list_id UUID REFERENCES shopping_lists(id),
    food_id UUID REFERENCES foods(id),
    user_food_id UUID REFERENCES user_foods(id),
    quantity DECIMAL,
    unit VARCHAR(50),
    checked BOOLEAN DEFAULT false,
    created_at TIMESTAMP
);
```

### API Endpoints

#### Authentication
```
POST /api/auth/register
POST /api/auth/login
POST /api/auth/logout
GET /api/auth/me
```

#### User Preferences
```
GET /api/preferences
PUT /api/preferences
GET /api/preferences/dietary-restrictions
PUT /api/preferences/dietary-restrictions
```

#### Meals
```
GET /api/meals
GET /api/meals/{id}
GET /api/meals/suggestions
POST /api/meals/favorites
DELETE /api/meals/favorites/{id}
```

#### Meal Planning
```
GET /api/plan/daily
GET /api/plan/weekly
POST /api/plan/generate
PUT /api/plan/customize
```

#### Progress Tracking
```
GET /api/progress/summary
GET /api/progress/goals
POST /api/progress/goals
PUT /api/progress/goals/{id}
```

#### Shopping Lists
```
GET /api/shopping-lists
POST /api/shopping-lists
PUT /api/shopping-lists/{id}
DELETE /api/shopping-lists/{id}
```

### AI Integration

#### OpenAI Integration
```java
@Service
public class MealSuggestionService {
    private final OpenAIClient openAIClient;
    
    public MealSuggestion generateSuggestion(UserPreferences preferences) {
        // Create prompt based on user preferences
        String prompt = buildPrompt(preferences);
        
        // Call OpenAI API
        CompletionRequest request = CompletionRequest.builder()
            .model("gpt-4")
            .prompt(prompt)
            .maxTokens(500)
            .temperature(0.7)
            .build();
            
        // Process and return suggestion
        return processAIResponse(openAIClient.createCompletion(request));
    }
}
```

### Frontend Components

#### Core Components
- UserProfile
- MealPlanner
- MealCard
- NutritionalInfo
- ProgressTracker
- ShoppingList
- PreferencesForm
- GoalTracker

#### Pages
- Home
- Dashboard
- Meal Planning
- Progress
- Settings
- Shopping List

## Implementation Phases

### Phase 1: Foundation
- [ ] Basic user authentication
- [ ] Database setup
- [ ] Core API endpoints
- [ ] Basic frontend structure

### Phase 2: Core Features
- [ ] Meal database
- [ ] Basic meal suggestions
- [ ] User preferences
- [ ] Simple meal planning

### Phase 3: AI Integration
- [ ] OpenAI integration
- [ ] Smart meal suggestions
- [ ] Preference learning
- [ ] Nutritional optimization

### Phase 4: Advanced Features
- [ ] Progress tracking
- [ ] Shopping lists
- [ ] Analytics
- [ ] Export functionality

### Phase 5: Polish
- [ ] UI/UX improvements
- [ ] Performance optimization
- [ ] Testing
- [ ] Documentation

## Technical Stack

### Backend
- Spring Boot
- PostgreSQL
- OpenAI API
- Spring Security
- Spring Data JPA

### Frontend
- React
- TypeScript
- Tailwind CSS
- TanStack Query
- TanStack Router

## Testing Strategy

### Unit Tests
- Service layer
- Repository layer
- AI integration
- Utility functions

### Integration Tests
- API endpoints
- Database operations
- Authentication flow
- AI suggestions

### E2E Tests
- User flows
- Meal planning
- Progress tracking
- Shopping list management

## Monitoring & Analytics

### Backend
- API performance
- Error tracking
- AI usage metrics
- Database performance

### Frontend
- User engagement
- Feature usage
- Error tracking
- Performance metrics

## Security Considerations

### Authentication
- JWT-based auth
- Password hashing
- Session management
- Rate limiting

### Data Protection
- Input validation
- XSS prevention
- CSRF protection
- Data encryption

## Deployment Strategy

### Development
- Local development
- Docker containers
- Hot reloading
- Development database

### Production
- CI/CD pipeline
- Automated testing
- Blue-green deployment
- Monitoring setup

## Future Enhancements

### Potential Features
- Mobile app
- Social sharing
- Recipe community
- Meal prep guides
- Integration with fitness apps
- Barcode scanning
- Restaurant recommendations
- Meal delivery integration 