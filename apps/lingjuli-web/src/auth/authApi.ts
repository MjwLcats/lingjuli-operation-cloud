import { createApiClient, type ApiResponse } from '@lingjuli/api-client';
import type { AuthSession } from '@lingjuli/auth-client';

export type LoginAudience = 'platform' | 'merchant';

export interface LoginInput {
  audience: LoginAudience;
  merchantNo?: string;
  loginName: string;
  password: string;
}

interface LoginData {
  accessToken: string;
  expiresAt: string;
  userId: string;
  tenantId?: string;
  displayName: string;
  accountType: 'PLATFORM' | 'MERCHANT';
}

const client = createApiClient({ baseURL: '/' });

export async function login(input: LoginInput): Promise<AuthSession> {
  const response = await client.post<ApiResponse<LoginData>>(
    `/api/v1/${input.audience}/auth/login`,
    {
      merchantNo: input.merchantNo ? Number(input.merchantNo) : undefined,
      loginName: input.loginName,
      password: input.password,
    },
  );
  const result = response.data.data;
  return {
    accessToken: result.accessToken,
    expiresAt: new Date(result.expiresAt).getTime(),
    tenantId: result.tenantId,
    userId: result.userId,
    displayName: result.displayName,
    accountType: result.accountType,
  };
}

export async function logout(accessToken: string): Promise<void> {
  await client.post('/api/v1/auth/logout', undefined, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });
}
