package senla.com.config;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClass(Class type);

    Reflections getScanner();
}
