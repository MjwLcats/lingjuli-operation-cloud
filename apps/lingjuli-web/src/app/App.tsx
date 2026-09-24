import { lazy, Suspense } from 'react';
import { Spin } from 'antd';
import { Navigate, Route, Routes } from 'react-router-dom';
import { AuthProvider } from '../auth/AuthContext';
import { ProtectedRoute } from '../auth/ProtectedRoute';

const MerchantFoundationPage = lazy(
  () => import('../pages/merchant/MerchantFoundationPage'),
);
const PlatformFoundationPage = lazy(
  () => import('../pages/platform/PlatformFoundationPage'),
);
const LoginPage = lazy(() => import('../pages/auth/LoginPage'));

export function App() {
  return (
    <AuthProvider>
      <Suspense fallback={<Spin fullscreen tip="正在加载" />}>
        <Routes>
          <Route path="/" element={<Navigate to="/merchant" replace />} />
          <Route path="/merchant/login" element={<LoginPage audience="merchant" />} />
          <Route path="/platform/login" element={<LoginPage audience="platform" />} />
          <Route path="/merchant/*" element={<ProtectedRoute audience="merchant"><MerchantFoundationPage /></ProtectedRoute>} />
          <Route path="/platform/*" element={<ProtectedRoute audience="platform"><PlatformFoundationPage /></ProtectedRoute>} />
          <Route path="*" element={<Navigate to="/merchant" replace />} />
        </Routes>
      </Suspense>
    </AuthProvider>
  );
}
