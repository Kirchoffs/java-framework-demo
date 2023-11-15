# Notes

## Surefire & Failsafe
The Failsafe Plugin is tailored for executing integration tests, while the Surefire Plugin is specifically designed for running unit tests. The term "failsafe" was selected as it not only resonates with "surefire" but also suggests that its failure mode is secure.

When the Surefire Plugin is employed for test execution, encountering a test failure will halt the build process during the integration-test phase, potentially leading to improper dismantling of the integration test environment.

In contrast, the Failsafe Plugin operates in the integration-test and verify stages of the build lifecycle, managing the application's integration tests. A key feature of the Failsafe Plugin is its ability to allow the build to progress past the integration-test phase even in the event of failures, ensuring that the subsequent post-integration-test phase can proceed.
