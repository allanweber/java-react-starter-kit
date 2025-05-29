import { Button } from '@/components/ui/button'
import { useHello } from '@/services/helloService'
import { Link } from '@tanstack/react-router'
import { Toaster } from 'sonner'

export const Dashboard = () => {
  const { data: greeting, isLoading } = useHello()

  return (
    <div className='min-h-screen bg-gray-100'>
      <Toaster position='top-right' />
      <header className='bg-white shadow'>
        <div className='max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8 flex justify-between items-center'>
          <h1 className='text-2xl font-bold text-gray-900'>Dashboard</h1>
          <nav>
            <Button variant='ghost' asChild>
              <Link to='/about'>About</Link>
            </Button>
          </nav>
        </div>
      </header>
      <div className='flex'>
        <main className='flex-1 p-4'>
          <div className='mb-8'>
            {isLoading ? (
              <p className='text-lg text-gray-700'>Loading greeting...</p>
            ) : greeting?.message ? (
              <p className='text-lg text-gray-700'>{greeting.message}</p>
            ) : (
              <p className='text-lg text-gray-700'>No greeting available</p>
            )}
          </div>
        </main>
      </div>
    </div>
  )
}
