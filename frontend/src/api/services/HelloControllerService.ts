/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class HelloControllerService {
  /**
   * Get a greeting
   * Returns a simple greeting message
   * @returns string Successfully retrieved greeting
   * @throws ApiError
   */
  public static hello(): CancelablePromise<string> {
    return __request(OpenAPI, {
      method: 'GET',
      url: '/api/hello',
    });
  }
}
