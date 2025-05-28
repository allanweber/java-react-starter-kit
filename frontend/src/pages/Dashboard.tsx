import { HelloControllerService } from '@/api/services/HelloControllerService'
import { Button } from '@/components/ui/button'
import { useState } from 'react'
import { toast, Toaster } from 'sonner'

export const Dashboard = () => {
  const [greeting, setGreeting] = useState<string>('')

  const handleHello = async () => {
    try {
      const response = await HelloControllerService.hello()
      if (response?.message) {
        setGreeting(response.message)
        toast.success('Greeting received!')
      } else {
        throw new Error('Invalid response format')
      }
    } catch (error) {
      toast.error('Failed to get greeting')
      console.error(error)
    }
  }

  return (
    <div className='min-h-screen bg-gray-100'>
      <Toaster position='top-right' />
      <header className='bg-white shadow'>
        <div className='max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8'>
          <h1 className='text-2xl font-bold text-gray-900'>Dashboard</h1>
        </div>
      </header>
      <div className='flex'>
        <main className='flex-1 p-4'>
          <div className='mb-8'>
            <Button onClick={handleHello}>Get Greeting</Button>
            {greeting && (
              <p className='mt-4 text-lg text-gray-700'>{greeting}</p>
            )}
          </div>
        </main>
      </div>
    </div>
  )
}
