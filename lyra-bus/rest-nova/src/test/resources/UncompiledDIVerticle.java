import org.vertx.java.platform.Verticle;
import test.com.lyra.vertx.inject.guice.MyDependency;

import javax.inject.Inject;

import static org.vertx.testtools.VertxAssert.assertNotNull;

public class UncompiledDIVerticle extends Verticle {

    private final MyDependency myDependency;

    @Inject
    public UncompiledDIVerticle(MyDependency myDependency) {
        this.myDependency = myDependency;
        assertNotNull(myDependency);
    }
}