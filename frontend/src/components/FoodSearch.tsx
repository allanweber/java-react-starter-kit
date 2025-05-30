import React, { useEffect, useState } from 'react';
import { Food } from '../api/models/Food';
import { useSearchFoods } from '../services/foodService';
import { FoodCard } from './FoodCard';

interface FoodSearchProps {
  onFoodSelect?: (food: Food) => void;
}

export function FoodSearch({ onFoodSelect }: FoodSearchProps) {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(0);
  const [debouncedSearchTerm, setDebouncedSearchTerm] = useState('');
  const [showLoading, setShowLoading] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedSearchTerm(searchTerm);
    }, 300);

    return () => clearTimeout(timer);
  }, [searchTerm]);

  const { data, isLoading, error } = useSearchFoods({
    query: debouncedSearchTerm,
    page,
  });

  useEffect(() => {
    let loadingTimer: ReturnType<typeof setTimeout>;
    if (isLoading) {
      loadingTimer = setTimeout(() => {
        setShowLoading(true);
      }, 500);
    } else {
      setShowLoading(false);
    }
    return () => clearTimeout(loadingTimer);
  }, [isLoading]);

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
    setPage(0);
  };

  const handlePageChange = (newPage: number) => {
    setPage(newPage);
  };

  return (
    <div className="w-full max-w-2xl mx-auto">
      <div className="relative">
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearchChange}
          placeholder="Search foods by name or description..."
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        {showLoading && (
          <div className="absolute right-3 top-2">
            <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-blue-500"></div>
          </div>
        )}
      </div>

      {error && (
        <div className="mt-2 text-red-500 text-sm">{error.message}</div>
      )}

      <div className="mt-4">
        {data?.content && data.content.length > 0 ? (
          <div className="space-y-2">
            {data.content.map((food) => (
              <FoodCard
                key={food.id}
                food={food}
                onClick={onFoodSelect}
              />
            ))}
          </div>
        ) : debouncedSearchTerm && !isLoading ? (
          <p className="text-gray-500 text-center">No foods found</p>
        ) : null}
      </div>

      {data?.content && data.content.length > 0 && data?.totalPages && data.totalPages > 1 && (
        <div className="mt-4 flex justify-center space-x-2">
          <button
            onClick={() => handlePageChange(page - 1)}
            disabled={page === 0}
            className="px-3 py-1 border rounded-lg disabled:opacity-50"
          >
            Previous
          </button>
          <span className="px-3 py-1">
            Page {page + 1} of {data.totalPages}
          </span>
          <button
            onClick={() => handlePageChange(page + 1)}
            disabled={page === data.totalPages - 1}
            className="px-3 py-1 border rounded-lg disabled:opacity-50"
          >
            Next
          </button>
        </div>
      )}
    </div>
  );
} 