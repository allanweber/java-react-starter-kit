/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $PageableObject = {
  properties: {
    paged: {
      type: 'boolean',
    },
    pageNumber: {
      type: 'number',
      format: 'int32',
    },
    pageSize: {
      type: 'number',
      format: 'int32',
    },
    unpaged: {
      type: 'boolean',
    },
    offset: {
      type: 'number',
      format: 'int64',
    },
    sort: {
      type: 'SortObject',
    },
  },
} as const;
