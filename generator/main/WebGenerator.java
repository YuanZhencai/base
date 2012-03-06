//import com.wcs.ncp.model.*;
import com.wcs.ncp.model.Role;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilationFailedException;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
public class WebGenerator {
 
 public static void main(String[] args) throws CompilationFailedException,
         IOException,InstantiationException, IllegalAccessException{

   ClassLoader parent = ClassLoader.getSystemClassLoader();
         
   GroovyClassLoader loader = new GroovyClassLoader(parent);

   Class groovyClass = loader.parseClass(new File
               ("generator\\tools\\PageGenerator.groovy"));
   GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
  
   Object obj = groovyObject.invokeMethod("generate",Code.class);
     obj = groovyObject.invokeMethod("generate",Resource.class);
    obj = groovyObject.invokeMethod("generate",Role.class);
   
   //System.out.println(obj);
 }

}