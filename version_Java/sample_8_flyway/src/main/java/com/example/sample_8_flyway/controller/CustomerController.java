package com.example.sample_8_flyway.controller;


import com.example.sample_8_flyway.form.CustomerForm;
import com.example.sample_8_flyway.domain.Customer;
import com.example.sample_8_flyway.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 画面遷移用のコントローラ：@Controller
 * @RequestMapping("customers")で「http://localhost:8080/customers」のURLとなる
 */
@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * Formの初期化を行う
     * @ModelAttribute
     * 　@RequestMappingでマッピングされたメソッドの前に実行され、「返り値」は自動的に「Model」に追加される。
     * 　listメソッドやcreateメソッドの前に、model.addAttribute("customerForm",new CustomerForm());が実行される
     * 　＊＊属性名はデフォルトで、クラス名の先頭を小文字にした文字列となる
     *
     * @return
     */
    @ModelAttribute
    CustomerForm setUpForm() {
        return new CustomerForm();
    }

    /**
     * URL http://localhost:8080/customers
     * @param model
     * @return [classpath:templates/customers/list.html]となる
     * Spring Bootではデフォルトで、「classpath:templates/+"ビュー名"+.html」が画面のパスとなる
     *
     */
    @GetMapping
    String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    /**
     *
     * @Validated 送信されたフォームの情報の入力チェックを行う。その結果をBindingResult型のオブジェクト格納する
     *
     * @param form
     * @param result
     * @param model
     * @return
     */
    @PostMapping(path = "create")
    String create(@Validated CustomerForm form, BindingResult result, Model model) {

        // Bean Validation
        if (result.hasErrors()) return list(model);

        // 「CustomerForm → Customer」にコピー（BeanUtilsはフィールドの名前と型が同じ場合にしか使えない）
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customerService.create(customer);

        // redirect:遷移先のパス（URL）
        return "redirect:/customers";
    }

    /**
     *
     * @RequestParam
     *  特定のリクエストパラメータをマッピングできる。（リクエストパラメータのidをIntegerの引数にマッピングする）
     *  ＊＊パラメータ名と引数名を一致させる
     *
     * @param id
     * @param form
     * @return
     */
    @GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, CustomerForm form) {
        Customer customer = customerService.findOne(id);
        BeanUtils.copyProperties(customer, form);
        return "customers/edit";
    }

    /**
     *
     * @param id
     * @param form
     * @param result
     * @return
     */
    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result) {

        // Bean Validation
        if (result.hasErrors()) return editForm(id, form);

        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setId(id);
        customerService.update(customer);

        return "redirect:/customers";
    }

    /**
     * 顧客編集フォームから一覧表示画面へ
     *
     * @return
     */
    @GetMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/customers";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}