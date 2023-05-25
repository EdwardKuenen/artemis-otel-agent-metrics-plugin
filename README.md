
## Building

Simply run `mvn install`. This command will build all modules and the output
will be in their respective `target` directories.

## Installing in ActiveMQ Artemis

After building the artifacts follow these steps:

1. Copy `/target/artemis-otel-agent-metrics-plugin-<VERSION>.jar`
   to `<ARTEMIS_INSTANCE>/lib`.

1. Add this to your `<ARTEMIS_INSTANCE>/etc/broker.xml`:

```xml
<metrics>
    <jvm-memory>true</jvm-memory>
    <jvm-gc>true</jvm-gc>
    <jvm-threads>true</jvm-threads>
    <netty-pool>true</netty-pool>
    <plugin class-name="nl.cibg.integratieteam.artemis.plugin.metrics.otelagent.ArtemisOtelAgentMetricsPlugin"/>
</metrics>
```

See for more information https://activemq.apache.org/components/artemis/documentation/latest/metrics.html