import { Button } from 'antd';
import { AppCard, ListPage, PageHeader } from '@lingjuli/web-ui';
import { useAuth } from '../../auth/AuthContext';

export default function PlatformFoundationPage() {
  const { session, logout } = useAuth();
  return (
    <ListPage
      header={
        <PageHeader
          title="同庆云 SaaS 平台端"
          description="平台治理能力将在平台专用入口、显式授权和审计约束下逐步接入。"
          actions={<Button onClick={() => void logout()}>退出登录</Button>}
        />
      }
      content={<AppCard>你好，{session?.displayName}。平台端应用容器与认证链路已就绪。</AppCard>}
    />
  );
}
