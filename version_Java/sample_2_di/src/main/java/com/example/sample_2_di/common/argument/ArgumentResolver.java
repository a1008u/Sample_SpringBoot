package com.example.sample_2_di.common.argument;

import java.io.InputStream;

public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}