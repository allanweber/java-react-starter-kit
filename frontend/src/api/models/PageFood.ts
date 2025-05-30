/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Food } from './Food';
import type { PageableObject } from './PageableObject';
import type { SortObject } from './SortObject';
export type PageFood = {
  totalElements?: number;
  totalPages?: number;
  pageable?: PageableObject;
  size?: number;
  content?: Array<Food>;
  number?: number;
  sort?: SortObject;
  first?: boolean;
  last?: boolean;
  numberOfElements?: number;
  empty?: boolean;
};

