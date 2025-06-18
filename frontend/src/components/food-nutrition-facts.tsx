import { Food } from '@/api'

interface FoodNutritionFactsProps {
  foodData: Food
}

export function FoodNutritionFacts ({ foodData }: FoodNutritionFactsProps) {
  return (
    <div className='max-w-md mx-auto border border-gray-400 rounded-lg bg-white p-6 shadow nutrition-facts'>
      <h2 className='text-2xl font-bold border-b border-black pb-2 mb-2'>
        Nutrition Facts
      </h2>
      <div className='font-bold'>Serving Size:</div>
      <div className='flex items-center mb-2'>
        <input
          type='number'
          value={Math.round(foodData.servingSizeG)}
          min={1}
          className='w-12 border rounded text-center mr-2'
          readOnly
        />
        <span>grams of {foodData.name}</span>
      </div>
      <div className='border-t-4 border-black my-2'></div>
      <div className='flex justify-between items-end mb-2'>
        <span className='text-2xl font-bold'>Calories</span>
        <span className='text-3xl font-bold'>
          {Math.round((foodData.caloriesPer100g * foodData.servingSizeG) / 100)}
        </span>
      </div>
      <div className='text-right text-xs font-semibold mb-2'>
        % Daily Value*
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Total Fat{' '}
          {((foodData.fatsPer100g * foodData.servingSizeG) / 100).toFixed(1)}g
        </span>
        <span>
          {Math.round(
            ((foodData.fatsPer100g * foodData.servingSizeG) / 100 / 65) * 100
          )}
          %
        </span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Saturated Fat (mocked) 4.9g</span>
        <span>25%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Trans Fat (mocked) 0g</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Polyunsaturated Fat (mocked) 1.5g</span>
      </div>
      <div className='flex justify-between pl-4 text-sm mb-1'>
        <span>Monounsaturated Fat (mocked) 5.6g</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>Cholesterol (mocked) 104mg</span>
        <span>35%</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Sodium{' '}
          {Math.round((foodData.sodiumPer100g * foodData.servingSizeG) / 100)}mg
        </span>
        <span>
          {Math.round(
            ((foodData.sodiumPer100g * foodData.servingSizeG) / 100 / 2300) *
              100
          )}
          %
        </span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Total Carbohydrates{' '}
          {((foodData.carbsPer100g * foodData.servingSizeG) / 100).toFixed(1)}g
        </span>
        <span>
          {Math.round(
            ((foodData.carbsPer100g * foodData.servingSizeG) / 100 / 275) * 100
          )}
          %
        </span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>
          Dietary Fiber{' '}
          {((foodData.fiberPer100g * foodData.servingSizeG) / 100).toFixed(1)}g
        </span>
        <span>
          {Math.round(
            ((foodData.fiberPer100g * foodData.servingSizeG) / 100 / 28) * 100
          )}
          %
        </span>
      </div>
      <div className='flex justify-between pl-4 text-sm mb-1'>
        <span>
          Sugars{' '}
          {((foodData.sugarPer100g * foodData.servingSizeG) / 100).toFixed(1)}g
        </span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Protein{' '}
          {((foodData.proteinPer100g * foodData.servingSizeG) / 100).toFixed(1)}
          g
        </span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Vitamin D (mocked) 0.3mcg</span>
        <span>2%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Calcium (mocked) 16mg</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Iron (mocked) 1.8mg</span>
        <span>10%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm mb-1'>
        <span>Potassium (mocked) 328.9mg</span>
        <span>7%</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>Caffeine (mocked) 0mg</span>
      </div>
      <div className='text-xs mt-2 border-t border-gray-300 pt-2'>
        * The % Daily Value (DV) tells you how much a nutrient in a serving of
        food contributes to a daily diet. 2000 calories a day is used for
        general nutrition advice.
      </div>
    </div>
  )
}
