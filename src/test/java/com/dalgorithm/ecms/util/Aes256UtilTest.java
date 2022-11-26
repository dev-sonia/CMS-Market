package com.dalgorithm.ecms.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("Hello");
        assertEquals(Aes256Util.decrypt(encrypt), "Hello");
    }
}