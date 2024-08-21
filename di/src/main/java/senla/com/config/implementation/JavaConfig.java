package senla.com.config.implementation;

import senla.com.config.Config;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {
    @Getter
    private Reflections scanner;
    private Map<Class, Class> ImplClassMap;

    public JavaConfig(String packageToScan, Map<Class, Class> ImplClassMap) {
        this.ImplClassMap = ImplClassMap;
        this.scanner = new Reflections(packageToScan);
    }

    public JavaConfig(String packageToScan) {
        this(packageToScan, new HashMap<>());
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class ifc) {
        if (ifc.isInterface()) {
            return ImplClassMap.computeIfAbsent(ifc, aClass -> {
                Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
                if (classes.size() != 1) {
                    throw new RuntimeException(ifc + " has 0 or more than one impl please check your config");
                }
                return classes.iterator().next();
            });
        } else {
            return ifc;
        }
    }
}