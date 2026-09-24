import { AppstoreOutlined, ArrowRightOutlined, BarChartOutlined, CheckSquareOutlined, GlobalOutlined, LockOutlined, QrcodeOutlined, SafetyCertificateOutlined, ShopOutlined, UserOutlined } from '@ant-design/icons';
import { Alert, Button, Checkbox, Form, Input, Typography } from 'antd';
import { getApiErrorMessage } from '@lingjuli/api-client';
import { useState } from 'react';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../../auth/AuthContext';
import type { LoginAudience } from '../../auth/authApi';
import './login.css';

interface LoginFormValues {
  merchantNo?: string;
  loginName: string;
  password: string;
}

const COPY = {
  platform: {
    eyebrow: 'TONGQING CLOUD PLATFORM',
    title: '平台管理工作台',
    description: '欢迎登录，开启同庆云平台治理与运营管理',
  },
  merchant: {
    eyebrow: 'TONGQING OPERATION CLOUD',
    title: '商户运营工作台',
    description: '欢迎登录，开启门店数字化运营管理',
  },
} as const;

export default function LoginPage({ audience }: { audience: LoginAudience }) {
  const { session, login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [error, setError] = useState<string>();
  const expectedAccountType = audience === 'platform' ? 'PLATFORM' : 'MERCHANT';
  if (session?.accountType === expectedAccountType) return <Navigate to={`/${audience}`} replace />;

  const submit = async (values: LoginFormValues) => {
    setError(undefined);
    try {
      await login({ audience, ...values });
      const destination = (location.state as { from?: string } | null)?.from;
      void navigate(destination?.startsWith(`/${audience}`) ? destination : `/${audience}`, { replace: true });
    } catch (requestError) {
      setError(getApiErrorMessage(requestError, '暂时无法登录，请稍后重试'));
    }
  };

  const copy = COPY[audience];
  return (
    <main className="lj-login-shell">
      <section className="lj-login-brand" aria-label="产品介绍">
        <div className="lj-login-brandmark">
          <span className="lj-login-brandmark__icon"><SafetyCertificateOutlined /></span>
          <span><strong>同庆云</strong><small>TONGQING OPERATION CLOUD</small></span>
        </div>
        <div className="lj-login-brand__copy">
          <Typography.Title className="lj-login-brand__title">让每一家门店，<br />运营得更清晰</Typography.Title>
          <Typography.Paragraph className="lj-login-brand__description">统一数据、规范化管理流程，<br />为连锁餐饮提供安全可靠的多场景运营服务。</Typography.Paragraph>
          <div className="lj-login-benefits">
            <div><span><BarChartOutlined /></span><p><strong>多门店统一管理</strong><small>经营数据可视化</small></p></div>
            <div><span><CheckSquareOutlined /></span><p><strong>高效任务协同</strong><small>提升门店执行效率</small></p></div>
            <div><span><SafetyCertificateOutlined /></span><p><strong>数据安全可靠</strong><small>企业级安全保障</small></p></div>
            <div><span><AppstoreOutlined /></span><p><strong>持续扩展的运营能力</strong><small>满足更多业务场景</small></p></div>
          </div>
        </div>
        <img className="lj-login-hero" src="/assets/auth/tongqing-cloud-operations-hero.png" alt="同庆云门店运营数据与任务管理" />
      </section>
      <section className="lj-login-form-panel" aria-label={`${copy.title}登录`}>
        <div className="lj-login-language"><GlobalOutlined /> 简体中文</div>
        <div className="lj-login-card">
          <Typography.Text className="lj-login-eyebrow lj-login-eyebrow--form">{copy.eyebrow}</Typography.Text>
          <Typography.Title level={1} className="lj-login-title">{copy.title}</Typography.Title>
          <Typography.Paragraph type="secondary" className="lj-login-description">{copy.description}</Typography.Paragraph>
          {error ? <Alert className="lj-login-error" type="error" showIcon message={error} /> : null}
          <Form<LoginFormValues> layout="vertical" requiredMark={false} size="large" onFinish={(values) => void submit(values)}>
            {audience === 'merchant' ? (
              <Form.Item label="商户号" name="merchantNo" rules={[{ required: true, message: '请输入商户号' }, { pattern: /^\d+$/, message: '商户号只能包含数字' }]}>
                <Input prefix={<ShopOutlined />} inputMode="numeric" autoComplete="organization" placeholder="请输入商户号" />
              </Form.Item>
            ) : null}
            <Form.Item label="账号 / 手机号" name="loginName" rules={[{ required: true, message: '请输入账号或手机号' }]}>
              <Input prefix={<UserOutlined />} autoComplete="username" placeholder="请输入账号或手机号" />
            </Form.Item>
            <Form.Item label="登录密码" name="password" rules={[{ required: true, message: '请输入登录密码' }]}>
              <Input.Password prefix={<LockOutlined />} autoComplete="current-password" placeholder="请输入登录密码" />
            </Form.Item>
            <div className="lj-login-options"><Checkbox>记住我</Checkbox><button type="button">忘记密码？</button></div>
            <Form.Item className="lj-login-submit">
              <Button type="primary" htmlType="submit" block>登录 <ArrowRightOutlined /></Button>
            </Form.Item>
          </Form>
          <div className="lj-login-divider"><span>其他登录方式</span></div>
          <button className="lj-login-scan" type="button" aria-label="扫码登录暂未开放"><span><QrcodeOutlined /></span><small>扫码登录</small></button>
          <Typography.Paragraph className="lj-login-help" type="secondary">登录即表示您已阅读并同意 <button type="button">《用户协议》</button> 和 <button type="button">《隐私政策》</button></Typography.Paragraph>
        </div>
      </section>
    </main>
  );
}
