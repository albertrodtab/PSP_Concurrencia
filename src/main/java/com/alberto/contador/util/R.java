package com.alberto.contador.util;

import java.io.File;
import java.net.URL;

public class R {

    public static URL getUi(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }
}
