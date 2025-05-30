import { Food } from '../api/models/Food';

interface FoodCardProps {
  food: Food;
  onClick?: (food: Food) => void;
}

export function FoodCard({ food, onClick }: FoodCardProps) {
  return (
    <div
      className="p-4 border border-gray-200 rounded-lg hover:bg-gray-50 hover:shadow-md hover:border-gray-300 cursor-pointer transition-all duration-200 ease-in-out"
      onClick={() => onClick?.(food)}
    >
      <h3 className="font-medium">{food.name}</h3>
      {food.description && (
        <p className="text-sm text-gray-600">{food.description}</p>
      )}
      <div className="mt-2 text-sm text-gray-500">
        <div className="flex flex-wrap items-center gap-x-3 gap-y-1">
          <span>Calories: {food.caloriesPer100g} kcal/100g</span>
          <span className="hidden sm:inline">•</span>
          <span>Protein: {food.proteinPer100g}g/100g</span>
          <span className="hidden sm:inline">•</span>
          <span>Carbs: {food.carbsPer100g}g/100g</span>
          <span className="hidden sm:inline">•</span>
          <span>Fats: {food.fatsPer100g}g/100g</span>
        </div>
      </div>
    </div>
  );
} 