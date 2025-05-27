/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { UserDTO } from '../models/UserDTO';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class UserControllerService {
  /**
   * Get user by ID
   * Returns a user by their ID
   * @returns UserDTO Successfully retrieved user
   * @throws ApiError
   */
  public static getUserById({
    id,
  }: {
    id: number,
  }): CancelablePromise<UserDTO> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/users/{id}',
      path: {
        'id': id,
      },
      errors: {
        404: `User not found`,
      },
    });
  }
  /**
   * Update a user
   * Updates a user by their ID
   * @returns UserDTO User updated successfully
   * @throws ApiError
   */
  public static updateUser({
    id,
    requestBody,
  }: {
    id: number,
    requestBody: UserDTO,
  }): CancelablePromise<UserDTO> {
    return __request(OpenAPI, {
      method: 'PUT',
      url: '/api/users/{id}',
      path: {
        'id': id,
      },
      body: requestBody,
      mediaType: 'application/json',
      errors: {
        404: `User not found`,
      },
    });
  }
  /**
   * Delete a user
   * Deletes a user by their ID
   * @returns void
   * @throws ApiError
   */
  public static deleteUser({
    id,
  }: {
    id: number,
  }): CancelablePromise<void> {
    return __request(OpenAPI, {
      method: 'DELETE',
      url: '/api/users/{id}',
      path: {
        'id': id,
      },
      errors: {
        404: `User not found`,
      },
    });
  }
  /**
   * Get all users
   * Returns a list of all users
   * @returns UserDTO Successfully retrieved users
   * @throws ApiError
   */
  public static getAllUsers(): CancelablePromise<Array<UserDTO>> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/users',
    });
  }
  /**
   * Create a new user
   * Creates a new user
   * @returns UserDTO User created successfully
   * @throws ApiError
   */
  public static createUser({
    requestBody,
  }: {
    requestBody: UserDTO,
  }): CancelablePromise<UserDTO> {
    return __request(OpenAPI, {
      method: 'POST',
      url: '/api/users',
      body: requestBody,
      mediaType: 'application/json',
    });
  }
}
