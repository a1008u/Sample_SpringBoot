package com.example.sample_6_restapi_docker.repository;


import com.example.sample_6_restapi_docker.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Dataはデータストアを操作する汎用的な機能を提供しています。
 * Spring Data JPAを利用して、RDBMSを操作する。
 *
 * CRUD操作の基本メソッドは実行時に、自動的に生成される。
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * JPQLを利用して、Queryを作成する
     * 全レコードを取得する場合は「*」を利用することはできません。
     * その代わり、エンティティー名のエイリアス名を利用することで、取得できる
     * @return
     */
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    List<Customer> findAllOrderByName();

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    Page<Customer> findAllOrderByName(Pageable pageable);

}
