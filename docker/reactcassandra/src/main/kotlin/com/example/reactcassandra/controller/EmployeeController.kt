package com.example.reactcassandra.controller


import com.example.reactcassandra.model.EmployeeDto
import com.example.reactcassandra.model.EmployeeUpdateReq
import com.example.reactcassandra.service.DepartmentService
import com.example.reactcassandra.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping

/**
 * FluxとMonoの違い
 *
 * FluxはReactive StreamsのPublisherを実装したN要素のストリームを表現するReactorのクラスです。
 * 以下の形式でレスポンスを返すことができる。
 *  - text/plain(デフォルト)
 *  - Server-Sent Event(Server-Sent Eventで返す場合はAcceptヘッダにtext/event-stream)
 *  - JSON Stream(JSON Streamで返すときはAcceptヘッダにapplication/stream+jsonを指定)
 *
 *  Monoは1または0要素のPublisher
 *  Spring WebFluxではリクエストボディ(ここでは文字列)をMonoにラップして受け取ることで、非同期で処理をchain / composeすることができます。
 *
 */

@RestController
class EmployeeController(private val employeeService: EmployeeService
                         , private val departmentService: DepartmentService){

    // 参照系 -----------------------------------

    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable("id") id: String) = employeeService.getEmployee(id).map { EmployeeDto.toDto(it) }

    @GetMapping("/employee")
    fun getEmployees(@RequestParam("minAge", required = false) minAge: Int?,
                     @RequestParam("minSalary", required = false) minSalary: Double?)
            = employeeService.getAllEmployees(minAge, minSalary)

    @GetMapping("/employeestreamjson", produces = ["application/stream+json"])
    fun getEmployeesStreamJson(@RequestParam("minAge", required = false) minAge: Int?,
                               @RequestParam("minSalary", required = false) minSalary: Double?)
            = employeeService.getAllEmployees(minAge, minSalary)

    // 明示的にStreamをFluxに変換する
    @GetMapping("/employeestream", produces = ["application/stream+json"])
    fun getEmployeesStream(@RequestParam("minAge", required = false) minAge: Int?,
                           @RequestParam("minSalary", required = false) minSalary: Double?)
            = Flux.fromStream(employeeService.getAllEmployeesStram(minAge, minSalary))

    @GetMapping("/departments")
    fun getAllDepartments() = departmentService.getAllDepartments()

    // 更新系 -----------------------------------
    @PostMapping("/employee")
    fun createEmployee(@Valid @RequestBody employee: EmployeeDto)
            = employeeService.createEmployee(employee).map { newEmployee -> ResponseEntity.status(HttpStatus.CREATED).body(newEmployee)}

    @PutMapping("/employee/{id}")
    fun updateEmployee(@PathVariable id: String,
                       @RequestBody updateEmployee: EmployeeUpdateReq)
            = employeeService.updateEmployee(id, updateEmployee).map { _ -> ResponseEntity.ok().build<String>()}

    // 返信系 -----------------------------------
    // postでMono利用
    @PostMapping("/echo")
    fun echo(@RequestBody body: Mono<String>): Mono<String> {
        return body.map(String::toUpperCase)
    }

    // 削除系 -----------------------------------

    @DeleteMapping("/employee/{id}")
    fun deleteEmployee(@PathVariable id: String): ResponseEntity<String> {
        val delete = employeeService.deleteEmployee(id).block()
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build<String >()
    }

}