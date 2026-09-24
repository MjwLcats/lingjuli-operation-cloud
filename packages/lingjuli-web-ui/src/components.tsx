import type { PropsWithChildren, ReactNode } from 'react';
import { Button, Card, Empty, Result, Spin } from 'antd';
import type { ButtonProps, CardProps } from 'antd';
import './ui.css';

export function AppPage({ children }: PropsWithChildren) {
  return <main className="lj-app-page">{children}</main>;
}

export function PageHeader({ title, description, actions }: { title: string; description?: string; actions?: ReactNode }) {
  return <header className="lj-page-header">
    <div><h1 className="lj-page-header__title">{title}</h1>{description ? <p className="lj-page-header__description">{description}</p> : null}</div>
    {actions ? <div>{actions}</div> : null}
  </header>;
}

export function PageSection({ children }: PropsWithChildren) {
  return <section className="lj-page-section">{children}</section>;
}

export function AppCard(props: CardProps) { return <Card bordered {...props} />; }
export function PermissionButton(props: ButtonProps & { allowed: boolean }) { return props.allowed ? <Button {...props} /> : null; }
export function LoadingState() { return <div className="lj-state"><Spin /></div>; }
export function EmptyState({ description = '暂无数据' }: { description?: string }) { return <Empty description={description} />; }
export function ErrorState({ onRetry }: { onRetry?: () => void }) { return <Result status="error" title="加载失败" extra={onRetry ? <Button onClick={onRetry}>重试</Button> : null} />; }
export function NoPermissionState() { return <Result status="403" title="暂无权限" subTitle="请联系管理员申请所需权限" />; }

export function ListPage({ header, content }: { header: ReactNode; content: ReactNode }) { return <AppPage>{header}<PageSection>{content}</PageSection></AppPage>; }
export function FilterListPage({ header, filter, content }: { header: ReactNode; filter: ReactNode; content: ReactNode }) { return <AppPage>{header}<PageSection>{filter}</PageSection><PageSection>{content}</PageSection></AppPage>; }
