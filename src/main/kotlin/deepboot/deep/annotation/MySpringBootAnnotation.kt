package deepboot.deep.annotation

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@ComponentScan
@Configuration
annotation class MySpringBootAnnotation()
