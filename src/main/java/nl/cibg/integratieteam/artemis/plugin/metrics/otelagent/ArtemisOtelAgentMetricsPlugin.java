package nl.cibg.integratieteam.artemis.plugin.metrics.otelagent;

import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import io.opentelemetry.instrumentation.micrometer.v1_5.OpenTelemetryMeterRegistry;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.apache.activemq.artemis.core.server.metrics.ActiveMQMetricsPlugin;

import java.util.Map;

public class ArtemisOtelAgentMetricsPlugin implements ActiveMQMetricsPlugin {

    private transient MeterRegistry meterRegistry;

    @Override
    public ActiveMQMetricsPlugin init(final Map<String, String> options) {

        final Resource resource = Resource.getDefault()
                .merge(Resource.create(Attributes.of(
                        ResourceAttributes.SERVICE_NAME, options.getOrDefault("service.name", "artemis-broker"))
                ));

        final SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
                .setResource(resource)
                .registerMetricReader(
                        PeriodicMetricReader.builder(
                                OtlpGrpcMetricExporter.builder().build())
                        .build())
                .build();

        final OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
                .setMeterProvider(sdkMeterProvider)
                .build();

        meterRegistry = OpenTelemetryMeterRegistry.create(openTelemetry);
        return this;
    }

    @Override
    public MeterRegistry getRegistry() {
        return meterRegistry;
    }
}
