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