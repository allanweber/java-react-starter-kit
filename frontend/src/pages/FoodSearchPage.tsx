import { useNavigate } from '@tanstack/react-router'
import { FoodInstantResponse } from '../api/models/FoodInstantResponse'
import { FoodSearch } from '../components/FoodSearch'

export function FoodSearchPage () {
  const navigate = useNavigate()

  const handleFoodSelect = (food: FoodInstantResponse) => {
    navigate({ to: '/food/$food', params: { food: food.food_name! } })
  }

  return (
    <div className='container mx-auto px-4 py-8'>
      <h1 className='text-3xl font-bold text-center mb-8'>Search Foods</h1>
      <FoodSearch onFoodSelect={handleFoodSelect} />
    </div>
  )
}
