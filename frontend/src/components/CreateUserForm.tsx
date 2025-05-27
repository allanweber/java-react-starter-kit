import { zodResolver } from '@hookform/resolvers/zod'
import { useForm } from 'react-hook-form'
import { toast } from 'sonner'
import * as z from 'zod'
import { UserControllerService } from '../api/services/UserControllerService'
import { Button } from './ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle
} from './ui/card'
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage
} from './ui/form'
import { Input } from './ui/input'

const formSchema = z.object({
  name: z
    .string()
    .min(2, 'Name must be at least 2 characters')
    .max(50, 'Name must be less than 50 characters')
    .regex(/^[a-zA-Z\s]*$/, 'Name can only contain letters and spaces'),
  email: z
    .string()
    .email('Invalid email address')
    .min(5, 'Email must be at least 5 characters')
    .max(100, 'Email must be less than 100 characters')
})

type CreateUserFormProps = {
  onUserCreated?: () => void
}

export function CreateUserForm ({ onUserCreated }: CreateUserFormProps) {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: '',
      email: ''
    }
  })

  async function onSubmit (values: z.infer<typeof formSchema>) {
    try {
      await UserControllerService.createUser({
        requestBody: {
          name: values.name,
          email: values.email
        }
      })
      form.reset()
      toast.success('User created successfully!')
      onUserCreated?.()
    } catch (error) {
      console.error('Failed to create user:', error)
      toast.error('Failed to create user. Please try again.')
    }
  }

  return (
    <Card className='w-full max-w-2xl mx-auto'>
      <CardHeader>
        <CardTitle>Create New User</CardTitle>
        <CardDescription>Add a new user to the system</CardDescription>
      </CardHeader>
      <CardContent>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className='space-y-6'>
            <FormField
              control={form.control}
              name='name'
              render={({ field }) => (
                <FormItem>
                  <FormLabel className='text-sm font-medium'>Name</FormLabel>
                  <FormControl>
                    <Input
                      placeholder='John Doe'
                      className='w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent'
                      {...field}
                    />
                  </FormControl>
                  <FormDescription className='text-sm text-gray-500'>
                    Enter the user's full name
                  </FormDescription>
                  <FormMessage className='text-sm text-red-500' />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name='email'
              render={({ field }) => (
                <FormItem>
                  <FormLabel className='text-sm font-medium'>Email</FormLabel>
                  <FormControl>
                    <Input
                      placeholder='john@example.com'
                      type='email'
                      className='w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent'
                      {...field}
                    />
                  </FormControl>
                  <FormDescription className='text-sm text-gray-500'>
                    Enter a valid email address
                  </FormDescription>
                  <FormMessage className='text-sm text-red-500' />
                </FormItem>
              )}
            />
            <Button
              type='submit'
              className='w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-2 px-4 rounded-md transition-colors duration-200'
            >
              Create User
            </Button>
          </form>
        </Form>
      </CardContent>
    </Card>
  )
}
