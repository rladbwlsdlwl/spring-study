package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        // Given
        Member m = new Member();
        m.setName("juyeon");

        // When

        memberService.join(m);

        // Then

        memberService.findUser(m.getId()).ifPresent((M) -> {
            assertThat(m.getName()).isEqualTo("juyeon");
        });
    }

    @Test
    void 회원가입_중복() {
        // Given
        Member m1 = new Member();
        m1.setName("juyeon");
        Member m2 = new Member();
        m2.setName("juyeon");


        // when

        memberService.join(m1);

        // 예외처리 방식 1
        try {
            memberService.join(m2);
            fail();
        } catch (IllegalStateException err) {
            assertThat(err.getMessage()).isEqualTo("이미 존재하는 유저");
        }


        // 예외처리 방식 2
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 유저");


        // Then
    }

    @Test
    void findUser() {
    }

    @Test
    void findUserAll() {
    }
}