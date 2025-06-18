import {
  createRootRoute,
  createRoute,
  createRouter,
  Outlet
} from '@tanstack/react-router'
import { About } from './pages/About'
import { FoodSearchPage } from './pages/FoodSearchPage'
import FoodPage from './pages/food'

const rootRoute = createRootRoute({
  component: () => (
    <div>
      <Outlet />
    </div>
  )
})

const indexRoute = createRoute({
  getParentRoute: () => rootRoute,
  path: '/',
  component: FoodSearchPage
})

const foodRoute = createRoute({
  getParentRoute: () => rootRoute,
  path: '/food/$food',
  component: FoodPage
})

const aboutRoute = createRoute({
  getParentRoute: () => rootRoute,
  path: '/about',
  component: About
})

const routeTree = rootRoute.addChildren([indexRoute, aboutRoute, foodRoute])

export const router = createRouter({ routeTree })

declare module '@tanstack/react-router' {
  interface Register {
    router: typeof router
  }
}
