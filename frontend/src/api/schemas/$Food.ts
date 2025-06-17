/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $Food = {
  properties: {
    id: {
      type: 'number',
      format: 'int64',
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
    fullNutrients: {
      type: 'array',
      contains: {
        type: 'FullNutrient',
      },
    },
    altMeasures: {
      type: 'array',
      contains: {
        type: 'AltMeasure',
      },
    },
    createdAt: {
      type: 'string',
      format: 'date-time',
    },
    updatedAt: {
      type: 'string',
      format: 'date-time',
    },
    image_url: {
      type: 'string',
      maxLength: 255,
    },
  },
} as const;
