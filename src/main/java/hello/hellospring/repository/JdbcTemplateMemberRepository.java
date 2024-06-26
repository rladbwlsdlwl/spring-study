package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;
    // autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) { // injection
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> param = new HashMap<>();
        param.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(param));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> member = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return member.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> member = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return member.stream().findAny();
    }

    @Override
    public ArrayList<Member> findAll() {
        return (ArrayList<Member>) jdbcTemplate.query("select * from member", memberRowMapper());
    }


    private RowMapper<Member> memberRowMapper(){
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//
//                return member;
//            }
//        };

        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }
}
