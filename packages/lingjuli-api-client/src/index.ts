import axios, { type AxiosInstance } from 'axios';

export interface ApiResponse<T> {
  code: string;
  message: string;
  data: T;
  requestId?: string;
}

export interface ApiClientOptions {
  baseURL: string;
  getAccessToken?: () => string | undefined;
  getTenantId?: () => string | undefined;
}

export function createApiClient(options: ApiClientOptions): AxiosInstance {
  const client = axios.create({ baseURL: options.baseURL, timeout: 15_000 });
  client.interceptors.request.use((config) => {
    const token = options.getAccessToken?.();
    const tenantId = options.getTenantId?.();
    if (token) config.headers.Authorization = `Bearer ${token}`;
    if (tenantId) config.headers['X-Tenant-Id'] = tenantId;
    config.headers['X-Request-Id'] = crypto.randomUUID();
    return config;
  });
  return client;
}

export function getApiErrorMessage(error: unknown, fallback: string): string {
  if (!axios.isAxiosError<ApiResponse<unknown>>(error)) return fallback;
  return error.response?.data.message ?? fallback;
}
