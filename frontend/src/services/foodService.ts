import { useQuery } from '@tanstack/react-query';
import { FoodClientResponse } from '../api/models/FoodClientResponse';
import { FoodsService } from '../api/services/FoodsService';

interface SearchFoodsParams {
  query: string;
}

export const useSearchFoods = ({
  query,
}: SearchFoodsParams) => {
  return useQuery<FoodClientResponse, Error>({
    queryKey: ['foods', 'search', query],
    queryFn: () => FoodsService.searchFoods({
      query,
    }),
    enabled: query.length > 0,
  });
}; 