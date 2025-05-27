import { useState } from 'react'
import { Toaster } from 'sonner'
import { CreateUserForm } from './CreateUserForm'
import { UserList } from './UserList'
import { Button } from './ui/button'
import { Sheet, SheetContent, SheetTrigger } from './ui/sheet'

export const Dashboard = () => {
  const [refreshKey, setRefreshKey] = useState(0)

  const handleUserCreated = () => {
    setRefreshKey(prev => prev + 1)
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
        <Sheet>
          <SheetTrigger asChild>
            <Button variant='outline' className='m-4'>
              Open Sidebar
            </Button>
          </SheetTrigger>
          <SheetContent>
            <nav className='space-y-2'>
              <a
                href='#'
                className='block px-4 py-2 text-gray-700 hover:bg-gray-100'
              >
                Home
              </a>
              <a
                href='#'
                className='block px-4 py-2 text-gray-700 hover:bg-gray-100'
              >
                Users
              </a>
              <a
                href='#'
                className='block px-4 py-2 text-gray-700 hover:bg-gray-100'
              >
                Settings
              </a>
            </nav>
          </SheetContent>
        </Sheet>
        <main className='flex-1 p-4'>
          <div className='mb-8'>
            <h2 className='text-xl font-semibold mb-4'>Create New User</h2>
            <CreateUserForm onUserCreated={handleUserCreated} />
          </div>
          <UserList key={refreshKey} />
        </main>
      </div>
    </div>
  )
}
