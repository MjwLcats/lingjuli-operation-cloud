export type StatusTone = 'success' | 'warning' | 'error' | 'processing' | 'disabled' | 'default';

export interface StatusMetadata<TCode extends string = string> {
  code: TCode;
  label: string;
  tone: StatusTone;
  description?: string;
}

export const dataScopeCodes = [
  'SELF', 'STORE_CURRENT', 'STORE_ASSIGNED', 'REGION_CURRENT', 'REGION_ASSIGNED',
  'ORG_CURRENT', 'ORG_AND_DESCENDANTS', 'ALL_TENANT', 'CUSTOM',
] as const;

export type DataScopeCode = (typeof dataScopeCodes)[number];
