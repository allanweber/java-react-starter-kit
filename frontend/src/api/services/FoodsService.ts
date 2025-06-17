/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Food } from '../models/Food';
import type { FoodSearchResponse } from '../models/FoodSearchResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class FoodsService {
  /**
   * Get food by name
   * @returns Food OK
   * @throws ApiError
   */
  public static getFoodByName({
    food,
  }: {
    food: string,
  }): CancelablePromise<Food> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/v1/foods/{food}',
      path: {
        'food': food,
      },
    });
  }
  /**
   * Search foods by name or description
   * @returns FoodSearchResponse OK
   * @throws ApiError
   */
  public static searchFoods({
    query,
  }: {
    query: string,
  }): CancelablePromise<FoodSearchResponse> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/v1/foods/search',
      query: {
        'query': query,
      },
    });
  }
}
