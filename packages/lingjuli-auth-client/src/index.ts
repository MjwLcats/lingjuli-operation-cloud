export interface AuthSession {
  accessToken: string;
  expiresAt: number;
  tenantId: string | undefined;
  userId: string;
  displayName: string;
  accountType: 'PLATFORM' | 'MERCHANT';
}

const SESSION_STORAGE_KEY = 'lingjuli.auth.session.v1';

export function loadAuthSession(): AuthSession | undefined {
  const serialized = sessionStorage.getItem(SESSION_STORAGE_KEY);
  if (!serialized) return undefined;
  try {
    const session = JSON.parse(serialized) as AuthSession;
    if (session.expiresAt <= Date.now()) {
      sessionStorage.removeItem(SESSION_STORAGE_KEY);
      return undefined;
    }
    return session;
  } catch {
    sessionStorage.removeItem(SESSION_STORAGE_KEY);
    return undefined;
  }
}

export function saveAuthSession(session: AuthSession): void {
  sessionStorage.setItem(SESSION_STORAGE_KEY, JSON.stringify(session));
}

export function clearAuthSession(): void {
  sessionStorage.removeItem(SESSION_STORAGE_KEY);
}

export interface PermissionSnapshot {
  permissions: ReadonlySet<string>;
  can(permission: string): boolean;
}

export function createPermissionSnapshot(codes: readonly string[]): PermissionSnapshot {
  const permissions = new Set(codes);
  return { permissions, can: (permission) => permissions.has(permission) };
}
