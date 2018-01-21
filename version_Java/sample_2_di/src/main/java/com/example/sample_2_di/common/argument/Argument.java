package com.example.sample_2_di.common.argument;

import lombok.Data;
import lombok.RequiredArgsConstructor;


/*
 * @Dataアノテーション
 * コンパイル時 (classファイル生成時) に、各フィールドの「seter/geter」「toStringメソッド」「equalsメソッド」「hashCodeメソッド」が生成される。
 *
 * そのため、ソースコードから冗長な部分なくなり、スッキリする。
 *　final修飾子がついてる場合は、setterは生成されない。
 */
@Data
@RequiredArgsConstructor
public class Argument {
    private final int a;
    private final int b;
}
