import { HelloControllerService } from '@/api/services/HelloControllerService'
import { useQuery } from '@tanstack/react-query'

export const useHello = () => {
  return useQuery({
    queryKey: ['hello'],
    queryFn: () => HelloControllerService.hello()
  })
} 