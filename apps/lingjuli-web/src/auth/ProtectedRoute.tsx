import type { AuthSession } from '@lingjuli/auth-client';
import type { ReactNode } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from './AuthContext';
import type { LoginAudience } from './authApi';

const ACCOUNT_TYPE: Record<LoginAudience, AuthSession['accountType']> = {
  platform: 'PLATFORM',
  merchant: 'MERCHANT',
};

export function ProtectedRoute({ audience, children }: { audience: LoginAudience; children: ReactNode }) {
  const { session } = useAuth();
  const location = useLocation();
  if (!session || session.accountType !== ACCOUNT_TYPE[audience]) {
    return <Navigate to={`/${audience}/login`} replace state={{ from: location.pathname }} />;
  }
  return children;
}
