import { useEffect, useState } from 'react'
import { UserDTO as User } from '../api/models/UserDTO'
import { UserControllerService as UserService } from '../api/services/UserControllerService'
import { Card, CardContent, CardHeader, CardTitle } from './ui/card'

export const UserList = () => {
  const [users, setUsers] = useState<User[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await UserService.getAllUsers()
        setUsers(response)
        setLoading(false)
      } catch (err) {
        setError('Failed to fetch users')
        setLoading(false)
      }
    }

    fetchUsers()
  }, [])

  if (loading) return <div>Loading...</div>
  if (error) return <div>{error}</div>

  return (
    <div className='p-4'>
      <h1 className='text-2xl font-bold mb-4'>Users</h1>
      <div className='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4'>
        {users.map(user => (
          <Card key={user.id}>
            <CardHeader>
              <CardTitle>{user.name}</CardTitle>
            </CardHeader>
            <CardContent>
              <p>{user.email}</p>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  )
}
