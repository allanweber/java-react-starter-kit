import React, { useEffect, useState } from 'react'
import { FoodInstantResponse } from '../api/models/FoodInstantResponse'
import { useSearchFoods } from '../services/foodService'
import { Tabs, TabsContent, TabsList, TabsTrigger } from './ui/tabs'

interface FoodSearchProps {
  onFoodSelect?: (food: FoodInstantResponse) => void
}

export function FoodSearch ({ onFoodSelect }: FoodSearchProps) {
  const [searchTerm, setSearchTerm] = useState('')
  const [debouncedSearchTerm, setDebouncedSearchTerm] = useState('')
  const [showLoading, setShowLoading] = useState(false)
  const [tab, setTab] = useState('common')

  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedSearchTerm(searchTerm)
    }, 500)

    return () => clearTimeout(timer)
  }, [searchTerm])

  const { data, isLoading, error } = useSearchFoods({
    query: debouncedSearchTerm
  })

  useEffect(() => {
    let loadingTimer: ReturnType<typeof setTimeout>
    if (isLoading) {
      loadingTimer = setTimeout(() => {
        setShowLoading(true)
      }, 500)
    } else {
      setShowLoading(false)
    }
    return () => clearTimeout(loadingTimer)
  }, [isLoading])

  // Automatically switch to 'branded' tab if no common foods but branded foods exist
  useEffect(() => {
    if (data) {
      const hasCommon = data.common && data.common.length > 0
      const hasBranded = data.branded && data.branded.length > 0
      if (!hasCommon && hasBranded && tab !== 'branded') {
        setTab('branded')
      } else if (hasCommon && tab !== 'common') {
        setTab('common')
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [data])

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value)
  }

  const renderFoodList = (foods: FoodInstantResponse[] | undefined) => {
    if (!foods || foods.length === 0)
      return <p className='text-gray-500 text-center'>No foods found</p>
    return (
      <div className='space-y-2'>
        {foods.map(food => (
          <div
            key={food.tag_id || food.nix_item_id}
            className='p-4 border border-gray-200 rounded-lg hover:bg-gray-50 hover:shadow-md hover:border-gray-300 cursor-pointer transition-all duration-200 ease-in-out'
            onClick={() => onFoodSelect?.(food)}
          >
            <div className='flex items-center gap-4'>
              {food.photo?.thumb && (
                <img
                  src={food.photo.thumb}
                  alt={food.food_name}
                  className='w-12 h-12 object-contain rounded bg-white'
                />
              )}
              <div className='flex-grow'>
                <h3 className='font-medium'>{food.food_name}</h3>
                {food.nf_calories && (
                  <p className='text-sm text-gray-600'>
                    {food.nf_calories} calories
                  </p>
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
    )
  }

  return (
    <div className='w-full max-w-2xl mx-auto'>
      <div className='relative'>
        <input
          type='text'
          value={searchTerm}
          onChange={handleSearchChange}
          placeholder='Search foods by name or description...'
          className='w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500'
        />
        {showLoading && (
          <div className='absolute right-3 top-2' data-testid='loading-spinner'>
            <div className='animate-spin rounded-full h-6 w-6 border-b-2 border-blue-500'></div>
          </div>
        )}
      </div>

      {error && (
        <div className='mt-2 text-red-500 text-sm'>{error.message}</div>
      )}

      <div className='mt-4'>
        {data &&
        ((data.common && data.common.length > 0) ||
          (data.branded && data.branded.length > 0)) ? (
          <Tabs value={tab} onValueChange={setTab} className='w-full'>
            <TabsList className='mb-4 bg-gray-100 rounded-lg p-1 flex gap-2'>
              <TabsTrigger
                value='common'
                className='data-[state=active]:bg-white data-[state=active]:font-bold data-[state=active]:shadow data-[state=active]:text-blue-600 px-4 py-2 rounded-md transition-colors'
              >
                Common Foods
              </TabsTrigger>
              <TabsTrigger
                value='branded'
                className='data-[state=active]:bg-white data-[state=active]:font-bold data-[state=active]:shadow data-[state=active]:text-blue-600 px-4 py-2 rounded-md transition-colors'
              >
                Branded Foods
              </TabsTrigger>
            </TabsList>
            <TabsContent value='common'>
              {renderFoodList(data.common)}
            </TabsContent>
            <TabsContent value='branded'>
              {renderFoodList(data.branded)}
            </TabsContent>
          </Tabs>
        ) : (
          debouncedSearchTerm &&
          !isLoading && (
            <p className='text-gray-500 text-center'>No foods found</p>
          )
        )}
      </div>
    </div>
  )
}
