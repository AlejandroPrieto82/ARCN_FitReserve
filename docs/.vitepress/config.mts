import { defineConfig } from 'vitepress'
import { withMermaid } from 'vitepress-plugin-mermaid'

export default withMermaid(defineConfig({
  title: 'FitReserve Backend',
  description: 'Documentación técnica del backend de reservas FitReserve',
  cleanUrls: true,
  themeConfig: {
    nav: [
      { text: 'Inicio', link: '/' },
      { text: 'Arquitectura', link: '/architecture' },
      { text: 'API', link: '/api' },
      { text: 'Despliegue', link: '/deployment' }
    ],
    sidebar: [
      {
        text: 'Visión General',
        items: [
          { text: 'Introducción', link: '/' },
          { text: 'Arquitectura', link: '/architecture' },
          { text: 'Modelo de Dominio', link: '/domain-model' },
          { text: 'Casos de Uso', link: '/use-cases' }
        ]
      },
      {
        text: 'Ingeniería',
        items: [
          { text: 'API', link: '/api' },
          { text: 'Estrategia de Testing', link: '/testing' },
          { text: 'Uso de IA', link: '/use-of-ai' },
          { text: 'Despliegue', link: '/deployment' },
          { text: 'Decisiones de Diseño', link: '/design-decisions' }
        ]
      }
    ],
    socialLinks: [
      { icon: 'github', link: 'https://github.com/AlejandroPrieto82/ARCN_FitReserve' }
    ],
    search: {
      provider: 'local'
    }
  }
}))
