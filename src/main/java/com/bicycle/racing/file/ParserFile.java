package com.bicycle.racing.file;

import java.io.InputStream;

public interface ParserFile<T> {
    T parser(InputStream in) throws Exception;
}
