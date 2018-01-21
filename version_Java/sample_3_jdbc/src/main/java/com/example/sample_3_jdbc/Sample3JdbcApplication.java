package com.example.sample_3_jdbc;

import com.example.sample_3_jdbc.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sun.misc.Version.println;

/*
 * NamedParameterJdbcTemplate
 * 　SQL文中にパラメータを埋め込むため、「:パラメータ名」形式のプレースホルダを利用します。
 * 　通常の「JDBCAPI」は、「?」をプレースホルダに使うので不便です。
 * 　扱いやすいプレースホルダを使うことで、「SQLインジェクション」の対策漏れを防ぎやすくなります。
 */
@SpringBootApplication
public class Sample3JdbcApplication implements CommandLineRunner {

	/*
     * 「DI:コンテナ」に登録された「NamedParameterJdbcTemplate」オブジェクトを取得。
     * 「SpringBoot」では「autoconfigure」という仕組みで下記が自動で生成され、「DI:コンテナ」に登録される。
     * 「DataSource」
     * 「JdbcTemplate」
     * 「「NamedParameterJdbcTemplate」
     *
     * 　したがって、「SpringBoot」では、「依存ライブラリ」に「spring‐boot‐jdbc」と「JDBCドライバ」を追加するだけで特に設定することなく、「JdbcTemplate」が使用できる。
     * 　今回は「pom.xml」に「JDBCドライバ」として「H2データベース」への「依存関係」を定義したので、「H2データベース」用の「DataSource」が生成される。
     * 　
     * 「H2データベース」を使って、データベースのURLなどの指定がない場合は、デフォルトで「インメモリの組み込みデータベース」が作られる。
     */
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * 自動設定（デフォルト設定の例）
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("パラメータを指定しない例---------------------------------");
		String sql = "SELECT 1";
		SqlParameterSource param = new MapSqlParameterSource();

		/* queryForObject(SQL, パラメータ, 返り値となるオブジェクトクラス」
		 * ＊＊クエリの返り値が一件でない場合は「IncorrectResultSizeDataAccessException」がスローされる
		 */
		Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);

		System.out.println("【動作確認】result = " + result);

		System.out.println("パラメータを指定した例---------------------------------");

		String sql2 = "SELECT :a + :b";
		SqlParameterSource param2 = new MapSqlParameterSource().addValue("a", 100)
															   .addValue("b", 200);
		Integer result2 = jdbcTemplate.queryForObject(sql2, param2, Integer.class);

		System.out.println("【動作確認】result = " + result2);

		/*
		 * data.sqlとschema.sqlを作成する（resourcesフォルダの中に）
		 *
		 */
		System.out.println("Customerをオブジェクトマッピングした例---------------------------------");

		// 主キーの指定
		String sql3 = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		SqlParameterSource param3 = new MapSqlParameterSource().addValue("id", 1);

		/*
		 * queryForObject(sql, パラメータ, RowMapper<?>の実装)
		 * 　RowMapperのCustomer mapRow(ResultSet rs, int rowNum)を利用して、Customerクラスを生成する
		 */
		Customer result3 = jdbcTemplate.queryForObject(sql3
														, param3
														, (rs, rowNum) -> new Customer(rs.getInt("id")
																					   , rs.getString("first_name")
																					   , rs.getString("last_name")));

		System.out.println("【動作確認】result = " + result3);
	}

	public static void main(String[] args) {
		SpringApplication.run(Sample3JdbcApplication.class, args);
	}
}
