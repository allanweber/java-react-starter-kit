/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { AltMeasure } from './AltMeasure';
import type { FullNutrient } from './FullNutrient';
export type Food = {
  id?: number;
  name: string;
  description?: string;
  caloriesPer100g: number;
  proteinPer100g: number;
  carbsPer100g: number;
  fatsPer100g: number;
  fiberPer100g: number;
  sugarPer100g: number;
  sodiumPer100g: number;
  servingSizeG: number;
  servingSizeUnit?: string;
  fullNutrients?: Array<FullNutrient>;
  altMeasures?: Array<AltMeasure>;
  createdAt?: string;
  updatedAt?: string;
  image_url?: string;
};

