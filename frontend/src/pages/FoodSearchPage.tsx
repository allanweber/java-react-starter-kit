import { useGetFoodByName } from '@/services/foodService'
import { useState } from 'react'
import { FoodInstantResponse } from '../api/models/FoodInstantResponse'
import { FoodSearch } from '../components/FoodSearch'

export function FoodSearchPage () {
  const [selectedFoodName, setSelectedFoodName] = useState<string>('')
  const { data } = useGetFoodByName({
    name: selectedFoodName
  })

  const handleFoodSelect = (food: FoodInstantResponse) => {
    setSelectedFoodName(food.food_name!)
    if (data) {
      console.log('Food:', data)
    }
  }

  return (
    <div className='container mx-auto px-4 py-8'>
      <h1 className='text-3xl font-bold text-center mb-8'>Search Foods</h1>
      <FoodSearch onFoodSelect={handleFoodSelect} />
    </div>
  )
}
