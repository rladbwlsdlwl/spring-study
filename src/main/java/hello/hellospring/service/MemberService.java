package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class MemberService {
    private final MemberRepository memory;

    @Autowired
    public MemberService(MemberRepository m){
        this.memory = m;
    }

    //    SIGN UP
    public Long join(Member member) {
        validateDuplicateUser(member);
        memory.save(member);

        return member.getId();
    }

    // SEARCH USER
    public Optional<Member> findUser(Long id){
        return memory.findById(id);
    }

    // SEARCH USER ALL
    public ArrayList<Member> findUserAll(){
        return memory.findAll();
    }

    private void validateDuplicateUser(Member member) {
        memory.findByName(member.getName()).ifPresent(e -> {
            throw new IllegalStateException("이미 존재하는 유저");
        });
    }


}
