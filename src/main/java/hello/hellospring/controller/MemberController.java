package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createMember(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String createMemberForm(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);
        System.out.println(member.getName());

        return "redirect:/";
    }


    @GetMapping("/members")
    public String getMemberList(Model model){

        model.addAttribute("memberList", memberService.findUserAll());

        return "members/memberList";
    }
}
