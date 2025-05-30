/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $Food = {
  properties: {
    id: {
      type: 'string',
      format: 'uuid',
    },
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
    createdAt: {
      type: 'string',
      format: 'date-time',
    },
    updatedAt: {
      type: 'string',
      format: 'date-time',
    },
  },
} as const;
