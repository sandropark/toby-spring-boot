package com.sandro.helloboot;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HelloControllerTest {

    HelloController helloController = new HelloController(name -> name);

    @Test
    void success() throws Exception {
        String name = "Test";
        assertThat(helloController.hello(name)).isEqualTo(name);
    }

    @Test
    void fail() throws Exception {
        isIllegalArgumentException((assertThatThrownBy(() -> helloController.hello(null))));
        isIllegalArgumentException((assertThatThrownBy(() -> helloController.hello(""))));
        isIllegalArgumentException((assertThatThrownBy(() -> helloController.hello("   "))));
    }

    private void isIllegalArgumentException(AbstractThrowableAssert<?, ? extends Throwable> throwableAssert) {
        throwableAssert.isInstanceOf(IllegalArgumentException.class);
    }

}