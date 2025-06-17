-- Add JSON columns for fullNutrients and altMeasures
ALTER TABLE foods
    ADD COLUMN IF NOT EXISTS full_nutrients JSONB,
    ADD COLUMN IF NOT EXISTS alt_measures JSONB; 