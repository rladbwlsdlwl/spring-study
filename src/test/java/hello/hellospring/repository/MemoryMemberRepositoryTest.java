package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memory = new MemoryMemberRepository();

    @AfterEach
    void afterEach(){
        memory.clearUser();
    }

    @Test
    void save(){
        Member m = new Member();
        m.setName("juyeon");

        Member n = memory.save(m);

        Assertions.assertSame(m, memory.findById(n.getId()).get());
    }

    @Test
    void findById(){
        Member m = new Member();
        m.setName("juyeon");

        Member m1 = new Member();
        m1.setName("juyeon1");

        Member a = memory.save(m), b = memory.save(m1);

        Assertions.assertSame(memory.findById(a.getId()).get(), memory.findById(m.getId()).get());

    }

    @Test
    void findAll(){
        Member m = new Member();
        m.setName("juyeon");

        Member m1 = new Member();
        m1.setName("juyeon1");

        Member a = memory.save(m), b = memory.save(m1);

        Assertions.assertEquals(2, memory.findAll().size());
    }
}
