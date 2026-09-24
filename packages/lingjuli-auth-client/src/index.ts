export interface AuthSession {
  accessToken: string;
  expiresAt: number;
  tenantId?: string;
  userId: string;
}

export interface PermissionSnapshot {
  permissions: ReadonlySet<string>;
  can(permission: string): boolean;
}

export function createPermissionSnapshot(codes: readonly string[]): PermissionSnapshot {
  const permissions = new Set(codes);
  return { permissions, can: (permission) => permissions.has(permission) };
}
