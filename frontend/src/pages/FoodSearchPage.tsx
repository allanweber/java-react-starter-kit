import { Food } from '../api/models/Food';
import { FoodSearch } from '../components/FoodSearch';

export function FoodSearchPage() {
  const handleFoodSelect = (food: Food) => {
    console.log('Selected food:', food);
    // Here you can implement what happens when a food is selected
    // For example, navigate to a food details page or add it to a meal plan
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold text-center mb-8">Search Foods</h1>
      <FoodSearch onFoodSelect={handleFoodSelect} />
    </div>
  );
} 