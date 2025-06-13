/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $FoodClientResponse = {
  properties: {
    common: {
      type: 'array',
      contains: {
        type: 'FoodInstantResponse',
      },
    },
    branded: {
      type: 'array',
      contains: {
        type: 'FoodInstantResponse',
      },
    },
  },
} as const;
