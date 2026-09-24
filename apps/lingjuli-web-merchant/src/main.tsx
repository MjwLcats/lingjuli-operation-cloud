import React from 'react';
import ReactDOM from 'react-dom/client';
import { ConfigProvider } from 'antd';
import { BrowserRouter } from 'react-router-dom';
import '@lingjuli/design-tokens/web.css';
import { lingjuliTheme } from '@lingjuli/web-ui';
import { App } from './app/App';
import './styles/global.css';
ReactDOM.createRoot(document.getElementById('root')!).render(<React.StrictMode><ConfigProvider theme={lingjuliTheme}><BrowserRouter><App /></BrowserRouter></ConfigProvider></React.StrictMode>);
