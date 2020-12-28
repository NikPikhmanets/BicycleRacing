package com.bicycle.racing.utils;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("rawtypes")
public class ResourceUtils {

    private ResourceUtils() {
    }

    public static String resourceAsString(Class clazz, String resource) {
        return withResource(clazz, resource, res -> IOUtils.toString(res, StandardCharsets.UTF_8));
    }

    private static <T> T withResource(Class clazz, String resource, WithResourceCallback<T> callback) {
        try (InputStream stream = clazz.getResourceAsStream(resource)) {
            return callback.apply(stream);
        } catch (Exception e) {
            throw new RuntimeException("Exception when processing " + resource, e);
        }
    }

    public interface WithResourceCallback<T> {
        T apply(InputStream stream) throws Exception;
    }
}