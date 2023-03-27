package deepboot.deep

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.IntStream

@MyTransactionTest
class HelloServiceCountTest {

    @Autowired
    lateinit var helloService: HelloService

    @Autowired
    lateinit var helloRepository: HelloRepository

    @Test
    fun sayHelloTest() {
        IntStream.rangeClosed(1, 10).forEach { count ->
            helloService.sayHello("Toby")
            Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(count)
        }
    }
}