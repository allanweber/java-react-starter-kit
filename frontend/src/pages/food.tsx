import { useGetFoodByName } from '@/services/foodService'
import { useParams } from '@tanstack/react-router'
import { FoodNutritionFacts } from '../components/food-nutrition-facts'

export default function FoodPage () {
  const { food } = useParams({ from: '/food/$food' })
  const { data: foodData, isLoading, error } = useGetFoodByName({ name: food })

  if (isLoading) {
    return (
      <div className='container mx-auto px-4 py-8'>
        <div className='flex justify-center items-center'>
          <div className='text-xl'>Loading food data...</div>
        </div>
      </div>
    )
  }

  if (error) {
    return (
      <div className='container mx-auto px-4 py-8'>
        <div className='flex justify-center items-center'>
          <div className='text-xl text-red-600'>
            Error loading food data: {error.message}
          </div>
        </div>
      </div>
    )
  }

  if (!foodData) {
    return (
      <div className='container mx-auto px-4 py-8'>
        <div className='flex justify-center items-center'>
          <div className='text-xl'>No food data found for "{food}"</div>
        </div>
      </div>
    )
  }

  return (
    <div className='container mx-auto px-4 py-8'>
      <div className='flex flex-col items-center mb-8'>
        <img
          src={
            foodData.image_url ||
            'https://img.icons8.com/ios-filled/100/steak.png'
          }
          alt={foodData.name}
          className='w-24 h-24 mb-2'
        />
        <h1 className='text-4xl font-serif font-semibold'>{foodData.name}</h1>
      </div>
      <FoodNutritionFacts foodData={foodData} />
    </div>
  )
}
