import { Food } from '@/api'
import { useState } from 'react'
import { Slider } from './ui/slider'

interface FoodNutritionFactsProps {
  foodData: Food
}

const SATURATED_FAT_ID = 606
const TRANS_FAT_ID = 605
const POLYUNSATURATED_FAT_ID = 646
const MONOUNSATURATED_FAT_ID = 645
const CHOLESTEROL_ID = 601
const VITAMIN_D_ID = 324
const CALCIUM_ID = 301
const IRON_ID = 303
const POTASSIUM_ID = 306
const CAFFEINE_ID = 262

export function FoodNutritionFacts ({ foodData }: FoodNutritionFactsProps) {
  const [servingSize, setServingSize] = useState(foodData.servingSizeG)

  const calculateValue = (per100g: number) => {
    return (per100g * servingSize) / 100
  }

  const calculateDailyValue = (per100g: number, dailyValue: number) => {
    return Math.round((calculateValue(per100g) / dailyValue) * 100)
  }

  const saturatedFat = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === SATURATED_FAT_ID
  )

  const transFat = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === TRANS_FAT_ID
  )

  const polyunsaturatedFat = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === POLYUNSATURATED_FAT_ID
  )

  const monounsaturatedFat = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === MONOUNSATURATED_FAT_ID
  )

  const cholesterol = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === CHOLESTEROL_ID
  )

  const vitaminD = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === VITAMIN_D_ID
  )

  const calcium = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === CALCIUM_ID
  )

  const iron = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === IRON_ID
  )

  const potassium = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === POTASSIUM_ID
  )

  const caffeine = foodData.fullNutrients?.find(
    nutrient => nutrient.attr_id === CAFFEINE_ID
  )

  return (
    <div className='max-w-md mx-auto border border-gray-400 rounded-lg bg-white p-6 shadow nutrition-facts'>
      <h2 className='text-2xl font-bold border-b border-black pb-2 mb-2'>
        Nutrition Facts
      </h2>
      <div className='font-bold'>Serving Size:</div>
      <div className='flex items-center mb-2'>
        <input
          type='number'
          value={Math.round(servingSize)}
          min={1}
          className='w-16 border rounded text-center mr-2'
          onChange={e => setServingSize(Number(e.target.value))}
        />
        <span>grams of {foodData.name}</span>
      </div>
      <Slider
        value={[Math.round(servingSize)]}
        onValueChange={(value: number[]) => setServingSize(value[0])}
        min={1}
        max={500}
        step={1}
        className='w-full mb-4 [&_[data-slot=slider-track]]:bg-gray-200'
      />
      <div className='border-t-4 border-black my-2'></div>
      <div className='flex justify-between items-end mb-2'>
        <span className='text-2xl font-bold'>Calories</span>
        <span className='text-3xl font-bold'>
          {Math.round(calculateValue(foodData.caloriesPer100g))}
        </span>
      </div>
      <div className='text-right text-xs font-semibold mb-2'>
        % Daily Value*
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Total Fat {calculateValue(foodData.fatsPer100g).toFixed(1)}g
        </span>
        <span>{calculateDailyValue(foodData.fatsPer100g, 65)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>
          Saturated Fat {calculateValue(saturatedFat?.value ?? 0).toFixed(2)}
          mg
        </span>
        <span>{calculateDailyValue(saturatedFat?.value ?? 0, 20)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>
          Trans Fat {calculateValue(transFat?.value ?? 0).toFixed(2)}mg
        </span>
        <span>{calculateDailyValue(transFat?.value ?? 0, 20)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>
          Polyunsaturated Fat{' '}
          {calculateValue(polyunsaturatedFat?.value ?? 0).toFixed(2)}mg
        </span>
        <span>{calculateDailyValue(polyunsaturatedFat?.value ?? 0, 22)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm mb-1'>
        <span>
          Monounsaturated Fat{' '}
          {calculateValue(monounsaturatedFat?.value ?? 0).toFixed(2)}mg
        </span>
        <span>{calculateDailyValue(monounsaturatedFat?.value ?? 0, 300)}%</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Cholesterol {calculateValue(cholesterol?.value ?? 0).toFixed(2)}mg
        </span>
        <span>{calculateDailyValue(cholesterol?.value ?? 0, 300)}%</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Sodium {Math.round(calculateValue(foodData.sodiumPer100g))}mg
        </span>
        <span>{calculateDailyValue(foodData.sodiumPer100g, 2300)}%</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Total Carbohydrates {calculateValue(foodData.carbsPer100g).toFixed(1)}
          g
        </span>
        <span>{calculateDailyValue(foodData.carbsPer100g, 275)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>
          Dietary Fiber {calculateValue(foodData.fiberPer100g).toFixed(1)}g
        </span>
        <span>{calculateDailyValue(foodData.fiberPer100g, 28)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm mb-1'>
        <span>Sugars {calculateValue(foodData.sugarPer100g).toFixed(1)}g</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Protein {calculateValue(foodData.proteinPer100g).toFixed(1)}g
        </span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>
          Vitamin D {calculateValue(vitaminD?.value ?? 0).toFixed(2)}mcg
        </span>
        <span>{calculateDailyValue(vitaminD?.value ?? 0, 20)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Calcium {calculateValue(calcium?.value ?? 0).toFixed(2)}mg</span>
        <span>{calculateDailyValue(calcium?.value ?? 0, 1000)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm'>
        <span>Iron {calculateValue(iron?.value ?? 0).toFixed(2)}mg</span>
        <span>{calculateDailyValue(iron?.value ?? 0, 18)}%</span>
      </div>
      <div className='flex justify-between pl-4 text-sm mb-1'>
        <span>
          Potassium {calculateValue(potassium?.value ?? 0).toFixed(2)}mg
        </span>
        <span>{calculateDailyValue(potassium?.value ?? 0, 2600)}%</span>
      </div>
      <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
        <span>
          Caffeine {calculateValue(caffeine?.value ?? 0).toFixed(2)}mg
        </span>
        <span>{calculateDailyValue(caffeine?.value ?? 0, 400)}%</span>
      </div>
      <div className='text-xs mt-2 border-t border-gray-300 pt-2'>
        * The % Daily Value (DV) tells you how much a nutrient in a serving of
        food contributes to a daily diet. 2000 calories a day is used for
        general nutrition advice.
      </div>
    </div>
  )
}
