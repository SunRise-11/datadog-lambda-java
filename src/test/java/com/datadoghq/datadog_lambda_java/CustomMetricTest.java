/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.datadoghq.datadog_lambda_java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CustomMetricTest {
    @Test public void testCustomMetrics() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        Date customTime = new Date();
        try {
            customTime = sdf.parse("2019-05-29T18:00:00Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CustomMetric ddm = new CustomMetric("foo", 24.3, null, customTime);
        assertEquals("{\"m\":\"foo\",\"v\":24.3,\"t\":[],\"e\":1559152800}", ddm.toJson());
    }


    @Test public void testCustomMetricWrite(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        Date customTime = new Date();
        try {
            customTime = sdf.parse("2019-05-29T18:00:00Z");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CustomMetric ddm = new CustomMetric("foo", 24.3, null, customTime);

        StringMetricWriter smw = new StringMetricWriter();
        MetricWriter.setMetricWriter(smw);
        ddm.write();

        assertNotNull(smw.getMetricsWritten());
        assertEquals("{\"m\":\"foo\",\"v\":24.3,\"t\":[],\"e\":1559152800}",smw.getMetricsWritten());
    }

    /**
     * For tests!
     */
    class StringMetricWriter extends MetricWriter{
        private String metricsWritten;

        public String getMetricsWritten(){
            return metricsWritten;
        }

        @Override
        public void write(CustomMetric cm) {
            if (metricsWritten == null) metricsWritten = "";
            metricsWritten = metricsWritten + cm.toJson();
        }

        @Override
        public void flush() {
        }
    }

}
