/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Food } from '../models/Food';
import type { FoodClientResponse } from '../models/FoodClientResponse';
import type { FoodRequest } from '../models/FoodRequest';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class FoodsService {
  /**
   * Get food by ID
   * @returns Food OK
   * @throws ApiError
   */
  public static getFoodById({
    id,
  }: {
    id: string,
  }): CancelablePromise<Food> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/v1/foods/{id}',
      path: {
        'id': id,
      },
    });
  }
  /**
   * Update an existing food
   * @returns Food OK
   * @throws ApiError
   */
  public static updateFood({
    id,
    requestBody,
  }: {
    id: string,
    requestBody: FoodRequest,
  }): CancelablePromise<Food> {
    return __request(OpenAPI, {
      method: 'PUT',
      url: '/api/v1/foods/{id}',
      path: {
        'id': id,
      },
      body: requestBody,
      mediaType: 'application/json',
    });
  }
  /**
   * Delete a food
   * @returns void
   * @throws ApiError
   */
  public static deleteFood({
    id,
  }: {
    id: string,
  }): CancelablePromise<void> {
    return __request(OpenAPI, {
      method: 'DELETE',
      url: '/api/v1/foods/{id}',
      path: {
        'id': id,
      },
    });
  }
  /**
   * Create a new food
   * @returns Food Created
   * @throws ApiError
   */
  public static createFood({
    requestBody,
  }: {
    requestBody: FoodRequest,
  }): CancelablePromise<Food> {
    return __request(OpenAPI, {
      method: 'POST',
      url: '/api/v1/foods',
      body: requestBody,
      mediaType: 'application/json',
    });
  }
  /**
   * Search foods by name or description
   * @returns FoodClientResponse OK
   * @throws ApiError
   */
  public static searchFoods({
    query,
  }: {
    query: string,
  }): CancelablePromise<FoodClientResponse> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/v1/foods/search',
      query: {
        'query': query,
      },
    });
  }
}
