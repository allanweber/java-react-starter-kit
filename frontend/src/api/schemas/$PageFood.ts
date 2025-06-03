/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $PageFood = {
  properties: {
    totalElements: {
      type: 'number',
      format: 'int64',
    },
    totalPages: {
      type: 'number',
      format: 'int32',
    },
    pageable: {
      type: 'PageableObject',
    },
    size: {
      type: 'number',
      format: 'int32',
    },
    content: {
      type: 'array',
      contains: {
        type: 'Food',
      },
    },
    number: {
      type: 'number',
      format: 'int32',
    },
    sort: {
      type: 'SortObject',
    },
    numberOfElements: {
      type: 'number',
      format: 'int32',
    },
    first: {
      type: 'boolean',
    },
    last: {
      type: 'boolean',
    },
    empty: {
      type: 'boolean',
    },
  },
} as const;
