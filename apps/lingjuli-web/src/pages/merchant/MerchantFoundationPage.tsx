import { Button } from 'antd';
import { AppCard, ListPage, PageHeader } from '@lingjuli/web-ui';
import { useAuth } from '../../auth/AuthContext';

export default function MerchantFoundationPage() {
  const { session, logout } = useAuth();
  return (
    <ListPage
      header={
        <PageHeader
          title="同庆云商家管理端"
          description="集团、区域和门店管理能力将在商家权限域内逐步接入。"
          actions={<Button onClick={() => void logout()}>退出登录</Button>}
        />
      }
      content={<AppCard>你好，{session?.displayName}。当前租户：{session?.tenantId}。</AppCard>}
    />
  );
}
