import { Food, FoodSearchResponse } from '@/api';
import { useQuery } from '@tanstack/react-query';
import { FoodsService } from '../api/services/FoodsService';

interface SearchFoodsParams {
  query: string;
}

interface GetFoodByNameParams {
  name: string;
}

export const useSearchFoods = ({
  query,
}: SearchFoodsParams) => {
  return useQuery<FoodSearchResponse, Error>({
    queryKey: ['foods', 'search', query],
    queryFn: () => FoodsService.searchFoods({
      query,
    }),
    enabled: query.length > 0,
  });
}; 

export const useGetFoodByName = ({
  name,
}: GetFoodByNameParams) => {
  return useQuery<Food, Error>({
    queryKey: [`food-${name}`],
    queryFn: () => FoodsService.getFoodByName({ food: name }),
    enabled: name.length > 0,
  });
};