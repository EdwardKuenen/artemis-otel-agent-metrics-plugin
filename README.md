## Building
Simply run `mvn package`. This command will build all modules and the output
will be in their respective `target` directories.

## Releases
You can als grab a pre-built jar from the releases page:
https://github.com/EdwardKuenen/artemis-otel-agent-metrics-plugin/releases

## Using the plugin
1. Copy `artemis-otel-agent-metrics-plugin-<VERSION>.jar` to `<ARTEMIS_INSTANCE>/lib`.
2. Add this to your `<ARTEMIS_INSTANCE>/etc/broker.xml`:

```xml
<metrics>
    <jvm-memory>true</jvm-memory>
    <jvm-gc>true</jvm-gc>
    <jvm-threads>true</jvm-threads>
    <netty-pool>true</netty-pool>
    <plugin class-name="nl.cibg.integratieteam.artemis.plugin.metrics.otelagent.ArtemisOtelAgentMetricsPlugin"/>
</metrics>
```

## Reference
https://artemis.apache.org/components/artemis/documentation/latest/metrics.html
