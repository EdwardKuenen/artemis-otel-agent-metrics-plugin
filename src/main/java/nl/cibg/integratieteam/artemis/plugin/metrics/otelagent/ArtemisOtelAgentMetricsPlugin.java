package nl.cibg.integratieteam.artemis.plugin.metrics.otelagent;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.apache.activemq.artemis.core.server.metrics.ActiveMQMetricsPlugin;

import java.util.Map;
import java.util.Optional;

public class ArtemisOtelAgentMetricsPlugin implements ActiveMQMetricsPlugin {

    private transient MeterRegistry meterRegistry;

    @Override
    public ActiveMQMetricsPlugin init(final Map<String, String> options) {

        final Optional<MeterRegistry> openTelemetryMeterRegistry = Metrics.globalRegistry.getRegistries().stream()
                .filter(registry -> registry.getClass().getName().contains("OpenTelemetryMeterRegistry"))
                .findAny();

        openTelemetryMeterRegistry.ifPresent(registry -> meterRegistry = registry);
        return this;
    }

    @Override
    public MeterRegistry getRegistry() {
        return meterRegistry;
    }
}
