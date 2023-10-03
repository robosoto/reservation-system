module.exports = (config: { set: (arg0: { basePath: string; frameworks: string[]; coverageReporter: { dir: any; subdir: string; reporters: { type: string }[]; check: { global: { statements: number; branches: number; functions: number; lines: number } } } }) => void }) => {
    config.set({
        basePath: '../..',
        frameworks: ['jasmine'],
        coverageReporter: {
            dir: require('path').join(__dirname, './coverage/<project-name>'),
            subdir: '.',
            reporters: [
              { type: 'html' },
              { type: 'text-summary' }
            ],
            check: {
              global: {
                statements: 80,
                branches: 80,
                functions: 80,
                lines: 80
              }
            }
          }
    })
}