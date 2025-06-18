import { useParams } from '@tanstack/react-router'

export default function FoodPage () {
  const { food } = useParams({ from: '/food/$food' })

  return (
    <div className='container mx-auto px-4 py-8'>
      <div className='flex flex-col items-center mb-8'>
        <img
          src='https://img.icons8.com/ios-filled/100/steak.png'
          alt={food}
          className='w-24 h-24 mb-2'
        />
        <h1 className='text-4xl font-serif font-semibold'>{food}</h1>
      </div>
      <div className='max-w-md mx-auto border border-gray-400 rounded-lg bg-white p-6 shadow nutrition-facts'>
        <h2 className='text-2xl font-bold border-b border-black pb-2 mb-2'>
          Nutrition Facts
        </h2>
        <div className='font-bold'>Serving Size:</div>
        <div className='flex items-center mb-2'>
          <input
            type='number'
            value={4}
            min={1}
            className='w-12 border rounded text-center mr-2'
            readOnly
          />
          <span>oz, cooked (113g) {food}</span>
        </div>
        <div className='border-t-4 border-black my-2'></div>
        <div className='flex justify-between items-end mb-2'>
          <span className='text-2xl font-bold'>Calories</span>
          <span className='text-3xl font-bold'>256</span>
        </div>
        <div className='text-right text-xs font-semibold mb-2'>
          % Daily Value*
        </div>
        <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
          <span>Total Fat 14g</span>
          <span>18%</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Saturated Fat 4.9g</span>
          <span>25%</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Trans Fat 0g</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Polyunsaturated Fat 1.5g</span>
        </div>
        <div className='flex justify-between pl-4 text-sm mb-1'>
          <span>Monounsaturated Fat 5.6g</span>
        </div>
        <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
          <span>Cholesterol 104mg</span>
          <span>35%</span>
        </div>
        <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
          <span>Sodium 80mg</span>
          <span>3%</span>
        </div>
        <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
          <span>Total Carbohydrates 0g</span>
          <span>0%</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Dietary Fiber 0g</span>
        </div>
        <div className='flex justify-between pl-4 text-sm mb-1'>
          <span>Sugars 0g</span>
        </div>
        <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
          <span>Protein 31g</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Vitamin D 0.3mcg</span>
          <span>2%</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Calcium 16mg</span>
        </div>
        <div className='flex justify-between pl-4 text-sm'>
          <span>Iron 1.8mg</span>
          <span>10%</span>
        </div>
        <div className='flex justify-between pl-4 text-sm mb-1'>
          <span>Potassium 328.9mg</span>
          <span>7%</span>
        </div>
        <div className='flex justify-between font-semibold border-t border-gray-300 pt-1'>
          <span>Caffeine 0mg</span>
        </div>
        <div className='text-xs mt-2 border-t border-gray-300 pt-2'>
          * The % Daily Value (DV) tells you how much a nutrient in a serving of
          food contributes to a daily diet. 2000 calories a day is used for
          general nutrition advice.
        </div>
      </div>
    </div>
  )
}
