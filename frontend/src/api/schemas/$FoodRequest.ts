/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $FoodRequest = {
  properties: {
    name: {
      type: 'string',
      isRequired: true,
      maxLength: 255,
    },
    description: {
      type: 'string',
    },
    caloriesPer100g: {
      type: 'number',
      isRequired: true,
    },
    proteinPer100g: {
      type: 'number',
      isRequired: true,
    },
    carbsPer100g: {
      type: 'number',
      isRequired: true,
    },
    fatsPer100g: {
      type: 'number',
      isRequired: true,
    },
    fiberPer100g: {
      type: 'number',
      isRequired: true,
    },
    sugarPer100g: {
      type: 'number',
      isRequired: true,
    },
    sodiumPer100g: {
      type: 'number',
      isRequired: true,
    },
    servingSizeG: {
      type: 'number',
      isRequired: true,
    },
    servingSizeUnit: {
      type: 'string',
      maxLength: 50,
    },
  },
} as const;
