import {defineConfig} from "cypress";

export default defineConfig({
    e2e: {
        setupNodeEvents(on, config) {
            // implement node event listeners here
        },
        retries: {
            runMode: 4,
            openMode: 0
        }
    },
});
