import { clearAuthSession, loadAuthSession, saveAuthSession, type AuthSession } from '@lingjuli/auth-client';
import { createContext, useCallback, useContext, useMemo, useState, type PropsWithChildren } from 'react';
import { login as loginRequest, logout as logoutRequest, type LoginInput } from './authApi';

interface AuthContextValue {
  session: AuthSession | undefined;
  login: (input: LoginInput) => Promise<AuthSession>;
  logout: () => Promise<void>;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export function AuthProvider({ children }: PropsWithChildren) {
  const [session, setSession] = useState<AuthSession | undefined>(() => loadAuthSession());

  const login = useCallback(async (input: LoginInput) => {
    const nextSession = await loginRequest(input);
    saveAuthSession(nextSession);
    setSession(nextSession);
    return nextSession;
  }, []);

  const logout = useCallback(async () => {
    const accessToken = session?.accessToken;
    clearAuthSession();
    setSession(undefined);
    if (accessToken) await logoutRequest(accessToken);
  }, [session?.accessToken]);

  const value = useMemo(() => ({ session, login, logout }), [session, login, logout]);
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth(): AuthContextValue {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used inside AuthProvider');
  return context;
}
