import { useQuery } from '@tanstack/react-query';
import { PageFood } from '../api/models/PageFood';
import { FoodsService } from '../api/services/FoodsService';

interface SearchFoodsParams {
  query: string;
  page: number;
  size?: number;
  sortBy?: string;
  direction?: string;
}

export const useSearchFoods = ({
  query,
  page,
  size = 10,
  sortBy = 'name',
  direction = 'asc',
}: SearchFoodsParams) => {
  return useQuery<PageFood, Error>({
    queryKey: ['foods', 'search', query, page, size, sortBy, direction],
    queryFn: () => FoodsService.searchFoods({
      query,
      page,
      size,
      sortBy,
      direction,
    }),
    enabled: query.length > 0,
  });
}; 