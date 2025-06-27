import { Button } from '@/components/ui/button';
import { createFileRoute, Link } from '@tanstack/react-router';

export const Route = createFileRoute('/about')({
  component: About,
});

function About() {
  return (
    <div className="container mx-auto py-8">
      <h1 className="text-4xl font-bold mb-6">About</h1>
      <div className="prose max-w-none">
        <p className="text-lg mb-4">
          Welcome to our application! This is a starter kit that demonstrates
          the use of modern web technologies including React, TanStack Router,
          and Tailwind CSS.
        </p>
        <p className="text-lg mb-4">
          This page serves as an example of how to create and navigate between
          different routes in your application.
        </p>
        <Button asChild>
          <Link to="/">Back to Dashboard</Link>
        </Button>
      </div>
    </div>
  );
}
