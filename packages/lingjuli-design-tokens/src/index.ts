export const color = {
  brand: { primary: '#2563EB', hover: '#1D4ED8', active: '#1E40AF' },
  text: { primary: '#182230', secondary: '#475467', tertiary: '#667085', disabled: '#98A2B3', inverse: '#FFFFFF' },
  border: { default: '#D0D5DD', secondary: '#EAECF0', strong: '#98A2B3' },
  bg: { page: '#F7F8FA', container: '#FFFFFF', elevated: '#FFFFFF' },
  fill: { subtle: '#F2F4F7', secondary: '#EAECF0' },
  status: { success: '#12B76A', warning: '#F79009', error: '#F04438', processing: '#2563EB', disabled: '#98A2B3' },
  chart: ['#2563EB', '#12B76A', '#F79009', '#7F56D9', '#06AED4', '#F04438'],
  auth: { panel: '#EEF6FF', panelAccent: '#CFE4FF', glow: '#5B8DEF', title: '#0D1B3E', formTitle: '#0D1426' },
} as const;

export const spacing = { 1: 4, 2: 8, 3: 12, 4: 16, 5: 20, 6: 24, 8: 32, 10: 40, 12: 48 } as const;
export const radius = { sm: 4, md: 6, lg: 8, xl: 12 } as const;

export const typography = {
  pageTitle: { fontSize: 24, lineHeight: 1.33, fontWeight: 600 },
  sectionTitle: { fontSize: 18, lineHeight: 1.45, fontWeight: 600 },
  cardTitle: { fontSize: 16, lineHeight: 1.5, fontWeight: 600 },
  body: { fontSize: 14, lineHeight: 1.57, fontWeight: 400 },
  secondary: { fontSize: 14, lineHeight: 1.57, fontWeight: 400 },
  caption: { fontSize: 12, lineHeight: 1.5, fontWeight: 400 },
  label: { fontSize: 14, lineHeight: 1.43, fontWeight: 500 },
  number: { fontSize: 28, lineHeight: 1.28, fontWeight: 600 },
} as const;

export const size = {
  controlSm: 28,
  controlMd: 32,
  controlLg: 40,
  tableRow: 48,
  mobileTapTarget: 44,
} as const;

export const layout = {
  sidebarWidth: 224,
  sidebarCollapsedWidth: 64,
  headerHeight: 56,
  pagePadding: 24,
  sectionGap: 24,
  cardPadding: 20,
  formGap: 16,
  contentMaxWidth: 1600,
  authCardWidth: 440,
} as const;

export const motion = { fast: 120, normal: 180, slow: 240 } as const;
