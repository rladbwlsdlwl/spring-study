package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    static HashMap<Long, Member> user = new HashMap<>();
    static Long sequence = 0L; // hash key

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        user.put(sequence, member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(user.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return user.values().stream()
                .filter(u -> u.getName().equals(name))
                .findAny();
    }

    @Override
    public ArrayList<Member> findAll() {
        return new ArrayList<>(user.values());
    }

    public void clearUser(){
        user.clear();
    }
}
