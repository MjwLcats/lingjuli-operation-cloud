import type { ThemeConfig } from 'antd';
import { color, layout, radius, size, typography } from '@lingjuli/design-tokens';

export const lingjuliTheme: ThemeConfig = {
  token: {
    colorPrimary: color.brand.primary,
    colorSuccess: color.status.success,
    colorWarning: color.status.warning,
    colorError: color.status.error,
    colorText: color.text.primary,
    colorTextSecondary: color.text.secondary,
    colorBorder: color.border.default,
    colorBorderSecondary: color.border.secondary,
    colorBgLayout: color.bg.page,
    colorBgContainer: color.bg.container,
    borderRadius: radius.md,
    borderRadiusLG: radius.lg,
    controlHeight: size.controlMd,
    controlHeightLG: size.controlLg,
    fontSize: typography.body.fontSize,
  },
  components: {
    Layout: { headerHeight: layout.headerHeight, siderBg: color.bg.container, bodyBg: color.bg.page },
    Card: { paddingLG: layout.cardPadding, boxShadowTertiary: 'none' },
    Table: { headerBg: color.fill.subtle, rowHoverBg: color.fill.subtle, cellPaddingBlock: 13 },
    Modal: { borderRadiusLG: radius.lg },
    Drawer: { colorBgElevated: color.bg.elevated },
  },
};
