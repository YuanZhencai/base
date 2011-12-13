package com.abien.testing.configuration.boundary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

/**
 *
 * @author Adam Bien, blog.adam-bien.com
 */
@RunWith(Arquillian.class)
public class MessageProviderIT {

    @Inject
    MessageProvider messageProvider;
    
    @Inject
    Configurable configurable;

    @Deployment
    public static JavaArchive createArchiveAndDeploy() {
        return ShrinkWrap.create(JavaArchive.class, "configuration.jar").
                addClasses(MessageProvider.class, Configurable.class).
                addAsManifestResource(
                new ByteArrayAsset("<beans/>".getBytes()),
                ArchivePaths.create("beans.xml"));
    }

    //@Test
    public void injectionWithExistingConfiguration() {
        String expected = MessageProvider.JAVA_IS_DEAD_MESSAGE;
        String actual = configurable.getJavaIsDeadError();
        assertNotNull(actual);
        assertThat(actual,is(expected));
    }
    
    //@Test
    public void injectionWithMissingConfiguration(){
        String shouldNotExist = configurable.getShouldNotExist();
        assertNull(shouldNotExist);
    }
}
