package deepboot.deep

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val helloService: HelloService,
) {
    @GetMapping("/hello")
    fun hello(name: String?): String {
        if (name.isNullOrEmpty()) {
            throw IllegalArgumentException()
        }
        return helloService.sayHello(name)
    }

    @GetMapping("/count")
    fun count(name: String?): String {
        if (name.isNullOrEmpty()) {
            throw IllegalArgumentException()
        }
        return "name : ${helloService.countOf(name)}"
    }
}